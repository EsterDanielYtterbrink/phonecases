package com.ytterbrink.phonecase.domain.data_ports;

import java.util.UUID;

import com.ytterbrink.phonecase.data.PhoneShapeEntity;
import com.ytterbrink.phonecase.domain.data.PhoneShape;

public class CreatePhoneShapeSpy implements CreatePhoneShape {

    private PhoneShape shape;

    @Override
    public PhoneShape createPhoneShape(PhoneShapeEntity phoneShape) {
        phoneShape.setId(UUID.randomUUID());
        this.shape = phoneShape;
        return phoneShape;
    }

    public PhoneShape getShape(){
        return this.shape;
    }
}

