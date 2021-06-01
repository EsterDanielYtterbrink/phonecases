package com.ytterbrink.phonecase.domain.services;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import com.ytterbrink.phonecase.data.PhoneCaseEntity;
import com.ytterbrink.phonecase.data.PhoneEntity;
import com.ytterbrink.phonecase.data.PhoneShapeEntity;
import com.ytterbrink.phonecase.domain.data.Phone;
import com.ytterbrink.phonecase.domain.data.PhoneCase;
import com.ytterbrink.phonecase.domain.data_ports.FindCasesByPhone;
import com.ytterbrink.phonecase.domain.data_ports.FindPhoneByName;
import com.ytterbrink.phonecase.domain.data_ports.FindPhoneByNameMock;
import com.ytterbrink.phonecase.exceptions.NoMatchingPhoneException;
import com.ytterbrink.phonecase.exceptions.NothingToSeeYetException;

public class FindCasesByPhoneNameServiceTests {

    public static final String PHONE = "iPhone5";

    private static class FindPhoneCasesByPhoneMock implements FindCasesByPhone {

        private final List<PhoneCase> cases;
        private final Phone phone;

        public FindPhoneCasesByPhoneMock(Phone phone, List<PhoneCase> cases) {
            this.phone = phone;
            this.cases = cases;
        }

        @Override
        public List<PhoneCase> findPhoneCaseByPhone(Phone phone) {
            //Asking for name throws nullpointer i hope
            if (phone.getName().equals(this.phone.getName())) {
                return this.cases;
            }
            return new LinkedList<>();
        }
    }

    @Test
    public void canGetAllCasesForAPhone()
            throws NoMatchingPhoneException,
            NothingToSeeYetException {
        final Phone aPhone = new PhoneEntity(PHONE, new PhoneShapeEntity());
        final List<PhoneCase> cases = Arrays.asList(
                new PhoneCaseEntity("fancy"),
                new PhoneCaseEntity("plain"));

        final FindCasesByPhoneNameService service = new FindCasesByPhoneNameService(
                new FindPhoneByNameMock(aPhone),
                new FindPhoneCasesByPhoneMock(aPhone, cases)
        );
        Assertions.assertThat(service.findCaseByPhone(aPhone.getName())).isEqualTo(cases);
    }

    @Test
    public void canDealWithNoPhone(){
        final Phone aPhone = new PhoneEntity(PHONE, new PhoneShapeEntity());
        final FindPhoneCasesByPhoneMock casesFinder = new FindPhoneCasesByPhoneMock(aPhone, null);
        final FindPhoneByName phoneFinder = new FindPhoneByNameMock(null);
        final FindCasesByPhoneNameService service = new FindCasesByPhoneNameService(
                phoneFinder,
                casesFinder
        );
        final Throwable thrown = Assertions.catchThrowable(
                ()-> service.findCaseByPhone("missingPhone")
        );
        Assertions.assertThat(thrown).isInstanceOf(NoMatchingPhoneException.class);
    }

    @Test
    public void canDealWithNoFoundCases(){
        final Phone aPhone = new PhoneEntity(PHONE, new PhoneShapeEntity());
        final FindPhoneCasesByPhoneMock casesFinder = new FindPhoneCasesByPhoneMock(
                aPhone,
                new LinkedList<>());
        final FindPhoneByName phoneFinder = new FindPhoneByNameMock(aPhone);
        final FindCasesByPhoneNameService service = new FindCasesByPhoneNameService(
                phoneFinder,
                casesFinder);
        final Throwable thrown = Assertions.catchThrowable(
                ()-> service.findCaseByPhone(aPhone.getName())
        );
        Assertions.assertThat(thrown).isInstanceOf(NothingToSeeYetException.class);

    }
}
