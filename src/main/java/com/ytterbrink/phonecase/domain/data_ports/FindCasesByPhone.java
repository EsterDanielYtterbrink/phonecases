package com.ytterbrink.phonecase.domain.data_ports;

import com.ytterbrink.phonecase.domain.data.Phone;
import com.ytterbrink.phonecase.domain.data.PhoneCase;

import java.util.List;

public interface FindCasesByPhone {
    List<PhoneCase> findPhoneCaseByPhone(Phone phone);
}
