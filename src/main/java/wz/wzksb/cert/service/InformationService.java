package wz.wzksb.cert.service;

import wz.wzksb.cert.domain.Information;

/**
 * Created by wu on 18-3-30.
 */
public interface InformationService {

    void save (String content,String location);

    Information findone (String location);
}
