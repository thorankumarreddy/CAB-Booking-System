package com.cab.booking.cab.serviceImpl;
import org.springframework.stereotype.Service;
import org.springframework.jdbc.core.JdbcTemplate;
import com.cab.booking.cab.service.UserService;
import com.cab.booking.cab.dto.User;
import org.springframework.beans.factory.annotation.Autowired;




@Service
public class UserServiceImpl implements UserService{


    @Autowired
    JdbcTemplate jdbcTemplate;

    public String addUser(User user){
        //insert query
        String insert_query="INSERT INTO USER_LOGIN"
                +"(USERNAME,EMAIL,CONTACT,PASSWORD)"
                +"VALUES"
                +"(?,?,?,?);";

        int rows=jdbcTemplate.update(insert_query,user.getUsername(),user.getEmail(),
                user.getContactNo(),user.getPassword());
        if(rows==1){
            return "success";
        }else{
            return "error";
        }

    }


}
