package com.ytterbrink.phonecase.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ytterbrink.phonecase.data.repositories.PhoneShapeRepository;
import com.ytterbrink.phonecase.domain.data.PhoneShape;
import com.ytterbrink.phonecase.domain.data_ports.CreatePhoneShape;

@Component
public class CreatePhoneShapeAdapter implements CreatePhoneShape {

    private final PhoneShapeRepository repository;

    @Autowired
    public CreatePhoneShapeAdapter(PhoneShapeRepository repository) {
        this.repository = repository;
    }

    @Override
    public PhoneShape createPhoneShape(PhoneShapeEntity phoneShape) {
        return repository.save(phoneShape);
    }
}
