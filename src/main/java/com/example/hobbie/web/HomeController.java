package com.example.hobbie.web;

import com.example.hobbie.config.UserInterceptor;
import com.example.hobbie.service.HobbyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class HomeController {
    private final HobbyService hobbyService;

    @Autowired
    public HomeController(HobbyService hobbyService) {
        this.hobbyService = hobbyService;
    }

    public String showHome(){

        return "index";
    }

    @GetMapping("/business_owner")
    public ModelAndView adminShow(@AuthenticationPrincipal UserDetails principal) {
        if (UserInterceptor.isUserLogged()) {
            ModelAndView mav = new ModelAndView("business_owner");
            mav.addObject("user", principal);
            mav.addObject("hobby_offers", hobbyService.getAllHobbyOffers());
            return mav;
        }
        else{
            ModelAndView mav = new ModelAndView("index");
            return mav;}
    }


//    //TODO: IMPLEMENT /user as Admin page
//    @GetMapping("/user")
//    public ModelAndView userShow(@AuthenticationPrincipal UserDetails principal) {
//        ModelAndView mav= new ModelAndView("user");
//        mav.addObject("user", principal);
//        return mav;
//    }

    @GetMapping("/user_home")
    public ModelAndView userHomeShow(@AuthenticationPrincipal UserDetails principal) {
        if (UserInterceptor.isUserLogged()) {
            ModelAndView mav = new ModelAndView("user_home");
            mav.addObject("user", principal);
            return mav;
        }
        else{
            ModelAndView mav = new ModelAndView("index");
            return mav;
        }
    }
}
