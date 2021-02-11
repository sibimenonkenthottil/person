package com.embl.person.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.print.attribute.standard.Media;

import com.embl.person.dto.PersonDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest(PersonController.class)
public class PersonControllerTest {
    
    @Autowired
    protected MockMvc mockMVC;    

    String uri = "/person";

    @Test
    public void testPersonCreate() throws Exception{

        PersonDTO personDTO = new PersonDTO("abc","dec",5,"red");

        this.mockMVC
        .perform(post(uri)
        .contentType(MediaType.APPLICATION_JSON)
        .content(writeValueAsString(personDTO))
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status()
        .isCreated())
        .andReturn();
        //assertTrue(true);
    }

    private String writeValueAsString(final Object object){
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (Exception ex){

        }
        return null;
    }
}
