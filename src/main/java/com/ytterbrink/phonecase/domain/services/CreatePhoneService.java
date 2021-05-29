package com.ytterbrink.phonecase.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ytterbrink.phonecase.data.PhoneEntity;
import com.ytterbrink.phonecase.data.PhoneShapeEntity;
import com.ytterbrink.phonecase.domain.data.Phone;
import com.ytterbrink.phonecase.domain.data.PhoneShape;
import com.ytterbrink.phonecase.domain.data_ports.CreatePhone;
import com.ytterbrink.phonecase.domain.data_ports.CreatePhoneShape;
import com.ytterbrink.phonecase.domain.data_ports.FindPhoneShapeByPhoneName;
import com.ytterbrink.phonecase.domain.web_ports.CreatePhoneFacade;
import com.ytterbrink.phonecase.domain.web_ports.parameters.PhoneParameters;

@Service
public class CreatePhoneService implements CreatePhoneFacade {

    private final CreatePhone createPhone;
    private final FindPhoneShapeByPhoneName findPhoneShapeByPhoneName;
    private final CreatePhoneShape createPhoneShape;

    @Autowired
    public CreatePhoneService(CreatePhone createPhone,
                              FindPhoneShapeByPhoneName  findPhoneShapeByPhoneName,
                              CreatePhoneShape createPhoneShape) {
        this.createPhone = createPhone;
        this.findPhoneShapeByPhoneName = findPhoneShapeByPhoneName;
        this.createPhoneShape = createPhoneShape;
    }

    @Override
    public Phone createPhone(PhoneParameters parameters) {
        final String phoneName = parameters.getNewPhoneName();
        final PhoneShape phoneShape = parameters.getSimilarPhoneName()
                .map(findPhoneShapeByPhoneName::findPhoneShapeByPhoneName)
                .orElseGet(() -> createPhoneShape.createPhoneShape(new PhoneShapeEntity()));
        return createPhone.createPhone(new PhoneEntity(phoneName, phoneShape));
    }

}
