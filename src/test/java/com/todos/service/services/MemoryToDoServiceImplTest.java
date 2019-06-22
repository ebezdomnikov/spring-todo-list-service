package com.todos.service.services;

import com.todos.service.domain.ToDoId;
import com.todos.service.domain.ToDoText;
import com.todos.service.model.ToDo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.util.HashMap;


@RunWith(SpringJUnit4ClassRunner.class)
public class MemoryToDoServiceImplTest {

    @Test
    public void can_get_all_data() {
        MemoryToDoServiceImpl service = new MemoryToDoServiceImpl();
        service.save(new ToDo(new ToDoId("1"), new ToDoText("test"), false, false));

        Assert.assertEquals(1,service.getAll().size());
        Assert.assertTrue(service.getAll() instanceof HashMap);
        Assert.assertTrue(service.getAll().get("1").getId().toString().equals("1"));
    }


    @Test
    public void can_update_item() {
        MemoryToDoServiceImpl service = new MemoryToDoServiceImpl();
        service.save(new ToDo(new ToDoId("1"), new ToDoText("test"), false, false));

        ToDo model = service.find("1");
        model.done();

        service.update(model);

        Assert.assertTrue(service.find("1").isDone());
    }

    @Test
    public void can_delete_item() {
        MemoryToDoServiceImpl service = new MemoryToDoServiceImpl();
        service.save(new ToDo(new ToDoId("1"), new ToDoText("test"), false, false));
        service.save(new ToDo(new ToDoId("2"), new ToDoText("test2"), false, false));

        service.delete("2");

        Assert.assertTrue(service.find("2") == null);
    }


    @Test
    public void can_update_item_text() {
        MemoryToDoServiceImpl service = new MemoryToDoServiceImpl();
        service.save(new ToDo(new ToDoId("1"), new ToDoText("test"), false, false));

        ToDo model = service.find("1");
        model.setText(new ToDoText("new_text"));

        service.update(model);

        Assert.assertEquals("new_text", service.find("1").getText().toString());
    }

}
