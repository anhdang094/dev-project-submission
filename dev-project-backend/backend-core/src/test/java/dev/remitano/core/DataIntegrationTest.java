package dev.remitano.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.remitano.core.models.User;
import dev.remitano.core.repository.UserRepository;
import dev.remitano.core.rest.VideoController;
import dev.remitano.infrastructure.dto.request.AuthenDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.Assert.assertSame;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(VideoController.class)
public class DataIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testGetApi() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get("/api/videos")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.videos").exists());
    }


    @Test
    public void registrationWorksThroughAllLayers() throws Exception {
        AuthenDto user = new AuthenDto();
        user.setEmail("test@gmail.com");
        user.setPassword("abc@123");

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/users/signup")
                .contentType(MediaType.TEXT_PLAIN)
                .content(objectMapper.writeValueAsString(user));

        mvc.perform(builder)
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());

        User userEntity = userRepository.findFirstByEmail("test@gmail.com");
        assertSame(userEntity.getEmail(), "test@gmail.com");
    }

}
