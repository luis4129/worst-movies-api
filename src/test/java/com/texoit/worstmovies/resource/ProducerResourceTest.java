package com.texoit.worstmovies.resource;

import com.texoit.worstmovies.exception.EmptySearchException;
import com.texoit.worstmovies.service.MovieService;
import com.texoit.worstmovies.service.ProducerService;
import com.texoit.worstmovies.service.StudioService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ProducerResource.class)
public class ProducerResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MovieService movieService;

    @MockBean
    private ProducerService producerService;

    @Test
    public void testValidFindIntervals() throws Exception {
        mockMvc.perform(get("/producers/intervals/highestAndLowest")).andExpect(status().isOk());
    }

    @Test
    public void testEmptyFindHighestIntervals() throws Exception {
        given(producerService.findHighestAndLowestWinInterval()).willThrow(EmptySearchException.class);
        mockMvc.perform(get("/producers/intervals/highestAndLowest")).andExpect(status().isNotFound());
    }

    @Test
    public void testEmptyFindLowestIntervals() throws Exception {
        given(producerService.findHighestAndLowestWinInterval()).willThrow(EmptySearchException.class);
        mockMvc.perform(get("/producers/intervals/highestAndLowest")).andExpect(status().isNotFound());
    }

}
