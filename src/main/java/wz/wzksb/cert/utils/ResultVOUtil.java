package wz.wzksb.cert.utils;

import wz.wzksb.cert.vo.ResultVO;

/**
 * Created by wu on 18-1-30.
 */
public class ResultVOUtil {

    public static ResultVO success(Object object){
        ResultVO resultVO=new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg("操作成功!");
        resultVO.setData(object);
        return resultVO;
    }
    public static ResultVO success(){
        return success(null);
    }

    public static ResultVO error(){
        ResultVO resultVO=new ResultVO();
        resultVO.setCode(1);
        resultVO.setMsg("操作失败！");
        return resultVO;
    }

    public static ResultVO error(String msg){
        ResultVO resultVO=new ResultVO();
        resultVO.setCode(1);
        resultVO.setMsg(msg);
        return resultVO;
    }
}
