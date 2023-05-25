package fa.youareright.repository;

import fa.youareright.model.HairService;
import fa.youareright.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface HairServiceRepository extends JpaRepository<HairService, String> {

//    @Query(value = "select * " +
//            "from hair_service " +
//            "where name like %:keyword% and " +
//            "description like %:keyword% " ,
//            nativeQuery = true)
//    Page<HairService> findAll(Pageable pageable, @Param("keyword") String keyword);

    @Query(value = "select * from hair_service where (name like concat('%',:name,'%') or description like concat('%',:description,'%'))",
            nativeQuery = true)
    Page<HairService> findAllByService(@Param("name") String name,
                                       @Param("description") String description,
                                       Pageable pageable);
}
