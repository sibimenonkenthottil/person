package com.embl.person.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.embl.person.domain.Person;
import com.embl.person.dto.PersonDTO;
import com.embl.person.repository.PersonRepository;

import org.aspectj.lang.annotation.Before;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
public class PersonServiceTest {
    
    @Mock
    PersonRepository personRepository;

    @InjectMocks
    PersonService personService;

    PersonDTO personDTO;
    Person person;

    
    @Test
    public void testCreateOrUpdatePerson(){
        personDTO = new PersonDTO("first_name","last_name",1,"blue");
        person = new Person("first_name","last_name",1,"blue");
        when(this.personRepository.save(any(Person.class))).thenReturn(person);

        PersonDTO result = personService.createOrUpdatePerson(personDTO);
        verify(personRepository,times(1)).save(any(Person.class));
        assertEquals(result.getFirst_name(),"first_name");        




    }

    @Test
    public void testGetPerson(){
        personDTO = new PersonDTO("first_name","last_name",1,"blue");
        person = new Person("first_name","last_name",1,"blue");        
        when(this.personRepository.findById(1)).thenReturn(Optional.of(person));
        PersonDTO result = personService.getPerson(1);
        verify(personRepository,times(1)).findById(any(Integer.class));
        assertEquals(result.getFirst_name(),"first_name");
    }

    @Test
    public void testGetAllPerson(){
        personDTO = new PersonDTO("first_name","last_name",1,"blue");
        person = new Person("first_name","last_name",1,"blue");    
        List<Person> persons = new ArrayList<Person>();
        persons.add(person);
        when(this.personRepository.findAll()).thenReturn(persons);
        List<PersonDTO> result = personService.getAllPersons();
        verify(personRepository,times(1)).findAll();
        assertEquals(result.size(), 1);
    }

    @Test
    public void testDeletePerson(){
        when(this.personRepository.existsById(any(Integer.class))).thenReturn(true);
        personService.deletePerson(1); 
        verify(personRepository,times(1)).deleteById(1);
    }
}
