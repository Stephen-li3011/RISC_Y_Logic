package view.recommendation;

import view.LabelTextPanel;

import java.awt.Component;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FiltersPanel extends JPanel {
    private final String viewName = "Filters";

    private final JTextField interestsInputField = new JTextField(15);
    private final JTextField DateInputField = new JTextField(15);
    private final JTextField TimeInputField = new JTextField(15);

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
    }

    public String getViewName() {
        return viewName;
    }
}
