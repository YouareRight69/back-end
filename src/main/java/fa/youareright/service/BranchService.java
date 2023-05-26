package fa.youareright.service;

import fa.youareright.model.Branch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

import java.util.List;

public interface BranchService {

    Page<Branch> listAll(String condition, Pageable pageable);

    void save(Branch branch);

    Optional<Branch> findById(String branchId);

    void delete(String branchId);

    List<Branch> findList();

    Branch get(String branchId);

}
