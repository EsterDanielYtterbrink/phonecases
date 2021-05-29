package com.ytterbrink.phonecase.data;

import com.ytterbrink.phonecase.domain.data.PhoneShape;
import com.ytterbrink.phonecase.domain.data.PhoneCase;
import com.ytterbrink.phonecase.data.repositories.PhoneCaseRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class PhoneCaseDataTests {

   @Autowired
   private PhoneCaseRepository repository;
   @Autowired
   private TestEntityManager testEntityManager;

   @Test
   public void shouldFindCaseByShape(){
       PhoneShape small = new PhoneShapeEntity();
       PhoneShape big = new PhoneShapeEntity();
       PhoneCase phoneCaseSmall = new PhoneCaseEntity("small");
       PhoneCase phoneCaseBig = new PhoneCaseEntity("big");
       testEntityManager.persist(small);
       testEntityManager.persist(big);
       phoneCaseBig.setPhoneShape(big);
       phoneCaseSmall.setPhoneShape(small);
       testEntityManager.persist(phoneCaseSmall);
       testEntityManager.persist(phoneCaseBig);
       List<PhoneCase> caseList = repository.findAllByPhoneShape(small);
       assertThat(caseList).isNotEmpty();
       assertThat(caseList.get(0)).isEqualTo(phoneCaseSmall);
   }

}
