package com.example.jwtinstamarket.model.entity;

import com.example.jwtinstamarket.model.enums.RoleName;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class RoleEntity extends BaseEntity {
    @Enumerated(EnumType.STRING)
    private RoleName name;

    public RoleEntity() {
    }

    public RoleName getName() {
        return name;
    }

    public RoleEntity setName(RoleName name) {
        this.name = name;
        return this;
    }
}
