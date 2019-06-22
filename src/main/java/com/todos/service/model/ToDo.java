package com.todos.service.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.tools.javac.comp.Todo;
import com.todos.service.domain.ToDoId;
import com.todos.service.domain.ToDoText;
import org.springframework.beans.BeanUtils;

import java.io.IOException;

public class ToDo {
    private ToDoId id;
    private ToDoText text;
    private boolean done;
    private boolean favorite;

    public ToDo(String json) throws IOException {
        System.out.println("-------------------");
        System.out.println(json);
        System.out.println("-------------------");
        ObjectMapper om = new ObjectMapper();
        ToDo u = om.readValue(json, ToDo.class);
        BeanUtils.copyProperties(this, u);
    }

    public ToDo(ToDoId id, ToDoText text, boolean done, boolean favorite) {
        this.id = id;
        this.text = text;
        this.done = done;
        this.favorite = favorite;
    }

    public ToDoId getId() {
        return id;
    }

    public ToDoText getText() {
        return text;
    }

    public boolean isDone() {
        return done;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setText(ToDoText text) {
        this.text = text;
    }

    public void done()
    {
        done = true;
    }

    public void undone()
    {
        done = false;
    }

    public void favorite()
    {
        favorite = true;
    }

    public void unfavorite()
    {
        favorite = false;
    }
}
