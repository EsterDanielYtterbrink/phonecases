package com.ytterbrink.phonecase.domain.data_ports;

import com.ytterbrink.phonecase.domain.Phone;
import com.ytterbrink.phonecase.domain.PhoneCase;

import java.util.List;

public interface FindCasesByPhone {
    List<PhoneCase> findPhoneCaseByPhone(Phone phone);
}
