package com.ytterbrink.phonecase.domain.web_ports;

import com.ytterbrink.phonecase.domain.PhoneCase;
import com.ytterbrink.phonecase.domain.PhoneCaseParameters;

public interface CreatePhoneCaseFacade {
    PhoneCase createPhoneCase(
            PhoneCaseParameters phoneCase);
}
