package view;

import java.awt.Component;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FiltersPanel extends JPanel {
    private final String viewName = "Filters";

    private final JTextField interestsInputField = new JTextField(15);
    private final PlaceholderTextField DateInputField = new PlaceholderTextField(15, "yyyy-MM-dd");
    private final PlaceholderTextField TimeInputField = new PlaceholderTextField(15, "HH:mm");
    private final JButton searchButton = new JButton("Search");

    public FiltersPanel() {
        final JLabel title = new JLabel("Filters");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final LabelTextPanel interestsInfo = new LabelTextPanel(
                new JLabel("Interests: "), interestsInputField);
        final LabelTextPanel DateInfo = new LabelTextPanel(
                new JLabel("Date: "), DateInputField);
        final LabelTextPanel TimeInfo = new LabelTextPanel(
                new JLabel("Time: "), TimeInputField);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(interestsInfo);
        this.add(DateInfo);
        this.add(TimeInfo);
        this.add(searchButton);
    }

    public String getViewName() {
        return viewName;
    }

    // Getter methods for the input fields
    public String getInterests() {
        return interestsInputField.getText().trim();
    }

    public String getDate() {
        return DateInputField.getText().trim(); // Format: "yyyy-MM-dd"
    }

    public String getTime() {
        return TimeInputField.getText().trim(); // Format: "HH:mm"
    }

    // Getter for the search button (for attaching action listeners)
    public JButton getSearchButton() {
        return searchButton;
    }
}