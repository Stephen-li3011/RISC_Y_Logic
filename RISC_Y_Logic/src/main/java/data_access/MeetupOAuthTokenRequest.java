package data_access;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class MeetupOAuthTokenRequest {
    public static String getAccessToken(
            String clientId,
            String clientSecret,
            String grantType,
            String redirectUri,
            String authorizationCode
    ) throws Exception {
        // Meetup OAuth token endpoint
        String tokenUrl = "https://secure.meetup.com/oauth2/access";

        // Prepare the request body
        String postData = String.format(
                "client_id=%s&client_secret=%s&grant_type=%s&redirect_uri=%s&code=%s",
                urlEncode(clientId),
                urlEncode(clientSecret),
                urlEncode(grantType),
                urlEncode(redirectUri),
                urlEncode(authorizationCode)
        );

        // Create connection
        URL url = new URL(tokenUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        // Set request method and headers
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("Accept", "application/json");
        conn.setDoOutput(true);

        // Write request body
        try (OutputStream os = conn.getOutputStream()) {
            byte[] postDataBytes = postData.getBytes(StandardCharsets.UTF_8);
            os.write(postDataBytes);
        }

        // Read the response
        int responseCode = conn.getResponseCode();

        // Check for successful response
        if (responseCode == HttpURLConnection.HTTP_OK) {
            // Read input stream for successful response
            try (BufferedReader in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8)
            )) {
                StringBuilder response = new StringBuilder();
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                return response.toString();
            }
        } else {
            // Read error stream if request failed
            try (BufferedReader in = new BufferedReader(
                    new InputStreamReader(conn.getErrorStream(), StandardCharsets.UTF_8)
            )) {
                StringBuilder error = new StringBuilder();
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    error.append(inputLine);
                }
                throw new Exception("Token request failed. Response Code: " + responseCode +
                        ", Error: " + error.toString());
            }
        }
    }

    // URL encoding utility method
    private static String urlEncode(String value) {
        try {
            return java.net.URLEncoder.encode(value, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Example usage
    public static void main(String[] args) {
        try {
            String accessTokenResponse = getAccessToken(
                    "tnm2rp8nphr7kd8ghpml8quhe2",
                    "41b3u7b9s6srlo006jtimdl7pn",
                    "authorization_code",
                    "http://www.riscylogic.com",
                    "f99e548f5d9731f7fef20b5af67d7a39"
            );
            System.out.println("Access Token Response: " + new Gson().fromJson(accessTokenResponse, JsonObject.class).get("access_token"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}