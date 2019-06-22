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
        items.put(item.getId().toString(), item);
    }

    @Override
    public ToDo find(String id) {
        return items.get(id);
    }

    @Override
    public boolean delete(String id) {
        if (!items.containsKey(id)) {
            throw new IllegalArgumentException("Not exists");
        }
        items.remove(id);
        return true;
    }

    @Override
    public void update(ToDo item) {
        items.replace(item.getId().toString(), item);
    }
}
