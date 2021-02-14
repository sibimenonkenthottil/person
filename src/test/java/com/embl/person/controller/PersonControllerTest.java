package com.embl.person.controller;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import com.embl.person.dto.PersonDTO;
import com.embl.person.service.PersonService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest(PersonController.class)
public class PersonControllerTest {

    @Autowired
    protected MockMvc mockMVC;

    @MockBean
    PersonService personService;

    String uri = "/person";

    @Test
    public void testPersonCreate() throws Exception {

        PersonDTO personDTO = new PersonDTO("abc", "dec", 5, "red");
        personDTO.setId(1);

        when(this.personService.createOrUpdatePerson(any(PersonDTO.class))).thenReturn(personDTO);

        this.mockMVC
                .perform(post(uri).contentType(MediaType.APPLICATION_JSON).content(writeValueAsString(personDTO))
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print()).andExpect(status().isCreated())
                .andExpect(header().string("Location", "http://localhost/person/1")).andReturn();
        // assertTrue(true);
    }

    private String writeValueAsString(final Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (Exception ex) {

        }
        return null;
    }

    @Test
    public void testGetPerson() {

        PersonDTO personDTO = new PersonDTO("abc", "dec", 5, "red");
        personDTO.setId(1);

        when(this.personService.getPerson(any(Integer.class))).thenReturn(personDTO);

        try {
            this.mockMVC.perform(get(uri + "/" + 1)).andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.first_name").value("abc")).andExpect(jsonPath("$.id").value("1"))
                    .andDo(MockMvcResultHandlers.print()).andReturn();
        } catch (Exception e) {
            fail("Test case failed");
        }

    }

    @Test
    public void testGetAllPerson() {

        PersonDTO personDTO = new PersonDTO("abc", "dec", 5, "red");
        personDTO.setId(1);

        List<PersonDTO> ret = new ArrayList<PersonDTO>();
        ret.add(personDTO);
        when(this.personService.getAllPersons()).thenReturn(ret);

        try {
            this.mockMVC.perform(get(uri + "/all")).andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$[0].first_name").value("abc")).andExpect(jsonPath("$[0].id").value("1"))
                    .andDo(MockMvcResultHandlers.print()).andReturn();
        } catch (Exception e) {
            fail("Test case failed");
        }
    }

    @Test
    public void testPersonUpdate() {
        PersonDTO personDTO = new PersonDTO("abc", "dec", 5, "red");
        personDTO.setId(1);

        when(this.personService.createOrUpdatePerson(any(PersonDTO.class))).thenReturn(personDTO);

        try {
            this.mockMVC
                    .perform(put(uri+"/1").contentType(MediaType.APPLICATION_JSON).content(writeValueAsString(personDTO))
                            .accept(MediaType.APPLICATION_JSON))
                    .andDo(MockMvcResultHandlers.print()).andExpect(status().isOk()).andReturn();
        } catch (Exception e) {
            fail("Error occured while testing update");
        }
        
    }

    @Test
    public void testPersonDelete(){
        
        doNothing().when(this.personService.deletePerson(any(Integer.class)));
        
        try {
            this.mockMVC
                    .perform(delete(uri+"/1"))  
                    .andDo(MockMvcResultHandlers.print()).andExpect(status().isOk()).andReturn();
        } catch (Exception e) {
            fail("Error occured while testing update");
        }
    }
}
