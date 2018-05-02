package wz.wzksb.cert.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import wz.wzksb.cert.service.CertService;
import wz.wzksb.cert.utils.ResultVOUtil;
import wz.wzksb.cert.vo.ResultVO;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by wu on 18-1-30.
 */
@RestController
@RequestMapping("/cert")
@CrossOrigin(origins = {},methods = {RequestMethod.GET,RequestMethod.OPTIONS,RequestMethod.POST})
public class CertController {

    @Autowired
    private CertService certService;

    @GetMapping("/findlist")
    public ResultVO findlist(@RequestParam("name") String name,
                             @RequestParam("idcard") String idCard){
        return this.certService.findList(name,idCard);
    }

    @PostMapping("/findbymailstatus")
    public ResultVO findbymailstatus(@RequestParam(value = "page",defaultValue = "1") Integer page,@RequestParam(value = "size",defaultValue = "10") Integer size,
                                     @RequestParam("mailstatus") String mailstatus){
        return this.certService.findByMailStatus(page,size,mailstatus);
    }

    @GetMapping("/findone")
    public ResultVO findone(@RequestParam("id") String id){
        return this.certService.findone(id);
    }

    @PostMapping("/save")
    public ResultVO save (@RequestParam("id") String id,
                         @RequestParam("mailmobile") String mailMobile,
                         @RequestParam("mailaddr") String mailAddr,
                         @RequestParam("code") String code){
        return this.certService.save(id,mailMobile,mailAddr,code);
    }

    @PostMapping("/uploadexl")
    public ResultVO uploadexl(@RequestParam("exl") MultipartFile exl){
        this.certService.uploadexl(exl);
        return ResultVOUtil.success();
    }

    @GetMapping("/downloadexl")
    public void downloadexl(HttpServletResponse response,@RequestParam("mailstatus") String mailStatus){
        this.certService.downloadexl(response,mailStatus);
    }

    @GetMapping("/sendsms")
    public ResultVO sendsms(@RequestParam("phone") String phone){
        this.certService.sendsms(phone);
        return ResultVOUtil.success();
    }

    @PostMapping("/scenetocert")
    public ResultVO scenetocert(@RequestParam("id") String id,@RequestParam(value = "names",required = false) String names,@RequestParam(value = "idcards",required = false) String idCards){
        return this.certService.scenetocert(id,names,idCards);
    }

    @PostMapping("/findbyidcard")
    public ResultVO findbyidcard(@RequestParam("page") Integer page,@RequestParam("size") Integer size,@RequestParam("idcard") String idcard){
        return this.certService.findbyidcard(page,size,idcard);
    }

    @PostMapping("/delete")
    public ResultVO deleteall(@RequestParam("id") String id){
        this.certService.delete(id);
        return ResultVOUtil.success();
    }
}
