package com.ytterbrink.phonecase.domain.services;

import com.ytterbrink.phonecase.domain.PhoneCase;
import com.ytterbrink.phonecase.domain.PhoneShape;
import com.ytterbrink.phonecase.domain.data_ports.CreatePhoneCase;
import com.ytterbrink.phonecase.doubles.FindPhoneShapeByPhoneNameMock;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class SavePhoneCaseServiceTest {

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
        PhoneShape shape = new PhoneShape();
        shape.setId(UUID.randomUUID());
        FindPhoneShapeByPhoneNameMock findPhoneShapeByPhoneNameMock = new FindPhoneShapeByPhoneNameMock(shape, "iPhone5");
        CreatePhoneCaseSpy createPhoneCaseSpy = new CreatePhoneCaseSpy();
        CreatePhoneCaseService service = new CreatePhoneCaseService(createPhoneCaseSpy, findPhoneShapeByPhoneNameMock);
        PhoneCase.PhoneCaseParameters parameters = new PhoneCase.PhoneCaseParameters("pretty", "iPhone5");
        PhoneCase phoneCase = service.createPhoneCase(parameters);
        assertThat(phoneCase.getPhoneShape()).isEqualTo(shape);
        assertThat(createPhoneCaseSpy.getPhoneCase()).isNotNull();
    }


}