package com.texoit.worstmovies.resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MovieResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testValidFindWinners() throws Exception {
        mockMvc.perform(get("/movies/years/2000/winners"))
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$[*].id").isNotEmpty())
                .andExpect(jsonPath("$[*].year").isNotEmpty())
                .andExpect(jsonPath("$[*].studios").isNotEmpty())
                .andExpect(jsonPath("$[*].producers").isNotEmpty())
                .andExpect(jsonPath("$[*].winner").value(everyItem(is(true))))
                .andExpect(status().isOk());
    }

    @Test
    public void testEmptyFindWinners() throws Exception {
        mockMvc.perform(get("/movies/years/2020/winners"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testValidFindAll() throws Exception {
        mockMvc.perform(get("/movies"))
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$[*].id").isNotEmpty())
                .andExpect(jsonPath("$[*].year").isNotEmpty())
                .andExpect(jsonPath("$[*].studios").isNotEmpty())
                .andExpect(jsonPath("$[*].producers").isNotEmpty())
                .andExpect(jsonPath("$[*].winner").isNotEmpty())
                .andExpect(status().isOk());
    }

    @Test
    public void testValidFindById() throws Exception {
        mockMvc.perform(get("/movies/1"))
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.year").isNotEmpty())
                .andExpect(jsonPath("$.studios").isNotEmpty())
                .andExpect(jsonPath("$.producers").isNotEmpty())
                .andExpect(jsonPath("$.winner").isNotEmpty())
                .andExpect(status().isOk());
    }

    @Test
    public void testEmptyFindById() throws Exception {
        mockMvc.perform(get("/movies/9999"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testValidDelete() throws Exception {
        mockMvc.perform(delete("/movies/4"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testInvalidIdDelete() throws Exception {
        mockMvc.perform(delete("/movies/9999"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testWinnerDelete() throws Exception {
        mockMvc.perform(delete("/movies/1"))
                .andExpect(status().isNotAcceptable());
    }

    @Test
    public void testValidMultipleWinners() throws Exception {
        mockMvc.perform(get("/movies/years/multipleWinners"))
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.years[*].year").isNotEmpty())
                .andExpect(jsonPath("$.years[*].winnerCount").value(everyItem(greaterThan(1))))
                .andExpect(status().isOk());
    }

}
