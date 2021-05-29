package com.ytterbrink.phonecase.data.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.ytterbrink.phonecase.data.PhoneCaseEntity;
import com.ytterbrink.phonecase.domain.data.PhoneCase;
import com.ytterbrink.phonecase.domain.data.PhoneShape;

@Component
public interface PhoneCaseRepository extends JpaRepository<PhoneCaseEntity, Long> {
    List<PhoneCase> findAllByPhoneShape(PhoneShape phoneShape);
}
