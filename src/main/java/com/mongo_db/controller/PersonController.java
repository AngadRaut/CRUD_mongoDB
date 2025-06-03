package com.mongo_db.controller;

import com.mongo_db.document.Person;
import com.mongo_db.document.PersonDTO;
import com.mongo_db.service.PersonService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/person")
public class PersonController {
    private static final Logger log = LoggerFactory.getLogger(PersonController.class);

    private PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping("/save")
    public ResponseEntity<?> savePerson(@RequestBody @Valid Person person) {
        log.info("Request to add new Person received at controller method!");
        Person person1 = this.personService.addPerson(person);
        log.debug("New person added successfully! personId : {}", person1.getPersonId());
        return ResponseEntity.status(HttpStatus.CREATED).body(person1.getPersonId());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getPerson(@PathVariable("id") String personId) {
        log.info("Request to get Person details received at controller method with id: {}", personId);
        PersonDTO person = this.personService.getPerson(personId);
        log.debug("New person added successfully!{} personId : ", personId);
        return ResponseEntity.status(HttpStatus.OK).body(person);
    }

    // delete api
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletePersonById(@PathVariable("id") String personId) {
        this.personService.deletePerson(personId);
        return ResponseEntity.status(HttpStatus.OK).body("deleted");
    }

    // fetch all
    @GetMapping("/get-all")
    public ResponseEntity<?> getAllPerson() {
        log.info("Request to get All Person details received at controller method");
        List<PersonDTO> allPerson = this.personService.getAllPerson();
        if (allPerson.isEmpty()) {
            log.debug("No record found in the database!");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No record found!");
        } else {
            log.debug("Record found in the database and return!" + allPerson);
            return ResponseEntity.status(HttpStatus.OK).body("found" + allPerson);
        }
    }
}