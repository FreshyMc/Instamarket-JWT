package com.example.jwtinstamarket.init;

import com.example.jwtinstamarket.model.entity.RoleEntity;
import com.example.jwtinstamarket.model.enums.RoleName;
import com.example.jwtinstamarket.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DBInit implements CommandLineRunner {
    private final RoleRepository roleRepository;

    public DBInit(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        this.initializeRoles();
    }

    private void initializeRoles(){
        if(roleRepository.count() == 0){
            Arrays.stream(RoleName.values()).map(r -> {
                RoleEntity roleEntity = new RoleEntity();

                roleEntity.setName(r);

                return roleEntity;
            }).forEach(roleRepository::save);
        }
    }
}
