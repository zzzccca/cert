package wz.wzksb.cert.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wz.wzksb.cert.domain.Admin;
import wz.wzksb.cert.repository.AdminRepository;
import wz.wzksb.cert.service.AdminService;

/**
 * Created by wu on 18-4-11.
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;


    @Override
    public Admin findadmin(String account, String password) {
        return this.adminRepository.findByAccountAndPassword(account,password);
    }

    @Override
    public void update(String password) {
        Admin admin=this.adminRepository.findByAccount("admin");
        admin.setPassword(password);
        this.adminRepository.save(admin);
    }
}
