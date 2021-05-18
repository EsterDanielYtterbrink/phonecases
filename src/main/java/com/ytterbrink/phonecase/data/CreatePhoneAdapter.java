package com.ytterbrink.phonecase.data;

import com.ytterbrink.phonecase.data.repositories.PhoneRepository;
import com.ytterbrink.phonecase.domain.Phone;
import com.ytterbrink.phonecase.domain.data_ports.CreatePhone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreatePhoneAdapter implements CreatePhone {

    private final PhoneRepository repository;

    @Autowired
    public CreatePhoneAdapter(PhoneRepository repository) {
        this.repository = repository;
    }

    @Override
    public Phone createPhone(Phone phone) {
        return repository.save(phone);
    }
}
