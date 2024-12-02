package use_case.calendar;

import entity.CalendarDay;

import java.util.List;

public class CalendarOutputData {
    private final List<CalendarDay> calendarDays;

    public CalendarOutputData(List<CalendarDay> calendarDays) {
        this.calendarDays = calendarDays;
    }

    public List<CalendarDay> getCalendarDays() {
        return calendarDays;
    }
}
