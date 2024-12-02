package use_case.calendar;

import entity.CalendarDay;
import entity.CourseSchedule;
import interface_adapter.calendar.CalendarPresenter;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class CalendarInteractorTest {

    @Test
    public void CalendarInteractorTest() {

        CalendarInputData calendarInputData = new CalendarInputData(2024, 10);

        CalendarOutputBoundary calendarOutputBoundary = new CalendarPresenter();

        CalendarInteractor calendarInteractor = new CalendarInteractor(calendarOutputBoundary);

        calendarInteractor.generate(calendarInputData);

        Map<LocalDate, List<CourseSchedule>> courseScheduleMap = calendarInteractor.getCourseScheduleMap();

        assertEquals(courseScheduleMap, new HashMap<LocalDate, List<CourseSchedule>>());

        assertEquals(2024, calendarInputData.getYear());
        assertEquals(10, calendarInputData.getMonth());
    }
}
