package com.ytterbrink.phonecase.domain.web_ports.parameters;

import java.util.Optional;

import lombok.Data;

@Data
public class PhoneParameters {
    private String newPhoneName;
    private Optional<String> similarPhoneName;

    public PhoneParameters(String newPhoneName, String similarPhoneName) {
        this.newPhoneName = newPhoneName;
        this.similarPhoneName = Optional.ofNullable(similarPhoneName);
    }
}
