package com.ytterbrink.phonecase.data.repositories;

import com.ytterbrink.phonecase.domain.PhoneShape;
import com.ytterbrink.phonecase.domain.PhoneCase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface PhoneCaseRepository extends JpaRepository<PhoneCase, Long> {
    List<PhoneCase> findAllByPhoneShape(PhoneShape phoneShape);
}
