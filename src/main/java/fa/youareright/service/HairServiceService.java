package fa.youareright.service;

import fa.youareright.model.HairService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

import java.util.List;

public interface HairServiceService {

    Page<HairService> listAll(String condition, Pageable pageable);

    void save(HairService hairService);

    Optional<HairService> findById(String serviceId);

    void delete(String serviceId);

    List<HairService> findList();

    HairService get(String serviceId);
}
