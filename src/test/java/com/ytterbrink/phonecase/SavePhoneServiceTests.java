package com.ytterbrink.phonecase;


import com.ytterbrink.phonecase.domain.Phone;
import com.ytterbrink.phonecase.domain.PhoneShape;
import com.ytterbrink.phonecase.domain.data_ports.CreatePhone;
import com.ytterbrink.phonecase.domain.data_ports.CreatePhoneShape;
import com.ytterbrink.phonecase.domain.services.CreatePhoneService;
import com.ytterbrink.phonecase.doubles.FindPhoneByNameMock;
import lombok.Getter;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SavePhoneServiceTests {

    @Test
    public void savesPhoneAndCreateNewPhoneShape(){
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

            @Getter
            private PhoneShape shape;
            @Override
            public PhoneShape createPhoneShape(PhoneShape phoneShape) {
                this.shape = phoneShape;
                return phoneShape;
            }
        }


        Phone toSave = new Phone("toSave", null);
        Phone.PhoneParameters parameters = new Phone.PhoneParameters(toSave, null);
        CreatePhoneSpy createPhoneSpy = new CreatePhoneSpy();
        FindPhoneByNameMock findPhoneByNameMock = new FindPhoneByNameMock(new Phone("dummy", new PhoneShape()));
        CreatePhoneShapeSpy createPhoneShapeSpy = new CreatePhoneShapeSpy();
        CreatePhoneService service = new CreatePhoneService(createPhoneSpy,findPhoneByNameMock, createPhoneShapeSpy);
        Phone createdPhone = service.createPhone(parameters);
        assertThat(createdPhone.getName()).isEqualTo(toSave.getName());
        assertThat(createdPhone.getPhoneShape()).isNotNull();

    }
}
