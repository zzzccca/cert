package wz.wzksb.cert.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wz.wzksb.cert.service.AdminService;
import wz.wzksb.cert.utils.ResultVOUtil;
import wz.wzksb.cert.vo.ResultVO;

/**
 * Created by wu on 18-4-11.
 */
@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = {},methods = {RequestMethod.GET,RequestMethod.OPTIONS,RequestMethod.POST})
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/login")
    public ResultVO login(String account, String password){
        if (this.adminService.findadmin(account,password)!=null) {
            return ResultVOUtil.success();
        }else
            return ResultVOUtil.error();
    }

    @PostMapping("/logout")
    public ResultVO logout(){
        return ResultVOUtil.error();
    }


    @PostMapping("/update")
    public ResultVO update(String password){
        this.adminService.update(password);
        return ResultVOUtil.success();
    }
}
