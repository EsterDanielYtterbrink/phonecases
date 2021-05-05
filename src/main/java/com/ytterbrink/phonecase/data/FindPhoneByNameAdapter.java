package com.ytterbrink.phonecase.data;

import com.ytterbrink.phonecase.data.repositories.PhoneRepository;
import com.ytterbrink.phonecase.domain.Phone;
import com.ytterbrink.phonecase.domain.data_ports.FindPhoneByName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FindPhoneByNameAdapter implements FindPhoneByName {

    private PhoneRepository phoneRepository;

    @Autowired
    public FindPhoneByNameAdapter(PhoneRepository phoneRepository) {
        this.phoneRepository = phoneRepository;
    }

    @Override
    public Phone findPhoneByName(String name) {
        return phoneRepository.findOneByName(name);
    }
}
