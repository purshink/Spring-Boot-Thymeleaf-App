package com.example.hobbie.web;

import com.example.hobbie.config.UserInterceptor;
import com.example.hobbie.model.binding.HobbyBindingModel;
import com.example.hobbie.model.binding.UpdateHobbyBindingModel;
import com.example.hobbie.model.entities.Hobby;
import com.example.hobbie.model.service.HobbyServiceModel;
import com.example.hobbie.model.service.UpdateHobbyServiceModel;
import com.example.hobbie.service.HobbyService;
import com.example.hobbie.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Objects;

@Controller
@RequestMapping("/hobbies")
public class HobbyController {

    private final ModelMapper modelMapper;
    private final HobbyService hobbyService;
    private final UserService userService;

    @Autowired
    public HobbyController(ModelMapper modelMapper, HobbyService hobbyService, UserService userService) {
        this.modelMapper = modelMapper;
        this.hobbyService = hobbyService;
        this.userService = userService;
    }

    @GetMapping("/create-offer-page")
    public ModelAndView showCreateOffer(Model model) {
        if (UserInterceptor.isUserLogged()) {
            if (!model.containsAttribute("hobbyBindingModel")) {
                model.addAttribute("hobbyBindingModel", new HobbyBindingModel());
                model.addAttribute("isExists", false);
                model.addAttribute("noImg", false);
            }
            ModelAndView mav = new ModelAndView("offer/create_offer");
            return mav;
        } else {
            ModelAndView mav = new ModelAndView("home/index");
            return mav;
        }
    }

    @PostMapping("/offer")
    public String saveHobby(@Valid HobbyBindingModel hobbyBindingModel, BindingResult bindingResult, RedirectAttributes redirectAttributes,
                            @RequestParam("img") MultipartFile multipartFile) throws IOException {
        if (UserInterceptor.isUserLogged()) {
            String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));

            if (bindingResult.hasErrors() || fileName.isBlank()) {
                redirectAttributes.addFlashAttribute("hobbyBindingModel", hobbyBindingModel);
                redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.hobbyBindingModel", bindingResult);
                if (fileName.isEmpty()) {
                    redirectAttributes.addFlashAttribute("noImg", true);
                }
                return ("offer/create_offer");
            }
            this.hobbyService.createHobby(this.modelMapper.map(hobbyBindingModel, HobbyServiceModel.class), fileName);
            return ("redirect:/business_owner");
        } else {
            return "home/index";
        }
    }

    @GetMapping("/hobby-details/{id}")
    public String showHome(@PathVariable Long id, Model model) {
        if (UserInterceptor.isUserLogged()) {
            Hobby hobby = this.hobbyService.findHobbieById(id);
            model.addAttribute("hobby", hobby);
            model.addAttribute("isSaved", this.hobbyService.isHobbySaved(id));
            return "offer/hobby-details";
        } else {
            return "home/index";
        }
    }

    @GetMapping("/offer-details/{id}")
    public String showOffer(@PathVariable Long id, Model model) {
        if (UserInterceptor.isUserLogged()) {
            Hobby hobby = this.hobbyService.findHobbieById(id);
            model.addAttribute("hobby", hobby);
            return "offer/offer-details";
        } else {
            return "home/index";
        }
    }

    @GetMapping("/save-hobby/{id}")
    public String saveHobbyInLikedHobbiesList(@PathVariable Long id, Model model) {
        if (UserInterceptor.isUserLogged()) {
            Hobby hobby = this.hobbyService.findHobbieById(id);
            this.hobbyService.saveHobbyForClient(hobby);
            model.addAttribute("hobby", hobby);
            model.addAttribute("isSaved", this.hobbyService.isHobbySaved(id));
            return "offer/hobby-details";
        } else {
            return "home/index";
        }
    }

    @GetMapping("/remove-hobby/{id}")
    public String removeHobbyFromLikedHobbiesList(@PathVariable Long id, Model model) {
        if (UserInterceptor.isUserLogged()) {
            Hobby hobby = this.hobbyService.findHobbieById(id);
            this.hobbyService.removeHobbyForClient(hobby);
            model.addAttribute("hobby", hobby);
            model.addAttribute("isSaved", this.hobbyService.isHobbySaved(id));
            return "offer/hobby-details";
        } else {
            return "home/index";
        }
    }

    @GetMapping("/update-hobby-page/{id}")
    public String showUpdateHobbyForm(@PathVariable("id") long id, Model model) {
        if (UserInterceptor.isUserLogged()) {
            Hobby hobbie = this.hobbyService.findHobbieById(id);
            UpdateHobbyBindingModel updateHobbyBindingModel = this.modelMapper.map(hobbie, UpdateHobbyBindingModel.class);
            model.addAttribute("updateHobbyBindingModel", updateHobbyBindingModel);
            model.addAttribute("noImg2", false);

            return "offer/update-hobby";
        } else {
            return "home/index";
        }
    }

    @PostMapping("updated-offer/{id}")
    public String updateHobby(@PathVariable("id") long id, @Valid UpdateHobbyBindingModel updateHobbyBindingModel, BindingResult bindingResult, RedirectAttributes redirectAttributes,
                              @RequestParam("img") MultipartFile multipartFile) throws IOException {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        if (UserInterceptor.isUserLogged()) {
            if (bindingResult.hasErrors() || fileName.isBlank()) {
                redirectAttributes.addFlashAttribute("updateHobbyBindingModel", updateHobbyBindingModel);
                redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.updateHobbyBindingModel", bindingResult);
                if (fileName.isBlank()) {
                    redirectAttributes.addFlashAttribute("noImg2", true);
                }
                return "offer/update-hobby";
            } else {
                updateHobbyBindingModel.setId(id);
                this.hobbyService.saveUpdatedHobby(this.modelMapper.map(updateHobbyBindingModel, UpdateHobbyServiceModel.class), fileName);
            }

            return "redirect:/business_owner";
        } else {
            return "home/index";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteAppClient(@PathVariable("id") long id) throws IOException {
        if (UserInterceptor.isUserLogged()) {
            this.hobbyService.deleteHobby(id);
            return "redirect:/business_owner";
        } else {
            return "home/index";
        }
    }
}



