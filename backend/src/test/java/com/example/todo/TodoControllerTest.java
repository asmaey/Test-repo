package com.example.todo;

import com.example.todo.controller.TodoController;
import com.example.todo.model.Todo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TodoControllerTest {

    private TodoController controller;

    @BeforeEach
    void setUp() {
        controller = new TodoController();
    }

    @Test
    void createAndGetTodo() {
        Todo input = new Todo();
        input.setTitle("Buy groceries");

        Todo created = controller.createTodo(input);
        assertNotNull(created);
        assertEquals("Buy groceries", created.getTitle());
        assertFalse(created.isCompleted());

        List<Todo> all = controller.getAllTodos();
        assertEquals(1, all.size());
    }

    @Test
    void updateTodo() {
        Todo input = new Todo();
        input.setTitle("Task");
        Todo created = controller.createTodo(input);

        Todo update = new Todo();
        update.setTitle("Updated Task");
        update.setCompleted(true);

        ResponseEntity<Todo> response = controller.updateTodo(created.getId(), update);
        assertEquals(200, response.getStatusCode().value());
        assertTrue(response.getBody().isCompleted());
    }

    @Test
    void deleteTodo() {
        Todo input = new Todo();
        input.setTitle("Delete me");
        Todo created = controller.createTodo(input);

        ResponseEntity<Void> response = controller.deleteTodo(created.getId());
        assertEquals(204, response.getStatusCode().value());
        assertEquals(0, controller.getAllTodos().size());
    }

    @Test
    void updateNonExistentTodo() {
        Todo update = new Todo();
        update.setTitle("Ghost");
        ResponseEntity<Todo> response = controller.updateTodo(9999L, update);
        assertEquals(404, response.getStatusCode().value());
    }
}
