package com.todos.service.domain;

import com.todos.service.domain.event.Event;
import com.todos.service.domain.event.ToDoTextUpdated;
import com.todos.service.infrastructure.factory.ToDoFactoryImpl;
import com.todos.service.model.ToDo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;

@RunWith(SpringJUnit4ClassRunner.class)
public class ToDoAggregateTest {

    @Test
    public void can_create_new_todo_and_raise_event() {
        ToDoFactory factory = new ToDoFactoryImpl();
        ToDoAggregate aggregate = factory.createNewToDo("123", "text");
        aggregate.updateText(new ToDoText("new-text"));
        ArrayList<Event> events = aggregate.getPendingEvents();

        Assert.assertTrue(events.size() == 1);
        Assert.assertTrue(ToDoTextUpdated.class.toString().equals(events.get(0).getClass().toString()));
    }
}