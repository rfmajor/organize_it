package pl.major.filip.organize_it.model.task;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDate;

import pl.major.filip.organize_it.model.topic.Topic;

public class ReoccurringTask extends Task {
    private LocalDate startDate;
    private LocalDate endDate;
    private byte daysOfWeek;

    public ReoccurringTask() {
    }

    private ReoccurringTask(ReoccurringTaskBuilder builder) {
        ReoccurringTask task = new ReoccurringTask();

        task.setTitle(builder.task.getTitle());
        task.setStatus(builder.task.getStatus());
        task.setTopic(builder.task.getTopic());
        task.startDate = builder.task.startDate;
        task.endDate = builder.task.endDate;
        task.daysOfWeek = builder.task.daysOfWeek;
    }

    public static ReoccurringTaskBuilder builder() {
        return new ReoccurringTaskBuilder();
    }

    public static class ReoccurringTaskBuilder extends TaskBuilder {
        private ReoccurringTask task;

        public ReoccurringTaskBuilder setStartDate(LocalDate startDate) {
            task.startDate = startDate;
            return this;
        }

        public ReoccurringTaskBuilder setEndDate(LocalDate endDate) {
            task.endDate = endDate;
            return this;
        }

        @Override
        public ReoccurringTaskBuilder setTitle(String title) {
            task.setTitle(title);
            return this;
        }

        @Override
        public ReoccurringTaskBuilder setTopic(Topic topic) {
            task.setTopic(topic);
            return this;
        }

        @Override
        public ReoccurringTaskBuilder setStatus(TaskStatus status) {
            task.setStatus(status);
            return this;
        }

        public ReoccurringTask build() {
            return new ReoccurringTask(this);
        }
    }

    public ReoccurringTask(String title) {
        super(title);
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public byte getDaysOfWeek() {
        return daysOfWeek;
    }

    public void setDaysOfWeek(byte daysOfWeek) {
        this.daysOfWeek = daysOfWeek;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void main(String[] args) {
        SimpleTask task = SimpleTask.builder()
                .setTitle("Tytuł")
                .setStatus(TaskStatus.STARTED)
                .setTopic(new Topic("dupa"))
                .setDate(LocalDate.of(1203, 3, 21))
                .build();

        ReoccurringTask reoccurringTask = ReoccurringTask.builder()
                .setTitle("costam")
                .setStartDate(LocalDate.of(2020, 4, 12))
                .setEndDate(LocalDate.of(2020, 5, 24))
                .build();

    }
}
