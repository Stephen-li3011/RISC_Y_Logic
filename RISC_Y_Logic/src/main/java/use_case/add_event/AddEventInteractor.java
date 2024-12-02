package use_case.add_event;

import entity.EventSchedule;
import entity.EventFactory;
import entity.TimeSlot;
import entity.TimeSlotFactory;
import interface_adapter.StringFormatChecker;

import java.time.LocalDate;
import java.util.Set;

public class AddEventInteractor {
    private final AddEventScheduleDataAccessInterface eventDataAccessObject;
    private final AddEventOutputBoundary addEventPresenter;
    private final EventFactory eventFactory;

    public AddEventInteractor(AddEventScheduleDataAccessInterface eventDataAccessObject,
                              AddEventOutputBoundary newEventPresenter,
                              EventFactory eventFactory) {
        this.eventDataAccessObject = eventDataAccessObject;
        this.addEventPresenter = newEventPresenter;
        this.eventFactory = eventFactory;
    }

    public void execute(AddEventInputData addEventInputData) {
        final String name = addEventInputData.getName();
        final String date = addEventInputData.getDate();
        final String time = addEventInputData.getTime();
        final String description = addEventInputData.getDescription();
        final String source = addEventInputData.getSource();
        final Set<String> tags = addEventInputData.getTags();

        if (name == null || date == null || time == null) {
            addEventPresenter.prepareFailView("Name, date, and time cannot be empty.");

        } else if (StringFormatChecker.checkString("date", date)) {
            addEventPresenter.prepareFailView("Date format is incorrect.");

        } else if (StringFormatChecker.checkString("time_slot", time)) {
            addEventPresenter.prepareFailView("Time format is incorrect.");

        } else {
            final LocalDate checkedDate = LocalDate.parse(date);
            final TimeSlot timeslot = new TimeSlotFactory().createTimeSlot(time);

            final EventSchedule newEvent = eventFactory.createEvent(name, checkedDate, timeslot, description, tags, source);
            eventDataAccessObject.saveEventSchedule(newEvent, checkedDate);
            AddEventOutputData outputData = new AddEventOutputData(name, false);
            addEventPresenter.prepareSuccessView(outputData);
        }
    }
}
