package pl.major.filip.organize_it.model.task;

import java.time.LocalDate;

public class ReocurringTask extends Task {
    private LocalDate startDate;
    private LocalDate endDate;
    private byte daysOfWeek;

    public ReocurringTask(String title) {
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

    public static void main(String[] args) {

    }
}
