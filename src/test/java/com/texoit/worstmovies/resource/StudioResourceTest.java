package com.texoit.worstmovies.resource;

import com.texoit.worstmovies.exception.EmptySearchException;
import com.texoit.worstmovies.service.MovieService;
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
@WebMvcTest(StudioResource.class)
public class StudioResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MovieService movieService;

    @MockBean
    private StudioService studioService;

    @Test
    public void testValidFindStudios() throws Exception {
        mockMvc.perform(get("/studios")).andExpect(status().isOk());
    }

    @Test
    public void testEmptyFindStudios() throws Exception {
        given(studioService.findAll()).willThrow(EmptySearchException.class);
        mockMvc.perform(get("/studios")).andExpect(status().isNotFound());
    }

}
