package com.ytterbrink.phonecase.domain.web_ports;

import com.ytterbrink.phonecase.domain.Phone;
import com.ytterbrink.phonecase.exceptions.NothingToSeeYetException;

import java.util.List;

public interface AllPhonesFacade {
    List<Phone> allPhones() throws NothingToSeeYetException;
}
