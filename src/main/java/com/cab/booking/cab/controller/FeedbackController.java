package com.cab.booking.cab.controller;

import com.cab.booking.cab.dto.Feedback;
import com.cab.booking.cab.dto.User;
import com.cab.booking.cab.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
class FeedbackController {

    private FeedbackService feedbackService;

    @Autowired
    public FeedbackController(FeedbackService feedbackService) {

        this.feedbackService = feedbackService;
    }

    @RequestMapping("/feedbackrating")
    public String showFeedbackForm() {

        return "Feedback.html";
    }


    @RequestMapping(path="/submitFeedback",method= RequestMethod.POST)
    public String submitFeedback(HttpServletRequest request, Model model) {

        Feedback feedback=new Feedback();
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals("name")) {
                    feedback.setUsername(cookie.getValue());
                }
            }
        }
        feedback.setBookingId(request.getParameter("bookid")); ;
        feedback.setFeedbackMsg(request.getParameter("feedback-text"));
        feedback.setRating(request.getParameter("rating"));
        Feedback data=feedbackService.submitFeedback(feedback);
        model.addAttribute("data", data);
        return "feedbackresponse.html";
    }

    @RequestMapping("/backHome")
    public String backHome(HttpServletRequest request, Model model) {
        User data=new User();
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                data.setUsername(cookie.getValue());
            }
        }
        User user=feedbackService.getUser(data);
        model.addAttribute("user", user);
        return "dashboard.html";
    }
}


