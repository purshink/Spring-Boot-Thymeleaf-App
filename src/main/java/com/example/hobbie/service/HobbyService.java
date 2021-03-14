package com.example.hobbie.service;

import com.example.hobbie.model.entities.BusinessOwner;
import com.example.hobbie.model.entities.Hobby;
import com.example.hobbie.model.service.HobbyServiceModel;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface HobbyService {
    Long createHobby(HobbyServiceModel hobbyServiceModel,  String fileName);

    List<Hobby> getAllHobbyOffers();

}
