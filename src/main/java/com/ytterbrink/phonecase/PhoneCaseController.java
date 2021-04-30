package com.ytterbrink.phonecase;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PhoneCaseController {

    private static final String template = " ";

    @GetMapping("/")
    public String phoneCases(){
        return "";
    }

}
