package fa.youareright.controller.employee;

import fa.youareright.dto.BookingDTO;
import fa.youareright.model.*;
import fa.youareright.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

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
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private HairServiceRepository hairServiceRepository;

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

    @PostMapping("create")
    public ResponseEntity<?> createBooking(@RequestBody BookingDTO bookingDTO) {

        Booking booking = bookingRepository
                .save(new Booking(LocalDate.parse(bookingDTO.getBookingDate()), bookingDTO.getIsDelete(), bookingDTO.getNote(),
                        userRepository.findById(bookingDTO.getUserId()).orElse(null)));
        bookingDTO.getServiceList().stream().forEach((item) -> {
            if (hairServiceRepository.findById(item).orElse(null).getType()
                    .equals(employeeRepository.findById(bookingDTO.getStyleId()).orElse(null).getType())) {
                bookingDetailRepository.save(new BookingDetail(userRepository.findById(bookingDTO.getUserId())
                        .orElse(null).getFullName(),
                        bookingDTO.getIsDelete(),
                        hairServiceRepository.findById(item).orElse(null),
                        booking,
                        employeeRepository.findById(bookingDTO.getStyleId()).orElse(null),
                        workingTimeRepository.findById(bookingDTO.getWorkTimeId()).orElse(null)));
            } else
                bookingDetailRepository.save(new BookingDetail(
                        userRepository.findById(bookingDTO.getUserId()).orElse(null).getFullName(),
                        bookingDTO.getIsDelete(),
                        hairServiceRepository.findById(item).orElse(null), booking,
                        employeeRepository.findById(bookingDTO.getSkinnerId()).orElse(null),
                        workingTimeRepository.findById(bookingDTO.getWorkTimeId()).orElse(null)));
        });
        return new ResponseEntity<>(booking, HttpStatus.OK);
    }

    @GetMapping("booking-get-exam")
    public ResponseEntity<?> getBooking() {
        return new ResponseEntity<>(bookingRepository.findAll(), HttpStatus.OK);
    }
}
