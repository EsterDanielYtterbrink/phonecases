package com.ytterbrink.phonecase.domain.data_ports;

import com.ytterbrink.phonecase.domain.PhoneShape;

public interface FindPhoneShapeByPhoneName {
    PhoneShape findPhoneShapeByPhoneName(String name);
}
