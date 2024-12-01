package view;

import interface_adapter.add_event.AddUserEventController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;

import javax.swing.*;

public class AddUserEventView {
    private final JFrame frame = new JFrame("Add Your Own Event");

    private final JTextField eventNameInputField = new JTextField(15);
    private final JTextField eventDateInputField = new PlaceholderTextField(15, "yyyy-mm-dd");
    private final JTextField eventTimeInputField = new PlaceholderTextField(15, "hh:mm~hh:mm");
    private final JTextField eventDescriptionInputField = new JTextField(15);

    private final JList<String> tagsInputField = new JList<>();

    private final JButton addEvent = new JButton("Add Event");

    public AddUserEventView(AddUserEventController addUserEventController) {

        final JPanel view = new JPanel();
        final JLabel title = new JLabel("Add Event");

        final JPanel eventNameInfo = new LabelTextPanel(
                new JLabel("Event Name: "), eventNameInputField);
        final JPanel eventDateInfo = new LabelTextPanel(
                new JLabel("Event Date: "), eventDateInputField);
        final JPanel eventTimeInfo = new LabelTextPanel(
                new JLabel("Event Time: "), eventTimeInputField);
        final JPanel eventDescriptionInfo = new LabelTextPanel(
                new JLabel("Event Description: "), eventDescriptionInputField);

        final JPanel buttons = new JPanel();
        buttons.add(addEvent);

        addEvent.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if(e.getSource().equals(addEvent)) {
                            addUserEventController.execute(
                                    eventNameInputField.getText(),
                                    eventDateInputField.getText(),
                                    eventTimeInputField.getText(),
                                    eventDescriptionInputField.getText(),
                                    new HashSet<>(tagsInputField.getSelectedValuesList())
                            );
                        }
                    }
                }
        );

        view.setLayout(new BoxLayout(view, BoxLayout.Y_AXIS));

        view.add(title);
        view.add(eventNameInfo);
        view.add(eventDateInfo);
        view.add(eventTimeInfo);
        view.add(eventDescriptionInfo);
        view.add(buttons);

        frame.add(view);
        frame.pack();
        frame.setVisible(true);
    }
}
