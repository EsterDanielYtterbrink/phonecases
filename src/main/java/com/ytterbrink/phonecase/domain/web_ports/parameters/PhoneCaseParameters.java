package com.ytterbrink.phonecase.domain.web_ports.parameters;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PhoneCaseParameters {
    private String name;
    private String madeFor;
}
