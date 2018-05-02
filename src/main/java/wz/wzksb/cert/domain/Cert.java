package wz.wzksb.cert.domain;

import wz.wzksb.cert.bos.BaseEntity;
import wz.wzksb.cert.bos.Bostype;
import wz.wzksb.cert.enums.MailStatusEnums;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by wu on 18-1-30.
 */
@Bostype("C01")
@Entity
@Table(name = "c_certificate")
public class Cert extends BaseEntity {

    private String name;//考生姓名

    private String idCard;//证件号码

    private String qualification;//考试名称

    private String appropriatetime;//合格时间

    private String certNumber;//证书序列号

    private String mailAddr;//邮寄地址

    private String mailMobile;//邮寄手机号码

    private String mailReqDate;//申请邮寄日期

    private String mailStatus = MailStatusEnums.CAN_SENT.getMsg();//邮寄状态

    private String emsNumber;//快递单号

    private String emsDate;//寄送日期

    private String names;//代领人姓名

    private String idCards;//代领人身份证

    @Override
    public String toString() {
        return "Cert{" +
                "name='" + name + '\'' +
                ", idCard='" + idCard + '\'' +
                ", qualification='" + qualification + '\'' +
                ", appropriatetime='" + appropriatetime + '\'' +
                ", certNumber='" + certNumber + '\'' +
                ", mailAddr='" + mailAddr + '\'' +
                ", mailMobile='" + mailMobile + '\'' +
                ", mailReqDate='" + mailReqDate + '\'' +
                ", mailStatus='" + mailStatus + '\'' +
                ", emsNumber='" + emsNumber + '\'' +
                ", emsDate='" + emsDate + '\'' +
                ", names='" + names + '\'' +
                ", idCards='" + idCards + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getMailAddr() {
        return mailAddr;
    }

    public void setMailAddr(String mailAddr) {
        this.mailAddr = mailAddr;
    }

    public String getMailMobile() {
        return mailMobile;
    }

    public void setMailMobile(String mailMobile) {
        this.mailMobile = mailMobile;
    }

    public String getMailReqDate() {
        return mailReqDate;
    }

    public void setMailReqDate(String mailReqDate) {
        this.mailReqDate = mailReqDate;
    }

    public String getMailStatus() {
        return mailStatus;
    }

    public void setMailStatus(String mailStatus) {
        this.mailStatus = mailStatus;
    }

    public String getEmsNumber() {
        return emsNumber;
    }

    public void setEmsNumber(String emsNumber) {
        this.emsNumber = emsNumber;
    }

    public String getCertNumber() {
        return certNumber;
    }

    public void setCertNumber(String certNumber) {
        this.certNumber = certNumber;
    }

    public String getEmsDate() {
        return emsDate;
    }

    public void setEmsDate(String emsDate) {
        this.emsDate = emsDate;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getAppropriatetime() {
        return appropriatetime;
    }

    public void setAppropriatetime(String appropriatetime) {
        this.appropriatetime = appropriatetime;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getIdCards() {
        return idCards;
    }

    public void setIdCards(String idCards) {
        this.idCards = idCards;
    }
}
