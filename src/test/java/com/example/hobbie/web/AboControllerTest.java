package com.example.hobbie.web;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class AboControllerTest {
    private static final String ABO_CONTROLLER_PREFIX = "/my-abos";

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(value = "user", roles = {"USER", "ADMIN"})
    public void testShowAllAbosOK() throws Exception {
        this.mockMvc.perform(get("/my-abos")).
                andExpect(view().name("abo/my-abos")).
                andExpect(status().isOk());
    }

    @Test
    @WithMockUser(value = "user", roles = {"USER", "ADMIN"})
    public void testShowAboOK() throws Exception {
        this.mockMvc.
                perform(get("/abo/{id}", 1L)).
                andExpect(status().isOk());
    }
    
    @Test
    @WithMockUser(value = "user", roles = {"USER", "ADMIN"})
    void deleteAboThrows() throws Exception {
        this.mockMvc.
                perform(post("/delete-abo/{id}", 1L)).
                andExpect(status().is(403));
    }
}