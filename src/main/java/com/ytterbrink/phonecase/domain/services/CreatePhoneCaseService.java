package com.ytterbrink.phonecase.domain.services;

import com.ytterbrink.phonecase.domain.data.PhoneCase;
import com.ytterbrink.phonecase.domain.web_ports.parameters.PhoneCaseParameters;
import com.ytterbrink.phonecase.domain.data.PhoneShape;
import com.ytterbrink.phonecase.domain.data_ports.CreatePhoneCase;
import com.ytterbrink.phonecase.domain.data_ports.FindPhoneShapeByPhoneName;
import com.ytterbrink.phonecase.domain.web_ports.CreatePhoneCaseFacade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreatePhoneCaseService implements CreatePhoneCaseFacade {

    private final CreatePhoneCase createPhoneCase;
    private final FindPhoneShapeByPhoneName findPhoneShapeByPhoneName;

    @Autowired
    public CreatePhoneCaseService(
            CreatePhoneCase createPhoneCase,
            FindPhoneShapeByPhoneName findPhoneShapeByPhoneName) {
        this.createPhoneCase = createPhoneCase;
        this.findPhoneShapeByPhoneName = findPhoneShapeByPhoneName;
    }

    @Override
    public PhoneCase createPhoneCase(PhoneCaseParameters phoneCaseParameters) {
        final PhoneCase phoneCase = new PhoneCase(phoneCaseParameters.getName());
        final String madeFor = phoneCaseParameters.getMadeFor();
        final PhoneShape shape = findPhoneShapeByPhoneName.findPhoneShapeByPhoneName(madeFor);
        phoneCase.setPhoneShape(shape);
        return createPhoneCase.createPhoneCase(phoneCase);
    }
}
