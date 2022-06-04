package pl.major.filip.organize_it.model.task;

import pl.major.filip.organize_it.model.topic.Topic;

public abstract class Task {
    private String title;
    private Topic topic;
    private TaskStatus status;

    public Task() {
    }

    public static abstract class TaskBuilder {
        private Task task;

        public abstract TaskBuilder setTitle(String title);

        public abstract TaskBuilder setTopic(Topic topic);

        public abstract TaskBuilder setStatus(TaskStatus status);

        public abstract Task build();
    }

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
}
