package com.todos.service.config;

import com.todos.service.domain.ToDoFactory;
import com.todos.service.domain.repository.ToDoAggregateRepository;
import com.todos.service.infrastructure.factory.ToDoFactoryImpl;
import com.todos.service.infrastructure.factory.repository.ToDoAggregateRepositoryImpl;
import com.todos.service.services.MemoryToDoServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import com.todos.service.services.ToDoService;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class ToDoServiceConfig {

    @Bean
    @Primary
    ToDoService toDoService() {
        return new MemoryToDoServiceImpl();
    }

    @Bean
    @Primary
    ToDoFactory toDoFactory() {
        return new ToDoFactoryImpl();
    }

    @Bean
    @Primary
    ToDoAggregateRepository toDoAggregateRepository() {
        return new ToDoAggregateRepositoryImpl(this.toDoService());
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://127.0.0.1:3001")
                        .allowedOrigins("http://localhost:3001")
                ;
            }
        };
    }
}
