package wz.wzksb.cert.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wz.wzksb.cert.domain.Information;

/**
 * Created by wu on 18-3-30.
 */
public interface InformationRepository extends JpaRepository<Information,String>{

    Information findByLocation(String location);
}
