package com.example.hobbie.service.impl;
import com.example.hobbie.model.entities.UserRoleEntity;
import com.example.hobbie.model.entities.enums.UserRoleEnum;
import com.example.hobbie.model.repostiory.UserRoleRepository;
import com.example.hobbie.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserRoleServiceImpl implements UserRoleService {
    private final UserRoleRepository userRoleRepository;

    @Autowired
    public UserRoleServiceImpl(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public UserRoleEntity getUserRoleByEnumName(UserRoleEnum userRoleEnum){

        Optional<UserRoleEntity> role = this.userRoleRepository.findByRole(userRoleEnum);
        if(role.isPresent()) {
            return role.get();
        }
        else {
            throw new NullPointerException();
        }
    }


}
