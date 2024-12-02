package interface_adapter.add_event;

import use_case.add_event.AddEventInteractor;

import java.util.Set;

public interface AddEventControllerInterface {

    void execute(String eventName, String eventDate, String eventTime, String eventDescription, Set<String> tags);
}
