package com.example.jwtinstamarket.model.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserDTO {
    @Email
    @NotBlank
    private String email;
    @Size(min = 8, max = 25)
    @NotBlank
    private String password;
    @Size(min = 2)
    @NotBlank
    private String firstName;
    @Size(min = 2)
    @NotBlank
    private String lastName;

    public UserDTO() {
    }

    public String getEmail() {
        return email;
    }

    public UserDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserDTO setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }
}
