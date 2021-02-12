package com.embl.person.service;

import java.util.List;
import java.util.stream.Collectors;

import com.embl.person.domain.Person;
import com.embl.person.dto.PersonDTO;
import com.embl.person.repository.PersonRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    ModelMapper modelMapper = new ModelMapper();

    @Autowired
    PersonRepository personRepository;

	public PersonDTO createOrUpdatePerson(PersonDTO personDTO) {

        Person entity = modelMapper.map(personDTO, Person.class);
        Person saved = personRepository.save(entity);
        return modelMapper.map(saved,PersonDTO.class);
	}

	public PersonDTO getPerson(int id) {
		Person saved = personRepository.findById(id).get();
        return modelMapper.map(saved,PersonDTO.class);
	}

	public List<PersonDTO> getAllPersons() {
        List<Person> persons = personRepository.findAll();
        return persons.stream().map(person -> {
           return  modelMapper.map(person,PersonDTO.class);
        }).collect(Collectors.toList());		
	}
    
}
