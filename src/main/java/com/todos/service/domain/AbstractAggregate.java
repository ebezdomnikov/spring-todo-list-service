package com.todos.service.domain;

import com.todos.service.domain.event.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

import java.util.ArrayList;

abstract public class AbstractAggregate {

    private ArrayList<Event> pendingEvents = new ArrayList<>();

    public void recordThat(Event event) {
        pendingEvents.add(event);
    }

    public ArrayList<Event> getPendingEvents() {
        return pendingEvents;
    }
}
