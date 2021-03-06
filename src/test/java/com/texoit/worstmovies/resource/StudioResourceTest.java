package com.texoit.worstmovies.resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class StudioResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testValidFindStudios() throws Exception {
        mockMvc.perform(get("/studios"))
                .andExpect(jsonPath("$.studios[*].name").isNotEmpty())
                .andExpect(jsonPath("$.studios[*].winCount").isNotEmpty())
                .andExpect(status().isOk());
    }

}
