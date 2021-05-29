package com.ytterbrink.phonecase.domain.web_ports;

import java.util.List;

import com.ytterbrink.phonecase.domain.data.PhoneCase;
import com.ytterbrink.phonecase.exceptions.NoMatchingPhoneException;
import com.ytterbrink.phonecase.exceptions.NothingToSeeYetException;

public interface FindCasesByPhoneNameFacade {
    List<PhoneCase> findCaseByPhone(String phoneName)
            throws NoMatchingPhoneException, NothingToSeeYetException;
}

