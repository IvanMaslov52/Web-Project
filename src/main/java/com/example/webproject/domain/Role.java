package com.example.webproject.domain;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER,ADMIN;//пометка : кто он?


    @Override
    public String getAuthority() {
        return name();
    }

}
