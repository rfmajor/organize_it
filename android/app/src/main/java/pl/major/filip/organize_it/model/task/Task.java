package pl.major.filip.organize_it.model.task;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.Objects;

import pl.major.filip.organize_it.model.topic.Topic;

public abstract class Task {
    private String title;
    private Topic topic;
    private TaskStatus status;

    public Task() {
    }

    public static abstract class TaskBuilder {
        public abstract TaskBuilder setTitle(String title);
        public abstract TaskBuilder setTopic(Topic topic);
        public abstract TaskBuilder setStatus(TaskStatus status);
        public abstract Task build();
    }

    public abstract void updateStatus();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public Task(String title) {
        this.title = title;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(title, task.title) && Objects.equals(topic, task.topic) && status == task.status;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public int hashCode() {
        return Objects.hash(title, topic, status);
    }
}
