package wz.wzksb.cert.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import wz.wzksb.cert.domain.Cert;

import java.util.List;

/**
 * Created by wu on 18-1-30.
 */
public interface CertRepository extends JpaRepository<Cert,String>{

    List<Cert> findByNameAndIdCard(String name,String idCard);

    List<Cert> findByCertNumberIn(List<String> certifList);

    List<Cert> findByMailStatusAndMailReqDateNotNull(String mailStatus);

    Page<Cert> findByMailStatus(Pageable pageable,String mailstatus);

    Page<Cert> findByMailStatusOrMailStatusOrMailStatus(Pageable pageable,String a,String b,String c);

    Page<Cert> findByIdCard(Pageable pageable,String idcard);

    void deleteAll();
}
