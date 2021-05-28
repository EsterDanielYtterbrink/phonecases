package com.ytterbrink.phonecase.domain.web_ports;

import com.ytterbrink.phonecase.domain.data.PhoneCase;
import com.ytterbrink.phonecase.exceptions.NothingToSeeYetException;

import java.util.List;

public interface AllPhoneCasesFacade {
    List<PhoneCase> allPhoneCases() throws NothingToSeeYetException;
}
