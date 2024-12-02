package data_access;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MeetupGraphQLClient {
    private static final String MEETUP_API_ENDPOINT = "https://api.meetup.com/gql";
    private final String accessToken;
    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    public MeetupGraphQLClient(String accessToken) {
        this.accessToken = accessToken;
        this.httpClient = HttpClient.newHttpClient();
        this.objectMapper = new ObjectMapper();
    }

    public String searchEvents(String query, double latitude, double longitude, int radius) throws IOException, InterruptedException {
        // Prepare the GraphQL query and variables
        Map<String, Object> variables = new HashMap<>();
        Map<String, Object> filter = new HashMap<>();
        filter.put("query", query);
        filter.put("lat", latitude);
        filter.put("lon", longitude);
        filter.put("radius", radius);
        filter.put("source", "EVENTS");
        variables.put("filter", filter);

        // Prepare the complete request
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("query",
                "query($filter: SearchConnectionFilter!) { " +
                        "  keywordSearch(filter: $filter) { " +
                        "    count " +
                        "    edges { " +
                        "      cursor " +
                        "      node { " +
                        "        id " +
                        "        result { " +
                        "          ... on Event { " +
                        "            title " +
                        "            eventUrl " +
                        "            description " +
                        "            dateTime " +
                        "            going " +
                        "          } " +
                        "        } " +
                        "      } " +
                        "    } " +
                        "  } " +
                        "}"
        );
        requestBody.put("variables", variables);

        // Convert request body to JSON
        String jsonBody;
        try {
            jsonBody = objectMapper.writeValueAsString(requestBody);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to convert request to JSON", e);
        }

        // Create HTTP request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(MEETUP_API_ENDPOINT))
                .header("Authorization", "Bearer " + accessToken)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        // Send the request and get the response
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        // Return the response body
        return response.body();
    }

    // Example usage method
    public static void main(String[] args) {
        try {
            MeetupGraphQLClient client =
                    new MeetupGraphQLClient
                            (System.getenv("MEETUP_ACCESS_TOKEN"));

            // Search for events near Toronto
            String result = client.searchEvents("Java Clean Architecture", 43.8, -79.4, 100);
            System.out.println(result);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}