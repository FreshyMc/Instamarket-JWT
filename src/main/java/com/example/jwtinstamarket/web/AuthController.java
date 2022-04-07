package com.example.jwtinstamarket.web;

import com.example.jwtinstamarket.model.dto.UserDTO;
import com.example.jwtinstamarket.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody @Valid UserDTO userDTO){
        userService.registerUser(userDTO);

        return ResponseEntity.ok().build();
    }
}
