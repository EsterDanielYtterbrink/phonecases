package com.ytterbrink.phonecase.web;

import com.ytterbrink.phonecase.domain.web_ports.AllPhoneCasesFacade;
import com.ytterbrink.phonecase.domain.web_ports.FindCasesByPhoneNameFacade;
import com.ytterbrink.phonecase.domain.web_ports.CreatePhoneCaseFacade;
import com.ytterbrink.phonecase.exceptions.NoMatchingPhoneException;
import com.ytterbrink.phonecase.exceptions.NothingToSeeYetException;
import com.ytterbrink.phonecase.domain.PhoneCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PhoneCaseController {

    @Autowired
    public PhoneCaseController(AllPhoneCasesFacade allPhoneCases, FindCasesByPhoneNameFacade findCasesByPhoneName, CreatePhoneCaseFacade savePhoneCase) {
        this.allPhoneCases = allPhoneCases;
        this.findCasesByPhoneName = findCasesByPhoneName;
        this.savePhoneCase = savePhoneCase;
    }

    private AllPhoneCasesFacade allPhoneCases;
    private FindCasesByPhoneNameFacade findCasesByPhoneName;
    private CreatePhoneCaseFacade savePhoneCase;

    @PostMapping("/phoneCases")
    @ResponseStatus(HttpStatus.CREATED)
    public PhoneCase newPhoneCase(@RequestBody PhoneCase.PhoneCaseParameters phoneCase){
       return savePhoneCase.createPhoneCase(phoneCase);
    }

    @GetMapping("/phoneCases")
    public List<PhoneCase> allPhoneCases() throws NothingToSeeYetException{
        List<PhoneCase> cases = allPhoneCases.allPhoneCases();
        if(cases.isEmpty()){
            throw new NothingToSeeYetException();
        }
        return cases;
    }

    @GetMapping("/phoneCases/{phoneName}")
    public List<PhoneCase> phoneCasesForPhone(@PathVariable String phoneName) throws NothingToSeeYetException, NoMatchingPhoneException {
        List<PhoneCase> cases = findCasesByPhoneName.findCaseByPhone(phoneName);
        if(cases.isEmpty()){
            throw new NothingToSeeYetException();
        }
        return cases;
    }

}
