package com.ytterbrink.phonecase.data.repositories;

import com.ytterbrink.phonecase.domain.data.PhoneShape;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneShapeRepository extends JpaRepository<PhoneShape, Long> {
}
