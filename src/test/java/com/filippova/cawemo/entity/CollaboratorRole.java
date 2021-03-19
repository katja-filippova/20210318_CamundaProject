package com.filippova.cawemo.entity;

public enum CollaboratorRole {

    EDITOR(1),
    COMMENTER(2),
    VIEWER(3);

    private final int index;

    CollaboratorRole(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}
