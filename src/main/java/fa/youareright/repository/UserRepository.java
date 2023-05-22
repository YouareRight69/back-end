package fa.youareright.repository;

import fa.youareright.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,String> {

    @Query(value = "select * " +
            "from user " +
            "where full_name like %:keyword% and " +
            "address like %:keyword% " ,
            nativeQuery = true)
    Page<User> findAll(Pageable pageable, @Param("keyword") String keyword);
}
