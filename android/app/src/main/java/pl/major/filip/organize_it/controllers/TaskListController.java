package pl.major.filip.organize_it.controllers;

import java.util.List;

import pl.major.filip.organize_it.model.task.Task;

public class TaskListController {
    private List<Task> tasks;

    public List<Task> getTasks() {
        return tasks;
    }

    public void deleteTask(Task task) {
        tasks.remove(task);
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    

}
