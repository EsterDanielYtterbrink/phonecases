package com.ytterbrink.phonecase.domain.services;

import com.ytterbrink.phonecase.domain.PhoneCase;
import com.ytterbrink.phonecase.domain.data_ports.AllPhoneCases;
import com.ytterbrink.phonecase.domain.web_ports.AllPhoneCasesFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AllPhoneCasesService implements AllPhoneCasesFacade {

    private final AllPhoneCases allPhoneCases;

    public AllPhoneCasesService(AllPhoneCases allPhoneCases) {
        this.allPhoneCases = allPhoneCases;
    }

    @Override
    public List<PhoneCase> allPhoneCases() {
        return allPhoneCases.allPhoneCases();
    }
}
