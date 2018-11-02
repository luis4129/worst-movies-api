package com.texoit.worstmovies.resource;

import com.texoit.worstmovies.exception.EmptySearchException;
import com.texoit.worstmovies.exception.WinnerDeleteException;
import com.texoit.worstmovies.service.MovieService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(MovieResource.class)
public class MovieResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MovieService movieService;

    @Test
    public void testValidFindWinners() throws Exception {
        mockMvc.perform(get("/movies/years/2000/winners")).andExpect(status().isOk());
    }

    @Test
    public void testEmptyFindWinners() throws Exception {
        given(movieService.findWinnersByYear(2020)).willThrow(EmptySearchException.class);
        mockMvc.perform(get("/movies/years/2020/winners")).andExpect(status().isNotFound());
    }

    @Test
    public void testValidFindAll() throws Exception {
        mockMvc.perform(get("/movies")).andExpect(status().isOk());
    }

    @Test
    public void testEmptyFindAll() throws Exception {
        given(movieService.findAll()).willThrow(EmptySearchException.class);
        mockMvc.perform(get("/movies")).andExpect(status().isNotFound());
    }

    @Test
    public void testValidFindById() throws Exception {
        mockMvc.perform(get("/movies/1")).andExpect(status().isOk());
    }

    @Test
    public void testEmptyFindById() throws Exception {
        given(movieService.findById(9999L)).willThrow(EmptySearchException.class);
        mockMvc.perform(get("/movies/9999")).andExpect(status().isNotFound());
    }

    @Test
    public void testValidDelete() throws Exception {
        mockMvc.perform(delete("/movies/4")).andExpect(status().isNoContent());
    }

    @Test
    public void testInvalidIdDelete() throws Exception {
        doThrow(new EmptySearchException()).when(movieService).delete(9999L);
        mockMvc.perform(delete("/movies/9999")).andExpect(status().isNotFound());
    }

    @Test
    public void testWinnerDelete() throws Exception {
        doThrow(new WinnerDeleteException()).when(movieService).delete(1L);
        mockMvc.perform(delete("/movies/1")).andExpect(status().isNotAcceptable());
    }

    @Test
    public void testValidMultipleWinners() throws Exception {
        mockMvc.perform(get("/movies/years/multipleWinners")).andExpect(status().isOk());
    }

    @Test
    public void testEmptyMultipleWinners() throws Exception {
        when(movieService.findYearsWithMultipleWinners()).thenThrow(EmptySearchException.class);
        mockMvc.perform(get("/movies/years/multipleWinners")).andExpect(status().isNotFound());
    }

}
