package wz.wzksb.cert.service;

import org.springframework.web.multipart.MultipartFile;
import wz.wzksb.cert.domain.Cert;
import wz.wzksb.cert.vo.ResultVO;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by wu on 18-1-30.
 */
public interface CertService {

    ResultVO findList(String name, String idCard);

    ResultVO findByMailStatus(Integer page,Integer size,String mailstatus);

    ResultVO findone(String id);

    ResultVO save(String id,String mailMobile,String mailAddr,String code);

    void save(List<Cert> certList,List<String> certidList);

    ResultVO uploadexl(MultipartFile exl);

    void downloadexl(HttpServletResponse res,String mailStatus);

    void sendsms(String phone);

    ResultVO scenetocert(String id,String names,String idCards);

    ResultVO findbyidcard(Integer page,Integer size,String idcard);

    void delete(String id);
}
