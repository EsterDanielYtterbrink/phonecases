package com.ytterbrink.phonecase.domain.web_ports;

import com.ytterbrink.phonecase.domain.PhoneCase;
import com.ytterbrink.phonecase.exceptions.NoMatchingPhoneException;

import java.util.List;

public interface FindCasesByPhoneNameFacade {
    List<PhoneCase> findCaseByPhone(String phoneName) throws NoMatchingPhoneException;
}

