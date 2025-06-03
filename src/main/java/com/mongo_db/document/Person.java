package com.mongo_db.document;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Document(collection = "person_details")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Person implements Serializable, UserDetails {
    @Id
    private String personId;
    @NotNull
    private String firstName;
    private String lastName;
    //@Size(min = 1,max = 100,message = "Age mush be in between 1-100!")
    private Integer age;
    @Email(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message = "Invalid Email format!")
    private String email;
    @Pattern(regexp = "^[1-9]\\d{9}$", message = "Mobile number must be exactly 10 digits and not start with 0!")
    private String mobileNo;
    private Set<String> hobbies;
    @Transient
    private Set<Address> addresses;

    private String role; // ✅ Add this field, e.g. "ROLE_USER"
    private String password; // ✅ Add this field if needed for login

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role));
    }

    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return "";
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}