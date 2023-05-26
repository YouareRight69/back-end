package fa.youareright.repository;

import fa.youareright.model.Branch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BranchRepository extends JpaRepository<Branch, String> {

    List<Branch> findByIsDelete(Integer isDelete);
}
