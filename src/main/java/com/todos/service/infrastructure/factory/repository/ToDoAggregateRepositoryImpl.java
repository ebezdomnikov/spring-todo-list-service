package com.todos.service.infrastructure.factory.repository;

import com.todos.service.domain.ToDoAggregate;
import com.todos.service.domain.ToDoId;
import com.todos.service.domain.event.Event;
import com.todos.service.domain.repository.ToDoAggregateRepository;
import com.todos.service.model.ToDo;
import com.todos.service.services.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class ToDoAggregateRepositoryImpl implements ToDoAggregateRepository {

    private ToDoService toDoService;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public ToDoAggregateRepositoryImpl(ToDoService toDoService) {
        this.toDoService = toDoService;
    }

    @Override
    public ToDoAggregate save(ToDoAggregate aggregate) {
        ToDo rootEntity = aggregate.getRootEntity();

        toDoService.save(rootEntity);

        ArrayList<Event> events = aggregate.getPendingEvents();
        for(int i = 0; i < events.size(); i++) {
            applicationEventPublisher.publishEvent(events.get(i));
        }

        return aggregate;
    }

    @Override
    public ToDoAggregate find(ToDoId id) throws Exception {
        ToDo toDo = (ToDo) toDoService.find(id.toString());
        if (toDo == null) {
            throw new Exception("ToDo not found");
        }

        return ToDoAggregate.rebuild(toDo);
    }
}
