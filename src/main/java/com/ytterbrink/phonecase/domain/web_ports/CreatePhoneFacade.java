package com.ytterbrink.phonecase.domain.web_ports;

import com.ytterbrink.phonecase.domain.data.Phone;
import com.ytterbrink.phonecase.domain.web_ports.parameters.PhoneParameters;
import com.ytterbrink.phonecase.exceptions.NoMatchingPhoneException;

public interface CreatePhoneFacade {
    Phone createPhone(PhoneParameters parameters)
            throws NoMatchingPhoneException;
}
