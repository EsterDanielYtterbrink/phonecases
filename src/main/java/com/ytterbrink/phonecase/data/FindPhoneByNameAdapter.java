package com.ytterbrink.phonecase.data;

import com.ytterbrink.phonecase.data.repositories.PhoneRepository;
import com.ytterbrink.phonecase.domain.Phone;
import com.ytterbrink.phonecase.domain.data_ports.FindPhoneByName;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class FindPhoneByNameAdapter implements FindPhoneByName {

    private final PhoneRepository phoneRepository;

    @Autowired
    public FindPhoneByNameAdapter(PhoneRepository phoneRepository) {
        this.phoneRepository = phoneRepository;
    }

    @Override
    public Optional<Phone> findPhoneByName(String name) {
        return Optional.ofNullable(phoneRepository.findOneByName(name));
    }
}
