package fa.youareright.service.impl;

import fa.youareright.model.HairService;
import fa.youareright.repository.HairServiceRepository;
import fa.youareright.service.HairServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HairServiceServiceImpl implements HairServiceService {
    @Autowired
    HairServiceRepository hairServiceRepository;

//    @Override
//    public Page<HairService> findAll(Pageable pageable, String keyword) {
//        return hairServiceRepository.findAll(pageable, keyword);
//    }

    public Page<HairService> listAll(String condition, Pageable pageable) {
        return hairServiceRepository.findAllByService(condition, condition, pageable);
    }

    @Override
    public void save(HairService hairService) {
        hairServiceRepository.save(hairService);
    }

    @Override
    public Optional<HairService> findById(String serviceId) {
        return hairServiceRepository.findById(serviceId);
    }

    @Override
    public void delete(String serviceId) {
        hairServiceRepository.deleteById(serviceId);
    }

}
