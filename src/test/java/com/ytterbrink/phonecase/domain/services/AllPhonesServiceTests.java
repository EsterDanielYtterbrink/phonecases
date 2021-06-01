package com.ytterbrink.phonecase.domain.services;

import java.util.LinkedList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import com.ytterbrink.phonecase.domain.data.Phone;
import com.ytterbrink.phonecase.domain.data_ports.AllPhones;
import com.ytterbrink.phonecase.exceptions.NothingToSeeYetException;

class AllPhonesServiceTests {

    @Test
    void allPhonesThrowsNothingToSeeYetExceptionWhenResultIsEmpty() {
        final AllPhonesService service = new AllPhonesService(new AllPhonesMock());
        final Throwable thrown = Assertions.catchThrowable(service::allPhones);
        Assertions.assertThat(thrown).isInstanceOf(NothingToSeeYetException.class);
    }

    private static class AllPhonesMock implements AllPhones {
        @Override
        public List<Phone> allPhones() {
            return new LinkedList<>();
        }
    }
}
