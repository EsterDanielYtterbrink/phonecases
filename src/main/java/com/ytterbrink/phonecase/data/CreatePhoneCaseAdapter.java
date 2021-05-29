package com.ytterbrink.phonecase.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ytterbrink.phonecase.data.repositories.PhoneCaseRepository;
import com.ytterbrink.phonecase.domain.data.PhoneCase;
import com.ytterbrink.phonecase.domain.data_ports.CreatePhoneCase;

@Component
public class CreatePhoneCaseAdapter implements CreatePhoneCase {

    private final PhoneCaseRepository repository;

    @Autowired
    public CreatePhoneCaseAdapter(PhoneCaseRepository repository) {
        this.repository = repository;
    }

    @Override
    public PhoneCase createPhoneCase(PhoneCase phoneCase) {
        return repository.save(PhoneCaseEntity.entityFromInterface(phoneCase));
    }
}
