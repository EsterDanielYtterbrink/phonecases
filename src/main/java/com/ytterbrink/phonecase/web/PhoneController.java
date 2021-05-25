package com.ytterbrink.phonecase.web;

import com.ytterbrink.phonecase.domain.PhoneParameters;
import com.ytterbrink.phonecase.exceptions.NoMatchingPhoneException;
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

    private final AllPhonesFacade allPhones;
    private final CreatePhoneFacade createPhone;

    @Autowired
    public PhoneController(
            AllPhonesFacade allPhones,
            CreatePhoneFacade createPhone) {
        this.createPhone = createPhone;
        this.allPhones = allPhones;
    }

    @PostMapping("/phones")
    @ResponseStatus(HttpStatus.CREATED)
    public Phone createPhone(@RequestBody PhoneParameters parameters)
            throws NoMatchingPhoneException {
        return createPhone.createPhone(parameters);
    }

    @GetMapping("/phones")
    public List<Phone> allPhones() throws NothingToSeeYetException {
        return allPhones.allPhones();
    }
}
