package com.ytterbrink.phonecase.doubles;

import com.ytterbrink.phonecase.domain.PhoneShape;
import com.ytterbrink.phonecase.domain.data_ports.FindPhoneShapeByPhoneName;
import lombok.Getter;

public class FindPhoneShapeByPhoneNameMock implements FindPhoneShapeByPhoneName {

    @Getter
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
