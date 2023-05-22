package fa.youareright.controller.employee;

import fa.youareright.dto.BookingDTO;
import fa.youareright.model.BookingDetail;
import fa.youareright.model.Branch;
import fa.youareright.model.Employee;
import fa.youareright.model.WorkingTime;
import fa.youareright.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/emp/booking")
public class BookingRestController {
    @Autowired
    private BranchRepository branchRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private WorkingTimeRepository workingTimeRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookingDetailRepository bookingDetailRepository;


    @GetMapping("")
    public ResponseEntity<?> getFormBooking() {
        List<Branch> branchList = branchRepository.findAll();
        List<Employee> employeeList = employeeRepository.findAll();
        List<WorkingTime> workingTimeList = workingTimeRepository.findAll();
        BookingDTO bookingDTO = new BookingDTO(branchList, employeeList, workingTimeList);
        return new ResponseEntity<>(bookingDTO, HttpStatus.OK);
    }

    @GetMapping("/list-branch")
    public ResponseEntity<?> getListBranch() {
        return new ResponseEntity<>(branchRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/list-employee-of-branch")
    public ResponseEntity<?> getEmployeeOfBranch(@RequestParam(name = "branchId") String branchId) {
        return new ResponseEntity<>(userRepository.getListEmployee(branchId), HttpStatus.OK);
    }

    @GetMapping("/working-time")
    public ResponseEntity<?> getWorkingTimeList() {
        return new ResponseEntity<>(workingTimeRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("busy-list")
    public ResponseEntity<?> getBusyListOfEmployee(@RequestParam("employeeId") String employeeId, @RequestParam("day") String day) {
        List<String> busyList;
        busyList = bookingDetailRepository.getBusyTimeOfEmployee(LocalDate.parse(day), employeeId)
                .stream()
                .map((item) -> item.getWorkingTime().getTimeZone().toString())
                .collect(Collectors.toList());
        return new ResponseEntity<>(busyList, HttpStatus.OK);
    }
}
