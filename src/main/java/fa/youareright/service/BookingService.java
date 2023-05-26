package fa.youareright.service;

import fa.youareright.dto.BookingDTO;
import fa.youareright.model.Booking;

public interface BookingService {
    Booking saveBookingAndBookingDetail(BookingDTO bookingDTO);
}
