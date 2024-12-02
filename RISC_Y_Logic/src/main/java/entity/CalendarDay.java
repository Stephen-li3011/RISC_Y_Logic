package entity;

import java.time.LocalDate;
import java.util.List;

public class CalendarDay {
    private final LocalDate date;
    private final List<CourseSchedule> schedules;

    public CalendarDay(LocalDate day, List<CourseSchedule> schedules) {
        this.date = day;
        this.schedules = schedules;
    }

    public LocalDate getDate() {
        return date;
    }

    public List<CourseSchedule> getSchedules() {
        return schedules;
    }

    public boolean isEmpty() {
        return date == null;
    }
}
