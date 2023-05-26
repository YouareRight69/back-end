package fa.youareright.service.impl;

import fa.youareright.dto.BookingDTO;
import fa.youareright.model.Booking;
import fa.youareright.model.BookingDetail;
import fa.youareright.repository.*;
import fa.youareright.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class BookingServiceImpl  implements BookingService {
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private WorkingTimeRepository workingTimeRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookingDetailRepository bookingDetailRepository;
    @Autowired
    private HairServiceRepository hairServiceRepository;

    @Override
    @Transactional
    public Booking saveBookingAndBookingDetail(BookingDTO bookingDTO) {
        String name =bookingDTO.getCustomerName() == null ? userRepository.findById(bookingDTO.getUserId())
                .orElse(null).getFullName() : bookingDTO.getCustomerName();
        Booking booking = bookingRepository
                .save(new Booking(LocalDate.parse(bookingDTO.getBookingDate()), bookingDTO.getIsDelete(), bookingDTO.getNote(),
                        userRepository.findById(bookingDTO.getUserId()).orElse(null)));
        bookingDTO.getServiceList().stream().forEach((item) -> {
            if (hairServiceRepository.findById(item).orElse(null).getType()
                    .equals(employeeRepository.findById(bookingDTO.getStyleId()).orElse(null).getType())) {
                bookingDetailRepository.save(new BookingDetail(name,
                        bookingDTO.getIsDelete(),
                        hairServiceRepository.findById(item).orElse(null),
                        booking,
                        employeeRepository.findById(bookingDTO.getStyleId()).orElse(null),
                        workingTimeRepository.findById(bookingDTO.getWorkTimeId()).orElse(null)));
            } else
                bookingDetailRepository.save(new BookingDetail(name,
                        bookingDTO.getIsDelete(),
                        hairServiceRepository.findById(item).orElse(null), booking,
                        employeeRepository.findById(bookingDTO.getSkinnerId()).orElse(null),
                        workingTimeRepository.findById(bookingDTO.getWorkTimeId()).orElse(null)));
        });
        return booking;
    }
}
