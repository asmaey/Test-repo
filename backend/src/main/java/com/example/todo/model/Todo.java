package com.example.todo.model;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicLong;

public class Todo {
    private static final AtomicLong counter = new AtomicLong();

    private long id;
    private String title;
    private boolean completed;
    private LocalDateTime createdAt;
    private LocalDateTime deadline;

    public Todo() {}

    public Todo(String title) {
        this.id = counter.incrementAndGet();
        this.title = title;
        this.completed = false;
        this.createdAt = LocalDateTime.now();
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public boolean isCompleted() { return completed; }
    public void setCompleted(boolean completed) { this.completed = completed; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getDeadline() { return deadline; }
    public void setDeadline(LocalDateTime deadline) { this.deadline = deadline; }
}
