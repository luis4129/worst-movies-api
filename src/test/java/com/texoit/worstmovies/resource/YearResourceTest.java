package com.texoit.worstmovies.resource;

import com.texoit.worstmovies.exception.EmptySearchException;
import com.texoit.worstmovies.service.MovieService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(YearResource.class)
public class YearResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MovieService movieService;

    @Test
    public void testValidMultipleWinners() throws Exception {
        mockMvc.perform(get("/years/multipleWinners")).andExpect(status().isOk());
    }

    @Test
    public void testEmptyMultipleWinners() throws Exception {
        when(movieService.findYearsWithMultipleWinners()).thenThrow(EmptySearchException.class);
        mockMvc.perform(get("/years/multipleWinners")).andExpect(status().isNotFound());
    }

}
