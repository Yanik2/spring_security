package com.example.tests_part2;

import com.example.tests_part2.security.WithCustomMockUser;
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
class TestsPart2ApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithCustomMockUser(priority = "LOW")
    void contextLoads() throws Exception {
        mockMvc.perform(get("/demo"))
                .andExpect(status().isOk());
    }

}
