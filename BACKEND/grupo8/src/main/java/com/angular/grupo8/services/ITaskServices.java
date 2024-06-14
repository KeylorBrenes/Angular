package com.angular.grupo8.services;

import java.util.List;

import com.angular.grupo8.domain.Task;

public interface ITaskServices {

    List<Task> getAllTasks();

    Task getTaskById(Long id);

    List<Task> getTasksByUserId(Long userId);

    Task saveTask(Task task);

    Task updateTask(Long id, Task task);

    void deleteTask(Long id);

}
