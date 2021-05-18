package com.ytterbrink.phonecase.data;

import com.ytterbrink.phonecase.data.repositories.PhoneCaseRepository;
import com.ytterbrink.phonecase.domain.PhoneCase;
import com.ytterbrink.phonecase.domain.data_ports.CreatePhoneCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreatePhoneCaseAdapter implements CreatePhoneCase {

   @Autowired
    public CreatePhoneCaseAdapter(PhoneCaseRepository repository) {
        this.repository = repository;
    }

    private final PhoneCaseRepository repository;

    @Override
    public PhoneCase createPhoneCase(PhoneCase phoneCase) {
        return repository.save(phoneCase);
    }
}
