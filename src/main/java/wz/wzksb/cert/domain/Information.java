package wz.wzksb.cert.domain;

import wz.wzksb.cert.bos.BaseEntity;
import wz.wzksb.cert.bos.Bostype;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by wu on 18-3-30.
 */
@Bostype("C01")
@Entity
@Table(name = "c_information")
public class Information extends BaseEntity {

    @Column(length = 10000)
    private String content;
    private String location;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
