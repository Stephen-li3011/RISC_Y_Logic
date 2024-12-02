package interface_adapter.mainmenu;

import data_access.MeetupGraphQLClient;
import view.recommendation.EventRecommendationView;

public class MainMenuEventsController implements ButtonController {

    @Override
    public void click() {
        // Instantiate the MeetupGraphQLClient
        MeetupGraphQLClient meetupClient = new MeetupGraphQLClient("your_access_token_here");

        // Create the EventRecommendationView and pass the meetupClient to its constructor
        new EventRecommendationView(meetupClient);
    }
}