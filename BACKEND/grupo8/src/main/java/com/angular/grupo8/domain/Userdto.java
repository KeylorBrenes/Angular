package com.angular.grupo8.domain;

public class Userdto{

    private Long id;
    private String userName;
    private String email;
    private boolean isActive;

    public Userdto() {
    }

    public Userdto(Long id) {
        this.id = id;
    }

    public Userdto(Long id, String userName, String email, boolean isActive) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.isActive = isActive;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }
}