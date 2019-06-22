package com.todos.service.domain;

import java.util.Objects;

public class ToDoText {
    private String text;

    public ToDoText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ToDoText toDoText = (ToDoText) o;
        return Objects.equals(text, toDoText.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text);
    }
}
