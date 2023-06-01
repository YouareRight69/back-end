package fa.youareright.repository;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;




import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.transaction.annotation.Transactional;

import fa.youareright.model.User;

public interface UserRepository extends JpaRepository<User, String> {

    @Query(value = "select user_id from user order by user_id desc limit 1;", nativeQuery = true)
    public String getLastUserId();

    @Query(value = "select u from User u where u.account.accountId = :accountId")
    Optional<User> findByAccountId(@Param("accountId") Integer accountId);

    @Modifying
    @Transactional
    @Query(value = "update User u\r\n" + "set u.address = :address, u.avatar = :avatar, "
            + "u.dateOfBirth = :dateOfBirth, u.fullName = :fullName, " + "u.phoneNumber = :phoneNumber\r\n"
            + "where u.userId = :userId")
    void updateUser(@Param("address") String address, @Param("avatar") String avatar,
                    @Param("dateOfBirth") LocalDate dateOfBirth, @Param("fullName") String fullName,
                    @Param("phoneNumber") String phoneNumber, @Param("userId") String userId);

    @Query(value = "select u from User u where (u.fullName like concat('%',:fullName,'%') or u.address like concat('%',:address,'%')) and u.status = '1'")
    Page<User> findAllByUser(@Param("fullName") String fullName,
                             @Param("address") String address,
                             Pageable pageable);

<<<<<<< HEAD
    @Query(value = "select u from User u where u.employee.branch.branchId like :branch and u.employee.isDelete = 0  ")
    List<User> getListEmployee(@Param("branch") String branch);
=======
//	@Query(value = "select u from User u where u.account.roles[ro].roleId = 3")
//	List<User> findEmp();

	@Query(value = "select u from User u where u.employee.branch.branchId like :branch and u.employee.isDelete = 0  ")
	List<User> getListEmployee(@Param("branch") String branch);
>>>>>>> 0923bbd1c211ccd7822215e38ff992d192c48bd3

    @Query(value = "select * from user where user.user_id = :userId", nativeQuery = true)
    Optional<User> findByUserId(@Param("userId") String userId);

    @Query(value = "select user.user_id from employee " +
            "inner join user on user.user_id = employee.user_id where employee.emp_id = :employeeId	", nativeQuery = true)
    Optional<User> findByEmpId(@Param("employeeId") String employeeId);

<<<<<<< HEAD
    @Modifying
    @Transactional
    @Query(value = "update User u set u.status = '0' " +
            "where u.userId = :userId")
    void updateStatus(@Param("userId") String userId);


    
 
    @Query(value ="select count(full_name) as totalUser from user",nativeQuery = true)
    List<UserTotal> getCountUser();
    
    
=======
	@Query( value = "select u.*\n" +
			"from employee e\n" +
			"inner join user u on u.user_id = e.user_id\n" +
			"inner join account acc on acc.account_id = u.account_id\n" +
			"inner join account_role accr on acc.account_id = accr.account_id\n" +
			"where accr.role_id = 3;", nativeQuery = true)
	List<User> findAllEmp();
>>>>>>> 0923bbd1c211ccd7822215e38ff992d192c48bd3
}
	