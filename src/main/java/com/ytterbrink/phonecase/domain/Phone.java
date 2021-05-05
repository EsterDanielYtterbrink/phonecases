package com.ytterbrink.phonecase.domain;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity
@EqualsAndHashCode
@Table(name = "phone", uniqueConstraints = @UniqueConstraint(columnNames = {"name"}))
public class Phone {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Getter @Setter private String brand;
    @Getter @Setter private String name;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @Getter private PhoneShape phoneShape;

    protected Phone() { }
    public Phone(String name, PhoneShape phoneShape) {
        this.name = name;
        this.phoneShape = phoneShape;
    }

    @Data
    @AllArgsConstructor
    public static class PhoneParameters {

        Phone newPhone;
        String similarPhoneName;
    }
}
