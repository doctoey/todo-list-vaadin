package org.vaadin.example;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.html.Span;

import java.util.ArrayList;
import java.util.List;

@Route
public class MainView extends VerticalLayout {

    private final List<String> tasks = new ArrayList<>();
    private final VerticalLayout taskLayout = new VerticalLayout();

    /**
     * Construct a new Vaadin view.
     *
     * @param taskService The task service.
     */
    public MainView(@Autowired TaskService taskService) {

        // Use TextField for standard text input
        TextField textField = new TextField("Enter Task");
        textField.setPlaceholder("Enter your task");
        textField.addClassName("bordered");

        // Add Task Button
        Button addButton = new Button("Add Task", e -> {
            String task = textField.getValue();
            String result = taskService.addTask(task, tasks);
            Notification.show(result);
            if (result.equals("Task " + task + " Added")) {
                updateTaskList(taskService);
                textField.clear();
            }
        });
        addButton.addClassName("add-button");

        // Style buttons
        addClassName("centered-content");

        // Add components to layout
        add(textField, addButton, taskLayout);

        // Initialize the task list display
        updateTaskList(taskService);
    }

    private void updateTaskList(TaskService taskService) {
        taskLayout.removeAll();
        for (String task : tasks) {
            HorizontalLayout taskItemLayout = new HorizontalLayout();
            taskItemLayout.addClassName("task-item");
            Span taskSpan = new Span(task);
            Button deleteButton = new Button("Delete", e -> {
                String result = taskService.deleteTask(task, tasks);
                Notification.show(result);
                if (result.equals("Task " + task + " Deleted")) {
                    updateTaskList(taskService);
                }
            });
            deleteButton.addClassName("delete-button");
            taskItemLayout.add(taskSpan, deleteButton);
            taskLayout.add(taskItemLayout);
        }
    }
}
