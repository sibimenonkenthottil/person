package com.embl.person.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import com.embl.person.dto.PersonDTO;
import com.embl.person.service.PersonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/person")
public class PersonController {
    
    @Autowired
    PersonService personService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> createPerson(@RequestBody PersonDTO personDTO) {
        PersonDTO persisted = personService.createOrUpdatePerson(personDTO);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        .buildAndExpand(persisted.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PersonDTO getPerson(@PathVariable("id") int id) {
        return personService.getPerson(id);
    }
    
    @GetMapping(path = "/all")
    @ResponseStatus(HttpStatus.OK)
    public List<PersonDTO> getPerson() {
        return personService.getAllPersons();
    }

    @PutMapping(value="/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void putMethodName(@PathVariable String id, @RequestBody PersonDTO personDTO) {
        personService.createOrUpdatePerson(personDTO);
    }
}
