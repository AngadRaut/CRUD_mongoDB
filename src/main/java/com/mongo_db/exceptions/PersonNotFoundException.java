package com.mongo_db.exceptions;


public class PersonNotFoundException extends RuntimeException{
    public PersonNotFoundException(String msg){
        super(msg);
    }
}
