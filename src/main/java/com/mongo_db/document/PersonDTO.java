package com.mongo_db.document;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PersonDTO implements Serializable {
    private String personId;
    private String firstName;
    private String lastName;
    private Integer age;
    private String email;
    private String mobileNo;
    private Set<String> hobbies;
    private Set<Address> addresses;
}