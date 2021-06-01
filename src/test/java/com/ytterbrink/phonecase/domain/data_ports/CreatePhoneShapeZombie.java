package com.ytterbrink.phonecase.domain.data_ports;

import com.ytterbrink.phonecase.data.PhoneShapeEntity;
import com.ytterbrink.phonecase.domain.data.PhoneShape;

public class CreatePhoneShapeZombie implements CreatePhoneShape {

    @Override
    public PhoneShape createPhoneShape(PhoneShapeEntity phoneShape) {
        throw new RuntimeException();
    }
}
