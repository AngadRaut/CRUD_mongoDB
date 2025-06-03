package com.mongo_db.service;

import com.mongo_db.document.Person;
import com.mongo_db.document.PersonDTO;

import java.util.List;

public interface PersonService {
    public Person addPerson(Person person);
    public PersonDTO getPerson(String personId);
    public List<PersonDTO> getAllPerson();
    public void deletePerson(String personId);

}
