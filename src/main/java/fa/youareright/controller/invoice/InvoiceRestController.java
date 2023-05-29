package fa.youareright.controller.invoice;

import fa.youareright.model.BookingDetail;
import fa.youareright.repository.BookingDetailRepository;
import fa.youareright.repository.BookingRepository;
import fa.youareright.repository.InvoiceDetailRepository;
import fa.youareright.repository.InvoiceRepository;
import fa.youareright.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/")
    public ResponseEntity<?> getListBooking() {
        return new ResponseEntity<>(bookingRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/bookingdetail")
    public ResponseEntity<?> getListBookingDetail() {
        return new ResponseEntity<>(bookingDetailRepository.findAll(), HttpStatus.OK);
    }
}
