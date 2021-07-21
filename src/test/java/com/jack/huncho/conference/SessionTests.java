package com.jack.huncho.conference;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jack.huncho.conference.controller.SessionController;
import com.jack.huncho.conference.model.Session;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@AutoConfigureMockMvc
@SpringBootTest
public class SessionTests {

    @Autowired
    private SessionController controller;

    Session theSession = new Session("Exciting Show", "Talking about cool stuff");

    @Autowired
    private MockMvc mvc;

    @Test
    void contextLoads() {
        assertThat(controller).isNotNull();
    }

    @Test
    void getRequest() throws Exception {
        this.mvc.perform(get("/session/get/1"))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void getAllRequest() throws Exception {
        this.mvc.perform(get("/session/get"))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void postRequest() throws Exception {
        this.mvc.perform(post("/session/post")
        .contentType(MediaType.APPLICATION_JSON)
        .content(toJson(theSession)))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void deleteRequest() throws Exception {
        this.mvc.perform(delete("/session/delete/3"))
        .andExpect(status().is2xxSuccessful());
    }






    private String toJson(final Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }
}
