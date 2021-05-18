package com.ytterbrink.phonecase.web;

import com.ytterbrink.phonecase.exceptions.NothingToSeeYetException;
import com.ytterbrink.phonecase.domain.Phone;
import com.ytterbrink.phonecase.domain.web_ports.CreatePhoneFacade;
import com.ytterbrink.phonecase.domain.web_ports.AllPhonesFacade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PhoneController {

    AllPhonesFacade allPhones;
    CreatePhoneFacade createPhone;

    @Autowired
    public PhoneController(AllPhonesFacade allPhones, CreatePhoneFacade createPhone) {
        this.createPhone = createPhone;
        this.allPhones = allPhones;
    }

    @PostMapping("/phones")
    @ResponseStatus(HttpStatus.CREATED)
    public Phone createPhone(@RequestBody Phone.PhoneParameters parameters){
        return createPhone.createPhone(parameters);
    }

    @GetMapping("/phones")
    public List<Phone> allPhones() throws NothingToSeeYetException {
        return allPhones.allPhones();
    }
}
