package use_case.add_course_schedule;

import entity.CourseSchedule;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface AddCourseScheduleDataAccessInterface {

    void saveCourseSchedule(CourseSchedule courseSchedule, List<LocalDate> dates);

    Map<LocalDate, List<CourseSchedule>> getDayToCourseScheduleMap();

    Map<String, CourseSchedule> getCourseSchedules();
}
