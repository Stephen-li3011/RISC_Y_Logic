package use_case.calendar;

import entity.CalendarDay;
import entity.CourseSchedule;

import java.time.LocalDate;
import java.util.*;

public class CalendarInteractor implements CalendarInputBoundary{

    private final Map<LocalDate, List<CourseSchedule>> courseScheduleMap;
    private final CalendarOutputBoundary calendarOutputBoundary;

    public CalendarInteractor(CalendarOutputBoundary calendarOutputBoundary) {
        this.calendarOutputBoundary = calendarOutputBoundary;
        this.courseScheduleMap = new HashMap<>();
    }

    @Override
    public void generate(CalendarInputData calendarInputData) {
        int year = calendarInputData.getYear();
        int month = calendarInputData.getMonth();

        List<CalendarDay> calendarDays = new ArrayList<>();
        Calendar calendar = new GregorianCalendar(year, month, 1);

        int startDay = calendar.get(Calendar.DAY_OF_WEEK);
        int numberOfDays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        for (int i = 0; i < startDay - 1; i++) {
            calendarDays.add(new CalendarDay(null, Collections.emptyList()));
        }

        for (int day = 1; day <= numberOfDays; day++) {
            LocalDate currentDate = LocalDate.of(year, month + 1, day);
            List<CourseSchedule> schedules = courseScheduleMap.getOrDefault(currentDate, Collections.emptyList());
            calendarDays.add(new CalendarDay(currentDate, schedules));
        }

        CalendarOutputData calendarOutputData = new CalendarOutputData(calendarDays);
        calendarOutputBoundary.present(calendarOutputData);
    }

    @Override
    public Map<LocalDate, List<CourseSchedule>> getCourseScheduleMap() {
        return courseScheduleMap;
    }
}
