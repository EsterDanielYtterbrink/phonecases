package com.ytterbrink.phonecase.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ytterbrink.phonecase.data.repositories.PhoneRepository;
import com.ytterbrink.phonecase.domain.data.Phone;
import com.ytterbrink.phonecase.domain.data_ports.CreatePhone;

@Component
public class CreatePhoneAdapter implements CreatePhone {

    private final PhoneRepository repository;

    @Autowired
    public CreatePhoneAdapter(PhoneRepository repository) {
        this.repository = repository;
    }

    @Override
    public Phone createPhone(PhoneEntity phone) {
        return repository.save(phone);
    }
}
