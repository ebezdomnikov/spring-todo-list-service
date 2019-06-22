package com.todos.service.bootstrap;

import com.todos.service.domain.ToDoId;
import com.todos.service.domain.ToDoText;
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
        toDoService.save(new ToDo(new ToDoId("1"), new ToDoText("test"), false, false));
        toDoService.save(new ToDo(new ToDoId("2"), new ToDoText("test1"), false, false));
        toDoService.save(new ToDo(new ToDoId("3"), new ToDoText("test2"), false, false));
        toDoService.save(new ToDo(new ToDoId("4"), new ToDoText("test3"), false, false));
        toDoService.save(new ToDo(new ToDoId("5"), new ToDoText("New item"), false, false));
        toDoService.save(new ToDo(new ToDoId("6"), new ToDoText("Another item"), false, false));
    }
}
