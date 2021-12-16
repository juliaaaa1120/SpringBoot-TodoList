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
//
//    @Test
//    void should_return_employee_by_id_when_find_by_id_given_employee_id() {
//        //given
//        Todo todo1 = new Todo("1", "Julia", 22, "Female", "1", 100000);
//        Todo todo2 = new Todo("2", "Jason", 22, "Male", "1", 100000);
//        Todo todo3 = new Todo("3", "Joanne", 22, "Female", "1",100000);
//
//        given(mockTodoRepositoryInMongo.findById(todo3.getId()))
//                .willReturn(java.util.Optional.of(todo3));
//
//        //when
//        Todo actual = todoService.findById(todo3.getId());
//
//        //then
//        assertEquals(todo3, actual);
//        assertEquals(todo3.getId(), actual.getId());
//        assertEquals(todo3.getName(), actual.getName());
//        assertEquals(todo3.getAge(), actual.getAge());
//        assertEquals(todo3.getGender(), actual.getGender());
//        assertEquals(todo3.getCompanyId(), actual.getCompanyId());
//        assertEquals(todo3.getSalary(), actual.getSalary());
//    }
//
//    @Test
//    void should_return_employees_by_gender_when_find_by_gender_given_employee_gender() {
//        //given
//        List<Todo> femaleTodos = new ArrayList<>();
//        Todo todo1 = new Todo("1", "Julia", 22, "Female", "1", 100000);
//        Todo todo2 = new Todo("2", "Jason", 22, "Male", "1", 100000);
//        Todo todo3 = new Todo("3", "Joanne", 22, "Female", "1", 100000);
//        femaleTodos.add(todo1);
//        femaleTodos.add(todo3);
//
//        given(mockTodoRepositoryInMongo.findByGender("Female"))
//                .willReturn(femaleTodos);
//
//        //when
//        List<Todo> actual = todoService.findByGender("Female");
//
//        //then
//        assertEquals(femaleTodos, actual);
//        assertEquals(2, actual.size());
//    }
//
//    @Test
//    void should_return_employees_by_page_when_find_by_page_given_page_and_page_size() {
//        //given
//        List<Todo> todos = new ArrayList<>();
//        Todo todo1 = new Todo("1", "Julia", 22, "Female", "1", 100000);
//        Todo todo2 = new Todo("2", "Jason", 22, "Male", "1", 100000);
//        Todo todo3 = new Todo("3", "Joanne", 22, "Female", "1", 100000);
//        Todo todo4 = new Todo("3", "John", 22, "Male", "1", 100000);
//        todos.add(todo1);
//        todos.add(todo2);
//        todos.add(todo3);
//        todos.add(todo4);
//
//        PageImpl<Todo> returnedPage = new PageImpl<>(todos, PageRequest.of(2, 2), 1);
//
//        given(mockTodoRepositoryInMongo.findAll(any(PageRequest.class)))
//                .willReturn(returnedPage);
//
//        //when
//        List<Todo> actual = todoService.findByPage(2,2);
//
//        //then
//        assertEquals(returnedPage.getContent(), actual);
//    }
//
//    @Test
//    void should_return_employee_when_create_employee_given_employee() {
//        //given
//        Todo todo = new Todo("1", "Julia", 22, "Female", "1", 100000);
//
//        given(mockTodoRepositoryInMongo.insert(todo))
//                .willReturn(todo);
//
//        //when
//        Todo actual = todoService.create(todo);
//
//        //then
//        assertEquals(todo, actual);
//        assertEquals(todo.getId(), actual.getId());
//        assertEquals(todo.getName(), actual.getName());
//        assertEquals(todo.getAge(), actual.getAge());
//        assertEquals(todo.getGender(), actual.getGender());
//        assertEquals(todo.getCompanyId(), actual.getCompanyId());
//        assertEquals(todo.getSalary(), actual.getSalary());
//    }
//
//    @Test
//    void should_return_updated_employee_when_edit_employee_given_updated_employee() {
//        //given
//        Todo todo = new Todo("1", "Julia", 22, "Female", "1", 100000);
//        Todo updatedTodo = new Todo("1", "Julia", 25, "Female", "1", 500000);
//        given(mockTodoRepositoryInMongo.findById(any()))
//                .willReturn(java.util.Optional.of(todo));
//        todo.setAge(updatedTodo.getAge());
//        todo.setSalary(updatedTodo.getSalary());
//        given(mockTodoRepositoryInMongo.save(any(Todo.class)))
//                .willReturn(todo);
//
//        //when
//        Todo actual = todoService.update(todo.getId(), updatedTodo);
//
//        //then
//        assertEquals(todo, actual);
//        assertEquals(todo.getId(), actual.getId());
//        assertEquals(todo.getName(), actual.getName());
//        assertEquals(todo.getAge(), actual.getAge());
//        assertEquals(todo.getGender(), actual.getGender());
//        assertEquals(todo.getCompanyId(), actual.getCompanyId());
//        assertEquals(todo.getSalary(), actual.getSalary());
//    }
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
