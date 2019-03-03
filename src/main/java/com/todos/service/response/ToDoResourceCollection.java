package com.todos.service.response;

import java.util.ArrayList;

public class ToDoResourceCollection  {
    private ArrayList<ToDoResource> data;

    public ToDoResourceCollection(ArrayList<ToDoResource> data) {
        this.data = data;
    }

    public ArrayList<ToDoResource> getData() {
        return data;
    }
}
