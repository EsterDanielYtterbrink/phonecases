package com.ytterbrink.phonecase.domain.services;

import java.util.LinkedList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import com.ytterbrink.phonecase.domain.data.PhoneCase;
import com.ytterbrink.phonecase.domain.data_ports.AllPhoneCases;
import com.ytterbrink.phonecase.exceptions.NothingToSeeYetException;

class AllPhoneCasesServiceTests {

    private class AllPhoneCasesMock implements AllPhoneCases {
        @Override
        public List<PhoneCase> allPhoneCases() {
            return new LinkedList<>();
        }
    }

    @Test
    public void throwsNothingToSeeYetExceptionForEmptyResult(){
        final AllPhoneCases allPhoneCases = new AllPhoneCasesMock();
        final AllPhoneCasesService service = new AllPhoneCasesService(allPhoneCases);
        final Throwable thrown = Assertions.catchThrowable(service::allPhoneCases);
        Assertions.assertThat(thrown).isInstanceOf(NothingToSeeYetException.class);
    }

}
