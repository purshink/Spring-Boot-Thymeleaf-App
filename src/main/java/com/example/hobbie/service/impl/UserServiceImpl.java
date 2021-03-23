package com.example.hobbie.service.impl;

import com.example.hobbie.model.entities.AppClient;
import com.example.hobbie.model.entities.BusinessOwner;
import com.example.hobbie.model.entities.UserEntity;
import com.example.hobbie.model.entities.UserRoleEntity;
import com.example.hobbie.model.entities.enums.GenderEnum;
import com.example.hobbie.model.entities.enums.UserRoleEnum;
import com.example.hobbie.model.repostiory.AppClientRepository;
import com.example.hobbie.model.repostiory.BusinessOwnerRepository;
import com.example.hobbie.model.repostiory.UserRepository;
import com.example.hobbie.model.repostiory.UserRoleRepository;
import com.example.hobbie.model.service.RegisterBusinessServiceModel;
import com.example.hobbie.model.service.SignUpServiceModel;
import com.example.hobbie.service.UserRoleService;
import com.example.hobbie.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AppClientRepository appClientRepository;
    private final BusinessOwnerRepository businessOwnerRepository;
    private final UserRoleService userRoleService;
    private SessionRegistry sessionRegistry;

    @Autowired
    public UserServiceImpl(ModelMapper modelMapper, UserRepository userRepository, UserRoleRepository userRoleRepository,
                           PasswordEncoder passwordEncoder, AppClientRepository appClientRepository, BusinessOwnerRepository businessOwnerRepository, UserRoleService userRoleService, SessionRegistry sessionRegistry) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
        this.appClientRepository = appClientRepository;
        this.businessOwnerRepository = businessOwnerRepository;
        this.userRoleService = userRoleService;
        this.sessionRegistry = sessionRegistry;
    }

    @Override
    public void seedUsersAndUserRoles() {


        //simple user
        if(appClientRepository.count() == 0) {
            UserRoleEntity userRoleEntity = new UserRoleEntity();
            userRoleEntity.setRole(UserRoleEnum.USER);
            UserRoleEntity userRole = userRoleRepository.save(userRoleEntity);
            UserRoleEntity userRoleEntity2 = new UserRoleEntity();
            userRoleEntity2.setRole(UserRoleEnum.ADMIN);
            UserRoleEntity adminRole = userRoleRepository.save(userRoleEntity2);
            AppClient user = new AppClient();
            user.setUsername("user");
            user.setEmail("n13@gmail.com");
            user.setPassword(this.passwordEncoder.encode("topsecret"));
            user.setRoles(List.of(userRole));
            user.setFullName("Nikoleta Doykova");
            user.setGender(GenderEnum.FEMALE);


            //admin
            AppClient admin = new AppClient();
            admin.setUsername("admin");
            admin.setEmail("n11@gamil.com");
            admin.setPassword(this.passwordEncoder.encode("topsecret"));
            admin.setRoles(List.of(adminRole, userRole));
            admin.setFullName("Full name of admin here");
            admin.setGender(GenderEnum.FEMALE);
            appClientRepository.save(user);
            appClientRepository.save(admin);
        }
        if(businessOwnerRepository.count() == 0){

            UserRoleEntity userRoleEntity3 = new UserRoleEntity();
            userRoleEntity3.setRole(UserRoleEnum.BUSINESS_USER);
            UserRoleEntity businessRole = userRoleRepository.save(userRoleEntity3);
            //business_user
            BusinessOwner business_user = new BusinessOwner();
            business_user.setUsername("business");
            business_user.setEmail("n10@gamil.com");
            business_user.setPassword(this.passwordEncoder.encode("topsecret"));
            business_user.setRoles(List.of(businessRole));
            business_user.setBusinessName("My Business name");
            business_user.setAddress("My business address");

            businessOwnerRepository.save(business_user);
        }
    }

    @Override
    public boolean register(SignUpServiceModel signUpServiceModel) {


        try {
            UserRoleEntity userRole = this.userRoleService.getUserRoleByEnumName(UserRoleEnum.USER);
            AppClient appClient = this.modelMapper.map(signUpServiceModel, AppClient.class);
            appClient.setRoles(List.of(userRole));
            appClient.setPassword(this.passwordEncoder.encode(signUpServiceModel.getPassword()));
            appClientRepository.save(appClient);
        }
        catch (Exception e){
            return false;
        }

        return true;

    }

    @Override
    public boolean registerBusiness(RegisterBusinessServiceModel registerBusinessServiceModel) {
        try {
            UserRoleEntity businessUserRole = this.userRoleService.getUserRoleByEnumName(UserRoleEnum.BUSINESS_USER);
            BusinessOwner businessOwner = this.modelMapper.map(registerBusinessServiceModel, BusinessOwner.class);
            businessOwner.setRoles(List.of(businessUserRole));
            businessOwner.setPassword(this.passwordEncoder.encode(registerBusinessServiceModel.getPassword()));
            businessOwnerRepository.save(businessOwner);
        }
        catch (Exception e){
            return false;
        }

        return true;
    }

    @Override
    public void saveUpdatedUser(BusinessOwner businessOwner) {
        this.businessOwnerRepository.save(businessOwner);
    }

    @Override
    public void saveUpdatedUserClient(AppClient appClient) {
        this.appClientRepository.save(appClient);
    }

    @Override
    public UserEntity findUserById(Long userId) {

        Optional<UserEntity> user = this.userRepository.findById(userId);
        if(user.isPresent()) {
            return user.get();
        }
        else {
            throw new NullPointerException();
        }



    }

    @Override
    public void deleteUser(Long id) {
        try {
            UserEntity user = userRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
            expireUserSessions(user.getUsername());
            userRepository.delete(user);
        }
        catch (Exception e){
            //TODO HANDLING ERRORS FOR DELETING PARENT - USER;
        }


    }

    @Override
    public BusinessOwner findBusinessOwnerById(long id) {
        Optional<BusinessOwner> businessOwner = this.businessOwnerRepository.findById(id);

        if(businessOwner.isPresent()) {
            return businessOwner.get();
        }
        else {
            throw new NullPointerException();
        }

    }

    public void expireUserSessions(String username) {
                if (findCurrentUsername().equals(username)) {
                    SecurityContextHolder.clearContext();
                }
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
