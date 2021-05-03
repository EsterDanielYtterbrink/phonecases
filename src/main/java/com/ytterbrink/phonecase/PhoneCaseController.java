package com.ytterbrink.phonecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PhoneCaseController {

    @Autowired
    private PhoneCaseRepository phoneCaseRepository;

    public PhoneCaseController(PhoneCaseRepository phoneCaseRepository){
        this.phoneCaseRepository = phoneCaseRepository;
    }

    @GetMapping("/{phoneName}")
    public ResponseEntity<List<PhoneCase>> phoneCases(@PathVariable String phoneName) throws NothingToSeeYetException{
        List<PhoneCase> cases = phoneCaseRepository.findAll();
        if(cases.isEmpty()){
            throw new NothingToSeeYetException();
        }
        return ResponseEntity.ok(cases);
    }

}
