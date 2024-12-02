package view;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import entity.Event;

public class SearchResultsPanel extends JPanel {
    private final String viewName = "Search Results";
    private final JPanel resultsContainer = new JPanel();

    public SearchResultsPanel() {
        JLabel title = new JLabel("Search Results");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        resultsContainer.setLayout(new BoxLayout(resultsContainer, BoxLayout.Y_AXIS));
        JScrollPane searchResultsScrollPane = new JScrollPane(resultsContainer);
        searchResultsScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        searchResultsScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        // Limit the height of the scroll pane to enable scrolling
        searchResultsScrollPane.setPreferredSize(new Dimension(700, 600));

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(searchResultsScrollPane);
    }

    /*public String getViewName() {
        return viewName;
    }
    */
    public void updateResults(List<Event> events) {
        resultsContainer.removeAll();

        for (Event event : events) {
            JPanel eventPanel = new JPanel();
            eventPanel.setLayout(new BoxLayout(eventPanel, BoxLayout.Y_AXIS));
            eventPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

            JLabel titleLabel = new JLabel("<html><div style='width: 350px;'>" + event.getTitle() + "</div></html>");
            titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            JLabel descriptionLabel = new JLabel("<html><div style='width: 350px;'>" + event.getDescription() + "</div></html>"); // Set max width
            descriptionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            JButton viewEventButton = new JButton("View Event");
            viewEventButton.addActionListener(e -> openUrl(event.getUrl()));
            viewEventButton.setAlignmentX(Component.LEFT_ALIGNMENT);

            eventPanel.add(titleLabel);
            eventPanel.add(descriptionLabel);
            eventPanel.add(viewEventButton);

            resultsContainer.add(eventPanel);
        }

        resultsContainer.revalidate();
        resultsContainer.repaint();
    }

    private void openUrl(String url) {
        try {
            Desktop.getDesktop().browse(new java.net.URI(url));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Unable to open URL: " + url, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}