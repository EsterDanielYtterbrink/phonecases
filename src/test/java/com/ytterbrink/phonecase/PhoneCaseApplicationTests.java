package com.ytterbrink.phonecase;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc

class PhoneCaseApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PhoneCaseRepository phoneCaseRepository;

    @Test
    void emptyResultsForEmptyDatabase() throws Exception{
       this.mockMvc.perform(get("/{}", "iphone"))
                .andDo(print())
                .andExpect(status().is(204))
               .andExpect( content().string(""));
    }

    @Test
    void getResultsForiPhone11() throws Exception {
        PhoneCase artsy = new PhoneCase();
        artsy.setName("ArtsyCase");
        phoneCaseRepository.save(artsy);
        this.mockMvc.perform(get("/{}", "iPhone11"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect( content().string("[{\"name\":\"ArtsyCase\",\"id\":1}]"));
    }

}
