package com.todos.service.services;

import java.util.HashMap;
import com.todos.service.model.ToDo;

public class MemoryToDoServiceImpl implements ToDoService<ToDo> {

    private HashMap<String, ToDo> items = new HashMap<>();

    @Override
    public HashMap<String, ToDo> getAll() {
        return items;
    }

    @Override
    public void save(ToDo item) {
        items.put(item.getId(), item);
    }

    @Override
    public ToDo find(String id) {
        return items.get(id);
    }

    @Override
    public void update(ToDo item) {
        items.replace(item.getId(), item);
    }
}
