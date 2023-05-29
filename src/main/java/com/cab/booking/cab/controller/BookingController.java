package com.cab.booking.cab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.cab.booking.cab.service.BookingService;
import org.springframework.web.bind.annotation.RequestMethod;
import com.cab.booking.cab.dto.Booking;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Random;

@Controller
public class BookingController {

    @Autowired
    private BookingService BookingService;

    @RequestMapping("/bookCab")
    public String bookCab() {

        return "Bookingcab.html";
    }

    @RequestMapping(path = "/cab-details", method = RequestMethod.POST)
    public String getCab(HttpServletRequest request, Model model) {

        Booking cab = new Booking();
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals("name")) {
                    cab.setUsername(cookie.getValue());
                }
            }
        }
        Random random = new Random();
        String bookingId = String.valueOf(100000 + random.nextInt(900000));
        cab.setId(bookingId);
        cab.setPickup(request.getParameter("pickup"));
        cab.setDropoff(request.getParameter("dropoff"));
        cab.setDate(request.getParameter("date"));
        cab.setTime(request.getParameter("time"));
        cab.setCabType(request.getParameter("cabtype"));
        cab.setPaymentMethod(request.getParameter("paymentmethod"));
        cab.setCardNo(request.getParameter("cardNo"));
        cab.setCvv(request.getParameter("cvv"));
        cab.setExpiryDate(request.getParameter("expiryDate"));
        cab.setFullName(request.getParameter("fullName"));
        Booking driver = BookingService.getDriver(cab);
        cab.setDriverName(driver.getDriverName());
        cab.setFeedback(driver.getFeedback());
        cab.setAmount("$20");
        Booking result = BookingService.bookCab(cab);
        model.addAttribute("result", result);
        return "Successcab.html";
    }


}
