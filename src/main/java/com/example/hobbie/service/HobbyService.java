package com.example.hobbie.service;

import com.example.hobbie.model.entities.BusinessOwner;
import com.example.hobbie.model.entities.Hobby;
import com.example.hobbie.model.service.HobbyServiceModel;
import com.example.hobbie.model.service.UpdateHobbyServiceModel;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface HobbyService {
    Long createHobby(HobbyServiceModel hobbyServiceModel,  String fileName);

    List<Hobby> getAllHobbyOffers();

    Hobby findHobbieById(Long id);


    void saveUpdatedHobby(UpdateHobbyServiceModel map, String fileName);

    void deleteHobby(long id) throws IOException;

    void initHobbyOffers();
}
