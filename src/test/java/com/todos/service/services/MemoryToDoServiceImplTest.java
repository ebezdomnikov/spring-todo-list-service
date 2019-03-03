package com.todos.service.services;

import com.todos.service.model.ToDo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.HashMap;

@RunWith(SpringJUnit4ClassRunner.class)
public class MemoryToDoServiceImplTest {

    @Test
    public void can_get_all_data() {
        MemoryToDoServiceImpl service = new MemoryToDoServiceImpl();
        service.save(new ToDo("1", "test", false, false));

        Assert.assertEquals(1,service.getAll().size());
        Assert.assertTrue(service.getAll() instanceof HashMap);
        Assert.assertTrue(service.getAll().get("1").getId().equals("1"));
    }


    @Test
    public void can_update_item() {
        MemoryToDoServiceImpl service = new MemoryToDoServiceImpl();
        service.save(new ToDo("1", "test", false, false));

        ToDo model = service.find("1");
        model.done();

        service.update(model);

        Assert.assertTrue(service.find("1").isDone());
    }

    @Test
    public void can_update_item_text() {
        MemoryToDoServiceImpl service = new MemoryToDoServiceImpl();
        service.save(new ToDo("1", "test", false, false));

        ToDo model = service.find("1");
        model.setText("new_text");

        service.update(model);

        Assert.assertEquals("new_text", service.find("1").getText());
    }

}
