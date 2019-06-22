package com.todos.service.domain.event;

import org.springframework.context.ApplicationEvent;

abstract public class Event extends ApplicationEvent {

    public Event(Object source) {
        super(source);
    }
}
