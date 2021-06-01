package com.ytterbrink.phonecase.domain.data_ports;

import com.ytterbrink.phonecase.domain.data.PhoneShape;

public class FindPhoneShapeByPhoneNameMock implements FindPhoneShapeByPhoneName {

    private final PhoneShape phoneShape;
    private final String phoneName;

    public FindPhoneShapeByPhoneNameMock(PhoneShape phoneShape, String name) {
        this.phoneShape = phoneShape;
        this.phoneName = name;
    }

    @Override
    public PhoneShape findPhoneShapeByPhoneName(String name) {
        if (name.equals(phoneName)) {
            return phoneShape;
        }
        return null;
    }
}
