package com.example.jwtinstamarket.repository;

import com.example.jwtinstamarket.model.entity.RoleEntity;
import com.example.jwtinstamarket.model.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    RoleEntity findByName(RoleName name);
}
