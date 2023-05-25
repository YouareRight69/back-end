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

	@Query(value = "select * " + "from user " + "where full_name like %:keyword% and "
			+ "address like %:keyword% ", nativeQuery = true)
	Page<User> findAll(Pageable pageable, @Param("keyword") String keyword);

	@Query(value = "select u from User u where u.employee.branch.branchId like :branch and u.employee.isDelete = 0  ")
	List<User> getListEmployee(@Param("branch") String branch);

}
