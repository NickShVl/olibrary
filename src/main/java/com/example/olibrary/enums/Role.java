package com.example.olibrary.enums;

public enum Role {
    USER, ADMIN;
    public String asAuthority() {
        return "ROLE_" + name();
    }
}
