package com.todos.service.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;

import java.io.IOException;

public class ToDo {
    private String id;
    private String text;
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

    public ToDo(String id, String text, boolean done, boolean favorite) {
        this.id = id;
        this.text = text;
        this.done = done;
        this.favorite = favorite;
    }

    public String getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public boolean isDone() {
        return done;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setText(String text) {
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
