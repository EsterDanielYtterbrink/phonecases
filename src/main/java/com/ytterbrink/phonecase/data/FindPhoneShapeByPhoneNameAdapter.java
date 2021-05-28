package com.ytterbrink.phonecase.data;

import com.ytterbrink.phonecase.data.repositories.PhoneRepository;
import com.ytterbrink.phonecase.domain.data.PhoneShape;
import com.ytterbrink.phonecase.domain.data_ports.FindPhoneShapeByPhoneName;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FindPhoneShapeByPhoneNameAdapter implements FindPhoneShapeByPhoneName {

    private final PhoneRepository repository;

   @Autowired
    public FindPhoneShapeByPhoneNameAdapter(PhoneRepository repository) {
        this.repository = repository;
    }

    @Override
    public PhoneShape findPhoneShapeByPhoneName(String name) {
       return repository.findOneByName(name).getPhoneShape();
    }
}
