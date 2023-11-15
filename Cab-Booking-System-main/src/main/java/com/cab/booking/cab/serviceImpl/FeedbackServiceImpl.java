package com.cab.booking.cab.serviceImpl;

import com.cab.booking.cab.dto.Feedback;
import com.cab.booking.cab.dto.User;
import com.cab.booking.cab.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    JdbcTemplate jdbcTemplate;


    @Override
    public Feedback submitFeedback(Feedback feedback) {
        Feedback data=getDriver(feedback);
        String sql = "INSERT INTO FEEDBACK (USERNAME, BOOKING_ID, RATING, MESSAGE,DRIVER) VALUES (?, ?, ?, ?,?)";
        jdbcTemplate.update(
                sql,
                feedback.getUsername(),
                feedback.getBookingId(),
                feedback.getRating(),
                feedback.getFeedbackMsg(),
                data.getDriver()
        );
        return feedback;
    }

    public Feedback getDriver(Feedback feedback) {

        Feedback data = new Feedback();
        String select_query = "SELECT * FROM CAB_BOOKING WHERE BOOKING_ID=? AND USERNAME=?;";
        jdbcTemplate.query(select_query, new Object[]{feedback.getBookingId(), feedback.getUsername()}, rs -> {

            data.setDriver(rs.getString("DRIVER"));

        });
        return data;
    }

    public User getUser(User data) {

        User user = new User();
        String select_query = "SELECT * FROM USER_LOGIN WHERE USERNAME=?;";
        jdbcTemplate.query(select_query, new Object[]{data.getUsername()}, rs -> {

            user.setEmail(rs.getString("EMAIL"));
            user.setContactNo(rs.getString("CONTACT"));
            user.setUsername(rs.getString("USERNAME"));

        });
        return user;
    }


}
