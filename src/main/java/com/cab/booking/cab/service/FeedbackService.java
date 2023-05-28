package com.cab.booking.cab.service;
import  com.cab.booking.cab.dto.Feedback;
import com.cab.booking.cab.dto.User;

public interface FeedbackService {

    public Feedback submitFeedback(Feedback user);
    public User getUser(User user);

}
