package com.cab.booking.cab.service;
import com.cab.booking.cab.dto.Booking;

public interface BookingService {
    public Booking bookCab(Booking booking);
    public Booking getDriver(Booking booking);

}