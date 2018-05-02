package wz.wzksb.cert.bos;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;

public class BosidGenerator implements IdentifierGenerator {

    @Override
    public Serializable generate(SessionImplementor session, Object object) throws HibernateException {

        if(object instanceof ICoreObject){
            Bostype bostype=object.getClass().getAnnotation(Bostype.class);
            String bt=bostype.value();
            IBostype ibt=new MyBostype(bt);
            return BostypeUtils.getBostypeid(ibt);
        }else{
            throw new RuntimeException("Can't generate id and persistent for those NOT ICoreObject!!!");
        }
    }

}
