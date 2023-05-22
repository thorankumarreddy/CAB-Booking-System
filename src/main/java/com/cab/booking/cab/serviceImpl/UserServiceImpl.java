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


    public User userdata(User user){

        User data=new User();
        String select_query="SELECT * FROM USER_LOGIN WHERE USERNAME=? AND PASSWORD=?;";
         jdbcTemplate.query(select_query,new Object[]{user.getUsername(),user.getPassword()},rs ->{

                    data.setUsername(rs.getString("USERNAME"));
                    data.setEmail(rs.getString("EMAIL"));
                    data.setContactNo(rs.getString("CONTACT"));


                });
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
