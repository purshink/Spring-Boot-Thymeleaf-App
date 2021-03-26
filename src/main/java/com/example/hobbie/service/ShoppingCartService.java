package com.example.hobbie.service;

import com.example.hobbie.model.entities.Abo;

import java.math.BigDecimal;
import java.util.List;

public interface ShoppingCartService {
    void addAboToCart(Long hobbyId);

    void removeProductFromCart(Long hobbyId);
    BigDecimal getTotal();
    List<Abo> getAbosInCart();

    void checkout();
}
