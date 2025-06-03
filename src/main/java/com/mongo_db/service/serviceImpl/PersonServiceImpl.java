package com.mongo_db.service.serviceImpl;

import com.mongo_db.document.Person;
import com.mongo_db.document.PersonDTO;
import com.mongo_db.repository.PersonRepository;
import com.mongo_db.service.PersonService;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@NoArgsConstructor
public class PersonServiceImpl implements PersonService {
    private static final Logger log = LoggerFactory.getLogger(PersonServiceImpl.class);
    private PersonRepository personRepository;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    @CachePut(value = "person", key = "#result.personId")
    public Person addPerson(Person person) {
        log.info("Request to add new Person received at service method!");
        log.info("New person record stored via repo method!");
        return this.personRepository.save(person);
    }

    @Override
    @CachePut(value = "person", key = "#result.personId")
    public PersonDTO getPerson(String personId) {
        log.info("Request to get Person details received at service method with id: {}", personId);
        Person person = this.personRepository.findById(personId).orElseThrow(() -> new RuntimeException("Person not found with id :" + personId));
        log.debug("finding the person with id :{}", personId);
        PersonDTO dto = new PersonDTO();
        log.debug("storing found data in  personDTO");
        dto.setPersonId(personId);
        dto.setFirstName(person.getFirstName());
        dto.setLastName(person.getLastName());
        dto.setEmail(person.getEmail());
        dto.setMobileNo(person.getMobileNo());
        dto.setAge(person.getAge());
        dto.setHobbies(person.getHobbies());
        log.debug("returning the found data in to controller method");
        return dto;
    }

    @Override
    @Cacheable(value = "persons", key = "'allPersons'")
    public List<PersonDTO> getAllPerson() {
        List<Person> all = this.personRepository.findAll();
        // map person with personDTO
        List<PersonDTO> list = all.stream().map(person -> new PersonDTO(person.getPersonId(), person.getFirstName(), person.getLastName(), person.getAge(),
                person.getEmail(), person.getMobileNo(), person.getHobbies(), person.getAddresses())).toList();
        log.debug("fetched all persons:{}",list);
        return list;
    }

    @Override
    @CacheEvict(value = "person", key = "#personId")
    public void deletePerson(String personId) {
        log.debug("delete the person request come in service deletePerson method");
        Person person = this.personRepository.findById(personId).orElseThrow(() -> new RuntimeException("Person with this id is not found in records!"));
        this.personRepository.delete(person);
        log.debug("person with given id is deleted successfully!");
    }
}