package com.ytterbrink.phonecase.data;

import com.ytterbrink.phonecase.data.repositories.PhoneShapeRepository;
import com.ytterbrink.phonecase.domain.PhoneShape;
import com.ytterbrink.phonecase.domain.data_ports.CreatePhoneShape;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreatePhoneShapeAdapter implements CreatePhoneShape {

    private final PhoneShapeRepository repository;

    @Autowired
    public CreatePhoneShapeAdapter(PhoneShapeRepository repository) {
        this.repository = repository;
    }

    @Override
    public PhoneShape createPhoneShape(PhoneShape phoneShape) {
        return repository.save(phoneShape);
    }
}
