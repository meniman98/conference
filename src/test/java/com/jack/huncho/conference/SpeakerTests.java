package com.jack.huncho.conference;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.jack.huncho.conference.controller.SpeakerController;
import com.jack.huncho.conference.model.Speaker;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@SpringBootTest
public class SpeakerTests {

    @Autowired
    private SpeakerController controller;

    Speaker speaker = new Speaker(3, "Quavo Huncho");

    @Autowired
    private MockMvc mvc;

    // check if controller isn't null
    @Test
    void contextLoads() {
        assertThat(controller).isNotNull();
    }

    // get request and receive 2 JSON objects
    @Test
    void getRequest() throws Exception {
        this.mvc.perform(get(Constants.SPEAKER_GET))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    // check if get request works and that we get back the desired object
    // by checking that the name is Cactus Jack
    @Test
    void getOneRequest() throws Exception {
        this.mvc.perform(get("/speaker/get/1"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.name", Matchers.is("Cactus Jack")));
    }

    // using an ID that doesn't exist should return a 404
    @Test
    void failingGetOneRequest() throws Exception {
        this.mvc.perform(get("/speaker/get/1789"))
                .andExpect(status().isNotFound());
    }

    // overloaded function
    @Test
    void failingGetOneRequest(int id) throws Exception {
        if (id == 0) {
            id = 1789;
        }
        this.mvc.perform(get("/speaker/get/" + id))
                .andExpect(status().isNotFound());
    }

    @Test
    void postRequestEmpty() throws Exception {
        this.mvc.perform(post(Constants.SPEAKER_POST))
                .andExpect(status().isBadRequest());
    }

    @Test
    void emptyObjectPutRequest() throws Exception {
        this.mvc.perform(put("/speaker/put/3"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void filledObjectPutRequest() throws Exception {
        this.mvc.perform(put("/speaker/put/3")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(speaker)))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void mismatchedIdPutRequest() throws Exception {
        this.mvc.perform(put("/speaker/put/39")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(speaker)))
                .andExpect(status().isNotFound());
    }

    @Test
    void postRequestWithObject() throws Exception {
        this.mvc.perform(post(Constants.SPEAKER_POST)
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(speaker)))
                .andExpect(status().is2xxSuccessful());

    }

    @Test
    void deleteRequest() throws Exception {
        this.mvc.perform(delete("/speaker/delete/3"))
                .andExpect(status().is2xxSuccessful());
        failingGetOneRequest(3);
    }

    private String toJson(final Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }


}
