package com.ytterbrink.phonecase.domain.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import java.util.UUID;

@Entity
@EqualsAndHashCode
@Table(name = "phone", uniqueConstraints = @UniqueConstraint(columnNames = {"name"}))
public class Phone {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Getter
    @Setter
    private String brand;

    @Getter
    @Setter
    private String name;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JsonIgnore
    @Getter private PhoneShape phoneShape;

    protected Phone() { }
    public Phone(String name, PhoneShape phoneShape) {
        this.name = name;
        this.phoneShape = phoneShape;
    }

}
