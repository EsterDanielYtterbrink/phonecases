package com.ytterbrink.phonecase.domain.services;

import com.ytterbrink.phonecase.domain.PhoneCase;
import com.ytterbrink.phonecase.domain.PhoneShape;
import com.ytterbrink.phonecase.domain.data_ports.CreatePhoneCase;
import com.ytterbrink.phonecase.domain.data_ports.FindPhoneShapeByPhoneName;
import com.ytterbrink.phonecase.domain.web_ports.CreatePhoneCaseFacade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreatePhoneCaseService implements CreatePhoneCaseFacade {

    final CreatePhoneCase createPhoneCase;
    final FindPhoneShapeByPhoneName findPhoneShapeByPhoneName;

    @Autowired
    public CreatePhoneCaseService(CreatePhoneCase createPhoneCase, FindPhoneShapeByPhoneName findPhoneShapeByPhoneName) {
        this.createPhoneCase = createPhoneCase;
        this.findPhoneShapeByPhoneName = findPhoneShapeByPhoneName;
    }

    @Override
    public PhoneCase createPhoneCase(PhoneCase.PhoneCaseParameters phoneCaseParameters) {
        PhoneCase phoneCase = new PhoneCase(phoneCaseParameters.getName());
        PhoneShape shape = findPhoneShapeByPhoneName.findPhoneShapeByPhoneName(phoneCaseParameters.getMadeFor());
        phoneCase.setPhoneShape(shape);
        return createPhoneCase.createPhoneCase(phoneCase);
    }
}
