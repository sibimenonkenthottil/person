package com.embl.person.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.embl.person.domain.Person;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
@DirtiesContext
public class PersonRepositoryTest {
    
    @Autowired
    PersonRepository personRepository;

    
    @Test
    public void testPersonCreate(){
        Person person = new Person("abc","cde",1,"red");
        Person saved = personRepository.save(person);
        assertEquals(saved, person);
        assertNotNull(saved);
    }


    @Test
    public void testPersonRead(){
        Person person = new Person("abc","cde",1,"red");
        Person saved = personRepository.save(person);                
        Person retrieved = personRepository.findById(saved.getId()).get();
        assertNotNull(retrieved);        
    }

    @Test
    public void testPersonUpdate(){
        Person person = new Person("abc","cde",1,"red");
        Person saved = personRepository.save(person);      

        saved.setFavourite_color("blue");
        Person updated = personRepository.save(saved);
        assertEquals(updated.getFavourite_color(),"blue");
    }

    @Test
    public void testPersonDelete(){
        Person person = new Person("abc","cde",1,"red");
        Person saved = personRepository.save(person);
        assertEquals(saved, person);
        assertNotNull(saved);
    }
}
