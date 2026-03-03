import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { TodoService, Todo } from './services/todo.service';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit {
  todos: Todo[] = [];
  newTitle = '';

  constructor(private todoService: TodoService) {}

  ngOnInit(): void {
    this.loadTodos();
  }

  loadTodos(): void {
    this.todoService.getAll().subscribe({
      next: todos => (this.todos = todos),
      error: err => console.error('Failed to load todos', err),
    });
  }

  addTodo(): void {
    const title = this.newTitle.trim();
    if (!title) return;
    this.todoService.create(title).subscribe({
      next: todo => {
        this.todos.push(todo);
        this.newTitle = '';
      },
      error: err => console.error('Failed to create todo', err),
    });
  }

  toggleCompleted(todo: Todo): void {
    const updated = { ...todo, completed: !todo.completed };
    this.todoService.update(updated).subscribe({
      next: result => {
        const index = this.todos.findIndex(t => t.id === result.id);
        if (index !== -1) this.todos[index] = result;
      },
      error: err => console.error('Failed to update todo', err),
    });
  }

  deleteTodo(id: number): void {
    this.todoService.delete(id).subscribe({
      next: () => {
        this.todos = this.todos.filter(t => t.id !== id);
      },
      error: err => console.error('Failed to delete todo', err),
    });
  }
}
