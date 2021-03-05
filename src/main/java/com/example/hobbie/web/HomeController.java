package com.example.hobbie.web;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class HomeController {

    public String showHome(){

        return "index";
    }

    @GetMapping("/business_owner")
    public ModelAndView admin(@AuthenticationPrincipal UserDetails principal) {
        ModelAndView mav= new ModelAndView("business_owner");
        mav.addObject("user", principal);
        return mav;
    }

    @GetMapping("/user")
    public ModelAndView user(@AuthenticationPrincipal UserDetails principal) {
        ModelAndView mav= new ModelAndView("user");
        mav.addObject("user", principal);
        return mav;
    }
}
