package com.ytterbrink.phonecase.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class PhoneCase {

  

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private String name;

    @ManyToOne()
    @JsonIgnore
    @Getter
    @Setter
    private PhoneShape phoneShape;

    protected PhoneCase(){}
    public PhoneCase(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }


}

