package wz.wzksb.cert.bos;

import java.util.Optional;
import java.util.Set;

/**
 * Created by liutim on 2018/1/1.
 */
public interface IBosSet<T extends IEntry> extends Set<T> {

    public boolean removeById(String entryid);

    public Optional<T> findById(String entryid);


}
