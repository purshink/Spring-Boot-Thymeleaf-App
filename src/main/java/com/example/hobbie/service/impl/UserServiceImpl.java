package com.example.hobbie.service.impl;

import com.example.hobbie.model.entities.UserEntity;
import com.example.hobbie.model.entities.UserRoleEntity;
import com.example.hobbie.model.entities.enums.UserRoleEnum;
import com.example.hobbie.model.repostiory.UserRepository;
import com.example.hobbie.model.repostiory.UserRoleRepository;
import com.example.hobbie.model.service.SignUpServiceModel;
import com.example.hobbie.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, UserRoleRepository userRoleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
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
        if(userRepository.count() == 0) {
            UserEntity user = new UserEntity();
            user.setUsername("user");
            user.setPassword(this.passwordEncoder.encode("topsecret"));
            user.setRoles(List.of(userRole));

            //admin
            UserEntity admin = new UserEntity();
            admin.setUsername("admin");
            admin.setPassword(this.passwordEncoder.encode("topsecret"));
            admin.setRoles(List.of(adminRole, userRole));
            //business_user
            UserEntity business_user = new UserEntity();
            business_user.setUsername("business");
            business_user.setPassword(this.passwordEncoder.encode("topsecret"));
            business_user.setRoles(List.of(businessRole));

            userRepository.save(user);
            userRepository.save(admin);
            userRepository.save(business_user);
        }
    }

    @Override
    public boolean register(SignUpServiceModel signUpServiceModel) {
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


}
