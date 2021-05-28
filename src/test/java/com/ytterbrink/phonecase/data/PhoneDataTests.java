package com.ytterbrink.phonecase.data;

import com.ytterbrink.phonecase.domain.data.Phone;
import com.ytterbrink.phonecase.data.repositories.PhoneRepository;
import com.ytterbrink.phonecase.domain.data.PhoneShape;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class PhoneDataTests {

   @Autowired
   private PhoneRepository repository;
   @Autowired
   private TestEntityManager testEntityManager;

   @Test
   public void shouldFindPhoneByName(){
       PhoneShape shape = new PhoneShape();
       testEntityManager.persist(shape);
       Phone iPhone = new Phone("big", shape);
       testEntityManager.persist(iPhone);
       assertThat(repository.findOneByName(iPhone.getName())).isEqualTo(iPhone);
   }

}
