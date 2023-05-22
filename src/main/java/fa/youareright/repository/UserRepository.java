package fa.youareright.repository;

import fa.youareright.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User,String> {
    @Query(value ="select u from User u where u.employee.branch.branchId like :branch and u.employee.isDelete = 0  ")
    List<User> getListEmployee (@Param("branch") String branch );
}
