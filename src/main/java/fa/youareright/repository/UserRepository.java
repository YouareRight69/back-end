package fa.youareright.repository;

import fa.youareright.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, String> {

	@Query(value = "select user_id from user order by user_id desc limit 1;", nativeQuery = true)
	public String getLastUserId();
}
