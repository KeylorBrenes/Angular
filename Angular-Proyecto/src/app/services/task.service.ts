import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Task } from '../models/task.model';

/**
 * Service for handling task-related operations.
 *
 * This service provides methods to interact with the task API.
 */
@Injectable({
    providedIn: 'root'
})
export class TaskService {
    /**
     * The base URL for the task API.
     */
    private baseUrl: string = 'http://localhost:8000/api/tasks';

    /**
     * Initializes a new instance of the TaskService class.
     *
     * @param http - The HttpClient used to make HTTP requests.
     */
    constructor(private http: HttpClient) { }

    /**
     * Gets the list of all tasks.
     *
     * @returns An Observable of an array of Task objects.
     */
    getTasks(): Observable<Task[]> {
        return this.http.get<Task[]>(`${this.baseUrl}/list`);
    }

    /**
     * Gets a task by its ID.
     *
     * @param id - The ID of the task to retrieve.
     * @returns An Observable of a Task object.
     */
    getTaskById(id: number): Observable<Task> {
        return this.http.get<Task>(`${this.baseUrl}/getTaskById/${id}`);
    }

    /**
     * Saves a new task or updates an existing one.
     *
     * @param task - The task object to save.
     * @returns An Observable of the server response.
     */
    saveTask(task: Task): Observable<any> {
        return this.http.post<any>(`${this.baseUrl}/save`, task);
    }

    /**
     * Deletes a task by its ID.
     *
     * @param id - The ID of the task to delete.
     * @returns An Observable of the server response.
     */
    deleteTask(id: number): Observable<any> {
        return this.http.delete<any>(`${this.baseUrl}/delete/${id}`);
    }

    /**
 * Updates an existing task.
 *
 * @param id - The ID of the task to update.
 * @param task - The task object with updated data.
 * @returns An Observable of the server response.
 */
    updateTask(id: number, task: Task): Observable<any> {
        return this.http.put<any>(`${this.baseUrl}/update/${id}`, task);
    }

}
