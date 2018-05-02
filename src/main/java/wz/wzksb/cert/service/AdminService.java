package wz.wzksb.cert.service;

import wz.wzksb.cert.domain.Admin;

/**
 * Created by wu on 18-4-11.
 */
public interface AdminService {

    Admin findadmin(String account,String password);

    void update(String password);
}
