package entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class EventFactory {

    public EventSchedule createEvent(String name, LocalDate date, TimeSlot timeSlot, String description, Set<String> tags, String source) {
        return new EventSchedule(name, date, timeSlot, description, tags, source);
    }
}
