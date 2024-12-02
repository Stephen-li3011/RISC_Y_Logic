package use_case.add_event;

public class AddEventOutputData {
    private final String eventName;
    private final boolean useCaseFailed;

    public AddEventOutputData(String eventName, boolean useCaseFailed) {
        this.eventName = eventName;
        this.useCaseFailed = useCaseFailed;
    }

    public String getEventName() {
        return eventName;
    }
}
