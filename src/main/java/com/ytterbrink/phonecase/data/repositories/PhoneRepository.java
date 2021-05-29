package com.ytterbrink.phonecase.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ytterbrink.phonecase.data.PhoneEntity;
import com.ytterbrink.phonecase.domain.data.Phone;

public interface PhoneRepository extends JpaRepository<PhoneEntity, Long> {
    Phone findOneByName(String name);

}
