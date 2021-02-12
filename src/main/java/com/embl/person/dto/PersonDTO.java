package com.embl.person.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonDTO {
    
    private String first_name;
    private String last_name;
    private int age;
    private String favourite_color;
    private int id;

    public PersonDTO(String first_name, String last_name, int age, String favourite_color) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.age = age;
        this.favourite_color = favourite_color;
    }
}
