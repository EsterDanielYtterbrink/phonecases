package com.ytterbrink.phonecase.data;

import com.ytterbrink.phonecase.data.repositories.PhoneCaseRepository;
import com.ytterbrink.phonecase.domain.Phone;
import com.ytterbrink.phonecase.domain.PhoneShape;
import com.ytterbrink.phonecase.domain.data_ports.FindCasesByPhone;
import com.ytterbrink.phonecase.domain.PhoneCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FindCasesByPhoneAdapter implements FindCasesByPhone {

    private final PhoneCaseRepository phoneCaseRepository;

    @Autowired
    public FindCasesByPhoneAdapter(PhoneCaseRepository phoneCaseRepository) {
        this.phoneCaseRepository = phoneCaseRepository;
    }

    @Override
    public List<PhoneCase> findPhoneCaseByPhone(Phone phone) {
        PhoneShape shape = phone.getPhoneShape();
        return phoneCaseRepository.findAllByPhoneShape(shape);
    }
}