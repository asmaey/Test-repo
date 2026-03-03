package com.example.todo;

import com.example.todo.controller.TodoController;
import com.example.todo.model.Todo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
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
        assertNotNull(created.getCreatedAt());

        List<Todo> all = controller.getAllTodos();
        assertEquals(1, all.size());
    }

    @Test
    void createTodoWithDeadline() {
        Todo input = new Todo();
        input.setTitle("Submit report");
        LocalDateTime deadline = LocalDateTime.of(2026, 12, 31, 23, 59);
        input.setDeadline(deadline);

        Todo created = controller.createTodo(input);
        assertNotNull(created);
        assertEquals("Submit report", created.getTitle());
        assertNotNull(created.getCreatedAt());
        assertEquals(deadline, created.getDeadline());
    }

    @Test
    void updateTodo() {
        Todo input = new Todo();
        input.setTitle("Task");
        Todo created = controller.createTodo(input);

        Todo update = new Todo();
        update.setTitle("Updated Task");
        update.setCompleted(true);
        LocalDateTime deadline = LocalDateTime.of(2026, 6, 15, 12, 0);
        update.setDeadline(deadline);

        ResponseEntity<Todo> response = controller.updateTodo(created.getId(), update);
        assertEquals(200, response.getStatusCode().value());
        assertTrue(response.getBody().isCompleted());
        assertEquals(deadline, response.getBody().getDeadline());
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
