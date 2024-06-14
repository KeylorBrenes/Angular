import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { TaskService } from '../services/task.service';
import { UserService, User } from '../services/user.service';
import { Task } from '../models/task.model';
import { CommonModule } from '@angular/common';

/**
 * Component for adding tasks.
 *
 * This component provides a form for creating new tasks.
 */
@Component({
  selector: 'app-agregar',
  templateUrl: './agregar.component.html',
  styleUrls: ['./agregar.component.css'],
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule]
})
export class AgregarComponent implements OnInit {
  /**
   * Reactive form for the task.
   */
  taskForm: FormGroup;

  /**
   * List of available users to assign to the task.
   */
  users: User[] = [];

  /**
   * Initializes a new instance of the AgregarComponent class.
   *
   * @param fb - FormBuilder to construct the reactive form.
   * @param taskService - Service to handle task-related operations.
   * @param userService - Service to handle user-related operations.
   */
  constructor(
    private fb: FormBuilder,
    private taskService: TaskService,
    private userService: UserService
  ) {
    this.taskForm = this.fb.group({
      title: ['', Validators.required],
      description: [''],
      dueDate: ['', Validators.required],
      progress: ['PENDING', Validators.required],
      priority: ['MEDIUM', Validators.required],
      photoUrl: ['', Validators.pattern('https?://.+')],
      hours: [0, [Validators.required, Validators.min(0)]],
      isReady: [false, Validators.required],
      userId: [0, Validators.required]
    });
  }

  /**
   * Lifecycle hook that is called after Angular has initialized all data-bound properties.
   *
   * This method retrieves the list of users to assign tasks to.
   */
  ngOnInit(): void {
    this.userService.getUsers().subscribe({
      next: users => {
        this.users = users;
      },
      error: error => {
        console.error('Error fetching users:', error);
        alert('An error occurred while fetching users.');
      }
    });
  }

  /**
   * Adds a new task.
   *
   * This method is called when the form is submitted. If the form is valid, the task is saved using the TaskService.
   */
  agregarTarea(): void {
    if (this.taskForm.invalid) {
      this.taskForm.markAllAsTouched();
      return;
    }

    const task: Task = this.taskForm.value;
    this.taskService.saveTask(task).subscribe({
      next: response => {
        console.log('Task added:', response);
        alert('Task added successfully');
        window.location.href = '/listar';
      },
      error: error => {
        console.error('Error adding task:', error);
        alert('An error occurred while adding the task.');
      }
    });
  }
}
