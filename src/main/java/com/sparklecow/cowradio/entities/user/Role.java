package com.sparklecow.cowradio.entities.user;

public enum Role {

    ADMIN("ADMIN"),
    USER("USER"),
    USER_VIP("USER_VIP"),
    ARTIST("ARTIST");

    private final String role;

    Role(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
