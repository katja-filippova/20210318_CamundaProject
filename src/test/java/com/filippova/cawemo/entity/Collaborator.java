package com.filippova.cawemo.entity;

public class Collaborator {

    private final String email;
    private final CollaboratorRole role;

    public Collaborator(String email, CollaboratorRole role) {
        this.email = email;
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public CollaboratorRole getRole() {
        return role;
    }
}
