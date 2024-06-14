import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { TaskService } from '../services/task.service';
import { Task } from '../models/task.model';
import { CommonModule } from '@angular/common';
import { UserService, User } from '../services/user.service';

/**
 * Component for editing tasks.
 *
 * This component provides a form for editing existing tasks.
 */
@Component({
  selector: 'app-editar',
  templateUrl: './editar.component.html',
  styleUrls: ['./editar.component.css'],
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule]
})
export class EditarComponent implements OnInit {
  /**
   * Reactive form for the task.
   */
  taskForm: FormGroup;

  /**
   * List of available users to assign to the task.
   */
  users: User[] = [];

  /**
   * The ID of the task being edited.
   */
  private taskId: number | null = null;

  /**
   * Initializes a new instance of the EditarComponent class.
   *
   * @param fb - FormBuilder to construct the reactive form.
   * @param taskService - Service to handle task-related operations.
   * @param userService - Service to handle user-related operations.
   * @param route - ActivatedRoute to access route parameters.
   */
  constructor(
    private fb: FormBuilder,
    private taskService: TaskService,
    private userService: UserService,
    private route: ActivatedRoute
  ) {
    this.taskForm = this.fb.group({
      title: ['', Validators.required],
      description: [''],
      dueDate: ['', Validators.required],
      progress: ['PENDING', Validators.required],
      priority: ['MEDIUM', Validators.required],
      photo_url: ['', Validators.pattern('https?://.+')],
      hours: [0, [Validators.required, Validators.min(0)]],
      isReady: [false, Validators.required],
      userId: [0, Validators.required]
    });
  }

  /**
   * Lifecycle hook that is called after Angular has initialized all data-bound properties.
   *
   * This method retrieves the task by ID from the route parameters and patches the form with the task data.
   * It also fetches the list of users to assign tasks to.
   */
  ngOnInit(): void {
    this.route.params.subscribe(params => {
      const id = params['id'];
      if (id) {
        this.taskId = id;
        this.taskService.getTaskById(id).subscribe({
          next: data => {
            this.taskForm.patchValue(data);
          },
          error: error => {
            console.error('There was an error!', error);
          }
        });
      }
    });

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
   * Saves the task.
   *
   * This method is called when the form is submitted. If the form is valid, the task is saved using the TaskService.
   * If the task has an ID, it ensures the task ID is set for updates.
   */
  saveTask(): void {
    if (this.taskForm.invalid) {
      this.taskForm.markAllAsTouched();
      return;
    }

    const task: Task = this.taskForm.value;
    if (this.taskId) {
      task.id = this.taskId;  // Ensure the task ID is set for updates
    }

    this.taskService.saveTask(task).subscribe({
      next: response => {
        console.log('Task saved:', response);
        alert('Task saved successfully');
        window.location.href = '/listar';
      },
      error: error => {
        console.log(task)
        console.error('Error saving task:', error);
        alert('An error occurred while saving the task.');
      }
    });
  }
}
