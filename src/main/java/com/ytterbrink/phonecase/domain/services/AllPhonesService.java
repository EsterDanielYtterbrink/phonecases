package com.ytterbrink.phonecase.domain.services;

import com.ytterbrink.phonecase.domain.Phone;
import com.ytterbrink.phonecase.domain.data_ports.AllPhones;
import com.ytterbrink.phonecase.domain.web_ports.AllPhonesFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AllPhonesService  implements AllPhonesFacade {
    private final AllPhones allPhones;

    @Autowired
    public AllPhonesService(AllPhones allPhones) {
        this.allPhones = allPhones;
    }

    @Override
    public List<Phone> allPhones() {
        return allPhones.allPhones();
    }
}
