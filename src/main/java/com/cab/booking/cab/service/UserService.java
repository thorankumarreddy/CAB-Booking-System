package com.cab.booking.cab.service;
import org.springframework.stereotype.Repository;
import com.cab.booking.cab.dto.User;
import com.cab.booking.cab.dto.Driver;
import java.util.List;


@Repository
public interface UserService {

    public String addUser(User user);
    public User userdata(User user);
    public void statusUpdate(String status, String tripId, String driver);
    public User trackRide(User user);
    public Driver driverData(Driver login);

    public User getCabBookingDetails(String tripId);
}