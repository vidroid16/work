package com.example.demo.security.models;

public enum Permission {
    SAVE("users:save"),
    DELETE("users:delete"),
    GET("users:get"),
    AUTH("auth:auth");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
