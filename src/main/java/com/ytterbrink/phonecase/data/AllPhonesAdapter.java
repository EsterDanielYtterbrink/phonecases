package com.ytterbrink.phonecase.data;

import com.ytterbrink.phonecase.data.repositories.PhoneRepository;
import com.ytterbrink.phonecase.domain.data.Phone;
import com.ytterbrink.phonecase.domain.data_ports.AllPhones;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class AllPhonesAdapter implements AllPhones {

    private final PhoneRepository repository;

    @Autowired
    public AllPhonesAdapter(PhoneRepository repository) {
        this.repository = repository;
    }

    @Override
    /*
      WHAT?
     */
    public List<Phone> allPhones() {
        return (List) repository.findAll();
    }
}
