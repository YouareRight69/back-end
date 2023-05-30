package fa.youareright.controller.invoice;

import fa.youareright.dto.ListServiceResponse;
import fa.youareright.model.Booking;
import fa.youareright.model.BookingDetail;
import fa.youareright.repository.BookingDetailRepository;
import fa.youareright.repository.BookingRepository;
import fa.youareright.repository.InvoiceDetailRepository;
import fa.youareright.repository.InvoiceRepository;
import fa.youareright.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/receptionist/invoice")
public class InvoiceRestController {
    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    BookingService bookingService;

    @Autowired
    BookingDetailRepository bookingDetailRepository;

    @Autowired
    InvoiceRepository invoiceRepository;

    @Autowired
    InvoiceDetailRepository invoiceDetailRepository;

    @GetMapping("")
    public ResponseEntity<?> getBookingInfo(@RequestParam(required = false, name = "c") String condition,
                                            @RequestParam(defaultValue = "0", name = "p") int page,
                                            @RequestParam(defaultValue = "5") int size) {
        PageRequest pageable = PageRequest.of(page,size);
        Page<Booking> bookingList = bookingService.findAll(condition,condition, pageable);
        return new ResponseEntity<>(bookingList,HttpStatus.OK);
    }

//    @GetMapping("/details")
//    public ResponseEntity<?> getDetails(@RequestParam(name= "id") String bookingId) {
//        Booking booking = bookingService.findById(bookingId);
//        List<ListServiceResponse> service = booking.getBookingDetailList().stream().map(item->
//                new ListServiceResponse(item.getHairService().getName(),item.getEmployee().getUser().getFullName(),
//                        item.getHairService().getPrice()) ).collect(Collectors.toList());
//        double total = service.stream().mapToDouble(ListServiceResponse:: getPrice).sum();
//        Map<String, Object> resp = new HashMap<>();
//        resp.put("id", booking.getBookingId());
//        resp.put("date", booking.getBookingDate());
//        resp.put("time", booking.getBookingDetailList().get(0).getWorkingTime().getTimeZone());
//        resp.put("name", booking.getBookingDetailList().get(0).getName());
//        resp.put("branch", booking.getBranch().getName());
//        resp.put("service",service);
//        resp.put("total", total);
//
//        return new ResponseEntity<>(resp,HttpStatus.OK);
//    }

    @GetMapping("/bookingdetail")
    public ResponseEntity<?> getListBookingDetail() {
        return new ResponseEntity<>(bookingDetailRepository.findAll(), HttpStatus.OK);
    }
}
