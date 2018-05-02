package wz.wzksb.cert.service.serviceImpl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import wz.wzksb.cert.domain.Cert;
import wz.wzksb.cert.enums.MailStatusEnums;
import wz.wzksb.cert.repository.CertRepository;
import wz.wzksb.cert.service.CertService;
import wz.wzksb.cert.utils.*;
import wz.wzksb.cert.vo.ResultVO;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wu on 18-1-30.
 */
@Service
public class CertServiceImpl implements CertService {

    @Autowired
    private CertRepository certRepository;

    @Autowired
    private UploadExlUtil uploadExlUtil;

    @Autowired
    private DownloadExlUtil downloadExlUtil;

    @Autowired
    private SmsCodeUtil smsCodeUtil;

    @Override
    public ResultVO findList(String name, String idCard) {
        List<Cert> certList = this.certRepository.findByNameAndIdCard(name, idCard);

        if (certList.size() > 0) {
            return ResultVOUtil.success(certList);
        } else
            return ResultVOUtil.error();
    }

    @Override
    public ResultVO findByMailStatus(Integer page, Integer size, String mailstatus) {
        Sort sort=new Sort(Sort.Direction.DESC,"createtime");
        Pageable pageable = new PageRequest(page - 1, size,sort);

        Page<Cert> certPage = this.certRepository.findByMailStatus(pageable, mailstatus);
        if (mailstatus.equals("已寄送")) {
            certPage = this.certRepository.findByMailStatusOrMailStatusOrMailStatus(pageable, "已寄送", "本人领证", "他人代领");
        }
        return ResultVOUtil.success(TimeConversionUtil.time(certPage));
    }

    @Override
    public ResultVO findone(String id) {
        Cert cert = this.certRepository.findOne(id);
        if (StringUtils.hasText(cert.getMailReqDate())) {
            cert.setMailReqDate(TimeConversionUtil.millis2longdate(cert.getMailReqDate()));
        }
        if (StringUtils.hasText(cert.getEmsDate())) {
            cert.setEmsDate(TimeConversionUtil.millis2date(cert.getEmsDate()));
        }
        return ResultVOUtil.success(cert);
    }

    @Override
    public ResultVO save(String id, String mailMobile, String mailAddr, String code) {
        Map map = new HashMap();
        try {
            map = this.smsCodeUtil.checkMsg(mailMobile, code);
        } catch (IOException e) {
        }
        if (map.get("errorcode").equals(200)) {
            Cert cert = this.certRepository.findOne(id);
            if (!StringUtils.isEmpty(cert.getMailAddr())) {
                cert.setMailMobile(mailMobile);
                cert.setMailAddr(mailAddr);
            } else {
                cert.setMailMobile(mailMobile);
                cert.setMailAddr(mailAddr);
                cert.setMailReqDate(String.valueOf(System.currentTimeMillis()));
                cert.setMailStatus(MailStatusEnums.APPLIED_SENT.getMsg());
            }
            this.certRepository.save(cert);
            return ResultVOUtil.success();
        } else
            return ResultVOUtil.error(map.get("errorinfo").toString());
    }

    @Override
    public void save(List<Cert> certList, List<String> certidList) {//todo BUG 若同时导入新的证书与单号 则for循环存在问题
        List<Cert> oddcertList = this.certRepository.findByCertNumberIn(certidList);
        for (int i = 0; i < certList.size(); i++) {
            for (int j = 0; j < oddcertList.size(); j++) {
                if (certList.get(i).getCertNumber().equals(oddcertList.get(j).getCertNumber()) && StringUtils.hasText(certList.get(i).getEmsNumber())) {
                    oddcertList.get(j).setEmsNumber(certList.get(i).getEmsNumber());
                    oddcertList.get(j).setEmsDate(certList.get(i).getEmsDate());
                    BeanUtils.copyProperties(oddcertList.get(j), certList.get(i));
                    certList.get(i).setMailStatus(MailStatusEnums.HAS_BEEN_SENT.getMsg());
                } else if (certList.get(i).getCertNumber().equals(oddcertList.get(j).getCertNumber()) && !StringUtils.hasText(certList.get(i).getEmsNumber())) {
                    BeanUtils.copyProperties(oddcertList.get(j), certList.get(i));

                }
            }
            this.certRepository.save(certList.get(i));
        }
//        if (oddcertList.size()<=0){
//            this.certRepository.save(certList);
//        }else{
//            for (Cert cert:oddcertList){
//                for (int i=0;i<certList.size();i++){
//                    if (cert.getCertifid().equals(certList.get(i).getCertifid())){
//                        cert.setEmsNumber(certList.get(i).getEmsNumber());
//                        cert.setEmsDate(certList.get(i).getEmsDate());
//                    }
//                }
//            }
//            this.certRepository.save(oddcertList);
//        }
    }

    @Override
    public ResultVO uploadexl(MultipartFile exl) {
        String name = exl.getOriginalFilename();
        boolean a = name.matches("^.+\\.(?i)(xls|xlsx)$");//正则匹配文件后缀
        if (a == false) {
            return ResultVOUtil.error();
        } else {
            try {
                uploadExlUtil.getexl(exl, name);
            } catch (Exception e) {
            }
        }
        return ResultVOUtil.success();
    }

    @Override
    public void downloadexl(HttpServletResponse res, String mailStatus) {
        List<Cert> certList = this.certRepository.findByMailStatusAndMailReqDateNotNull(mailStatus);
        downloadExlUtil.getExcel(res, certList);
    }

    @Override
    public void sendsms(String phone) {
        String code = String.valueOf((Math.random() * 9 + 1) * 100000);
        try {
            this.smsCodeUtil.Sms(code, phone);
        } catch (Exception e) {
        }
    }

    @Override
    public ResultVO scenetocert(String id, String names, String idCards) {
        Cert cert = this.certRepository.findOne(id);
        if (StringUtils.hasText(names)) {
            cert.setNames(names);
            cert.setIdCards(idCards);
            cert.setMailStatus(MailStatusEnums.OTHER_TO_CERT.getMsg());
        } else {
            cert.setMailStatus(MailStatusEnums.PERSONAL_TO_CERT.getMsg());
        }
        cert.setEmsDate(String.valueOf(System.currentTimeMillis()));
        this.certRepository.save(cert);
        return ResultVOUtil.success();
    }

    @Override
    public ResultVO findbyidcard(Integer page, Integer size, String idcard) {
        Pageable pageable = new PageRequest(page - 1, size);
        Page<Cert> certPage = this.certRepository.findByIdCard(pageable, idcard);
        return ResultVOUtil.success(TimeConversionUtil.time(certPage));
    }

    @Override
    public void delete(String id) {
        this.certRepository.delete(id);
    }
}
