package view.recommendation;

import javax.swing.*;

public class EventRecommendationView {
    private final JFrame frame = new JFrame("Event Recommendation");
    private final String viewName = "Event Recommendation";

    private final JPanel filters = new FiltersPanel();
    private final JPanel searchResults = new SearchResultsPanel();

    public EventRecommendationView() {
        final JPanel view = new JPanel();
        view.add(filters);
        view.add(searchResults);

        view.setLayout(new BoxLayout(view, BoxLayout.X_AXIS));

        frame.add(view);
        frame.pack();
        frame.setVisible(true);
    }

    public String getViewName() {
        return viewName;
    }
}
