package com.ytterbrink.phonecase.domain;

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
    @Getter @Setter private PhoneShape phoneShape;

    @Override
    public String toString() {
        return name;
    }
}
