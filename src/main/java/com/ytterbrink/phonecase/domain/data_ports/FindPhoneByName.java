package com.ytterbrink.phonecase.domain.data_ports;

import com.ytterbrink.phonecase.domain.data.Phone;

public interface FindPhoneByName {
    Phone findPhoneByName(String name);
}
