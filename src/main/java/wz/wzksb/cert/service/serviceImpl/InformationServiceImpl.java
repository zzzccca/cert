package wz.wzksb.cert.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import wz.wzksb.cert.domain.Information;
import wz.wzksb.cert.repository.InformationRepository;
import wz.wzksb.cert.service.InformationService;

/**
 * Created by wu on 18-3-30.
 */
@Service
public class InformationServiceImpl implements InformationService{

    @Autowired
    private InformationRepository informationRepository;
    @Override
    public void save(String content, String location) {
        Information info=new Information();
        Information information=this.informationRepository.findByLocation(location);
        if (StringUtils.isEmpty(information)){
            info.setLocation(location);
            info.setContent(content);
            this.informationRepository.save(info);
        }else {
            information.setContent(content);
            this.informationRepository.save(information);
        }
    }

    @Override
    public Information findone(String location) {
        return this.informationRepository.findByLocation(location);
    }
}
