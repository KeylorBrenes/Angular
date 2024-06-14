import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { TaskService } from '../services/task.service';
import { Task } from '../models/task.model';
import Swal from 'sweetalert2';

/**
 * Component for displaying and managing the list of tasks.
 *
 * This component provides functionality to view, edit, and delete tasks.
 */
@Component({
  selector: 'app-lista-tareas',
  templateUrl: './listar.component.html',
  styleUrls: ['./listar.component.css'],
  standalone: true,
  imports: [CommonModule]
})
export class ListaComponent implements OnInit {
  /**
   * List of tasks to be displayed.
   */
  tareas: Task[] = [];

  /**
   * Initializes a new instance of the ListaComponent class.
   *
   * @param taskService - Service to handle task-related operations.
   * @param router - Router to handle navigation.
   */
  constructor(private taskService: TaskService, private router: Router) {}

  /**
   * Lifecycle hook that is called after Angular has initialized all data-bound properties.
   *
   * This method fetches the list of tasks.
   */
  ngOnInit(): void {
    this.obtenerTareas();
  }

  /**
   * Fetches the list of tasks from the TaskService.
   */
  obtenerTareas(): void {
    this.taskService.getTasks().subscribe({
      next: response => {
        console.log('Tasks:', response);
        this.tareas = response;
      },
      error: error => {
        console.error('Error fetching tasks:', error);
        alert('An error occurred while fetching tasks.');
      }
    });
  }

  /**
   * Returns the CSS class for task progress based on its status.
   *
   * @param progress - The progress status of the task.
   * @returns The CSS class for the task progress.
   */
  getTaskProgressClass(progress: string): string {
    switch (progress) {
      case 'PENDING':
        return 'badge-pending';
      case 'IN_PROGRESS':
        return 'badge-in-progress';
      case 'COMPLETED':
        return 'badge-completed';
      default:
        return '';
    }
  }

  /**
   * Returns the CSS class for task priority based on its level.
   *
   * @param priority - The priority level of the task.
   * @returns The CSS class for the task priority.
   */
  getPriorityClass(priority: string): string {
    switch (priority) {
      case 'LOW':
        return 'badge-low';
      case 'MEDIUM':
        return 'badge-medium';
      case 'HIGH':
        return 'badge-high';
      default:
        return '';
    }
  }

  /**
   * Navigates to the edit page for a specific task.
   *
   * @param id - The ID of the task to be edited.
   */
  editarTarea(id: number): void {
    console.log('Task to edit:', id);
    this.router.navigate(['/editar', id]);
  }

  /**
   * Deletes a task after confirming with the user.
   *
   * @param id - The ID of the task to be deleted.
   */
  eliminarTarea(id: number): void {
    Swal.fire({
      title: 'Are you sure?',
      text: "You won't be able to revert this!",
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Yes, delete it'
    }).then((result) => {
      if (result.isConfirmed) {
        console.log(`Attempting to delete task with id: ${id}`);
        this.taskService.deleteTask(id).subscribe({
          next: response => {
            console.log('Task deleted:', response);
            Swal.fire(
              'Deleted!',
              'The task has been deleted.',
              'success'
            ).then(() => {
              this.obtenerTareas(); // Refresh the task list after SweetAlert2 confirmation
            });
          },
          error: error => {
            console.error('Error deleting task:', error);
            Swal.fire(
              'Error',
              'An error occurred while deleting the task.',
              'error'
            );
          }
        });
      }
    });
  }
}
