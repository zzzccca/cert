package wz.wzksb.cert.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wz.wzksb.cert.domain.Information;
import wz.wzksb.cert.service.InformationService;
import wz.wzksb.cert.utils.ResultVOUtil;
import wz.wzksb.cert.vo.ResultVO;

/**
 * Created by wu on 18-3-30.
 */
@RestController
@RequestMapping("/info")
@CrossOrigin(origins = {},methods = {RequestMethod.GET,RequestMethod.OPTIONS,RequestMethod.POST})
public class InformationController {

    @Autowired
    private InformationService informationService;

    @PostMapping("/save")
    public ResultVO saveinfo(String content,String location){
        this.informationService.save(content,location);
        return ResultVOUtil.success();
    }

    @GetMapping("/findone")
    public Information findone(String location){
        return this.informationService.findone(location);
    }
}
