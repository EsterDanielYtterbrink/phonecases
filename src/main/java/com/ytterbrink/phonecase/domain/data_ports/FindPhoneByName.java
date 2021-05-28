package com.ytterbrink.phonecase.domain.data_ports;

import com.ytterbrink.phonecase.domain.data.Phone;

import java.util.Optional;

public interface FindPhoneByName {
    Optional<Phone> findPhoneByName(String name);
}
