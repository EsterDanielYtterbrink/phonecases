package com.ytterbrink.phonecase.domain.services;

import com.ytterbrink.phonecase.domain.PhoneCase;
import com.ytterbrink.phonecase.domain.data_ports.CreatePhoneCase;
import com.ytterbrink.phonecase.domain.web_ports.SavePhoneCaseFacade;
import org.springframework.stereotype.Service;

@Service
public class SavePhoneCaseService implements SavePhoneCaseFacade {

    CreatePhoneCase savePhoneCase;

    @Override
    public PhoneCase savePhoneCase(PhoneCase phoneCase) {
        return savePhoneCase.savePhoneCase(phoneCase);
    }
}
