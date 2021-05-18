package com.ytterbrink.phonecase.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

import java.util.Optional;
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
    @JsonIgnore
    @Getter private PhoneShape phoneShape;

    protected Phone() { }
    public Phone(String name, PhoneShape phoneShape) {
        this.name = name;
        this.phoneShape = phoneShape;
    }

    @Data
    public static class PhoneParameters {
        String newPhoneName;
        Optional<String> similarPhoneName;

        public PhoneParameters(String newPhoneName, String similarPhoneName) {
            this.newPhoneName = newPhoneName;
            this.similarPhoneName = Optional.ofNullable(similarPhoneName);
        }
    }
}
