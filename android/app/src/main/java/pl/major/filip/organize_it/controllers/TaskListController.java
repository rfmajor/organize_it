package pl.major.filip.organize_it.controllers;

import pl.major.filip.organize_it.model.task.Task;

public interface TaskListController<T extends Task> {
    void addTask(T task);
    void deleteTask(T task);
    void refresh();
    void cancelTask(T task);
}
