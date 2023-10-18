package com.example.authorizatioin_tests;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
class AuthorizatioinTestsApplicationTests {


    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    void contextLoads() throws Exception {
        mockMvc.perform(get("/demo"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(authorities = "read")
    void testHello() throws Exception {
        mockMvc.perform(post("/hello"))
                .andExpect(status().isOk());
    }

}
