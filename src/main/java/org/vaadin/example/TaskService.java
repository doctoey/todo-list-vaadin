package org.vaadin.example;

import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service
public class TaskService implements Serializable {

    public String addTask(String task, List<String> tasks) {
        if (task == null || task.isEmpty()) {
            return "Task cannot be empty";
        } else {
            tasks.add(task);
            System.out.println("Task Added: " + task);
            return "Task " + task + " Added";
        }
    }

    public String deleteTask(String task, List<String> tasks) {
        if (task == null || task.isEmpty()) {
            return "Task cannot be empty";
        } else if (tasks.contains(task)) {
            tasks.remove(task);
            System.out.println("Task Deleted: " + task);
            return "Task " + task + " Deleted";
        } else {
            return "Task not found";
        }
    }
}
