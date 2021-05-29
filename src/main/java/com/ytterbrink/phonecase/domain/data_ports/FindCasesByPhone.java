package com.ytterbrink.phonecase.domain.data_ports;

import java.util.List;

import com.ytterbrink.phonecase.domain.data.Phone;
import com.ytterbrink.phonecase.domain.data.PhoneCase;

public interface FindCasesByPhone {

    List<PhoneCase> findPhoneCaseByPhone(Phone phone);
}
