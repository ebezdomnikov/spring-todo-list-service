package com.todos.service.domain;

import com.todos.service.model.ToDo;

public interface ToDoFactory {
    /**
     * Create new aggregate
     * @param id
     * @param text
     * @return
     */
    ToDoAggregate createNewToDo(String id, String text);
}
