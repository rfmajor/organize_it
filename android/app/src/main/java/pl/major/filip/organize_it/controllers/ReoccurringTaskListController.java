package pl.major.filip.organize_it.controllers;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.List;

import pl.major.filip.organize_it.model.task.ReoccurringTask;
import pl.major.filip.organize_it.model.task.TaskStatus;

public class ReoccurringTaskListController implements TaskListController<ReoccurringTask> {
    private List<ReoccurringTask> tasks;

    @Override
    public void addTask(ReoccurringTask task) {
        tasks.add(task);
    }

    @Override
    public void updateTask(ReoccurringTask oldTask, ReoccurringTask newTask) {
        int i = tasks.indexOf(oldTask);
        tasks.set(i, newTask);
    }

    @Override
    public void deleteTask(ReoccurringTask task) {
        tasks.remove(task);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void refresh() {
        tasks.forEach(ReoccurringTask::updateStatus);
    }

    @Override
    public void cancelTask(ReoccurringTask task) {
        int i = tasks.indexOf(task);
        tasks.get(i).setStatus(TaskStatus.CANCELLED);
    }
}
