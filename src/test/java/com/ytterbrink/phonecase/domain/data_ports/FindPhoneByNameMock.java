package com.ytterbrink.phonecase.domain.data_ports;

import java.util.Optional;

import com.ytterbrink.phonecase.domain.data.Phone;

public class FindPhoneByNameMock implements FindPhoneByName {

    private Phone phone;

    public FindPhoneByNameMock(Phone phone) {
        this.phone = phone;
    }

    @Override
    public Optional<Phone> findPhoneByName(String name) {
        return Optional.ofNullable(phone);
    }
}
