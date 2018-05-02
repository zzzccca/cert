package wz.wzksb.cert.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wz.wzksb.cert.domain.Admin;

/**
 * Created by wu on 18-4-11.
 */
public interface AdminRepository extends JpaRepository<Admin,String> {

    Admin findByAccountAndPassword(String account,String password);

    Admin findByAccount(String account);
}
