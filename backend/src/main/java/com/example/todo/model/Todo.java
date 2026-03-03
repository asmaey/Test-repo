package com.example.todo.model;

import java.util.concurrent.atomic.AtomicLong;

public class Todo {
    private static final AtomicLong counter = new AtomicLong();

    private long id;
    private String title;
    private boolean completed;

    public Todo() {}

    public Todo(String title) {
        this.id = counter.incrementAndGet();
        this.title = title;
        this.completed = false;
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public boolean isCompleted() { return completed; }
    public void setCompleted(boolean completed) { this.completed = completed; }
}
