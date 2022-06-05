package pl.major.filip.organize_it.model.task;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDate;
import java.util.Objects;

import pl.major.filip.organize_it.model.topic.Topic;

public class SimpleTask extends Task {
    private LocalDate date;

    public SimpleTask() {
        super();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void updateStatus() {
        LocalDate now = LocalDate.now();
        if (now.isBefore(date)) {
            setStatus(TaskStatus.SCHEDULED);
        }
        else if (now.isEqual(date)) {
            setStatus(TaskStatus.ACTIVE);
        }
        else if (now.isAfter(date)) {
            setStatus(TaskStatus.FINISHED);
        }
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

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimpleTask that = (SimpleTask) o;
        return Objects.equals(date, that.date) && Objects.equals(getTitle(), that.getTitle())
                && Objects.equals(getTopic(), that.getTopic()) && getStatus() == that.getStatus();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public int hashCode() {
        return Objects.hash(date, getTitle(), getTopic(), getStatus());
    }
}
