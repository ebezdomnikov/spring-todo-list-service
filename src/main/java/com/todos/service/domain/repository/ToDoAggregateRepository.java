package com.todos.service.domain.repository;

import com.todos.service.domain.ToDoAggregate;
import com.todos.service.domain.ToDoId;

public interface ToDoAggregateRepository {
    ToDoAggregate save(ToDoAggregate aggregate);
    ToDoAggregate find(ToDoId id) throws Exception;
}
