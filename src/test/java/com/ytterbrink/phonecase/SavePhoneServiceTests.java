package com.ytterbrink.phonecase;


import com.ytterbrink.phonecase.domain.Phone;
import com.ytterbrink.phonecase.domain.PhoneShape;
import com.ytterbrink.phonecase.domain.data_ports.CreatePhone;
import com.ytterbrink.phonecase.domain.data_ports.CreatePhoneShape;
import com.ytterbrink.phonecase.domain.services.CreatePhoneService;
import com.ytterbrink.phonecase.doubles.FindPhoneByNameMock;
import lombok.Getter;
import org.junit.jupiter.api.Test;

import javax.persistence.ManyToOne;

import java.util.UUID;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;

public class SavePhoneServiceTests {

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
            this.shape = phoneShape;
            phoneShape.setId(UUID.randomUUID());
            return phoneShape;
        }
        public PhoneShape getShape(){
            return this.shape;
        }
    }

    @Test
    public void savesPhoneAndCreateNewPhoneShape(){
        Phone toSave = new Phone("toSave", null);
        Phone.PhoneParameters parameters = new Phone.PhoneParameters(toSave.getName(), null);
        CreatePhoneSpy createPhoneSpy = new CreatePhoneSpy();
        FindPhoneByNameMock findPhoneByNameMock = new FindPhoneByNameMock(new Phone("dummy", new PhoneShape()));
        CreatePhoneShapeSpy createPhoneShapeSpy = new CreatePhoneShapeSpy();
        CreatePhoneService service = new CreatePhoneService(createPhoneSpy,findPhoneByNameMock, createPhoneShapeSpy);
        Phone createdPhone = service.createPhone(parameters);
        assertThat(createdPhone.getName()).isEqualTo(toSave.getName());
        assertThat(createPhoneShapeSpy.getShape()).isNotNull();
        assertThat(createPhoneShapeSpy.getShape()).isEqualTo(createPhoneShapeSpy.getShape());
    }
    @Test
    public void savesPhoneWithOldPhoneShape(){
        Phone toSave = new Phone("toSave", null);
        Phone.PhoneParameters parameters = new Phone.PhoneParameters(toSave.getName(), "iPhoneSE");
        CreatePhoneSpy createPhoneSpy = new CreatePhoneSpy();
        PhoneShape oldShape = new PhoneShape();
        FindPhoneByNameMock findPhoneByNameMock = new FindPhoneByNameMock(new Phone("iPhoneSE", oldShape));
        CreatePhoneShapeSpy createPhoneShapeSpy = new CreatePhoneShapeSpy();
        CreatePhoneService service = new CreatePhoneService(createPhoneSpy, findPhoneByNameMock, createPhoneShapeSpy);
        Phone createdPhone = service.createPhone(parameters);
        assertThat(createdPhone.getName()).isEqualTo(toSave.getName());
        assertThat(createPhoneShapeSpy.getShape()).isNull();
        assertThat(createdPhone.getPhoneShape()).isEqualTo(oldShape);

    }
}
