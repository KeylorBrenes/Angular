import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

/**
 * Interface representing a user.
 */
export interface User {
    /**
     * The ID of the user.
     */
    id: number;

    /**
     * The username of the user.
     */
    userName: string;

    /**
     * The email address of the user.
     */
    email: string;

    /**
     * Indicates whether the user is active.
     */
    active: boolean;
}

/**
 * Service for handling user-related operations.
 *
 * This service provides methods to interact with the user API.
 */
@Injectable({
    providedIn: 'root',
})
export class UserService {
    /**
     * The base URL for the user API.
     */
    private baseUrl: string = 'http://localhost:8000/api/users';

    /**
     * Initializes a new instance of the UserService class.
     *
     * @param http - The HttpClient used to make HTTP requests.
     */
    constructor(private http: HttpClient) { }

    /**
     * Gets the list of all users.
     *
     * @returns An Observable of an array of User objects.
     */
    getUsers(): Observable<User[]> {
        return this.http.get<User[]>(`${this.baseUrl}/list`);
    }
}
