package com.embl.person.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
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
