package com.cab.booking.cab.serviceImpl;

import org.springframework.stereotype.Service;
import org.springframework.jdbc.core.JdbcTemplate;
import com.cab.booking.cab.service.UserService;
import com.cab.booking.cab.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import com.cab.booking.cab.dto.Driver;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.util.Date;


@Service
public class UserServiceImpl implements UserService {


    @Autowired
    JdbcTemplate jdbcTemplate;

    public String addUser(User user) {
        //insert query
        String insert_query = "INSERT INTO USER_LOGIN"
                + "(USERNAME,EMAIL,CONTACT,PASSWORD)"
                + "VALUES"
                + "(?,?,?,?);";

        int rows = jdbcTemplate.update(insert_query, user.getUsername(), user.getEmail(),
                user.getContactNo(), user.getPassword());
        if (rows == 1) {
            return "success";
        } else {
            return "error";
        }

    }


    public User userdata(User user) {

        User data = new User();
        String select_query = "SELECT * FROM USER_LOGIN WHERE USERNAME=? AND PASSWORD=?;";
        jdbcTemplate.query(select_query, new Object[]{user.getUsername(), user.getPassword()}, rs -> {

            data.setUsername(rs.getString("USERNAME"));
            data.setEmail(rs.getString("EMAIL"));
            data.setContactNo(rs.getString("CONTACT"));


        });
        return data;
    }

    public void statusUpdate(String status, String tripId, String driver) {
        //insert query
//        String sqlUpdate = "SET SQL_SAFE_UPDATES = 0;";
        String update_query = "UPDATE CAB_BOOKING SET BOOK_STATUS=? WHERE BOOKING_ID=?";
        int rows = jdbcTemplate.update(update_query, status, tripId);
        if (rows == 1) {
            System.out.println("update success with status = " + status);
        } else {
            System.out.println("error in status update");
        }
    }

    public User getCabBookingDetails(String tripId) {
        User ride = new User();
        String select_query = "SELECT * FROM CAB_BOOKING WHERE BOOKING_ID=?;";
        jdbcTemplate.query(select_query, new Object[]{tripId}, rs -> {
            ride.setDriver(rs.getString("DRIVER"));
            ride.setCabtype(rs.getString("CAB_TYPE"));
            ride.setBookdate(rs.getDate("BOOK_DATE"));
            ride.setPickup(rs.getString("PICK_UP"));
            ride.setDrop(rs.getString("DROP_LOC"));
            ride.setRideStatus(rs.getString("BOOK_STATUS"));
            ride.setBookid(tripId);
        });
        return ride;
    }

    public User trackRide(User user) {

        User ride = new User();
        String select_query = "SELECT * FROM CAB_BOOKING WHERE USERNAME=? AND BOOKING_ID=?;";
        jdbcTemplate.query(select_query, new Object[]{user.getUsername(), user.getBookid()}, rs -> {

            ride.setDriver(rs.getString("DRIVER"));
            ride.setCabtype(rs.getString("CAB_TYPE"));
            ride.setBookdate(rs.getDate("BOOK_DATE"));
            ride.setPickup(rs.getString("PICK_UP"));
            ride.setDrop(rs.getString("DROP_LOC"));
            ride.setBookid(user.getBookid());
            ride.setUsername(user.getUsername());


        });
        String pickLoc = "SELECT * FROM Picklocation WHERE location_name=?;";
        jdbcTemplate.query(pickLoc, new Object[]{ride.getPickup()}, rs -> {

            ride.setPickLat(rs.getString("latitude"));
            ride.setPickLong(rs.getString("longitude"));
        });

        String dropLocations = "SELECT * FROM Droplocation WHERE location_name=?;";
        jdbcTemplate.query(dropLocations, new Object[]{ride.getDrop()}, rs -> {

            ride.setDropLat(rs.getString("latitude"));
            ride.setDropLong(rs.getString("longitude"));
        });

        return ride;
    }

    public Driver driverData(Driver login) {

        Driver data = new Driver();
        String query="SELECT COUNT(*) as count FROM DRIVER_LOGIN WHERE DRIVER_NAME=? AND PASSWORD=? ;";
        jdbcTemplate.query(query,new Object[]{login.getDrivername(),login.getPassword()},rs ->{

            data.setCount(rs.getInt("count"));

        });


      if(data.getCount()==1){

        String details = "SELECT * FROM CAB_BOOKING WHERE BOOK_STATUS='upcoming' AND DRIVER=?;";
        jdbcTemplate.query(details, new Object[]{login.getDrivername()}, rs -> {

            data.setBookId(rs.getString("BOOKING_ID"));
            data.setPickup(rs.getString("PICK_UP"));
            data.setDrop(rs.getString("DROP_LOC"));
            data.setRideStatus(rs.getString("BOOK_STATUS"));
            data.setDrivername(login.getDrivername());

        });
        String pickLocations = "SELECT * FROM Picklocation WHERE location_name=?;";
        jdbcTemplate.query(pickLocations, new Object[]{data.getPickup()}, rs -> {

            data.setPickLat(rs.getString("latitude"));
            data.setPickLong(rs.getString("longitude"));
        });

        String dropLocations = "SELECT * FROM Droplocation WHERE location_name=?;";
        jdbcTemplate.query(dropLocations, new Object[]{data.getDrop()}, rs -> {

            data.setDropLat(rs.getString("latitude"));
            data.setDropLong(rs.getString("longitude"));
        });

        }

        return data;

    }




//    public String loginUser(User user){
//        //insert query
//        String select_query="SELECT COUNT(*) FROM USER_LOGIN WHERE USERNAME=? AND PASSWORD=?;";
//
//        String rows=jdbcTemplate.queryForObject(select_query,new Object[]{user.getUsername(),user.getPassword()},String.class);
//        int result=Integer.valueOf(rows);
//        if(result==1){
//            return "valid";
//        }else{
//            return "invalid";
//        }
//
//    }


//
//    public List<User> userDetails(User user){
//        List<User> details = new ArrayList<>();
//        String select_query="SELECT COUNT(*) FROM USER_LOGIN WHERE USERNAME=? AND PASSWORD=?;";
//        details=jdbcTemplate.queryForList(select_query,new Object[]{user.getUsername(),user.getPassword()},User.class);
//        return details;
//
//    }


}
