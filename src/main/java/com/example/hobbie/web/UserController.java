package com.example.hobbie.web;

import com.example.hobbie.model.binding.RegisterBusinessBindingModel;
import com.example.hobbie.model.binding.SignUpBindingModel;
import com.example.hobbie.model.entities.AppClient;
import com.example.hobbie.model.service.RegisterBusinessServiceModel;
import com.example.hobbie.model.service.SignUpServiceModel;
import com.example.hobbie.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {

    //TODO CREATE CUSTOM VALIDATION NOT NULL ERROR MESSAGE FOR GENDER
    //TODO CREATE POP UP THAT USER HAT SUCCESSFULLY SIGNED UP
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
            model.addAttribute("dontMatch",false);
            model.addAttribute("emptyGender", false);
        }

        return "signup";
    }

    @PostMapping("/signup")
    public String signup(@Valid SignUpBindingModel signUpBindingModel, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors() || !(signUpBindingModel.getPassword().equals(signUpBindingModel.getConfirmPassword()))||signUpBindingModel.getGender() == null){
            redirectAttributes.addFlashAttribute("signUpBindingModel", signUpBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.signUpBindingModel", bindingResult);
            if(!(signUpBindingModel.getPassword().equals(signUpBindingModel.getConfirmPassword()))){
                redirectAttributes.addFlashAttribute("dontMatch", true);
            }
            if(signUpBindingModel.getGender() == null){
                redirectAttributes.addFlashAttribute("emptyGender", true);
            }
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
    public String showRegisterBusiness(Model model){
        if(!model.containsAttribute("registerBusinessBindingModel")){
            model.addAttribute("registerBusinessBindingModel", new RegisterBusinessBindingModel());
            model.addAttribute("isExists",false);
            model.addAttribute("dontMatch",false);
        }

        return "register-business";
    }

    @PostMapping("/register-business")
    public String registerBusiness(@Valid RegisterBusinessBindingModel registerBusinessBindingModel, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors() || !(registerBusinessBindingModel.getPassword().equals(registerBusinessBindingModel.getConfirmPassword()))){
            redirectAttributes.addFlashAttribute("registerBusinessBindingModel", registerBusinessBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.registerBusinessBindingModel", bindingResult);
            if(!(registerBusinessBindingModel.getPassword().equals(registerBusinessBindingModel.getConfirmPassword()))){
                    redirectAttributes.addFlashAttribute("dontMatch", true);
            }
            return "redirect:/users/register-business";
        }

        else {

            boolean isSaved = this.userService.registerBusiness(this.modelMapper.map(registerBusinessBindingModel, RegisterBusinessServiceModel.class));

            if(!isSaved){
                redirectAttributes.addFlashAttribute("registerBusinessBindingModel", registerBusinessBindingModel);
                redirectAttributes.addFlashAttribute("isExists", true);
                return "redirect:/users/register-business";
            }
            return "redirect:/users/login";
        }
    }

    @GetMapping("/login")
    public String showLogin(){

        return "login";
    }

    @PostMapping("/login-error")
    public ModelAndView failedLogin(@ModelAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY)
                                            String username) {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("bad_credentials", true);
        modelAndView.addObject("username", username);

        modelAndView.setViewName("/login");

        return modelAndView;
    }

}

