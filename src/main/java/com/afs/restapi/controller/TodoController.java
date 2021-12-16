package com.afs.restapi.controller;

import com.afs.restapi.dto.TodoRequest;
import com.afs.restapi.dto.TodoResponse;
import com.afs.restapi.entity.Todo;
import com.afs.restapi.mapper.TodoMapper;
import com.afs.restapi.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("todos")
@CrossOrigin(origins = "http://localhost:3000")
public class TodoController {
    private final TodoService todoService;
    private final TodoMapper todoMapper;

    public TodoController(TodoService todoService, TodoMapper todoMapper) {
        this.todoService = todoService;
        this.todoMapper = todoMapper;
    }

    @GetMapping
    public List<TodoResponse> getAllTodos() {
        return todoService.findAll().stream()
                .map(todoMapper::toResponse)
                .collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Todo addTodo(@RequestBody TodoRequest todoRequest) {
        return todoService.create(todoMapper.toEntity(todoRequest));
    }

    @PutMapping("/{id}")
    public Todo updateTodo(@PathVariable String id, @RequestBody TodoRequest todoRequest) {
        return todoService.update(id, todoMapper.toEntity(todoRequest));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTodo(@PathVariable String id) {
        todoService.delete(id);
    }
}
