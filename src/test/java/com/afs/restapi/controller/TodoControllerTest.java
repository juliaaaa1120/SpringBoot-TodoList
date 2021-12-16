package com.afs.restapi.controller;

import com.afs.restapi.entity.Todo;
import com.afs.restapi.repository.TodoRepositoryInMongo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TodoControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    TodoRepositoryInMongo todoRepositoryInMongo;

    @BeforeEach
    void cleanRepository() {
        todoRepositoryInMongo.deleteAll();
    }

    @Test
    void should_get_all_todos_when_perform_get_given_todos() throws Exception {
        //given
        Todo todo = new Todo(null, "Have fun Christmas party", false);
        todoRepositoryInMongo.insert(todo);
        // when
        mockMvc.perform(MockMvcRequestBuilders.get("/todos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id").value(todo.getId()))
                .andExpect(jsonPath("$[0].text").value("Have fun Christmas party"))
                .andExpect(jsonPath("$[0].done").value(false));
        // then
    }

    @Test
    void should_return_todo_when_perform_post_given_todo() throws Exception {
        //given
        String todo = "{\n" +
                "    \"text\": \"Have fun Christmas party\"\n" +
                "}";
        //when
        mockMvc.perform(MockMvcRequestBuilders.post("/todos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(todo))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.text").value("Have fun Christmas party"))
                .andExpect(jsonPath("$.done").value("false"));
    }

    @Test
    void should_return_updated_todo_when_perform_put_given_todo_id() throws Exception {
        //given
        Todo todo1 = new Todo(null, "Have fun Christmas party", false);
        todoRepositoryInMongo.insert(todo1);
        Todo todo2 = new Todo(null, "Buy Christmas gifts", false);
        todoRepositoryInMongo.insert(todo2);
        Todo todo3 = new Todo(null, "Do homework before 22:00", false);
        todoRepositoryInMongo.insert(todo3);
        String updatedEmployee = "{\n" +
                "    \"text\": \"Do homework before 00:00\",\n" +
                "    \"done\": true\n" +
                "}";
        //when
        mockMvc.perform(MockMvcRequestBuilders.put("/todos/{id}", todo3.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(updatedEmployee))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(todo3.getId()))
                .andExpect(jsonPath("$.text").value("Do homework before 00:00"))
                .andExpect(jsonPath("$.done").value(true));
    }
//
//    @Test
//    void should_return_employee_when_perform_delete_given_employee_id() throws Exception {
//        //given
//        Todo todo1 = new Todo(null, "Julia", 18, "Female", "1",100000);
//        todoRepositoryInMongo.insert(todo1);
//        Todo todo2 = new Todo(null, "Jason", 18, "Male", "2",100000);
//        todoRepositoryInMongo.insert(todo2);
//        Todo todo3 = new Todo(null, "Gloria", 18, "Female","3", 100000);
//        todoRepositoryInMongo.insert(todo3);
//        //when
//        mockMvc.perform(MockMvcRequestBuilders.delete("/employees/{id}", todo2.getId()))
//                .andExpect(status().isNoContent());
//        assertEquals(2, todoRepositoryInMongo.findAll().size());
//    }
}

