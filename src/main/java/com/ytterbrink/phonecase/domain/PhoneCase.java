package com.ytterbrink.phonecase.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Entity
public class PhoneCase {

    protected PhoneCase(){}
    public PhoneCase(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter @Setter private Long id;

    @Getter @Setter private String name;

    @ManyToOne()
    @JsonIgnore
    @Getter @Setter private PhoneShape phoneShape;

    @Override
    public String toString() {
        return name;
    }


    @Data
    @AllArgsConstructor
    public static class PhoneCaseParameters {
        String name;
        String madeFor;
    }
}

