package com.ytterbrink.phonecase.domain.services;

import java.util.UUID;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import com.ytterbrink.phonecase.data.PhoneShapeEntity;
import com.ytterbrink.phonecase.domain.data.PhoneCase;
import com.ytterbrink.phonecase.domain.data_ports.CreatePhoneCase;
import com.ytterbrink.phonecase.domain.data_ports.FindPhoneShapeByPhoneNameMock;
import com.ytterbrink.phonecase.domain.web_ports.parameters.PhoneCaseParameters;

class SavePhoneCaseServiceTests {

    private static final String PHONE_NAME = "iPhone5";

    class CreatePhoneCaseSpy implements CreatePhoneCase{

        private PhoneCase phoneCase;

        public PhoneCase getPhoneCase() {
            return phoneCase;
        }

        @Override
        public PhoneCase createPhoneCase(PhoneCase phoneCase) {
            this.phoneCase = phoneCase;
            return phoneCase;
        }
    }

    @Test
    void savePhoneCaseWhenEverythingWorks() {
        final PhoneShapeEntity shape = new PhoneShapeEntity();
        shape.setId(UUID.randomUUID());
        final CreatePhoneCaseSpy createPhoneCaseSpy = new CreatePhoneCaseSpy();
        final CreatePhoneCaseService service = new CreatePhoneCaseService(
                createPhoneCaseSpy,
                new FindPhoneShapeByPhoneNameMock(
                        shape,
                        PHONE_NAME));
        final PhoneCaseParameters parameters = new PhoneCaseParameters("pretty", PHONE_NAME);
        final PhoneCase phoneCase = service.createPhoneCase(parameters);
        Assertions.assertThat(phoneCase.getPhoneShape()).isEqualTo(shape);
        Assertions.assertThat(createPhoneCaseSpy.getPhoneCase()).isNotNull();
    }
}
