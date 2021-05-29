package com.ytterbrink.phonecase.domain.web_ports;

import java.util.List;

import com.ytterbrink.phonecase.domain.data.Phone;
import com.ytterbrink.phonecase.exceptions.NothingToSeeYetException;

public interface AllPhonesFacade {
    List<Phone> allPhones() throws NothingToSeeYetException;
}
