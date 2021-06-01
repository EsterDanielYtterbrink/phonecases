package com.ytterbrink.phonecase.data;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.ytterbrink.phonecase.data.repositories.PhoneRepository;
import com.ytterbrink.phonecase.domain.data.Phone;
import com.ytterbrink.phonecase.domain.data.PhoneShape;

@DataJpaTest
public class PhoneDataTests {

    @Autowired
    private PhoneRepository repository;
    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void shouldFindPhoneByName(){
        final PhoneShape shape = new PhoneShapeEntity();
        testEntityManager.persist(shape);
        final Phone iPhone = new PhoneEntity("big", shape);
        testEntityManager.persist(iPhone);
        Assertions.assertThat(repository.findOneByName(iPhone.getName())).isEqualTo(iPhone);
    }

}
