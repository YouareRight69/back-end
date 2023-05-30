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

    @GetMapping("/bookingdetail")
    public ResponseEntity<?> getListBookingDetail() {
        return new ResponseEntity<>(bookingDetailRepository.findAll(), HttpStatus.OK);
    }
    @GetMapping("/booking/{bookingId}")
    public ResponseEntity<?> getListBookingById(@PathVariable String bookingId) {
        return new ResponseEntity<>(bookingRepository.findById(bookingId), HttpStatus.OK);
    }
}
