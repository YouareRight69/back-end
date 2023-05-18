package fa.youareright.repository;

import fa.youareright.model.Media;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface MediaRepository  extends JpaRepository<Media, Integer> {
    List<Media> findByBranch_BranchId(String id);
}
