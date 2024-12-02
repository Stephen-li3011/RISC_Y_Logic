package use_case.add_course_schedule;

import entity.TimeSlot;

import java.time.LocalTime;

public class AddCourseScheduleInputData {

    private final String courseName;
    private final String courseDate;
    private final LocalTime startTime;
    private final LocalTime endTime;
    private final String semester;

    public AddCourseScheduleInputData(String courseName, String courseDate, LocalTime startTime, LocalTime endTime, String semester) {
        this.courseName = courseName;
        this.courseDate = courseDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.semester = semester;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getCourseDate() {
        return courseDate;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public String getSemester() {
        return semester;
    }

}
