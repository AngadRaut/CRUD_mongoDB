package com.mongo_db.repository;

import com.mongo_db.document.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends MongoRepository<Person,String> {
    public static final Logger log = LoggerFactory.getLogger(PersonRepository.class);
    Optional<Person> findPersonByEmail(String email);
    Optional<Person> findPersonByLastName(String lastName);
}