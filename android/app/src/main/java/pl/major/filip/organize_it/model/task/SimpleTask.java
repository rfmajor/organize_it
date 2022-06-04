package pl.major.filip.organize_it.model.task;

import java.time.LocalDate;

import pl.major.filip.organize_it.model.topic.Topic;

public class SimpleTask extends Task {
    private LocalDate date;

    public SimpleTask() {
        super();
    }

    private SimpleTask(SimpleTaskBuilder builder) {
        SimpleTask task = new SimpleTask();

        task.setTitle(builder.task.getTitle());
        task.setStatus(builder.task.getStatus());
        task.setTopic(builder.task.getTopic());
        task.date = builder.task.date;
    }

    public static SimpleTaskBuilder builder() {
        return new SimpleTaskBuilder();
    }

    public static class SimpleTaskBuilder extends TaskBuilder {
        private SimpleTask task;

        public SimpleTaskBuilder setDate(LocalDate date) {
            task.date = date;
            return this;
        }

        @Override
        public SimpleTaskBuilder setTitle(String title) {
            task.setTitle(title);
            return this;
        }

        @Override
        public SimpleTaskBuilder setTopic(Topic topic) {
            task.setTopic(topic);
            return this;
        }

        @Override
        public SimpleTaskBuilder setStatus(TaskStatus status) {
            task.setStatus(status);
            return this;
        }

        @Override
        public SimpleTask build() {
            return new SimpleTask(this);
        }
    }

    public SimpleTask(String title) {
        super(title);
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
