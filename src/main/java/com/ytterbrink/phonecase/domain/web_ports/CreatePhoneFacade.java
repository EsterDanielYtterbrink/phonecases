package com.ytterbrink.phonecase.domain.web_ports;

import com.ytterbrink.phonecase.domain.Phone;

public interface CreatePhoneFacade {
    Phone createPhone(Phone.PhoneParameters parameters);
}
