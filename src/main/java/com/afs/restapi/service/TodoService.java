package com.afs.restapi.service;

import com.afs.restapi.entity.Todo;
import com.afs.restapi.exception.NoTodoFoundException;
import com.afs.restapi.repository.TodoRepositoryInMongo;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TodoService {
    private final TodoRepositoryInMongo todoRepositoryInMongo;

    public TodoService(TodoRepositoryInMongo todoRepositoryInMongo) {
        this.todoRepositoryInMongo = todoRepositoryInMongo;
    }

    public List<Todo> findAll() {
        return todoRepositoryInMongo.findAll();
    }

    public Todo update(String id, Todo updatedTodo) {
        Todo todo = todoRepositoryInMongo.findById(id)
                .orElseThrow(NoTodoFoundException::new);
        if (updatedTodo.getText() != null) {
            todo.setText(updatedTodo.getText());
        }
        if (updatedTodo.getDone() != null) {
            todo.setDone(updatedTodo.getDone());
        }
        return todoRepositoryInMongo.save(todo);
    }

    public Todo create(Todo todo) {
        return todoRepositoryInMongo.insert(todo);
    }

    public void delete(String id) {
        todoRepositoryInMongo.deleteById(id);
    }
}
