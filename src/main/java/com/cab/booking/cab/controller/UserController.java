package com.cab.booking.cab.controller;
import com.cab.booking.cab.dto.User;
import com.cab.booking.cab.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Autowired;
import javax.servlet.http.HttpServletRequest;
import org.springframework.ui.Model;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Cookie;



@Controller
public class UserController {

    @RequestMapping("/registration")
    public String registration() {

        return "registration.html";
    }

    @RequestMapping("/login")
    public String login() {

        return "login.html";
    }

    @RequestMapping("/logout")
    public String logout(HttpServletResponse response) {

        Cookie ck=new Cookie("name","");
        ck.setMaxAge(0);
        response.addCookie(ck);
        return "logout.html";
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

    @RequestMapping(path="/loginUser",method=RequestMethod.POST)
    public String loginUser(HttpServletRequest request,Model model, HttpServletResponse response){

        User user=new User();
        user.setUsername(request.getParameter("usrnm"));
        user.setPassword(request.getParameter("psw"));
        User data= userService.userdata(user);
        if(data.isNull()){
            model.addAttribute("message", "invalid");
            return "login.html";
        }else{
            model.addAttribute("user", data);
            Cookie ck=new Cookie("name",data.getUsername());
            response.addCookie(ck);
            return "dashboard.html";
        }



    }


    @RequestMapping("/driver")
    public String driver() {
        return "driver.html";
    }

    @RequestMapping("/heatmap")
    public String heatmap() {
        return "tripheatmap.html";
    }

    @RequestMapping("/triptracker")
    public String triptracker() {
        return "triptracker.html";
    }


}
