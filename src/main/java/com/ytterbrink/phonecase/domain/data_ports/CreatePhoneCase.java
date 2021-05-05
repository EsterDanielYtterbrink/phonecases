package com.ytterbrink.phonecase.domain.data_ports;

import com.ytterbrink.phonecase.domain.PhoneCase;

public interface CreatePhoneCase {
    PhoneCase savePhoneCase(PhoneCase phoneCase);
}
