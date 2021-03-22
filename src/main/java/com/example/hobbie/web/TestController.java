package com.example.hobbie.web;

import com.example.hobbie.config.UserInterceptor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

    @GetMapping("/test")
    public String showTest(){
        if (UserInterceptor.isUserLogged()) {
            return "test";
        }
        else{
            return "index";
        }
    }
}
