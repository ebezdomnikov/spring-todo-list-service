package com.todos.service.infrastructure.factory;

import com.todos.service.domain.ToDoAggregate;
import com.todos.service.domain.ToDoFactory;
import com.todos.service.domain.ToDoId;
import com.todos.service.domain.ToDoText;
import com.todos.service.model.ToDo;

public class ToDoFactoryImpl implements ToDoFactory {
    @Override
    public ToDoAggregate createNewToDo(String id, String text) {
        {
            return ToDoAggregate.createNew(
                    new ToDo(
                        new ToDoId(id),
                        new ToDoText(text),
                        false,
                        false
                    )
            );
        }
    }
}
