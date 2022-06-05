package pl.major.filip.organize_it.model.task;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import pl.major.filip.organize_it.model.topic.Topic;

public class ReoccurringTask extends Task {
    private LocalDate startDate;
    private LocalDate endDate;
    private Set<DayOfWeek> daysOfWeek;

    public ReoccurringTask() {
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void updateStatus() {
        LocalDate now = LocalDate.now();
        //before the start
        if (now.isBefore(startDate)) {
            setStatus(TaskStatus.SCHEDULED);
        }
        //after the start, before the end
        else if (!now.isBefore(startDate) && !now.isAfter(endDate)) {
            if (daysOfWeek.contains(now.getDayOfWeek())) {
                setStatus(TaskStatus.ACTIVE);
            } else {
                setStatus(TaskStatus.SCHEDULED);
            }
        }
        //after the end
        else if (now.isAfter(endDate)) {
            setStatus(TaskStatus.FINISHED);
        }
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

        public ReoccurringTaskBuilder setDaysOfWeek(Set<DayOfWeek> daysOfWeek) {
            task.daysOfWeek = daysOfWeek;
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

    public LocalDate getEndDate() {
        return endDate;
    }

    public Set<DayOfWeek> getDaysOfWeek() {
        return daysOfWeek;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public String getDaysOfWeekString() {
        return daysOfWeek.stream()
                .map(day -> String.valueOf(day.getValue()))
                .collect(Collectors.joining(","));
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static Set<DayOfWeek> getDaysOfWeekFromString(String daysOfWeek) {
        return Arrays.stream(daysOfWeek.split(","))
                .map(day -> DayOfWeek.of(Integer.parseInt(day)))
                .collect(Collectors.toSet());
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReoccurringTask that = (ReoccurringTask) o;
        return Objects.equals(startDate, that.startDate)
                && endDate.isEqual(that.endDate)
                && daysOfWeek.equals(that.daysOfWeek)
                && Objects.equals(getTitle(), that.getTitle())
                && Objects.equals(getTopic(), that.getTopic()) && getStatus() == that.getStatus();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public int hashCode() {
        return Objects.hash(startDate, endDate, daysOfWeek, getTitle(), getTopic(), getStatus());
    }
}
