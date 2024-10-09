package com.example.todoapp;

import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private static List<Task> instance;
    private static List<Task> completed;

    private TaskManager() {
        instance = new ArrayList<>();
    }

    public static List<Task> getInstance() {
        if (instance == null) {
            instance = new ArrayList<Task>();
            completed = new ArrayList<Task>();
        }
        return instance;
    }
    public static List<Task> getInstanceCompleted() {
        return completed;
    }

    public static void addTask(Task task) {
        instance.add(task);
    }

    public static void removeTask(Task task) {
       instance.remove(task);
    }
}
