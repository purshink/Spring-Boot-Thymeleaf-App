package com.example.hobbie.service.impl;

import com.example.hobbie.model.entities.AppClient;
import com.example.hobbie.model.entities.BusinessOwner;
import com.example.hobbie.model.entities.UserRoleEntity;
import com.example.hobbie.model.entities.enums.GenderEnum;
import com.example.hobbie.model.entities.enums.UserRoleEnum;
import com.example.hobbie.model.repostiory.AppClientRepository;
import com.example.hobbie.model.repostiory.BusinessOwnerRepository;
import com.example.hobbie.model.repostiory.UserRoleRepository;
import com.example.hobbie.model.service.SignUpServiceModel;
import com.example.hobbie.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final ModelMapper modelMapper;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AppClientRepository appClientRepository;
    private final BusinessOwnerRepository businessOwnerRepository;

    @Autowired
    public UserServiceImpl(ModelMapper modelMapper, UserRoleRepository userRoleRepository,
                           PasswordEncoder passwordEncoder, AppClientRepository appClientRepository, BusinessOwnerRepository businessOwnerRepository) {
        this.modelMapper = modelMapper;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
        this.appClientRepository = appClientRepository;
        this.businessOwnerRepository = businessOwnerRepository;
    }

    @Override
    public void seedUsers() {


        UserRoleEntity userRoleEntity = new UserRoleEntity();
        userRoleEntity.setRole(UserRoleEnum.USER);
        UserRoleEntity userRole = userRoleRepository.save(userRoleEntity);
        UserRoleEntity userRoleEntity2 = new UserRoleEntity();
        userRoleEntity2.setRole(UserRoleEnum.ADMIN);
        UserRoleEntity adminRole = userRoleRepository.save(userRoleEntity2);
        UserRoleEntity userRoleEntity3 = new UserRoleEntity();
        userRoleEntity3.setRole(UserRoleEnum.BUSINESS_USER);
        UserRoleEntity businessRole = userRoleRepository.save(userRoleEntity3);

        //simple user
        if(appClientRepository.count() == 0) {
            AppClient user = new AppClient();
            user.setUsername("user");
            user.setPassword(this.passwordEncoder.encode("topsecret"));
            user.setRoles(List.of(userRole));
            user.setFullName("Nikoleta Doykova");
            user.setGender(GenderEnum.FEMALE);


            //admin
            AppClient admin = new AppClient();
            admin.setUsername("admin");
            admin.setPassword(this.passwordEncoder.encode("topsecret"));
            admin.setRoles(List.of(adminRole, userRole));
            admin.setFullName("Full name of admin here");
            appClientRepository.save(user);
            appClientRepository.save(admin);
        }
        if(businessOwnerRepository.count() == 0){
            //business_user
            BusinessOwner business_user = new BusinessOwner();
            business_user.setUsername("business");
            business_user.setPassword(this.passwordEncoder.encode("topsecret"));
            business_user.setRoles(List.of(businessRole));
            business_user.setBusinessName("My Business name");
            business_user.setAddress("My business address");

            businessOwnerRepository.save(business_user);
        }
    }

    @Override
    public boolean register(SignUpServiceModel signUpServiceModel) {

        //todo implement logic for register user
//        try {
////            AppClient appClient = this.modelMapper.map(signUpServiceModel, AppClient.class);
////            appClient.setRole(UserRoleEnum.USER);
////            this.userRepository.save(appClient);
//        }
//        catch (Exception e){
//            return false;
//        }

        return true;

    }

    @Override
    public BusinessOwner findCurrentUserBusinessOwner() {

        Optional<BusinessOwner> user = this.businessOwnerRepository.findByUsername(findCurrentUsername());
        if(user.isPresent()) {
            return user.get();
        }
        else {
            throw new NullPointerException();
        }

    }

    @Override
    public AppClient findCurrentUserAppClient() {
        Optional<AppClient> user = this.appClientRepository.findByUsername(findCurrentUsername());
        if(user.isPresent()) {
            return user.get();
        }
        else {
            throw new NullPointerException();
        }
    }

    @Override
    public String findCurrentUsername() {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = "";
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
            return username;
    }



}
