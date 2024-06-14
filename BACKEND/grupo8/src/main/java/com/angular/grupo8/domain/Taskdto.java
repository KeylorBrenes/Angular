package com.angular.grupo8.domain;
import java.time.LocalDate;

public class Taskdto {

    private Long id;
    private String title;
    private String description;
    private LocalDate dueDate;
    private String Progress = "PENDING";
    private String priority = "LOW";
    private String photoUrl;
    private Integer hours;
    private boolean isReady = false;
    private long userId;

    public Taskdto() {
    }

    public Taskdto(Long id, String title, String description, LocalDate dueDate, String Progress, String priority, String photoUrl, Integer hours, boolean isReady, long userId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.Progress = Progress;
        this.priority = priority;
        this.photoUrl = photoUrl;
        this.hours = hours;
        this.isReady = isReady;
        this.userId = userId;
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

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
    @Override
    public String toString() {
        return "Taskdto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", dueDate=" + dueDate +
                ", Progress='" + Progress + '\'' +
                ", priority='" + priority + '\'' +
                ", photoUrl='" + photoUrl + '\'' +
                ", hours=" + hours +
                ", isReady=" + isReady +
                ", userId=" + userId +
                '}';
    }
}
