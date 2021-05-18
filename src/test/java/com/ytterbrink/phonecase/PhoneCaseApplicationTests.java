package com.ytterbrink.phonecase;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc

class PhoneCaseApplicationTests {

    // TODO
    // Find cases by phone model
    // Make it proper REST
    // Fix duplication of controller logic
    // Fix how id is calculated

    @Autowired
    private MockMvc mockMvc;

    private ResultActions createiPhone5() throws Exception {
        return  mockMvc.perform(post("/phones")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"newPhoneName\":\"iPhone5\"}}"));
    }

    @Test
    @DirtiesContext
    void createOnePhone() throws Exception{
        createiPhone5()
                .andExpect(status().isCreated());
    }

    @Test
    @DirtiesContext
    void emptyResultsForEmptyDatabase() throws Exception{
        createiPhone5();
        this.mockMvc.perform(get("/phoneCases/{phone}", "iPhone5"))
                .andDo(print())
                .andExpect(status().is(204))
               .andExpect( content().string(""));
    }

    @Test
    @DirtiesContext
    void createOnePhoneCase() throws Exception{
        createiPhone5();
        mockMvc.perform(post("/phoneCases")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"madeFor\":\"iPhone5\", \"name\":\"Pretty case\"}}"))
                .andExpect(status().isCreated());
    }

    @Test
    @DirtiesContext
    void getAllPhones() throws Exception{
        createiPhone5();
        this.mockMvc.perform(get("/phones"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"name\":\"iPhone5\"}]", false));
    }

    @Test
    @DirtiesContext
    void getAllPhoneCases(){
       // Create phone cases and get them
    }
}
