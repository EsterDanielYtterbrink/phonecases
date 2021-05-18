package com.ytterbrink.phonecase.domain.services;


import com.ytterbrink.phonecase.domain.Phone;
import com.ytterbrink.phonecase.domain.PhoneShape;
import com.ytterbrink.phonecase.domain.data_ports.CreatePhone;
import com.ytterbrink.phonecase.domain.data_ports.CreatePhoneShape;
import com.ytterbrink.phonecase.domain.services.CreatePhoneService;
import com.ytterbrink.phonecase.doubles.FindPhoneByNameMock;
import com.ytterbrink.phonecase.doubles.FindPhoneShapeByPhoneNameMock;
import com.ytterbrink.phonecase.exceptions.NoMatchingPhoneException;
import lombok.Getter;
import org.junit.jupiter.api.Test;

import javax.persistence.ManyToOne;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;

public class CreatePhoneServiceTests {


    class CreatePhoneSpy implements CreatePhone{
        @Getter
        private Phone phone;
        @Override
        public Phone createPhone(Phone phone) {
            this.phone = phone;
            return phone;
        }
    }
    class CreatePhoneShapeSpy implements CreatePhoneShape {

        private PhoneShape shape;
        @Override
        public PhoneShape createPhoneShape(PhoneShape phoneShape) {
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
        Phone toSave = new Phone("toSave", null);
        Phone.PhoneParameters parameters = new Phone.PhoneParameters(toSave.getName(), null);
        CreatePhoneSpy createPhoneSpy = new CreatePhoneSpy();
        FindPhoneShapeByPhoneNameMock findPhoneShapeByPhoneNameMock = new FindPhoneShapeByPhoneNameMock(new PhoneShape(), "dummy");

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
            public PhoneShape createPhoneShape(PhoneShape phoneShape) {
               throw new RuntimeException();
            }
        }
        Phone toSave = new Phone("toSave", null);
        Phone.PhoneParameters parameters = new Phone.PhoneParameters(toSave.getName(), "iPhoneSE");
        CreatePhoneSpy createPhoneSpy = new CreatePhoneSpy();
        PhoneShape oldShape = new PhoneShape();
        oldShape.setId(UUID.randomUUID());
        FindPhoneShapeByPhoneNameMock findPhoneShapeByPhoneNameMock = new FindPhoneShapeByPhoneNameMock(oldShape, "iPhoneSE");
        CreatePhoneShapeZombie createPhoneShapeZombie = new CreatePhoneShapeZombie();
        CreatePhoneService service = new CreatePhoneService(createPhoneSpy, findPhoneShapeByPhoneNameMock, createPhoneShapeZombie);
        Phone createdPhone = service.createPhone(parameters);
        assertThat(createdPhone.getName()).isEqualTo(toSave.getName());
        assertThat(createdPhone.getPhoneShape()).isEqualTo(oldShape);
    }
}
