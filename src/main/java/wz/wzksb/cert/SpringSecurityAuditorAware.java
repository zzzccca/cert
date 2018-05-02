package wz.wzksb.cert;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

/**
 * Created by wu on 18-4-11.
 */
@Component
public class SpringSecurityAuditorAware implements AuditorAware<String> {

    public String getCurrentAuditor(){
        return "admin";
    }
}
