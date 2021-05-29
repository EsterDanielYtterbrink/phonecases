package com.ytterbrink.phonecase.data;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ytterbrink.phonecase.data.repositories.PhoneCaseRepository;
import com.ytterbrink.phonecase.domain.data.PhoneCase;
import com.ytterbrink.phonecase.domain.data_ports.AllPhoneCases;

@Component
public class AllPhoneCasesAdapter implements AllPhoneCases {

    private final PhoneCaseRepository repository;

    @Autowired
    public AllPhoneCasesAdapter(PhoneCaseRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<PhoneCase> allPhoneCases() {
        return (List) repository.findAll();
    }
}
