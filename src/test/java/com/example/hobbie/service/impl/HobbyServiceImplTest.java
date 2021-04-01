package com.example.hobbie.service.impl;

import com.example.hobbie.model.entities.AppClient;
import com.example.hobbie.model.entities.BusinessOwner;
import com.example.hobbie.model.entities.UserEntity;
import com.example.hobbie.model.repostiory.*;
import com.example.hobbie.model.service.RegisterBusinessServiceModel;
import com.example.hobbie.model.service.SignUpServiceModel;
import com.example.hobbie.service.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.Mockito.mock;

class HobbyServiceImplTest {
    private HobbyRepository mockHobbyRepository;
    private AboRepository aboRepository;
    private CategoryRepository mockCategoryRepository;
    private HobbyService hobbyServiceToTest;
    private CategoryService categoryServiceTest;

    private UserService userServiceTest;
    private UserRepository mockUserRepository;
    private AppClientRepository mockAppClientRepository;
    private BusinessOwnerRepository mockBusinessOwnerRepository;
    private UserRoleService mockUserRoleService;
    private UserEntity userEntity;
    private AppClient appClient;
    private BusinessOwner businessOwner;
    private SignUpServiceModel signUpServiceModel;
    private RegisterBusinessServiceModel registerBusinessServiceModel;
    private  PasswordEncoder mockPasswordEncoder;
    private LocationService locationServiceTest;
    private AboService aboServiceTest;
    private ShoppingCartService shoppingCartServiceTest;
    private CloudinaryService cloudinaryServiceTest;
    private ModelMapper modelMapper;
    private EntryService entryServiceTest;
    private EntryRepository mockEntryRepository;




    @BeforeEach
    void setUp() {
        mockEntryRepository = mock(EntryRepository.class);
       mockPasswordEncoder = mock(BCryptPasswordEncoder.class);
       aboRepository = mock(AboRepository.class);
        mockHobbyRepository= mock(HobbyRepository.class);
        mockCategoryRepository = mock(CategoryRepository.class);
        categoryServiceTest = new CategoryServiceImpl(mockCategoryRepository);
        mockUserRepository = mock(UserRepository.class);
        mockCategoryRepository = mock(CategoryRepository.class);
        mockAppClientRepository = mock(AppClientRepository.class);
        mockBusinessOwnerRepository = mock(BusinessOwnerRepository.class);
        modelMapper = new ModelMapper();
        userServiceTest = new UserServiceImpl(modelMapper, mockUserRepository, mockPasswordEncoder, mockAppClientRepository,
                mockBusinessOwnerRepository, mockUserRoleService);
        aboServiceTest = new AboServiceImpl(aboRepository,userServiceTest,modelMapper);
        entryServiceTest = new EntryServiceImpl(mockEntryRepository,modelMapper);
        shoppingCartServiceTest = new ShoppingCartServiceImpl(userServiceTest,aboServiceTest,entryServiceTest);
        hobbyServiceToTest = new HobbyServiceImpl(modelMapper,mockHobbyRepository,mockCategoryRepository,categoryServiceTest,userServiceTest,locationServiceTest,
                aboServiceTest,shoppingCartServiceTest,cloudinaryServiceTest);
    }

    @Test
    void createHobby() {
    }

    @Test
    void getAllHobbyOffers() {
    }

    @Test
    void findHobbieById() {
    }

    @Test
    void saveUpdatedHobby() {
    }

    @Test
    void deleteHobby() {
    }

    @Test
    void initHobbyOffers() {
    }

    @Test
    void findHobbyMatches() {
    }

    @Test
    void getHobbyMatches() {
    }

    @Test
    void saveHobbyForClient() {
    }

    @Test
    void removeHobbyForClient() {
    }

    @Test
    void isHobbySaved() {
    }

    @Test
    void findSavedHobbies() {
    }
}