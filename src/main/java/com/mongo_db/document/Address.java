package com.mongo_db.document;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Address implements Serializable {
    @NotNull(message = "Pin code should not be null!")
    @NotEmpty(message = "Pin code should not be empty!")
    private int pinCode;
    private String street;
    private String city;
    private String district;
    @NotEmpty(message = "Please give the state name!")
    private String state;
}