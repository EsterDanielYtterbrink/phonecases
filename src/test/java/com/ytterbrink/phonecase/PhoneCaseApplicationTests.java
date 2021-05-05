package com.ytterbrink.phonecase;

import com.ytterbrink.phonecase.domain.Phone;
import com.ytterbrink.phonecase.data.repositories.PhoneRepository;
import com.ytterbrink.phonecase.domain.PhoneShape;
import com.ytterbrink.phonecase.data.repositories.PhoneShapeRepository;
import com.ytterbrink.phonecase.domain.PhoneCase;
import com.ytterbrink.phonecase.data.repositories.PhoneCaseRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

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
    // Use rest to add things to remove repositories from this
    // Fix duplication of controller logic
    // Fix how id is calculated

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PhoneCaseRepository phoneCaseRepository;

   @Autowired
    private PhoneRepository phoneRepository;
   @Autowired
    private PhoneShapeRepository phoneShapeRepository;

    @Test
    @DirtiesContext
    void emptyResultsForEmptyDatabase() throws Exception{
        mockMvc.perform(post("/phones")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"iPhone5\"}"))
                .andExpect(status().isCreated());

       this.mockMvc.perform(get("/{}", "iPhone5"))
                .andDo(print())
                .andExpect(status().is(204))
               .andExpect( content().string(""));
    }

    @Test
    @DirtiesContext
    void canCreatePhoneShapeAndFindItById() throws Exception {
        mockMvc.perform(post("/phones")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"iPhone5\", \"phoneShape\":{\"name\":\"smallPhone\"}}"))
                .andExpect(status().isCreated());
        mockMvc.perform(get("/phoneshapes")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().json("[{\"name\":\"smallPhone\"}]"));


    }

    @Test
    @DirtiesContext
    void getResultsForiPhone11() throws Exception {
        PhoneShape iPhoneBig = new PhoneShape();
        Phone iPhone11 = new Phone("iPhone11", iPhoneBig);
        phoneRepository.save(iPhone11);

        PhoneCase artsy = new PhoneCase("ArtsyCase");
        PhoneCase plain = new PhoneCase("Plain");
        artsy.setPhoneShape(iPhoneBig);
        phoneCaseRepository.save(artsy);
        phoneCaseRepository.save(plain);

        this.mockMvc.perform(get("/{}", "iPhone11"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect( content().json("[{\"name\":\"ArtsyCase\",\"id\":"+artsy.getId()+"}]"));
    }

    @Test
    @DirtiesContext
    void getAllPhones() throws Exception{
        mockMvc.perform(post("/phones")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"iPhone5\"}"))
                .andExpect(status().isCreated());

        this.mockMvc.perform(get("/phones/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"name\":\"iPhone5\"}]", false));


    }
    @Test
    @DirtiesContext
    void getAllPhoneCases() throws Exception{
        mockMvc.perform(post("/phonecases")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"ArtsyCase\"}")).andExpect(status().isCreated());
        mockMvc.perform(post("/phonecases")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"PlainCase\"}")).andExpect(status().isCreated());

        mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"name\":\"ArtsyCase\"},{\"name\":\"PlainCase\"}]", false));


    }
}
