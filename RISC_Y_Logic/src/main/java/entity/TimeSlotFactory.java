package entity;

import java.time.LocalTime;

public class TimeSlotFactory {

    public TimeSlot createTimeSlot(String timeSlot) {
        String[] time = timeSlot.split("~");
        LocalTime startTime = LocalTime.parse(time[0]);
        LocalTime endTime = LocalTime.parse(time[1]);
        try {
            return new TimeSlot(startTime, endTime);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
