package com.ytterbrink.phonecase.domain.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ytterbrink.phonecase.domain.data.PhoneCase;
import com.ytterbrink.phonecase.domain.data_ports.FindCasesByPhone;
import com.ytterbrink.phonecase.domain.data_ports.FindPhoneByName;
import com.ytterbrink.phonecase.domain.web_ports.FindCasesByPhoneNameFacade;
import com.ytterbrink.phonecase.exceptions.NoMatchingPhoneException;
import com.ytterbrink.phonecase.exceptions.NothingToSeeYetException;

@Component
public class FindCasesByPhoneNameService
        implements FindCasesByPhoneNameFacade {

    private final FindPhoneByName phoneFinder;
    private final FindCasesByPhone casesFinder;

    @Autowired
    public FindCasesByPhoneNameService(
            FindPhoneByName phoneFinder,
            FindCasesByPhone casesFinder) {
        this.phoneFinder = phoneFinder;
        this.casesFinder= casesFinder;
    }

    @Override
    public List<PhoneCase> findCaseByPhone(String phoneName)
            throws NoMatchingPhoneException, NothingToSeeYetException {
        final List<PhoneCase> cases = Optional.ofNullable(phoneFinder.findPhoneByName(phoneName))
                .map(casesFinder::findPhoneCaseByPhone)
                .orElseThrow(NoMatchingPhoneException::new);
        if (cases == null || cases.isEmpty()){
            throw new NothingToSeeYetException();
        }
        return cases;
    }
}
