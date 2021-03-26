package com.example.hobbie.service.impl;

import com.example.hobbie.model.entities.Abo;
import com.example.hobbie.model.entities.Entry;
import com.example.hobbie.model.entities.Hobby;
import com.example.hobbie.model.entities.enums.AboTypeEnum;
import com.example.hobbie.service.AboService;
import com.example.hobbie.service.HobbyService;
import com.example.hobbie.service.ShoppingCartService;
import com.example.hobbie.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final HobbyService hobbyService;
    private final UserService userService;
    private final AboService aboService;

    private List<Abo> inCart = new ArrayList<>();

    @Autowired
    public ShoppingCartServiceImpl(HobbyService hobbyService, UserService userService, AboService aboService) {
        this.hobbyService = hobbyService;
        this.userService = userService;
        this.aboService = aboService;
    }

    @Override
    public void addAboToCart(Long hobbyId) {
        Hobby hobbieById = this.hobbyService.findHobbieById(hobbyId);
//        Entry entry = new Entry();
//        List<Entry> entries = Collections.nCopies(5, entry);
        Abo abo = new Abo();
        abo.setClient(this.userService.findCurrentUserAppClient());
        abo.setHobby(hobbieById);

        BigDecimal price = hobbieById.getPrice().multiply(new BigDecimal(5));
        price =  price.add(price.multiply(new BigDecimal("0.1")));
        price = price.setScale(2, RoundingMode.HALF_EVEN);
        abo.setAboPrice(price);
        abo.setAboType(AboTypeEnum.FIVE_ENTRIES);
//        abo.setEntries(entries);

        if(!inCart.contains(abo)){
            inCart.add(abo);
        }
    }


    @Override
    public void removeProductFromCart(Long hobbyId) {
        for (Abo abo : inCart) {
            if(abo.getHobby().getId().equals(hobbyId)){
                inCart.remove(abo);
                break;
            }
        }
    }

    @Override
    public BigDecimal getTotal() {

        return inCart.stream().map(Abo::getAboPrice).reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
    }

    @Override
    public List<Abo> getAbosInCart() {
        return inCart;
    }

    @Override
    public void checkout() {
        this.aboService.saveAbos(inCart);
        inCart.clear();
    }
}
