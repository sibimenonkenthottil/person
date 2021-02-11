package com.embl.person.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Person {
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String first_name;
    private String last_name;
    private int age;
    private String favourite_color;

    public Person(String first_name, String last_name, int age, String favourite_color) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.age = age;
        this.favourite_color = favourite_color;
    }

}
