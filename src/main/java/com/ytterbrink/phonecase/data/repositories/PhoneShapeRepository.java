package com.ytterbrink.phonecase.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ytterbrink.phonecase.data.PhoneShapeEntity;

public interface PhoneShapeRepository extends JpaRepository<PhoneShapeEntity, Long> {
}
