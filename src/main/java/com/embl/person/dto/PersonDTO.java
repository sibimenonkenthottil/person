package com.embl.person.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PersonDTO {
    
    private String first_name;
    private String last_name;
    private int age;
    private String favourite_color;
}
