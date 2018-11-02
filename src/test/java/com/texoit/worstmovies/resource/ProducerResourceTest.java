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
public class ProducerResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testValidFindIntervals() throws Exception {
        mockMvc.perform(get("/producers/intervals/highestAndLowest"))
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.min[*].producer").isNotEmpty())
                .andExpect(jsonPath("$.min[*].interval").isNotEmpty())
                .andExpect(jsonPath("$.min[*].previousWin").isNotEmpty())
                .andExpect(jsonPath("$.min[*].followingWin").isNotEmpty())
                .andExpect(jsonPath("$.max[*].producer").isNotEmpty())
                .andExpect(jsonPath("$.max[*].interval").isNotEmpty())
                .andExpect(jsonPath("$.max[*].previousWin").isNotEmpty())
                .andExpect(jsonPath("$.max[*].followingWin").isNotEmpty())
                .andExpect(status().isOk());
    }

}
