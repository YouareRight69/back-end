package fa.youareright.service;

import fa.youareright.model.HairService;
import fa.youareright.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

public interface HairServiceService {

    Page<HairService> findAll(Pageable pageable, @Param("keyword") String keyword);

    void save(HairService hairService);
}
