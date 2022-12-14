package ru.kata.spring.boot_security.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "roles")
@Getter
@Setter
public class Role {
    @Id
    private String role;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private Set<User> users;

    public Role(String s) {
        this.role = s;
    }

    public Role() {
    }

    @Override
    public String toString() {
        return role;
    }

}