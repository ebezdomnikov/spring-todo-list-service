package com.todos.service.config;

import com.todos.service.services.MemoryToDoServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import com.todos.service.services.ToDoService;

@Configuration
public class ToDoServiceConfig {

    @Bean
    @Primary
    ToDoService toDoService() {
        return new MemoryToDoServiceImpl();
    }
}
