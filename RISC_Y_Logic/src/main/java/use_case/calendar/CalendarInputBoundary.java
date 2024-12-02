package use_case.calendar;

import entity.CourseSchedule;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface CalendarInputBoundary {
    void generate(CalendarInputData calendarInputData);

    Map<LocalDate, List<CourseSchedule>> getCourseScheduleMap();
}
