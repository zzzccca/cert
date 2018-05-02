package wz.wzksb.cert.enums;

/**
 * Created by wu on 18-1-30.
 */
public enum MailStatusEnums {
    CAN_SENT("可寄送",0),
    APPLIED_SENT("已申请寄送",1),
    HAS_BEEN_SENT("已寄送",2),
    PERSONAL_TO_CERT("本人领证",3),
    OTHER_TO_CERT("他人代领",4)
    ;
    private String msg;
    private Integer code;

    MailStatusEnums (String msg,Integer code){
        this.msg=msg;
        this.code=code;
    }

    public String getMsg() {
        return msg;
    }

    public Integer getCode() {
        return code;
    }
}
