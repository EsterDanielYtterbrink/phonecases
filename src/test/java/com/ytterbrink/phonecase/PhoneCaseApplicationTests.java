package com.ytterbrink.phonecase;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class PhoneCaseApplicationTests {

    private static final String PHONE = "iPhone5";
    private static final String SIMILAR_PHONE = "iPhoneSE";
    private static final String DIFFERENT_PHONE = "iPhone11";
    private static final String PHONE_PATH = "/phones";
    private static final String CASES_PATH = "/phoneCases";
    private static final String CASE_PATH = "/phoneCases/{phone}";
    private static final String NEW_PHONE_KEY = "newPhoneName";
    private static final String NAME_KEY = "name";
    private static final String SIMILAR_PHONE_KEY = "similarPhoneName";
    private static final String DELIM = ",";

    @Autowired
    private MockMvc mockMvc;

    private static String JSONKeyValue(String key, String value){
        return String.format("\"%1$s\":\"%2$s\"", key, value);
    }

    private static String JSONObject(CharSequence... parts){
        return String.format("{%s}", String.join(DELIM, parts ));
    }

    private static String JSONArray(CharSequence... parts){
        return String.format("[%s]", String.join(DELIM, parts ));
    }

    private ResultActions createPhone(String name) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders.post(PHONE_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSONObject(
                        JSONKeyValue(NEW_PHONE_KEY, name)
                )));
    }
    
    private ResultActions createPhone(String name, String similarPhone) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders.post(PHONE_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSONObject(
                        JSONKeyValue(NEW_PHONE_KEY, name),
                        JSONKeyValue(SIMILAR_PHONE_KEY, similarPhone)
                        )));
    }

    @Test
    @DirtiesContext
    void integrationTest() throws Exception{
        // Create a phone
        createPhone(PHONE).andExpect(MockMvcResultMatchers.status().isCreated());
        // Get the newly created phone
        this.mockMvc.perform(MockMvcRequestBuilders.get(PHONE_PATH))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(
                        JSONArray(
                                JSONObject(
                                        JSONKeyValue(NAME_KEY, PHONE)
                                )
                        ), false));
        //List all cases when there are none
        this.mockMvc.perform(MockMvcRequestBuilders.get(CASE_PATH, PHONE))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNoContent())
               .andExpect( MockMvcResultMatchers.content().string(""));

        //Create a first phone case
        mockMvc.perform(MockMvcRequestBuilders.post(CASES_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"madeFor\":\""+ PHONE +"\", \"name\":\"Pretty case\"}}"))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        //Create more phones
        createPhone(SIMILAR_PHONE, PHONE);
        createPhone(DIFFERENT_PHONE);

        // And more phone cases
        mockMvc.perform(MockMvcRequestBuilders.post(CASES_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSONObject(
                        JSONKeyValue("madeFor", DIFFERENT_PHONE),
                        JSONKeyValue(NAME_KEY, "Big case")
                        )
                ))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        // Check that we can get cases that fit iPhoneSE also when they are created for iPhone5
        this.mockMvc.perform(MockMvcRequestBuilders.get(CASES_PATH))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(
                        "[{\"name\":\"Pretty case\"}, {\"name\":\"Big case\"}]", false));
        this.mockMvc.perform(MockMvcRequestBuilders.get(CASE_PATH, SIMILAR_PHONE))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect( MockMvcResultMatchers.content().string(
                        "[{\"id\":1,\"name\":\"Pretty case\"}]"));
    }
}
