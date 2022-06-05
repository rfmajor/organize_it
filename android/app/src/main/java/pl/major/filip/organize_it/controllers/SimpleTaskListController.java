package pl.major.filip.organize_it.controllers;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;

import pl.major.filip.organize_it.model.task.SimpleTask;
import pl.major.filip.organize_it.model.task.TaskStatus;

public class SimpleTaskListController implements TaskListController<SimpleTask> {
    private final List<SimpleTask> tasks = new ArrayList<>();

    @Override
    public void addTask(SimpleTask task) {
        tasks.add(task);
    }

    @Override
    public void updateTask(SimpleTask oldTask, SimpleTask newTask) {
        int i = tasks.indexOf(oldTask);
        tasks.set(i, newTask);
    }

    @Override
    public void deleteTask(SimpleTask task) {
        tasks.remove(task);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void refresh() {
        tasks.forEach(SimpleTask::updateStatus);
    }

    @Override
    public void cancelTask(SimpleTask task) {
        int i = tasks.indexOf(task);
        tasks.get(i).setStatus(TaskStatus.CANCELLED);
    }

    @Override
    public List<SimpleTask> getTasks() {
        return tasks;
    }
}
