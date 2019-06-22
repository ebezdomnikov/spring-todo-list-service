package com.todos.service.repositories;

import com.todos.service.domain.ToDoAggregate;
import com.todos.service.domain.ToDoFactory;
import com.todos.service.domain.ToDoText;
import com.todos.service.domain.event.Event;
import com.todos.service.domain.event.ToDoCreated;
import com.todos.service.domain.event.ToDoTextUpdated;
import com.todos.service.domain.repository.ToDoAggregateRepository;
import com.todos.service.infrastructure.factory.ToDoFactoryImpl;
import com.todos.service.infrastructure.factory.repository.ToDoAggregateRepositoryImpl;
import com.todos.service.model.ToDo;
import com.todos.service.services.ToDoService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@TestComponent
class EventTestListener {
    @EventListener
    public void handle(Event event) {
        // only for spy events
    }
}

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class ToDoAggregateRepositoryTest {

    @SpyBean
    private EventTestListener testEventListener;

    @Autowired
    private ToDoAggregateRepository toDoAggregateRepository;

    @Autowired
    private ToDoService toDoService;

    @Test
    public void can_create_new_todo_and_raise_event() {

        ToDoFactory factory = new ToDoFactoryImpl();
        ToDoAggregate aggregate = factory.createNewToDo("123", "text");
        aggregate.updateText(new ToDoText("new-text"));
        aggregate.updateText(new ToDoText("new-text-2"));

        toDoAggregateRepository.save(aggregate);

        verify(testEventListener).handle(any(ToDoCreated.class));
        verify(testEventListener, times(2)).handle(any(ToDoTextUpdated.class));

        ToDo toDo = (ToDo)toDoService.find("123");
        Assert.assertEquals("new-text-2", toDo.getText().toString());
    }
}
