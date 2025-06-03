package com.mongo_db.security;

import com.mongo_db.exceptions.PersonNotFoundException;
import com.mongo_db.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomerUserDetailsService implements UserDetailsService {
    private PersonRepository personRepository;

    @Autowired
    public CustomerUserDetailsService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public CustomerUserDetailsService() {
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return personRepository.findPersonByEmail(email).orElseThrow(()->new PersonNotFoundException("Not Found!!"));
    }

}
