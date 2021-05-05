package com.ytterbrink.phonecase.domain.data_ports;

import com.ytterbrink.phonecase.domain.Phone;
import org.springframework.stereotype.Component;

import java.util.List;

public interface AllPhones {
    List<Phone> allPhones();
}
