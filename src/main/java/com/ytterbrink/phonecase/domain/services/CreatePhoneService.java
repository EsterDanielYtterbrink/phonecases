package com.ytterbrink.phonecase.domain.services;

import com.ytterbrink.phonecase.domain.Phone;
import com.ytterbrink.phonecase.domain.PhoneShape;
import com.ytterbrink.phonecase.domain.data_ports.CreatePhone;
import com.ytterbrink.phonecase.domain.data_ports.CreatePhoneShape;
import com.ytterbrink.phonecase.domain.data_ports.FindPhoneByName;
import com.ytterbrink.phonecase.domain.web_ports.CreatePhoneFacade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreatePhoneService implements CreatePhoneFacade {

    CreatePhone createPhone;
    FindPhoneByName findPhoneByName;
    CreatePhoneShape createPhoneShape;

    @Autowired
    public CreatePhoneService(CreatePhone createPhone, FindPhoneByName findPhoneByName, CreatePhoneShape createPhoneShape) {
        this.createPhone = createPhone;
        this.findPhoneByName = findPhoneByName;
        this.createPhoneShape = createPhoneShape;
    }

    @Override
    public Phone createPhone(Phone.PhoneParameters parameters) {
        PhoneShape phoneShape = this.createPhoneShape.createPhoneShape(new PhoneShape());
        Phone toSave = new Phone(parameters.getNewPhone().getName(), phoneShape);
        return this.createPhone.createPhone(toSave);
    }
}
