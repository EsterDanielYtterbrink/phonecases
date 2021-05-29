package com.ytterbrink.phonecase.domain.data_ports;

import com.ytterbrink.phonecase.data.PhoneShapeEntity;
import com.ytterbrink.phonecase.domain.data.PhoneShape;

public interface CreatePhoneShape {
    PhoneShape createPhoneShape(PhoneShapeEntity phoneShape);
}
