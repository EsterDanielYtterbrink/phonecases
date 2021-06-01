package com.ytterbrink.phonecase.data;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.ytterbrink.phonecase.data.repositories.PhoneCaseRepository;
import com.ytterbrink.phonecase.domain.data.PhoneCase;
import com.ytterbrink.phonecase.domain.data.PhoneShape;

@DataJpaTest
public class PhoneCaseDataTests {

    @Autowired
    private PhoneCaseRepository repository;
    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void shouldFindCaseByShape(){
        final PhoneShape small = getPhoneShape();
        final PhoneCase phoneCaseSmall = getPhoneCase(small, "small");
        final PhoneShape big = getPhoneShape();
        getPhoneCase(big, "big");
        final List<PhoneCase> caseList = repository.findAllByPhoneShape(small);
        Assertions.assertThat(caseList).isNotEmpty();
        Assertions.assertThat(caseList.get(0)).isEqualTo(phoneCaseSmall);
    }

    @NotNull
    private PhoneCase getPhoneCase(PhoneShape shape, String name) {
        final PhoneCase phoneCase = new PhoneCaseEntity(name);
        phoneCase.setPhoneShape(shape);
        testEntityManager.persist(phoneCase);
        return phoneCase;
    }

    @NotNull
    private PhoneShape getPhoneShape() {
        final PhoneShape shape = new PhoneShapeEntity();
        testEntityManager.persist(shape);
        return shape;
    }

}
