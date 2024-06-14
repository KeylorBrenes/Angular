package com.angular.grupo8.domain;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tasks")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Task implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String title;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "dueDate")
    private LocalDate dueDate;

    @Column(name = "progress", length = 25, columnDefinition = "CHAR(25) DEFAULT 'PENDING'")
    private String Progress = "PENDING";

    @Column(length = 15, columnDefinition = "CHAR(15) DEFAULT 'LOW'")
    private String priority = "LOW";

    @Column(name = "photo_url", length = 250)
    private String photoUrl;

    private Integer hours;

    @Column(name = "is_ready", columnDefinition = "CHAR(1) DEFAULT '0'")
    private boolean isReady;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Task() {
    }

    public Task(Long id, String title, String description, LocalDate dueDate, String Progress, String priority, String photoUrl, Integer hours, boolean isReady, User user) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.Progress = Progress;
        this.priority = priority;
        this.photoUrl = photoUrl;
        this.hours = hours;
        this.isReady = isReady;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public String getProgress() {
        return Progress;
    }

    public void setProgress(String Progress) {
        this.Progress = Progress;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public Integer getHours() {
        return hours;
    }

    public void setHours(Integer hours) {
        this.hours = hours;
    }

    public boolean getIsReady() {
        return isReady;
    }

    public void setIsReady(boolean isReady) {
        this.isReady = isReady;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Task [Progress=" + Progress + ", description=" + description + ", dueDate=" + dueDate + ", hours=" + hours
                + ", id=" + id + ", isReady=" + isReady + ", photoUrl=" + photoUrl + ", priority=" + priority + ", title="
                + title + ", user=" + user + "]";
    }
}
