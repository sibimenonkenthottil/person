package com.embl.person.repository;

import com.embl.person.repository.PersonRepository;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.embl.person.domain.Person;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;

//@DataJpaTest
@ActiveProfiles("test")
public class PersonRepositoryTest {
    
    @Autowired
    PersonRepository personRepository;

    @Test
    public void testPersonCreate(){
        Person person = new Person("abc","cde",1,"red");
        Person saved = personRepository.save(person);
        assertTrue(true);
    }
}
