package com.ytterbrink.phonecase.domain.services;

import com.ytterbrink.phonecase.domain.Phone;
import com.ytterbrink.phonecase.domain.data_ports.AllPhones;
import com.ytterbrink.phonecase.exceptions.NothingToSeeYetException;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;

class AllPhonesServiceTest {

    @Test
    void allPhonesThrowsNothingToSeeYetExceptionWhenResultIsEmpty() {
        AllPhonesService service = new AllPhonesService(new AllPhonesMock());
        final Throwable thrown = catchThrowable(service::allPhones);
        assertThat(thrown).isInstanceOf(NothingToSeeYetException.class);
    }

    private static class AllPhonesMock implements AllPhones {
        @Override
        public List<Phone> allPhones() {
            return new LinkedList<>();
        }
    }
}