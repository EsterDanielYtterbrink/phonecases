package com.ytterbrink.phonecase.domain.web_ports;

import java.util.List;

import com.ytterbrink.phonecase.domain.data.PhoneCase;
import com.ytterbrink.phonecase.exceptions.NothingToSeeYetException;

public interface AllPhoneCasesFacade {

    List<PhoneCase> allPhoneCases() throws NothingToSeeYetException;
}
