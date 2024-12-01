package interface_adapter.add_event;

import use_case.add_event.AddEventOutputBoundary;
import use_case.add_event.AddEventOutputData;

import javax.swing.*;

public class AddEventPresenter implements AddEventOutputBoundary {

    public void prepareSuccessView(AddEventOutputData outputData) {
        JOptionPane.showMessageDialog(null, "Event " + outputData.getEventName() + " added successfully", "Result", JOptionPane.INFORMATION_MESSAGE);
    }

    public void prepareFailView(String message) {
        JOptionPane.showMessageDialog(null, message, "Result", JOptionPane.INFORMATION_MESSAGE);
    }
}
