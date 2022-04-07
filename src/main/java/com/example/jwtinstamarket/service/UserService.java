package com.example.jwtinstamarket.service;

import com.example.jwtinstamarket.exception.UserAlreadyRegistered;
import com.example.jwtinstamarket.model.dto.UserDTO;

public interface UserService {
    public UserDTO registerUser(UserDTO user) throws UserAlreadyRegistered;
}
