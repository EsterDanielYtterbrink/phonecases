package com.ytterbrink.phonecase.phone;

import com.ytterbrink.phonecase.exceptions.NothingToSeeYetException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PhoneController {

    @Autowired
    PhoneRepository phoneRepository;

    public PhoneController(PhoneRepository phoneRepository) {
        this.phoneRepository = phoneRepository;
    }

    @GetMapping("/phones")
    public ResponseEntity<List<Phone>> allPhones() throws NothingToSeeYetException {
        List<Phone> phones = phoneRepository.findAll();
        if(phones.isEmpty()){
            throw new NothingToSeeYetException();
        }
        return ResponseEntity.ok(phones);
    }
}
