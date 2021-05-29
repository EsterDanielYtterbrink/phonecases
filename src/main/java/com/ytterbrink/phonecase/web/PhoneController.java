package com.ytterbrink.phonecase.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.ytterbrink.phonecase.domain.data.Phone;
import com.ytterbrink.phonecase.domain.web_ports.AllPhonesFacade;
import com.ytterbrink.phonecase.domain.web_ports.CreatePhoneFacade;
import com.ytterbrink.phonecase.domain.web_ports.parameters.PhoneParameters;
import com.ytterbrink.phonecase.exceptions.NoMatchingPhoneException;
import com.ytterbrink.phonecase.exceptions.NothingToSeeYetException;

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
