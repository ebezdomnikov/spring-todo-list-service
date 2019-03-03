package com.todos.service.bootstrap;

import com.todos.service.model.ToDo;
import com.todos.service.services.ToDoService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final ToDoService toDoService;

    public DataLoader(ToDoService toDoService) {
        this.toDoService = toDoService;
    }

    @Override
    public void run(String... args) throws Exception {
        toDoService.save(new ToDo("1", "test", false, false));
        toDoService.save(new ToDo("2", "test1", false, false));
        toDoService.save(new ToDo("3", "test2", false, false));
        toDoService.save(new ToDo("4", "test3", false, false));
    }
}
