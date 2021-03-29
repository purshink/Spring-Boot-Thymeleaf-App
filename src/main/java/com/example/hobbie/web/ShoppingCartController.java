package com.example.hobbie.web;

import com.example.hobbie.service.AboService;
import com.example.hobbie.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;

    private final AboService aboService;


    @Autowired
    public ShoppingCartController(ShoppingCartService shoppingCartService, AboService aboService) {
        this.shoppingCartService = shoppingCartService;
        this.aboService = aboService;
    }

    @GetMapping("/shoppingCart")
    public ModelAndView shoppingCart() {
        ModelAndView modelAndView = new ModelAndView("/shoppingCart");
        modelAndView.addObject("abos", shoppingCartService.getAbosInCart());
        modelAndView.addObject("total", shoppingCartService.getTotal().toString());
        return modelAndView;
    }

    @GetMapping("/shoppingCart/addProduct/{hobbyId}")
    public ModelAndView addProductToCart(@PathVariable("hobbyId") Long hobbyId) {
        this.shoppingCartService.addAboToCart(hobbyId);
        return shoppingCart();
    }

    @GetMapping("/shoppingCart/removeProduct/{hobbyId}")
    public ModelAndView removeProductFromCart(@PathVariable("hobbyId") Long hobbyId) {
        this.shoppingCartService.removeProductFromCart(hobbyId);
        return shoppingCart();
    }

    @GetMapping("/shoppingCart/checkout")
    public String checkout() {

            shoppingCartService.checkout();

        return "success";
    }

}
