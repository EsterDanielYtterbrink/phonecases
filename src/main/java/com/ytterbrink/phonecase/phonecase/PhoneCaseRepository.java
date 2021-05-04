package com.ytterbrink.phonecase.phonecase;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface PhoneCaseRepository extends JpaRepository<PhoneCase, Long> {
}
