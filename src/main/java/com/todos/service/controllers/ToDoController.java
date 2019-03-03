package com.todos.service.controllers;

import com.todos.service.model.ToDo;
import com.todos.service.response.ToDoResource;
import com.todos.service.response.ToDoResourceCollection;
import com.todos.service.services.ToDoService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;


@CrossOrigin
@RestController
public class ToDoController {
    private ToDoService toDoService;

    public ToDoController(ToDoService toDoService) {
        this.toDoService = toDoService;
    }

    private ToDoResourceCollection getResponse() {
        ArrayList<ToDoResource> resources = new ArrayList<>();

        toDoService.getAll().forEach((key, value) -> resources.add(new ToDoResource((ToDo) value)));

        return new ToDoResourceCollection(resources);
    }

    @RequestMapping(value = "/todos", method = RequestMethod.GET, produces = "application/json")
    ToDoResourceCollection getAll() {
        return this.getResponse();
    }

    @RequestMapping(value = "/todos/done/{id}", method = RequestMethod.PATCH, produces = "application/json")
    ToDoResourceCollection done(@PathVariable String id) {
        ToDo model = (ToDo) toDoService.find(id);
        model.done();
        toDoService.update(model);

        return this.getResponse();
    }

    @PostMapping(value = "/todos/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    ToDoResourceCollection update(
            @PathVariable String id,
            @RequestBody ToDo todoText
    ) {

        String text = todoText.getText();
        ToDo model = (ToDo) toDoService.find(id);
        model.setText(text);
        toDoService.update(model);

        return this.getResponse();
    }

    @RequestMapping(value = "/todos/undone/{id}", method = RequestMethod.PATCH, produces = "application/json")
    ToDoResourceCollection undone(@PathVariable String id) {
        ToDo model = (ToDo) toDoService.find(id);
        model.undone();
        toDoService.update(model);

        return this.getResponse();
    }

    @RequestMapping(value = "/todos/favorite/{id}", method = RequestMethod.PATCH, produces = "application/json")
    ToDoResourceCollection favorite(@PathVariable String id) {
        ToDo model = (ToDo) toDoService.find(id);
        model.favorite();
        toDoService.update(model);

        return this.getResponse();
    }

    @RequestMapping(value = "/todos/unfavorite/{id}", method = RequestMethod.PATCH, produces = "application/json")
    ToDoResourceCollection unfavorite(@PathVariable String id) {
        ToDo model = (ToDo) toDoService.find(id);
        model.unfavorite();
        toDoService.update(model);

        return this.getResponse();
    }
}
