package pl.major.filip.organize_it.model.task;

import java.time.LocalDate;

public class SimpleTask extends Task {
    private LocalDate date;

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
