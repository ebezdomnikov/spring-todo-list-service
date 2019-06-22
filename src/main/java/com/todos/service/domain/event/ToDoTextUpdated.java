package com.todos.service.domain.event;

import com.todos.service.domain.ToDoId;
import com.todos.service.domain.ToDoText;

public class ToDoTextUpdated extends Event {
    private ToDoId id;
    private ToDoText text;

    public ToDoTextUpdated(Object source, ToDoId id, ToDoText text) {
        super(source);
        this.id = id;
        this.text = text;
    }

    public ToDoId id() {
        return id;
    }

    public ToDoText text() {
        return text;
    }
}
