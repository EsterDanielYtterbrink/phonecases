package com.ytterbrink.phonecase.domain.web_ports;

import com.ytterbrink.phonecase.domain.PhoneCase;

public interface CreatePhoneCaseFacade {
    PhoneCase createPhoneCase(PhoneCase.PhoneCaseParameters phoneCase);
}
