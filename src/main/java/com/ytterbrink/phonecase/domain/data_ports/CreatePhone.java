package com.ytterbrink.phonecase.domain.data_ports;

import com.ytterbrink.phonecase.data.PhoneEntity;
import com.ytterbrink.phonecase.domain.data.Phone;

public interface CreatePhone {
    Phone createPhone(PhoneEntity phone);
}
