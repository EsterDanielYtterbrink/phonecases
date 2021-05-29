package com.ytterbrink.phonecase.data;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ytterbrink.phonecase.data.repositories.PhoneCaseRepository;
import com.ytterbrink.phonecase.domain.data.Phone;
import com.ytterbrink.phonecase.domain.data.PhoneCase;
import com.ytterbrink.phonecase.domain.data_ports.FindCasesByPhone;

@Component
public class FindCasesByPhoneAdapter implements FindCasesByPhone {

    private final PhoneCaseRepository phoneCaseRepository;

    @Autowired
    public FindCasesByPhoneAdapter(PhoneCaseRepository phoneCaseRepository) {
        this.phoneCaseRepository = phoneCaseRepository;
    }

    @Override
    public List<PhoneCase> findPhoneCaseByPhone(Phone phone) {
        return phoneCaseRepository.findAllByPhoneShape(phone.getPhoneShape());
    }
}
