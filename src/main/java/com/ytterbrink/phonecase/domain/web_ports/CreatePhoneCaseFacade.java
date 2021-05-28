package com.ytterbrink.phonecase.domain.web_ports;

import com.ytterbrink.phonecase.domain.data.PhoneCase;
import com.ytterbrink.phonecase.domain.web_ports.parameters.PhoneCaseParameters;

public interface CreatePhoneCaseFacade {
    PhoneCase createPhoneCase(
            PhoneCaseParameters phoneCase);
}
