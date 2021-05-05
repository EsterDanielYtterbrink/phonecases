package com.ytterbrink.phonecase.data;

import com.ytterbrink.phonecase.data.repositories.PhoneCaseRepository;
import com.ytterbrink.phonecase.domain.data_ports.AllPhoneCases;
import com.ytterbrink.phonecase.domain.PhoneCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AllPhoneCasesAdapter implements AllPhoneCases {

    PhoneCaseRepository repository;

    @Autowired
    public AllPhoneCasesAdapter(PhoneCaseRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<PhoneCase> allPhoneCases() {
        return repository.findAll();
    }
}
