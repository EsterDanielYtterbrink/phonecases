package com.ytterbrink.phonecase.domain.data_ports;

import com.ytterbrink.phonecase.data.PhoneEntity;
import com.ytterbrink.phonecase.domain.data.Phone;

public class CreatePhoneDouble implements CreatePhone {

    @Override
    public Phone createPhone(PhoneEntity phone) {
        return phone;
    }

}
