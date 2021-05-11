package com.ytterbrink.phonecase.domain.services;

import com.ytterbrink.phonecase.domain.PhoneCase;
import com.ytterbrink.phonecase.domain.PhoneShape;
import com.ytterbrink.phonecase.domain.data_ports.CreatePhoneCase;
import com.ytterbrink.phonecase.domain.data_ports.FindPhoneShapeByPhoneName;
import lombok.Getter;
import org.junit.jupiter.api.Test;

import javax.imageio.stream.IIOByteBuffer;

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
    private class FindPhoneShapeByPhoneNameMock implements FindPhoneShapeByPhoneName {

        @Getter
        private final PhoneShape phoneShape;
        private final String phoneName;

        public FindPhoneShapeByPhoneNameMock(PhoneShape phoneShape, String name) {
            this.phoneShape = phoneShape;
            this.phoneName = name;
        }

        @Override
        public PhoneShape findPhoneShapeByPhoneName(String name) {
            if(name.equals(phoneName)){
                return phoneShape;
            }
            return null;
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