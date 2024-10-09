package com.example.todoapp.Model;

public class Task
{
    private String name;
    private String description;
    private Boolean isCompleted;

    public Task(String name, String description, Boolean isCompleted) {
        this.name = name;
        this.description = description;
        this.isCompleted = isCompleted;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(Boolean completed) {
        isCompleted = completed;
    }
}
