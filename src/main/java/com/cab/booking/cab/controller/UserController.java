package com.cab.booking.cab.controller;
import com.cab.booking.cab.dto.User;
import com.cab.booking.cab.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Autowired;
import javax.servlet.http.HttpServletRequest;


@Controller
public class UserController {

    @RequestMapping("/registration")
    public String registration() {

        return "registration.html";
    }


    @Autowired
    private UserService userService;

    @RequestMapping(path="/addUser",method=RequestMethod.POST)
    public String addUser(HttpServletRequest request){

            User user=new User();
            user.setUsername(request.getParameter("usrnm"));
            user.setEmail(request.getParameter("email"));
            user.setContactNo(request.getParameter("contact"));
            user.setPassword(request.getParameter("psw"));
            String result= userService.addUser(user);
            if(result.equals("success")){
                return "Success.html";
            }else{
                return "Error.html";
            }

    }


}
