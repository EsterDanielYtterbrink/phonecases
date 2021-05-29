package com.ytterbrink.phonecase.domain.data_ports;

import java.util.Optional;

import com.ytterbrink.phonecase.domain.data.Phone;

public interface FindPhoneByName {
    Optional<Phone> findPhoneByName(String name);
}
