package com.ytterbrink.phonecase.doubles;

import com.ytterbrink.phonecase.domain.Phone;
import com.ytterbrink.phonecase.domain.data_ports.FindPhoneByName;

import java.util.Optional;

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
