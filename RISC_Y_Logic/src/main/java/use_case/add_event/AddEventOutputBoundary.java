package use_case.add_event;

public interface AddEventOutputBoundary {

    void prepareSuccessView(AddEventOutputData outputData);

    void prepareFailView(String message);
}
