package com.example.todo.controller;

import com.example.todo.model.Todo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/api/todos")
@CrossOrigin(origins = "*")
public class TodoController {

    private final Map<Long, Todo> todos = new ConcurrentHashMap<>();

    @GetMapping
    public List<Todo> getAllTodos() {
        return new ArrayList<>(todos.values());
    }

    @PostMapping
    public Todo createTodo(@RequestBody Todo todo) {
        Todo newTodo = new Todo(todo.getTitle());
        newTodo.setDeadline(todo.getDeadline());
        todos.put(newTodo.getId(), newTodo);
        return newTodo;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable long id, @RequestBody Todo todo) {
        Todo existing = todos.get(id);
        if (existing == null) {
            return ResponseEntity.notFound().build();
        }
        existing.setTitle(todo.getTitle());
        existing.setCompleted(todo.isCompleted());
        existing.setDeadline(todo.getDeadline());
        return ResponseEntity.ok(existing);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable long id) {
        if (todos.remove(id) == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
