package com.ytterbrink.phonecase.domain.services;

import java.util.UUID;

import org.assertj.core.api.Assertions;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

import com.ytterbrink.phonecase.data.PhoneShapeEntity;
import com.ytterbrink.phonecase.domain.data.Phone;
import com.ytterbrink.phonecase.domain.data_ports.CreatePhoneDouble;
import com.ytterbrink.phonecase.domain.data_ports.CreatePhoneShape;
import com.ytterbrink.phonecase.domain.data_ports.CreatePhoneShapeSpy;
import com.ytterbrink.phonecase.domain.data_ports.CreatePhoneShapeZombie;
import com.ytterbrink.phonecase.domain.data_ports.FindPhoneShapeByPhoneNameMock;
import com.ytterbrink.phonecase.domain.web_ports.parameters.PhoneParameters;

public class CreatePhoneServiceTests {

    private static final String PHONE_NAME = "iPhone5";
    private static final String SIMILAR_PHONE_NAME = "iPhoneSE";

    @Test
    public void createsPhoneAndCreateNewPhoneShape() {
        final PhoneShapeEntity phoneShape =  new PhoneShapeEntity();
        final CreatePhoneShapeSpy createPhoneShapeSpy = new CreatePhoneShapeSpy();
        final CreatePhoneService service = getCreatePhoneService(
                phoneShape,
                createPhoneShapeSpy,
                "dummy");
        final PhoneParameters parameters = new PhoneParameters(PHONE_NAME, null);
        final Phone createdPhone = service.createPhone(parameters);
        Assertions.assertThat(createdPhone.getName()).isEqualTo(PHONE_NAME);
        Assertions.assertThat(createdPhone.getPhoneShape()).isNotNull();
        Assertions.assertThat(createPhoneShapeSpy.getShape())
                .isEqualTo(createdPhone.getPhoneShape());
    }

    @NotNull
    private CreatePhoneService getCreatePhoneService(
            PhoneShapeEntity phoneShape,
            CreatePhoneShape createPhoneShapeSpy,
            String name) {
        return new CreatePhoneService(
                new CreatePhoneDouble(),
                new FindPhoneShapeByPhoneNameMock(
                        phoneShape,
                        name),
                createPhoneShapeSpy);
    }

    @Test
    public void createsPhoneWithOldPhoneShape(){
        final PhoneShapeEntity oldShape = new PhoneShapeEntity();
        oldShape.setId(UUID.randomUUID());
        final CreatePhoneService service = getCreatePhoneService(
                oldShape,
                new CreatePhoneShapeZombie(),
                SIMILAR_PHONE_NAME);
        final PhoneParameters parameters = new PhoneParameters(PHONE_NAME, SIMILAR_PHONE_NAME);
        final Phone createdPhone = service.createPhone(parameters);
        Assertions.assertThat(createdPhone.getName()).isEqualTo(PHONE_NAME);
        Assertions.assertThat(createdPhone.getPhoneShape()).isEqualTo(oldShape);
    }
}
