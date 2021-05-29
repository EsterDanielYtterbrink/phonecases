package com.ytterbrink.phonecase.domain.services;


import com.ytterbrink.phonecase.data.PhoneEntity;
import com.ytterbrink.phonecase.data.PhoneShapeEntity;
import com.ytterbrink.phonecase.domain.data.Phone;
import com.ytterbrink.phonecase.domain.web_ports.parameters.PhoneParameters;
import com.ytterbrink.phonecase.domain.data.PhoneShape;
import com.ytterbrink.phonecase.domain.data_ports.CreatePhone;
import com.ytterbrink.phonecase.domain.data_ports.CreatePhoneShape;
import com.ytterbrink.phonecase.doubles.FindPhoneShapeByPhoneNameMock;
import lombok.Getter;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class CreatePhoneServiceTests {

    static class CreatePhoneSpy implements CreatePhone{
        @Getter
        private Phone phone;
        @Override
        public Phone createPhone(PhoneEntity phone) {
            this.phone = phone;
            return phone;
        }
    }
    static class CreatePhoneShapeSpy implements CreatePhoneShape {

        private PhoneShape shape;
        @Override
        public PhoneShape createPhoneShape(PhoneShapeEntity phoneShape) {
            phoneShape.setId(UUID.randomUUID());
            this.shape = phoneShape;
            return phoneShape;
        }
        public PhoneShape getShape(){
            return this.shape;
        }
    }

    @Test
    public void savesPhoneAndCreateNewPhoneShape() {
        Phone toSave = new PhoneEntity("toSave", null);
        PhoneParameters parameters = new PhoneParameters(toSave.getName(), null);
        CreatePhoneSpy createPhoneSpy = new CreatePhoneSpy();
        FindPhoneShapeByPhoneNameMock findPhoneShapeByPhoneNameMock = new FindPhoneShapeByPhoneNameMock(new PhoneShapeEntity(), "dummy");

        CreatePhoneShapeSpy createPhoneShapeSpy = new CreatePhoneShapeSpy();
        CreatePhoneService service = new CreatePhoneService(createPhoneSpy,findPhoneShapeByPhoneNameMock, createPhoneShapeSpy);
        Phone createdPhone = service.createPhone(parameters);
        assertThat(createdPhone.getName()).isEqualTo(toSave.getName());
        assertThat(createdPhone.getPhoneShape()).isNotNull();
        assertThat(createPhoneShapeSpy.getShape()).isEqualTo(createdPhone.getPhoneShape());
    }
    @Test
    public void savesPhoneWithOldPhoneShape(){
        class CreatePhoneShapeZombie implements CreatePhoneShape {

            @Override
            public PhoneShape createPhoneShape(PhoneShapeEntity phoneShape) {
               throw new RuntimeException();
            }
        }
        Phone toSave = new PhoneEntity("toSave", null);
        PhoneParameters parameters = new PhoneParameters(toSave.getName(), "iPhoneSE");
        CreatePhoneSpy createPhoneSpy = new CreatePhoneSpy();
        PhoneShapeEntity oldShape = new PhoneShapeEntity();
        oldShape.setId(UUID.randomUUID());
        FindPhoneShapeByPhoneNameMock findPhoneShapeByPhoneNameMock = new FindPhoneShapeByPhoneNameMock(oldShape, "iPhoneSE");
        CreatePhoneShapeZombie createPhoneShapeZombie = new CreatePhoneShapeZombie();
        CreatePhoneService service = new CreatePhoneService(createPhoneSpy, findPhoneShapeByPhoneNameMock, createPhoneShapeZombie);
        Phone createdPhone = service.createPhone(parameters);
        assertThat(createdPhone.getName()).isEqualTo(toSave.getName());
        assertThat(createdPhone.getPhoneShape()).isEqualTo(oldShape);
    }
}
