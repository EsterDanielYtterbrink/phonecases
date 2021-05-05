package com.ytterbrink.phonecase.domain.web_ports;

import com.ytterbrink.phonecase.domain.PhoneCase;

public interface SavePhoneCaseFacade {
    PhoneCase savePhoneCase(PhoneCase phoneCase);
}
