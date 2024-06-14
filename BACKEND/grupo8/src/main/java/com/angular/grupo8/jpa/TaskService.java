package com.angular.grupo8.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.angular.grupo8.domain.Task;
import com.angular.grupo8.repository.TaskRepository;
import com.angular.grupo8.services.ITaskServices;

import java.util.List;

@Service
public class TaskService implements ITaskServices {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public List<Task> getTasksByUserId(Long userId) {
        return taskRepository.findByUserId(userId);
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Task getTaskById(Long id) {
        return taskRepository.findById(id).get();
    }

    @Override
    public Task saveTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Task updateTask(Long id, Task taskDetails) {
        Task task = taskRepository.findById(id).get();

        task.setTitle(taskDetails.getTitle() != null ? taskDetails.getTitle() : task.getTitle());
        task.setDescription(
                taskDetails.getDescription() != null ? taskDetails.getDescription() : task.getDescription());
        task.setDueDate(taskDetails.getDueDate() != null ? taskDetails.getDueDate() : task.getDueDate());
        task.setProgress(taskDetails.getProgress() != null ? taskDetails.getProgress() : task.getProgress());
        task.setPriority(taskDetails.getPriority() != null ? taskDetails.getPriority() : task.getPriority());
        task.setPhotoUrl(taskDetails.getPhotoUrl() != null ? taskDetails.getPhotoUrl() : task.getPhotoUrl());
        task.setHours(taskDetails.getHours() != null ? taskDetails.getHours() : task.getHours());
        task.setIsReady(taskDetails.getIsReady());

        return taskRepository.save(task);
    }

    @Override
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}
