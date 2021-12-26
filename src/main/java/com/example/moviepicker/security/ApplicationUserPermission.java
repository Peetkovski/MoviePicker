package com.example.moviepicker.security;

public enum ApplicationUserPermission {


    GUEST_READ("guest:read"),
    USER_WRITE("user:write"),
    USER_READ("user:read");


    private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}