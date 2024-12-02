package use_case.add_event;

import entity.EventSchedule;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface AddEventScheduleDataAccessInterface {

    void saveEventSchedule(EventSchedule eventSchedule, LocalDate date);

    Map<LocalDate, List<EventSchedule>> getDayToEventScheduleMap();

    Map<String, EventSchedule> getEventSchedules();
}
