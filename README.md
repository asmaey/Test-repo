# Todo List App

A simple full-stack Todo List application built with **Java (Spring Boot)** backend and **Angular** frontend.

## Features

- Add new tasks
- Mark tasks as completed / incomplete
- Delete tasks
- REST API backend with in-memory storage

## Project Structure

```
├── backend/    # Spring Boot REST API (Java 17, Maven)
└── frontend/   # Angular 19 SPA
```

## Getting Started

### Backend

Requirements: Java 17+, Maven 3.6+

```bash
cd backend
mvn spring-boot:run
```

The API will be available at `http://localhost:8080/api/todos`.

### Frontend

Requirements: Node.js 18+, npm

```bash
cd frontend
npm install
ng serve
```

The app will be available at `http://localhost:4200`.

## API Endpoints

| Method | Path               | Description          |
|--------|--------------------|----------------------|
| GET    | /api/todos         | List all todos       |
| POST   | /api/todos         | Create a new todo    |
| PUT    | /api/todos/{id}    | Update a todo        |
| DELETE | /api/todos/{id}    | Delete a todo        |

## Running Tests

**Backend:**
```bash
cd backend && mvn test
```

**Frontend:**
```bash
cd frontend && ng test
```
