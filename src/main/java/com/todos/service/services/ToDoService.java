package com.todos.service.services;

import java.util.ArrayList;
import java.util.HashMap;

public interface ToDoService <T> {
    HashMap<String, T> getAll();

    void save(T item);

    void update(T item);

    T find(String id);
}
