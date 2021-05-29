package com.ytterbrink.phonecase.data.repositories;

import com.ytterbrink.phonecase.data.PhoneEntity;
import com.ytterbrink.phonecase.domain.data.Phone;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PhoneRepository extends JpaRepository<PhoneEntity, Long> {
    Phone findOneByName(String name);

}
