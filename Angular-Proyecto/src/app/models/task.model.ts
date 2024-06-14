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
 * Interface representing a task.
 */
export interface Task {
  /**
   * The ID of the task (optional).
   */
  id?: number;

  /**
   * The title of the task.
   */
  title: string;

  /**
   * A brief description of the task (optional).
   */
  description?: string;

  /**
   * The due date for the task.
   */
  dueDate: Date;

  /**
   * The current progress of the task.
   */
  progress: string;

  /**
   * The priority level of the task.
   */
  priority: string;

  /**
   * The URL of the photo associated with the task (optional).
   */
  photoUrl?: string;

  /**
   * The estimated number of hours to complete the task.
   */
  hours: number;

  /**
   * Indicates whether the task is ready for review or completion.
   */
  isReady: boolean;

  /**
   * The user assigned to the task (can be null).
   */
  user: User | null;
}
