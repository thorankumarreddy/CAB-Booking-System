package com.cab.booking.cab.service;
import org.springframework.stereotype.Repository;
import com.cab.booking.cab.dto.User;


@Repository
public interface UserService {

    public String addUser(User user);
    public User userdata(User user);


}