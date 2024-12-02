package data_access;

import entity.CourseSchedule;
import entity.EventSchedule;
import use_case.add_course_schedule.AddCourseScheduleDataAccessInterface;
import use_case.add_event.AddEventScheduleDataAccessInterface;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryScheduleRepository implements AddCourseScheduleDataAccessInterface, AddEventScheduleDataAccessInterface {

    private final Map<LocalDate, List<CourseSchedule>> dateToCourseScheduleMap =  new HashMap<>();
    private final Map<String, CourseSchedule> courseSchedules = new HashMap<>();
    private final Map<LocalDate, List<EventSchedule>> dateToEventScheduleMap = new HashMap<>();
    private final Map<String, EventSchedule> eventSchedules = new HashMap<>();

    @Override
    public void saveCourseSchedule(CourseSchedule courseSchedule, List<LocalDate> dates) {
        for (LocalDate date : dates) {
            dateToCourseScheduleMap.putIfAbsent(date, new ArrayList<>());
            dateToCourseScheduleMap.get(date).add(courseSchedule);
            courseSchedule.getInstanceDateAndTimeSlot().putIfAbsent(date, new ArrayList<>());
        }
        courseSchedules.put(courseSchedule.getInstanceName(), courseSchedule);
    }

    @Override
    public void saveEventSchedule(EventSchedule eventSchedule, LocalDate date) {
        dateToEventScheduleMap.putIfAbsent(date, new ArrayList<>());
        dateToEventScheduleMap.get(date).add(eventSchedule);
        eventSchedules.put(eventSchedule.getName(), eventSchedule);
    }

    @Override
    public Map<LocalDate, List<CourseSchedule>> getDayToCourseScheduleMap() {
        return dateToCourseScheduleMap;
    }

    @Override
    public final Map<String, CourseSchedule> getCourseSchedules() {
        return courseSchedules;
    }

    @Override
    public Map<LocalDate, List<EventSchedule>> getDayToEventScheduleMap() {
        return dateToEventScheduleMap;
    }

    @Override
    public Map<String, EventSchedule> getEventSchedules() {
        return eventSchedules;
    }
}

