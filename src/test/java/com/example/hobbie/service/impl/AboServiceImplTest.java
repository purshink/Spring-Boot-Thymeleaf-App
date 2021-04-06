package com.example.hobbie.service.impl;

import com.example.hobbie.handler.FailToDeleteException;
import com.example.hobbie.handler.NotFoundException;
import com.example.hobbie.model.entities.Abo;
import com.example.hobbie.model.repostiory.AboRepository;
import com.example.hobbie.service.AboService;
import com.example.hobbie.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.BDDAssumptions.given;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AboServiceImplTest {

    private AboService aboServiceTest;
    private AboRepository mockAboRepository;
    private UserService userService;
    private ModelMapper modelMapper;
    private Abo abo;

    @BeforeEach
    void setUp() {
        modelMapper = new ModelMapper();
        userService = mock(UserService.class);
        mockAboRepository = mock(AboRepository.class);
        abo = new Abo();
        abo.setId(1L);
        aboServiceTest = new AboServiceImpl(mockAboRepository, userService, modelMapper);

        //abo data

        //config mock
        when(mockAboRepository.save(Mockito.any(Abo.class)))
                .thenAnswer(i -> i.getArguments()[0]);

    }



    @Test
    void getUserAbos_should_work() {
        List<Abo> byId = new ArrayList<>();
        byId.add(abo);
        when(mockAboRepository.findByClientId(1L)).thenReturn(byId);
        aboServiceTest.getUserAbos(1L);
        assertEquals(1, aboServiceTest.getUserAbos(1L).size());
    }

    @Test
    void testUserNotFound() {
        Assertions.assertThrows(
                NotFoundException.class, () -> {
                    aboServiceTest.findAboById(null);
                   aboServiceTest.findAboId(null);
                   aboServiceTest.getClientDetails(null);
                   aboServiceTest.deleteAbo(null);
                }
        );
    }

    @Test
    void FailToDeleteException() {
        Assertions.assertThrows(
                FailToDeleteException.class, () -> {
                    List<Abo> byId = new ArrayList<>();
                    byId.add(abo);
                    mockAboRepository.save(abo);
                    when(mockAboRepository.findByHobbyId(1L)).thenReturn(byId);
                    aboServiceTest.findExcistingAbosWithHobbyId(1L);

                }
        );
    }

//    @Test
//    void findAboId() {
//    }

//    @Test
//    void updateAbosWithEntries() {
//
//    }
//
//
//    @Test
//    void getAbosPerBusiness() {
//    }
//
//    @Test
//    void getClientDetails() {
//    }
//
//    @Test
//    void findAboById() {
//    }
//
//    @Test
//    void getExcistingAbosForClient() {
//    }
//
//    @Test
//    void deleteAbo() {
//    }
//
//    @Test
//    void findExcistingAbosWithHobbyId() {
//    }
}