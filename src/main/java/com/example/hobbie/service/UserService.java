package com.example.hobbie.service;

import com.example.hobbie.model.entities.AppClient;
import com.example.hobbie.model.entities.BusinessOwner;
import com.example.hobbie.model.service.RegisterBusinessServiceModel;
import com.example.hobbie.model.service.SignUpServiceModel;

public interface UserService {
    void seedUsersAndUserRoles();

    boolean register(SignUpServiceModel signUpServiceModel);
    BusinessOwner findCurrentUserBusinessOwner();
    AppClient findCurrentUserAppClient();
    String findCurrentUsername();
    boolean registerBusiness(RegisterBusinessServiceModel map);
}
