package com.todos.service.response;

import com.todos.service.model.ToDo;

public class ToDoResource {

    private ToDo model;

    public ToDoResource(ToDo model) {
        this.model = model;
    }

    public String getId() {
        return model.getId().toString();
    }

    public String getText() {
        return model.getText().toString();
    }

    public boolean isDone() {
        return model.isDone();
    }

    public boolean isFavorite() {
        return model.isFavorite();
    }
}
