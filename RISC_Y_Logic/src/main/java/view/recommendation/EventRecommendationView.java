package view.recommendation;

import data_access.MeetupGraphQLClient;

import javax.swing.*;
import java.util.List;
import java.util.ArrayList;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Event;

public class EventRecommendationView {
    private final JFrame frame = new JFrame("Event Recommendation");
    private final String viewName = "Event Recommendation";

    private final view.FiltersPanel filtersPanel = new view.FiltersPanel();
    private final view.SearchResultsPanel searchResultsPanel = new view.SearchResultsPanel();
    private final MeetupGraphQLClient meetupClient; // Add the API client

    public EventRecommendationView(MeetupGraphQLClient meetupClient) {
        this.meetupClient = meetupClient;

        final JPanel view = new JPanel();
        view.add(filtersPanel);
        view.add(searchResultsPanel);

        view.setLayout(new BoxLayout(view, BoxLayout.X_AXIS));

        filtersPanel.getSearchButton().addActionListener(e -> searchEvents());

        frame.add(view);
        frame.pack();
        frame.setVisible(true);
    }

    public String getViewName() {

        return viewName;
    }

    // Method to handle searching and updating results
    private void searchEvents() {
        // Get inputs from FiltersPanel
        String interests = filtersPanel.getInterests();
        String date = filtersPanel.getDate();
        String time = filtersPanel.getTime();

        // Validate inputs
        if (interests.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please enter interests to search for events.");
            return;
        }

        try {
            // Query the Meetup API
            String response = meetupClient.searchEvents(interests, 40.7128, -74.0060, 50); // Example lat/lon
            List<Event> events = parseEventResponse(response); // Parse API response

            // Update the SearchResultsPanel with events
            searchResultsPanel.updateResults(events);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Error fetching events: " + ex.getMessage());
        }
    }

    private List<Event> parseEventResponse(String jsonResponse) {
        List<Event> events = new ArrayList<>();
        try {
            // Parse the JSON response using ObjectMapper
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonResponse);

            // Navigate to the relevant data in the JSON response
            JsonNode edges = rootNode.path("data").path("keywordSearch").path("edges");

            // Iterate through each event and extract the relevant information
            for (JsonNode edge : edges) {
                JsonNode eventNode = edge.path("node").path("result");

                String title = eventNode.path("title").asText();
                String description = eventNode.path("description").asText();
                String url = eventNode.path("eventUrl").asText();

                // Create an Event object and add it to the list
                events.add(new Event(title, description, url));
            }
        } catch (Exception e) {
            e.printStackTrace();  // Handle parsing errors
        }

        return events;
    }

}