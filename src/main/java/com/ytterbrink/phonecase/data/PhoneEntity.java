package com.ytterbrink.phonecase.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ytterbrink.phonecase.domain.data.Phone;
import com.ytterbrink.phonecase.domain.data.PhoneShape;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


@Entity
@EqualsAndHashCode
@Table(name = "phone", uniqueConstraints = @UniqueConstraint(columnNames = {"name"}))
public class PhoneEntity implements Phone {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;



    @ManyToOne(targetEntity = PhoneShapeEntity.class, cascade = CascadeType.PERSIST)
    @JsonIgnore
    private PhoneShape phoneShape;

    protected PhoneEntity() { }
    public PhoneEntity(String name, PhoneShape phoneShape) {
        this.name = name;
        this.phoneShape = phoneShape;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PhoneShape getPhoneShape() {
        return phoneShape;
    }
}

