package wz.wzksb.cert.domain;

import wz.wzksb.cert.bos.BaseEntity;
import wz.wzksb.cert.bos.Bostype;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by wu on 18-4-11.
 */
@Bostype("A01")
@Entity
@Table(name = "c_admin")
public class Admin extends BaseEntity {

    private String account;

    private String password;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
