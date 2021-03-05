package com.example.hobbie.web;

import com.example.hobbie.model.binding.SignUpBindingModel;
import com.example.hobbie.model.entities.AppClient;
import com.example.hobbie.model.service.SignUpServiceModel;
import com.example.hobbie.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/signup")
    public String showSignUp(Model model){
        if(!model.containsAttribute("signUpBindingModel")){
            model.addAttribute("signUpBindingModel", new SignUpBindingModel());
            model.addAttribute("isExists",false);
        }

        return "signup";
    }

    @PostMapping("/signup")
    public String signup(@Valid SignUpBindingModel signUpBindingModel, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors() || !(signUpBindingModel.getPassword().equals(signUpBindingModel.getConfirmPassword()))){
            redirectAttributes.addFlashAttribute("signUpBindingModel", signUpBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.signUpBindingModel", bindingResult);
            return "redirect:/users/signup";
        }

        else {

            boolean isSaved = this.userService.register(this.modelMapper.map(signUpBindingModel, SignUpServiceModel.class));

            if(!isSaved){
                redirectAttributes.addFlashAttribute("signUpBindingModel", signUpBindingModel);
                redirectAttributes.addFlashAttribute("isExists", true);
                return "redirect:/users/signup";
            }
            return "redirect:/users/login";
        }
    }

    @GetMapping("/register-business")
    public String showRegisterBusiness(){
        return "register-business";
    }

    @GetMapping("/login")
    public String showLogin(){

        return "login";
    }



}

