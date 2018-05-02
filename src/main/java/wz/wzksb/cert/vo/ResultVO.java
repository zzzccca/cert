package wz.wzksb.cert.vo;

/**
 * Created by wu on 18-1-30.
 */
public class ResultVO<T> {

    private Integer code;

    private String msg;

    private T data;

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }
}
