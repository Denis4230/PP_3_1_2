package ru.kata.spring.boot_security.demo.security;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

@Data
public class GrantedAuthorityImpl implements GrantedAuthority {
    private String authority;

    public GrantedAuthorityImpl(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }
}