package com.ytterbrink.phonecase.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor

public class PhoneShape {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @OneToMany(targetEntity = Phone.class, mappedBy = "phoneShape")
    private Set<Phone> phones;

    @OneToMany(targetEntity = PhoneCase.class, mappedBy = "phoneShape")
    private Set<PhoneCase> phoneCases;
}
