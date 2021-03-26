package com.example.hobbie.service.impl;

import com.example.hobbie.model.entities.Abo;
import com.example.hobbie.model.repostiory.AboRepository;
import com.example.hobbie.service.AboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AboServiceImpl implements AboService {
    private final AboRepository aboRepository;

    @Autowired
    public AboServiceImpl(AboRepository aboRepository) {
        this.aboRepository = aboRepository;
    }

    @Override
    public void saveAbos(List<Abo> inCart) {
        this.aboRepository.saveAll(inCart);
    }
}
