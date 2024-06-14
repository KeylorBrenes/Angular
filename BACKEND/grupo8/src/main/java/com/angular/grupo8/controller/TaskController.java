package com.angular.grupo8.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.angular.grupo8.domain.Task;
import com.angular.grupo8.domain.Taskdto;
import com.angular.grupo8.domain.User;
import com.angular.grupo8.jpa.TaskService;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins = "*")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/list")
    public ResponseEntity<List<Task>> listTasks() {
        List<Task> tasks = taskService.getAllTasks();
        if (tasks.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(tasks);
    }

    @PostMapping("/save")
    public ResponseEntity<List<Task>> saveTask(@RequestBody Taskdto task) {
        Task t = new Task();
        if (task.getId() != null && task.getId() != 0){
            t.setId(task.getId());
        }
        t.setTitle(task.getTitle());
        t.setDescription(task.getDescription());
        t.setDueDate(task.getDueDate());
        t.setProgress(task.getProgress());
        t.setPriority(task.getPriority());
        t.setHours(task.getHours());
        t.setIsReady(task.getIsReady());
        t.setUser(new User(task.getUserId()));

        taskService.saveTask(t);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "Task created successfully");
        return new ResponseEntity<>(taskService.getAllTasks(), headers, HttpStatus.CREATED);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task updates) {
        try {
            Task updatedTask = taskService.updateTask(id, updates);
            return ResponseEntity.ok(updatedTask);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<List<Task>> deleteById(@PathVariable Long id) {
        try {
            taskService.deleteTask(id);
            return ResponseEntity.ok(taskService.getAllTasks());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Task>> listTasksByUser(@PathVariable Long userId) {
        List<Task> tasks = taskService.getTasksByUserId(userId);
        if (tasks.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/getTaskById/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        Task task = taskService.getTaskById(id);
        if (task == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(task);
    }

    @PostMapping("/{id}/uploadPhoto")
    public ResponseEntity<String> uploadPhoto(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        Task task = taskService.getTaskById(id);

        try {
            String uploadDir = "uploads/";
            File uploadDirFile = new File(uploadDir);
            if (!uploadDirFile.exists()) {
                uploadDirFile.mkdirs();
            }

            String filePath = uploadDir + file.getOriginalFilename();
            file.transferTo(new File(filePath));
            task.setPhotoUrl(filePath);
            taskService.saveTask(task);

            return ResponseEntity.ok("File uploaded successfully: " + filePath);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file");
        }
    }
}
