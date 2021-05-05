package com.ytterbrink.phonecase.data;

import com.ytterbrink.phonecase.data.repositories.PhoneRepository;
import com.ytterbrink.phonecase.domain.Phone;
import com.ytterbrink.phonecase.domain.data_ports.AllPhones;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AllPhonesAdapter implements AllPhones {

    PhoneRepository repository;

    @Override
    public List<Phone> allPhones() {
        return repository.findAll();
    }
}
