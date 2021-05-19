package com.ytterbrink.phonecase.domain;

import lombok.Data;

import java.util.Optional;

@Data
public class PhoneParameters {
    String newPhoneName;
    Optional<String> similarPhoneName;

    public PhoneParameters(String newPhoneName, String similarPhoneName) {
        this.newPhoneName = newPhoneName;
        this.similarPhoneName = Optional.ofNullable(similarPhoneName);
    }
}
