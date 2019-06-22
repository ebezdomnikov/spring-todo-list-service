package com.todos.service.controllers;

import com.todos.service.domain.ToDoAggregate;
import com.todos.service.domain.ToDoFactory;
import com.todos.service.domain.ToDoId;
import com.todos.service.domain.ToDoText;
import com.todos.service.domain.repository.ToDoAggregateRepository;
import com.todos.service.model.ToDo;
import com.todos.service.response.ToDoResource;
import com.todos.service.response.ToDoResourceCollection;
import com.todos.service.services.ToDoService;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
//import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;


@CrossOrigin
@RestController
public class ToDoController {

    private ToDoService toDoService;

    @Autowired
    private ToDoAggregateRepository toDoAggregateRepository;

    @Autowired
    private ToDoFactory toDoFactory;

//    @Autowired
//    private KafkaTemplate<Object, Object> template;

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
    ToDoResourceCollection done(@PathVariable String id) throws Exception {
        ToDoAggregate aggregate = toDoAggregateRepository.find(new ToDoId(id));
        aggregate.done();
        toDoAggregateRepository.save(aggregate);
        return this.getResponse();
    }

    @RequestMapping(value = "/todos", method = RequestMethod.POST, produces = "application/json")
    ResponseEntity create(@RequestBody ToDo toDo) {
        ToDoAggregate aggregate = toDoFactory.createNewToDo(toDo.getId().toString(), toDo.getText().toString());
        toDoAggregateRepository.save(aggregate);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseNewId(toDo.getId().toString()));
    }

    @PostMapping(value = "/todos/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    ToDoResourceCollection update(
            @PathVariable String id,
            @RequestBody ToDo todoText
    ) throws Exception {

        ToDoText text = todoText.getText();
        ToDoAggregate aggregate = toDoAggregateRepository.find(new ToDoId(id));
        aggregate.updateText(text);
        toDoAggregateRepository.save(aggregate);
        return this.getResponse();
    }

    @RequestMapping(value = "/todos/undone/{id}", method = RequestMethod.PATCH, produces = "application/json")
    ToDoResourceCollection undone(@PathVariable String id) throws Exception {
        ToDoAggregate aggregate = toDoAggregateRepository.find(new ToDoId(id));
        aggregate.undone();
        toDoAggregateRepository.save(aggregate);
        return this.getResponse();
    }

    @RequestMapping(value = "/todos/{id}", method = RequestMethod.DELETE, produces = "application/json")
    ResponseEntity delete(@PathVariable String id) {
        ToDo model = (ToDo) toDoService.find(id);
        if (model == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        toDoService.delete(id);

        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @RequestMapping(value = "/todos/favorite/{id}", method = RequestMethod.PATCH, produces = "application/json")
    ToDoResourceCollection favorite(@PathVariable String id) throws Exception {
        ToDoAggregate aggregate = toDoAggregateRepository.find(new ToDoId(id));
        aggregate.favorite();
        toDoAggregateRepository.save(aggregate);
        return this.getResponse();
    }

    @RequestMapping(value = "/todos/unfavorite/{id}", method = RequestMethod.PATCH, produces = "application/json")
    ToDoResourceCollection unfavorite(@PathVariable String id) throws Exception {
        ToDoAggregate aggregate = toDoAggregateRepository.find(new ToDoId(id));
        aggregate.unfavorite();
        toDoAggregateRepository.save(aggregate);
        return this.getResponse();
    }

    class ResponseNewId {
        private String id;

        public ResponseNewId(String id) {
            this.id = id;
        }

        public String getId() {
            return id;
        }
    }
}
