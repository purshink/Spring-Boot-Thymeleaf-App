package com.example.hobbie.web;

import com.example.hobbie.config.UserInterceptor;
import com.example.hobbie.model.entities.AppClient;
import com.example.hobbie.service.AboService;
import com.example.hobbie.service.HobbyService;
import com.example.hobbie.service.UserService;
import com.example.hobbie.view.HobbyCardViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {
    private final HobbyService hobbyService;
    private final UserService userService;
    private final AboService aboService;

    @Autowired
    public HomeController(HobbyService hobbyService, UserService userService, AboService aboService) {
        this.hobbyService = hobbyService;
        this.userService = userService;
        this.aboService = aboService;
    }

    @RequestMapping("/")
    public String showHome(HttpServletRequest request) {
        if (UserInterceptor.isUserLogged()) {
            if (request.isUserInRole("ROLE_BUSINESS_USER")) {
                return "redirect:/business_owner";
            } else if (request.isUserInRole("ROLE_USER")) {
                return "redirect:/user_home";
            }
            return "redirect:/";
        }
        return "home/index";
    }

    @GetMapping("/business_owner")
    public ModelAndView adminShow(@AuthenticationPrincipal UserDetails principal) {
        if (UserInterceptor.isUserLogged()) {
            ModelAndView mav = new ModelAndView("home/business_owner");
            mav.addObject("user", principal);
            mav.addObject("hobby_offers", hobbyService.getAllHobbyOffers());
            mav.addObject("abos", this.aboService.getAbosPerBusiness());
            return mav;
        } else {
            ModelAndView mav = new ModelAndView("home/index");
            return mav;
        }
    }

    @GetMapping("/user_home")
    public ModelAndView userHomeShow(@AuthenticationPrincipal UserDetails principal) {
        if (UserInterceptor.isUserLogged()) {
            boolean isEmpty = false;
            boolean hasNoResults = false;
            ModelAndView mav = new ModelAndView("home/user_home");
            mav.addObject("user", principal);
            AppClient currentUserAppClient = this.userService.findCurrentUserAppClient();
            List<HobbyCardViewModel> hobbyMatches = this.hobbyService.getHobbyMatches(currentUserAppClient);
            if (currentUserAppClient.getTestResults() == null) {
                hasNoResults = true;
            } else if (hobbyMatches.isEmpty()) {
                isEmpty = true;
            }

            mav.addObject("hobby_matches", hobbyMatches);
            mav.addObject("hasNoMatches", isEmpty);
            mav.addObject("hasNoResults", hasNoResults);

            return mav;
        } else {
            ModelAndView mav = new ModelAndView("home/index");
            return mav;
        }
    }
}
