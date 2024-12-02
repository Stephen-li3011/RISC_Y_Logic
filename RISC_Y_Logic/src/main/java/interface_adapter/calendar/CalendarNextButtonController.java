package interface_adapter.calendar;

import use_case.calendar.CalendarInputBoundary;
import use_case.calendar.CalendarInputData;

public class CalendarNextButtonController {

    private final CalendarInputBoundary calendarInputBoundary;
    private final int[] year;
    private final int[] month;

    public CalendarNextButtonController(CalendarInputBoundary calendarInputBoundary, int[] year, int[] month) {
        this.calendarInputBoundary = calendarInputBoundary;
        this.year = year;
        this.month = month;
    }

    public void handleNextButtonClick() {
        month[0] = month[0] + 1;
        if (month[0] >= 12) {
            month[0] = 0;
            year[0] = year[0] + 1;
        }
        calendarInputBoundary.generate(new CalendarInputData(year[0], month[0]));
    }
}
