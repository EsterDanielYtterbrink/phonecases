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

    @Autowired
    private MockMvc mockMvc;

    private ResultActions createPhone(String name) throws Exception {
        return mockMvc.perform(post("/phones")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"newPhoneName\":\""+name+"\"}}"));
    }
    private ResultActions createPhone(String name, String similarPhone) throws Exception {
        return mockMvc.perform(post("/phones")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"newPhoneName\":\""+name+"\",\"similarPhoneName\":\""+similarPhone+"\" }"));
    }
    private ResultActions createiPhone5() throws Exception {
        return  createPhone("iPhone5");
    }

    @Test
    @DirtiesContext
    void integrationTest() throws Exception{
        // Create a phone
        createiPhone5()
                .andExpect(status().isCreated());

        // Get the newly created phone
        this.mockMvc.perform(get("/phones"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"name\":\"iPhone5\"}]", false));

        //List all cases when there are none
        this.mockMvc.perform(get("/phoneCases/{phone}", "iPhone5"))
                .andDo(print())
                .andExpect(status().is(204))
               .andExpect( content().string(""));

        //Create a first phone case
        mockMvc.perform(post("/phoneCases")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"madeFor\":\"iPhone5\", \"name\":\"Pretty case\"}}"))
                .andExpect(status().isCreated());

        //Create more phones
        createPhone("iPhoneSE", "iPhone5");
        createPhone("iPhone11");

        // And more phone cases
        mockMvc.perform(post("/phoneCases")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"madeFor\":\"iPhone11\", \"name\":\"Big case\"}}"))
                .andExpect(status().isCreated());

        // Check that we can get cases that fit iPhoneSE also when they are created for iPhone5
        this.mockMvc.perform(get("/phoneCases"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"name\":\"Pretty case\"}, {\"name\":\"Big case\"}]", false));
        this.mockMvc.perform(get("/phoneCases/{phone}", "iPhoneSE"))
                .andDo(print())
                .andExpect(status().is(200))
                .andExpect( content().string("[{\"id\":1,\"name\":\"Pretty case\"}]"));
    }
}
