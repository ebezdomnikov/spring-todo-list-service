package com.todos.service.domain;

import com.todos.service.domain.event.ToDoCreated;
import com.todos.service.domain.event.ToDoTextUpdated;
import com.todos.service.model.ToDo;

public class ToDoAggregate extends AbstractAggregate {

    private ToDo rootEntity;

    private ToDoAggregate(ToDo rootEntity) {
        this.rootEntity = rootEntity;
    }

    public static ToDoAggregate createNew(ToDo rootEntity) {
        ToDoAggregate aggregate = new ToDoAggregate(rootEntity);
        aggregate.recordThat(new ToDoCreated(aggregate, rootEntity.getId(), rootEntity.getText()));
        return aggregate;
    }

    public ToDo getRootEntity() {
        return rootEntity;
    }

    public static ToDoAggregate rebuild(ToDo rootEntity) {
        return new ToDoAggregate(rootEntity);
    }

    public ToDoId id() {
        return this.rootEntity.getId();
    }

    public void done() {
        this.rootEntity.done();
    }

    public void undone() {
        this.rootEntity.undone();
    }

    public void updateText(ToDoText text) {
        this.rootEntity.setText(text);
        this.recordThat(new ToDoTextUpdated(this, this.id(), text));
    }

    public void favorite() {
        this.rootEntity.favorite();
    }

    public void unfavorite() {
        this.rootEntity.unfavorite();
    }
}
