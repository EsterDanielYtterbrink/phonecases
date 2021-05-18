package com.ytterbrink.phonecase.domain.services;

import com.ytterbrink.phonecase.domain.PhoneCase;
import com.ytterbrink.phonecase.domain.data_ports.AllPhoneCases;
import com.ytterbrink.phonecase.exceptions.NothingToSeeYetException;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;

class AllPhoneCasesServiceTest {

    private class AllPhoneCasesMock implements AllPhoneCases {
        @Override
        public List<PhoneCase> allPhoneCases() {
            return new LinkedList<>();
        }
    }

    @Test
    public void throwsNothingToSeeYetExceptionForEmptyResult(){
        AllPhoneCases allPhoneCases = new AllPhoneCasesMock();
        AllPhoneCasesService service = new AllPhoneCasesService(allPhoneCases);
        final Throwable thrown = catchThrowable(service::allPhoneCases);
        assertThat(thrown).isInstanceOf(NothingToSeeYetException.class);
    }

}