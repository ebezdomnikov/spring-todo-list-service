package com.todos.service.domain;

import java.util.Objects;

public class ToDoId {
    private String id;

    public ToDoId(String id) {
        this.id = id;
    }

    public String toString() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ToDoId toDoId = (ToDoId) o;
        return Objects.equals(id, toDoId.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
