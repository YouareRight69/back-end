package fa.youareright.controller.branch;
import fa.youareright.dto.BookingDTO;
import fa.youareright.model.Branch;
import fa.youareright.model.Employee;
import fa.youareright.model.WorkingTime;
import fa.youareright.repository.BranchRepository;
import fa.youareright.repository.EmployeeRepository;
import fa.youareright.repository.WorkingTimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/admin/branch")
public class BranchRestController {
    @Autowired
    private BranchRepository branchRepository;
    @GetMapping("")
    public ResponseEntity<?> getFormBooking() {
        List<Branch> branchList = branchRepository.findAll();

        return new ResponseEntity<>(branchList, HttpStatus.OK);
    }
}
