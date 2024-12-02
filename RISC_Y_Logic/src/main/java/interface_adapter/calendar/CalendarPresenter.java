package interface_adapter.calendar;

import entity.CalendarDay;
import use_case.calendar.CalendarOutputBoundary;
import use_case.calendar.CalendarOutputData;

import java.util.List;

public class CalendarPresenter implements CalendarOutputBoundary {

    private List<CalendarDay> calendarDays;

    @Override
    public void present(CalendarOutputData calendarOutputData) {
        this.calendarDays = calendarOutputData.getCalendarDays();
    }

    public List<CalendarDay> getCalendarDays() {
        return calendarDays;
    }
}
