
package com.cab.booking.cab.serviceImpl;


import com.cab.booking.cab.dto.Booking;
import com.cab.booking.cab.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;


@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Booking bookCab(Booking booking) {

        String insertQuery = "INSERT INTO CAB_BOOKING(USERNAME,PICK_UP,DROP_LOC,BOOK_DATE,BOOK_TIME,CAB_TYPE,BOOKING_ID,DRIVER,BOOK_STATUS,AMOUNT,PAYMENT) VALUES (?, ?, ?, ?, ?, ?, ?, ?,?,?,?)";
        int rows=jdbcTemplate.update(insertQuery,
                booking.getUsername(),
                booking.getPickup(),
                booking.getDropoff(),
                booking.getDate(),
                booking.getTime(),
                booking.getCabType(),
                booking.getId(),
                booking.getDriverName(),
                "upcoming",
                booking.getAmount(),
        booking.getPaymentMethod());

        if(rows==1){
           if(booking.getPaymentMethod().equals("creditcard") ){
               String Query = "INSERT INTO PAYMENT (BOOKING_ID,USERNAME,CARD_NUMBER,CVV,EXPIRY_DATE,CARD_HOLDER) VALUES (?, ?, ?, ?, ?, ?)";
               jdbcTemplate.update(Query,
                       booking.getId(),
                       booking.getUsername(),
                       booking.getCardNo(),
                       booking.getCvv(),
                       booking.getExpiryDate(),
                       booking.getFullName());
           }


        }

        return booking;
    }

    public Booking getDriver(Booking booking){

        Booking driver=new Booking();
        String select_query="select * from DRIVER_LOGIN WHERE CAB_TYPE=?;";
        jdbcTemplate.query(select_query,new Object[]{booking.getCabType()},rs ->{

            driver.setDriverName(rs.getString("DRIVER_NAME"));

        });

        String query="SELECT AVG(RATING) AS average_rating FROM FEEDBACK WHERE DRIVER=? GROUP BY DRIVER;";
        jdbcTemplate.query(query,new Object[]{driver.getDriverName()},rs ->{

            driver.setFeedback(rs.getString("average_rating"));

        });

        return driver;
    }

    public Booking checkRide(Booking cab){

        Booking user=new Booking();
        String select_query="select * from CAB_BOOKING WHERE BOOK_STATUS IN ('upcoming','arrived') AND USERNAME=?;";
        jdbcTemplate.query(select_query,new Object[]{cab.getUsername()},rs ->{

            user.setId(rs.getString("BOOKING_ID"));
            user.setCheck(false);

        });


        return user;
    }





}