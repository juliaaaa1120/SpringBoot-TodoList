package com.afs.restapi.service;

import com.afs.restapi.entity.Todo;
import com.afs.restapi.repository.TodoRepositoryInMongo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
public class TodoServiceTest {
    @Mock
    TodoRepositoryInMongo mockTodoRepositoryInMongo;
    @InjectMocks
    TodoService todoService;

    @Test
    void should_return_all_todos_when_find_all_given_todos() {
        //given
        List<Todo> todos = new ArrayList<>();
        todos.add(new Todo("1", "Have fun Christmas party", false));
        given(mockTodoRepositoryInMongo.findAll())
                .willReturn(todos);

        //when
        List<Todo> actual = todoService.findAll();

        //then
        assertEquals(todos, actual);
        assertEquals(1, actual.size());
        assertEquals(todos.get(0).getId(), actual.get(0).getId());
        assertEquals(todos.get(0).getText(), actual.get(0).getText());
        assertEquals(todos.get(0).getDone(), actual.get(0).getDone());
    }

    @Test
    void should_return_todo_when_create_todo_given_todo() {
        //given
        Todo todo = new Todo("1", "Have fun Christmas party", false);

        given(mockTodoRepositoryInMongo.insert(todo))
                .willReturn(todo);

        //when
        Todo actual = todoService.create(todo);

        //then
        assertEquals(todo, actual);
        assertEquals(todo.getId(), actual.getId());
        assertEquals(todo.getText(), actual.getText());
        assertEquals(todo.getDone(), actual.getDone());
    }

    @Test
    void should_return_updated_todo_when_update_todo_given_updated_todo() {
        //given
        Todo todo = new Todo("1", "Take 5 lunch boxes", false);
        Todo updatedTodo = new Todo("1", "Take 2 lunch boxes", true);
        given(mockTodoRepositoryInMongo.findById(any()))
                .willReturn(java.util.Optional.of(todo));
        todo.setText(updatedTodo.getText());
        todo.setDone(updatedTodo.getDone());
        given(mockTodoRepositoryInMongo.save(any(Todo.class)))
                .willReturn(todo);

        //when
        Todo actual = todoService.update(todo.getId(), updatedTodo);

        //then
        assertEquals(todo, actual);
        assertEquals(todo.getId(), actual.getId());
        assertEquals(todo.getText(), actual.getText());
        assertEquals(todo.getDone(), actual.getDone());
    }
//
//    @Test
//    void should_return_employee_when_delete_employee_given_employee_id() {
//        //given
//        Todo todo1 = new Todo("1", "Julia", 22, "Female", "1", 100000);
//
//        willDoNothing().given(mockTodoRepositoryInMongo).deleteById(todo1.getId());
//
//        //when
//        todoService.delete(todo1.getId());
//
//        //then
//        verify(mockTodoRepositoryInMongo).deleteById(todo1.getId());
//        assertEquals(0, todoService.findAll().size());
//    }
}
