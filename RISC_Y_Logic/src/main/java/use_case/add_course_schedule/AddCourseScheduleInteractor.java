package use_case.add_course_schedule;

import entity.CourseSchedule;
import entity.TimeSlot;

import java.time.LocalDate;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

public class AddCourseScheduleInteractor implements AddCourseScheduleInputBoundary {
    private final AddCourseScheduleDataAccessInterface addCourseScheduleDataAccessInterface;
    private final AddCourseScheduleOutputBoundary addCourseScheduleOutputBoundary;

    public AddCourseScheduleInteractor(AddCourseScheduleDataAccessInterface addCourseScheduleDataAccessInterface, AddCourseScheduleOutputBoundary addCourseScheduleOutputBoundary) {
        this.addCourseScheduleDataAccessInterface = addCourseScheduleDataAccessInterface;
        this.addCourseScheduleOutputBoundary = addCourseScheduleOutputBoundary;
    }

    @Override
    public void add(AddCourseScheduleInputData inputData) {
        try {
            DayOfWeek dayOfWeek = DayOfWeek.valueOf(inputData.getCourseDate().toUpperCase());
            LocalTime startTime = inputData.getStartTime();
            LocalTime endTime = inputData.getEndTime();
            TimeSlot timeSlot = new TimeSlot(startTime, endTime);
            List<LocalDate> localDates = mapDayToDates(dayOfWeek, inputData.getSemester());
            CourseSchedule schedule = new CourseSchedule(
                    inputData.getCourseName(), new HashMap<>());
            for (LocalDate date : localDates) {
                schedule.getInstanceDateAndTimeSlot().putIfAbsent(date, new ArrayList<>());
                schedule.getInstanceDateAndTimeSlot().get(date).add(timeSlot);
            }
            addCourseScheduleDataAccessInterface.saveCourseSchedule(schedule, localDates);
            addCourseScheduleOutputBoundary.present(
                    new AddCourseScheduleOutputData("Course schedule added successfully"));
        } catch (Exception e) {
            addCourseScheduleOutputBoundary.present(
                    new AddCourseScheduleOutputData("Error: " + e.getMessage()));
        }
    }

    private List<LocalDate> mapDayToDates(DayOfWeek dayOfWeek, String semester) {
        LocalDate startDate;
        LocalDate endDate;
        switch (semester) {
            case "Fall":
                startDate = LocalDate.of(LocalDate.now().getYear(), 9, 1);
                endDate = LocalDate.of(LocalDate.now().getYear(), 12, 31);
                break;
            case "Winter":
                startDate = LocalDate.of(LocalDate.now().getYear(), 1, 1);
                endDate = LocalDate.of(LocalDate.now().getYear(), 4, 30);
                break;
            case "Summer":
                startDate = LocalDate.of(LocalDate.now().getYear(), 5, 1);
                endDate = LocalDate.of(LocalDate.now().getYear(), 8, 31);
                break;
            default:
                throw new IllegalArgumentException("Invalid semester");
        }
        List<LocalDate> dates = new ArrayList<>();
        while (!startDate.isAfter(endDate)) {
            if (startDate.getDayOfWeek() == dayOfWeek) {
                dates.add(startDate);
            }
            startDate = startDate.plusDays(1);
        }

        return dates;
    }
}