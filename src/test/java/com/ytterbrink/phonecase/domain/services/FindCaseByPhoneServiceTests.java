package com.ytterbrink.phonecase.domain.services;

import com.ytterbrink.phonecase.domain.services.FindCasesByPhoneNameService;
import com.ytterbrink.phonecase.domain.*;
import com.ytterbrink.phonecase.domain.data_ports.FindPhoneByName;
import com.ytterbrink.phonecase.domain.data_ports.FindCasesByPhone;
import com.ytterbrink.phonecase.doubles.FindPhoneByNameMock;
import com.ytterbrink.phonecase.exceptions.NoMatchingPhoneException;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class FindCaseByPhoneServiceTests {

    private class FindPhoneCasesByPhoneMock implements FindCasesByPhone {

        private List<PhoneCase> cases;
        private Phone phone;

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
    public void canGetAllCasesForAPhone() throws NoMatchingPhoneException {
        Phone aPhone = new Phone("iPhone5", new PhoneShape());
        List<PhoneCase> cases = Arrays.asList(new PhoneCase("fancy"), new PhoneCase("plain"));
        FindPhoneCasesByPhoneMock casesFinder = new FindPhoneCasesByPhoneMock(aPhone, cases);
        FindPhoneByName phoneFinder = new FindPhoneByNameMock(aPhone);

        FindCasesByPhoneNameService service = new FindCasesByPhoneNameService( phoneFinder, casesFinder);
        assertThat(service.findCaseByPhone(aPhone.getName())).isEqualTo(cases);
    }

    @Test
    public void canDealWithNoPhone(){
        Phone aPhone = new Phone("iPhone5", new PhoneShape());
        FindPhoneCasesByPhoneMock casesFinder = new FindPhoneCasesByPhoneMock(aPhone, null);
        FindPhoneByName phoneFinder = new FindPhoneByNameMock(null);
        FindCasesByPhoneNameService service = new FindCasesByPhoneNameService(phoneFinder, casesFinder);
        final Throwable thrown = catchThrowable(()-> service.findCaseByPhone("missingPhone"));
        assertThat(thrown).isInstanceOf(NoMatchingPhoneException.class);
    }
}
