package wz.wzksb.cert.bos;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created by liutim on 2017/11/25.
 */


@MappedSuperclass
@Access(AccessType.FIELD)
public class CoreObject implements ICoreObject,Serializable {


    private String id;

    @Id
    @GeneratedValue(generator="bosidgenerator")
    @GenericGenerator(name="bosidgenerator",strategy="wz.wzksb.cert.bos.BosidGenerator")
    @Column(name="id",nullable=false,length=25)
    @Access(AccessType.PROPERTY)
    @Override
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id=id;
    }

    @Override
    final public boolean equals(Object obj) {
        if(this.id==null || obj==null || !(obj instanceof ICoreObject)){
            return false;
        }else{
            return Objects.equals(this.id,((ICoreObject)obj).getId());
        }
    }

    @Override
    final public int hashCode() {
        return (this.id==null)?13: Objects.hash(this.id);
    }
}
