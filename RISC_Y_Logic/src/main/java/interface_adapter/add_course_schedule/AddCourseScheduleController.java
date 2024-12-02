package interface_adapter.add_course_schedule;

import use_case.add_course_schedule.AddCourseScheduleInputBoundary;
import use_case.add_course_schedule.AddCourseScheduleInputData;

import java.time.LocalTime;

public class AddCourseScheduleController {
    private final AddCourseScheduleInputBoundary addCourseScheduleInputBoundary;

    public AddCourseScheduleController(AddCourseScheduleInputBoundary addCourseScheduleInputBoundary) {
        this.addCourseScheduleInputBoundary = addCourseScheduleInputBoundary;
    }

    public void execute(String courseName, String dayOfWeek, LocalTime startTime, LocalTime endTime, String semester) {

        AddCourseScheduleInputData addCourseScheduleInputData = new AddCourseScheduleInputData(
                courseName,
                dayOfWeek,
                LocalTime.parse(startTime.toString()),
                LocalTime.parse(endTime.toString()),
                semester
        );

        addCourseScheduleInputBoundary.add(addCourseScheduleInputData);

    }

}
