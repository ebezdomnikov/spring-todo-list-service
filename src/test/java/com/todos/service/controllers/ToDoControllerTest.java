package com.todos.service.controllers;

import com.sun.tools.javac.comp.Todo;
import com.todos.service.ServiceApplication;
import com.todos.service.domain.ToDoFactory;
import com.todos.service.domain.ToDoId;
import com.todos.service.domain.ToDoText;
import com.todos.service.domain.repository.ToDoAggregateRepository;
import com.todos.service.infrastructure.factory.ToDoFactoryImpl;
import com.todos.service.infrastructure.factory.repository.ToDoAggregateRepositoryImpl;
import com.todos.service.model.ToDo;
import com.todos.service.services.MemoryToDoServiceImpl;
import com.todos.service.services.ToDoService;
import javafx.application.Application;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.async.CallableProcessingInterceptor;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringBootTest
@ContextHierarchy(@ContextConfiguration(classes = ToDoControllerTest.WebConfig.class))
public class ToDoControllerTest {

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private CallableProcessingInterceptor callableInterceptor;

    private MockMvc mockMvc;

    @Autowired
    ToDoService toDoService;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void it_raise_bad_request_if_record_not_found_during_deletion() throws Exception {
        this.mockMvc.perform(delete("/todos/2")).andExpect(status().isBadRequest());
    }
    @Test
    public void it_can_delete_todo_by_given_id() throws Exception {
        ToDo todo = new ToDo(new ToDoId("12345"), new ToDoText("test"), false, false);
        toDoService.save(todo);
        this.mockMvc.perform(delete("/todos/12345")).andExpect(status().isOk());
    }


    @Configuration
    @EnableWebMvc
    static class WebConfig implements WebMvcConfigurer {

        @Autowired
        ToDoService toDoService;

        @Override
        public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
            configurer.registerCallableInterceptors(callableInterceptor());
        }

        @Bean
        public CallableProcessingInterceptor callableInterceptor() {
            return Mockito.mock(CallableProcessingInterceptor.class);
        }

        @Bean
        public ToDoController toDoController() {
            return new ToDoController(toDoService);
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
        public ToDoService toDoService() {
            return new MemoryToDoServiceImpl();
        }
    }
}

