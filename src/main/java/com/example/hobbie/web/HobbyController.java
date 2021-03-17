package com.example.hobbie.web;


import com.example.hobbie.model.binding.HobbyBindingModel;
import com.example.hobbie.model.binding.SignUpBindingModel;
import com.example.hobbie.model.entities.Hobby;
import com.example.hobbie.model.service.HobbyServiceModel;
import com.example.hobbie.model.service.SignUpServiceModel;
import com.example.hobbie.service.HobbyService;
import com.example.hobbie.util.FileUploadUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Objects;

@Controller
@RequestMapping("/business")
public class HobbyController {

    private final ModelMapper modelMapper;
    private final HobbyService hobbyService;

    @Autowired
    public HobbyController(ModelMapper modelMapper, HobbyService hobbyService) {
        this.modelMapper = modelMapper;
        this.hobbyService = hobbyService;
    }


    @GetMapping("/create_offer")
    public ModelAndView showCreateOffer(Model model) {
        if(!model.containsAttribute("hobbyBindingModel")){
            model.addAttribute("hobbyBindingModel", new HobbyBindingModel());
            model.addAttribute("isExists",false);
        }
        ModelAndView mav= new ModelAndView("create_offer");
        return mav;

    }

    @PostMapping("/create_offer")
    public RedirectView saveHobby(@Valid HobbyBindingModel hobbyBindingModel, BindingResult bindingResult, RedirectAttributes redirectAttributes,

                                  @RequestParam("image") MultipartFile multipartFile) throws IOException {

        String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("hobbyBindingModel", hobbyBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.hobbyBindingModel", bindingResult);

            return new RedirectView("/business/create_offer", true);
        } else {

            Long id = this.hobbyService.createHobby(this.modelMapper.map(hobbyBindingModel, HobbyServiceModel.class), fileName);

            String uploadDir = "hobby-photos/" + id;

            FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
        }

        return new RedirectView("/business_owner", true);
    }
    @GetMapping("/hobbie-details/{id}")
    public String showHome(@PathVariable Long id, Model model){
        Hobby hobby = this.hobbyService.findHobbieById(id);
        model.addAttribute("hobbie", hobby);
        return "hobbie-details";
    }
}



