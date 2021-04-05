package com.example.hobbie.web;

import com.example.hobbie.model.repostiory.AboRepository;
import com.example.hobbie.model.repostiory.EntryRepository;
import com.example.hobbie.model.repostiory.HobbyRepository;
import com.example.hobbie.model.repostiory.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class AboControllerTest {
    private static final String ABO_CONTROLLER_PREFIX = "/my-abos";

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AboRepository aboRepository;
    @Autowired
    private HobbyRepository hobbyRepository;
    @Autowired
    private EntryRepository entryRepository;
    @Autowired
    private ModelMapper modelMapper;




    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void showAllAbos() {
    }

    @Test
    @WithMockUser(value = "pesho", roles = {"USER", "ADMIN"})
    void showAbo_should_work() throws Exception {

//        mockMvc
//                .perform(MockMvcRequestBuilders
//
//                        .get("/abo/1"))
//
//                .andExpect(status().isOk())
//
//                .andExpect(view().name("abo"))
//
//                .andExpect(model().attributeExists("clientDetails"))
//                .andExpect(model().attributeExists("aboDetails"))
//                .andExpect(model().attributeExists("entries"))
//                .andExpect(model().attributeExists("aboId"))
//                .andExpect(model().attributeExists("hobbyName"));
    }



    @Test
    void updateAbo() {
    }

    @Test
    void deleteAbo() {
    }
}