package com.rooftopacademy.challenge.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.rooftopacademy.challenge.dto.response.GETIdResponseDTO;
import com.rooftopacademy.challenge.utils.UtilsTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class TextControllerTest {

    @Autowired
    MockMvc mockMvc;

    ObjectWriter writer;

    @BeforeEach
    void setup(){
        this.writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .registerModule(new JavaTimeModule())
                .writer()
                .withDefaultPrettyPrinter();
    }

    @Test
    @DisplayName("Correct text by ID")
    void getById() throws Exception{
        //Arrange
        GETIdResponseDTO responseDTO = UtilsTest.newResponse();
        String expectedJSON = this.writer.writeValueAsString(responseDTO);

        //Matchers / assert
        ResultMatcher expectedStatus = MockMvcResultMatchers.status().isOk();
        ResultMatcher expectedBody = MockMvcResultMatchers.content()
                .json(expectedJSON);

        //Request
        MockHttpServletRequestBuilder requestParam = MockMvcRequestBuilders
                .get("/text/{id}", 1);

        //Act and Assert
        this.mockMvc.perform(requestParam)
                .andDo(MockMvcResultHandlers.print())
                .andExpectAll(expectedStatus, expectedBody);
    }
}
