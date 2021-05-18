package com.ytterbrink.phonecase.domain.services;

import com.ytterbrink.phonecase.exceptions.NoMatchingPhoneException;
import com.ytterbrink.phonecase.domain.Phone;
import com.ytterbrink.phonecase.domain.web_ports.FindCasesByPhoneNameFacade;
import com.ytterbrink.phonecase.domain.data_ports.FindPhoneByName;
import com.ytterbrink.phonecase.domain.data_ports.FindCasesByPhone;
import com.ytterbrink.phonecase.domain.PhoneCase;

import com.ytterbrink.phonecase.exceptions.NothingToSeeYetException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;


@Component
public class FindCasesByPhoneNameService implements FindCasesByPhoneNameFacade {


    private final FindPhoneByName phoneFinder;
    private final FindCasesByPhone casesFinder;

    @Autowired
    public FindCasesByPhoneNameService(FindPhoneByName phoneFinder, FindCasesByPhone casesFinder) {
        this.phoneFinder = phoneFinder;
        this.casesFinder= casesFinder;
    }

    @Override
    public List<PhoneCase> findCaseByPhone(String phoneName) throws NoMatchingPhoneException, NothingToSeeYetException {
        Phone phone  = phoneFinder.findPhoneByName(phoneName).orElseThrow(NoMatchingPhoneException::new);
        List<PhoneCase> cases = casesFinder.findPhoneCaseByPhone(phone);
        if(cases == null || cases.isEmpty()){
            throw new NothingToSeeYetException();
        }
        return cases;
    }
}
