package com.ytterbrink.phonecase.data.repositories;

import com.ytterbrink.phonecase.domain.data.PhoneShape;
import com.ytterbrink.phonecase.domain.data.PhoneCase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface PhoneCaseRepository extends JpaRepository<PhoneCase, Long> {
    List<PhoneCase> findAllByPhoneShape(PhoneShape phoneShape);
}
