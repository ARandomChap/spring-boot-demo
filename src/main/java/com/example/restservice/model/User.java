package com.example.restservice.model;

import java.util.UUID;

public class User {
    private final UUID uuid;
    private final String email;
    private final String name;

    public User(UUID uuid, String name, String email) {
        this.uuid = uuid;
        this.name = name;
        this.email = email;
    }

    public UUID getId() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public boolean idCheck(Object obj) {
        if (obj instanceof User) {
            return (((User) obj).uuid == this.uuid);
        } else {
            return false;
        }
    }
    public boolean emailCheck(Object obj) {
        if (obj instanceof User) {
            return (((User) obj).email.equals(this.email));
        } else {
            return false;
        }
    }
}
