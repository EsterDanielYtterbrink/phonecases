package com.ytterbrink.phonecase;

import com.ytterbrink.phonecase.phone.Phone;
import com.ytterbrink.phonecase.phone.PhoneRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc

class PhoneCaseApplicationTests {

    // TODO
    // Clean up
    // Find cases by phone model
    // Fix duplication of controller logic
    // Fix how id is calculated

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PhoneCaseRepository phoneCaseRepository;

   @Autowired
    private PhoneRepository phoneRepository;

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
                .andExpect( content().json("[{\"name\":\"ArtsyCase\",\"id\":"+artsy.getId()+"}]"));
    }

    @Test
    void getAllPhones() throws Exception{
        Phone iPhone5 = new Phone("iPhone5");
        phoneRepository.save(iPhone5);
        this.mockMvc.perform(get("/phones/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"name\":\"iPhone5\",\"id\":"+iPhone5.getId()+"}]", false));


    }
}
