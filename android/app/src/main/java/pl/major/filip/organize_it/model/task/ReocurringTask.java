package pl.major.filip.organize_it.model.task;

import java.time.LocalDate;

public class ReocurringTask extends Task {
    private LocalDate startDate;
    private LocalDate endDate;
    private byte daysOfWeek;

}
