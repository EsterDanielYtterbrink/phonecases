package com.ytterbrink.phonecase.domain.data_ports;

import com.ytterbrink.phonecase.domain.data.PhoneShape;

public interface FindPhoneShapeByPhoneName {
    PhoneShape findPhoneShapeByPhoneName(String name);
}
