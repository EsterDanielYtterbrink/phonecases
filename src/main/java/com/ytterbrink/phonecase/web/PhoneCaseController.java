package com.ytterbrink.phonecase.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.ytterbrink.phonecase.domain.data.PhoneCase;
import com.ytterbrink.phonecase.domain.web_ports.AllPhoneCasesFacade;
import com.ytterbrink.phonecase.domain.web_ports.CreatePhoneCaseFacade;
import com.ytterbrink.phonecase.domain.web_ports.FindCasesByPhoneNameFacade;
import com.ytterbrink.phonecase.domain.web_ports.parameters.PhoneCaseParameters;
import com.ytterbrink.phonecase.exceptions.NoMatchingPhoneException;
import com.ytterbrink.phonecase.exceptions.NothingToSeeYetException;

@RestController
public class PhoneCaseController {

    private final AllPhoneCasesFacade allPhoneCases;
    private final FindCasesByPhoneNameFacade findCasesByPhoneName;
    private final CreatePhoneCaseFacade savePhoneCase;

    @Autowired
    public PhoneCaseController(
            AllPhoneCasesFacade allPhoneCases,
            FindCasesByPhoneNameFacade findCasesByPhoneName,
            CreatePhoneCaseFacade savePhoneCase) {
        this.allPhoneCases = allPhoneCases;
        this.findCasesByPhoneName = findCasesByPhoneName;
        this.savePhoneCase = savePhoneCase;
    }
    
    @PostMapping("/phoneCases")
    @ResponseStatus(HttpStatus.CREATED)
    public PhoneCase newPhoneCase(@RequestBody PhoneCaseParameters phoneCase){
        return savePhoneCase.createPhoneCase(phoneCase);
    }

    @GetMapping("/phoneCases")
    public List<PhoneCase> allPhoneCases()
            throws NothingToSeeYetException{
        return allPhoneCases.allPhoneCases();
    }

    @GetMapping("/phoneCases/{phoneName}")
    public List<PhoneCase> phoneCasesForPhone(@PathVariable String phoneName)
            throws NothingToSeeYetException, NoMatchingPhoneException {
        return findCasesByPhoneName.findCaseByPhone(phoneName);
    }

}
