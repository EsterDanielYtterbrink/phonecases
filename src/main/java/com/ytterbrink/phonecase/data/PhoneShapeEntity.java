package com.ytterbrink.phonecase.data;

import com.ytterbrink.phonecase.domain.data.Phone;
import com.ytterbrink.phonecase.domain.data.PhoneCase;
import com.ytterbrink.phonecase.domain.data.PhoneShape;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
@Data
@NoArgsConstructor
public class PhoneShapeEntity implements PhoneShape {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @OneToMany(targetEntity = PhoneEntity.class, mappedBy = "phoneShape")
    private Set<Phone> phones;

    @OneToMany(targetEntity = PhoneCaseEntity.class, mappedBy = "phoneShape")
    private Set<PhoneCase> phoneCases;
}
