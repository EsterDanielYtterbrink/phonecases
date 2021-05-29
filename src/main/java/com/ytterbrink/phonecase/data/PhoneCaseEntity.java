package com.ytterbrink.phonecase.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ytterbrink.phonecase.domain.data.PhoneCase;
import com.ytterbrink.phonecase.domain.data.PhoneShape;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "phone_case", uniqueConstraints = @UniqueConstraint(columnNames = {"name"}))
public class PhoneCaseEntity implements PhoneCase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private String name;

    @ManyToOne(targetEntity = PhoneShapeEntity.class)
    @JsonIgnore
    @Getter
    private PhoneShape phoneShape;

    protected PhoneCaseEntity(){}
    
    public PhoneCaseEntity(String name) {
        this.name = name;
    }

    @org.jetbrains.annotations.NotNull
    public static PhoneCaseEntity entityFromInterface(PhoneCase phoneCase) {
        final PhoneCaseEntity entity = new PhoneCaseEntity(phoneCase.getName());
        entity.setPhoneShape(phoneCase.getPhoneShape());
        return entity;
    }

    @Override
    public void setPhoneShape(PhoneShape phoneShape) {
        this.phoneShape = phoneShape;
    }

    @Override
    public String toString() {
        return name;
    }

}
