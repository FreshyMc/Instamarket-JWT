package com.example.jwtinstamarket.service.impl;

import com.example.jwtinstamarket.exception.UserAlreadyRegistered;
import com.example.jwtinstamarket.model.dto.UserDTO;
import com.example.jwtinstamarket.model.entity.RoleEntity;
import com.example.jwtinstamarket.model.entity.UserEntity;
import com.example.jwtinstamarket.model.enums.RoleName;
import com.example.jwtinstamarket.repository.RoleRepository;
import com.example.jwtinstamarket.repository.UserRepository;
import com.example.jwtinstamarket.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    public UserDTO registerUser(UserDTO user) throws UserAlreadyRegistered {
        if(userRepository.findByEmail(user.getEmail()).isPresent()){
            throw new UserAlreadyRegistered();
        }

        UserEntity newUser = modelMapper.map(user, UserEntity.class);

        RoleEntity userRole = roleRepository.findByName(RoleName.USER);

        newUser.setRoles(List.of(userRole));
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));

        UserEntity saved = userRepository.save(newUser);

        return modelMapper.map(saved, UserDTO.class);
    }
}
