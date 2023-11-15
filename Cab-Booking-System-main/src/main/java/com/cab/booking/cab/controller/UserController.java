package com.cab.booking.cab.controller;

import com.cab.booking.cab.dto.User;
import com.cab.booking.cab.service.UserService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Autowired;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Cookie;

import com.cab.booking.cab.dto.Driver;
import org.springframework.web.bind.annotation.ResponseBody;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;


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

    @RequestMapping("/index")
    public String index() {

        return "index.html";
    }

    @RequestMapping("/logout")
    public String logout(HttpServletResponse response) {

        Cookie ck = new Cookie("name", "");
        ck.setMaxAge(0);
        response.addCookie(ck);
        return "logout.html";
    }

    @Autowired
    private UserService userService;

    @RequestMapping(path = "/addUser", method = RequestMethod.POST)
    public String addUser(HttpServletRequest request) {

        User user = new User();
        user.setUsername(request.getParameter("usrnm"));
        user.setEmail(request.getParameter("email"));
        user.setContactNo(request.getParameter("contact"));
        user.setPassword(request.getParameter("psw"));
        String result = userService.addUser(user);
        if (result.equals("success")) {
            return "Success.html";
        } else {
            return "Error.html";
        }

    }

    @RequestMapping(path = "/loginUser", method = RequestMethod.POST)
    public String loginUser(HttpServletRequest request, Model model, HttpServletResponse response) {

        User user = new User();
        user.setUsername(request.getParameter("usrnm"));
        user.setPassword(request.getParameter("psw"));
        User data = userService.userdata(user);
        if (data.isNull()) {
            model.addAttribute("message", "invalid");
            return "login.html";
        } else {
            model.addAttribute("user", data);
            Cookie ck = new Cookie("name", data.getUsername());
            response.addCookie(ck);
            return "dashboard.html";
        }

    }

    @RequestMapping(path = "/status-Update", method = RequestMethod.POST)
    public void statusUpdate(HttpServletRequest request, Model model, HttpServletResponse response) {

        String tripId = request.getParameter("tripId");
        String driver = request.getParameter("driver");
        String status = request.getParameter("status");
        userService.statusUpdate(status, tripId, driver);
    }

    @RequestMapping("/logindriver")
    public String loginDriver() {
        return "logindriver.html";
    }

    @RequestMapping("/driver")
    public String driver(HttpServletRequest request, Model model) {
        Driver login = new Driver();
        login.setDrivername(request.getParameter("name"));
        login.setPassword(request.getParameter("passwrd"));
        Driver driver = userService.driverData(login);
        model.addAttribute("driver", driver);
        return "driver.html";
    }

    @RequestMapping("/heatmap")
    public String heatmap() {
        return "tripheatmap.html";
    }

    @RequestMapping("/triptracker")
    public String triptracker(HttpServletRequest request, Model model) {
        User user = new User();
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                user.setUsername(cookie.getValue());
            }
        }
        user.setBookid(request.getParameter("id"));
        User ride = userService.trackRide(user);
        model.addAttribute("ride", ride);
        model.addAttribute("tripId", ride.getBookid());
        return "triptracker.html";
    }


    @RequestMapping("/ridetrack")
    public String ridetrack(HttpServletRequest request, Model model) {
        return "ridetrack.html";
    }

    @Autowired
    private ResourceLoader resourceLoader;


    @GetMapping(path = "/taxi.png", produces = MediaType.IMAGE_PNG_VALUE)
    public @ResponseBody byte[] getImage() throws IOException {
        InputStream inputStream = resourceLoader.getResource("classpath:pages/taxi.png").getInputStream();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, length);
        }
        return outputStream.toByteArray();
    }

    @GetMapping(path = "/pickup.png", produces = MediaType.IMAGE_PNG_VALUE)
    public @ResponseBody byte[] getPickupImage() throws IOException {
        InputStream inputStream = resourceLoader.getResource("classpath:pages/pickuploc.png").getInputStream();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, length);
        }
        return outputStream.toByteArray();
    }

}
