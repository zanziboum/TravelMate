package fr.isep.TravelMate.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OpenTripMapService {
    @Value(value = "${api-key}")
    private String apiKey;

    public void getNearbyAttractions(double latitude, double longitude, int radius) {
        String apiUrl = String.format("https://api.opentripmap.com/0.1/en/places/radius?radius=%d&lon=%f&lat=%f&format=json&apikey=%s",
                radius, longitude, latitude, apiKey);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<JsonNode> response = restTemplate.getForEntity(apiUrl, JsonNode.class);
        if (response.getStatusCode().is2xxSuccessful()) {
            JsonNode responseBody = response.getBody();
            // Parse the JSON response and extract the required information
            // ...
        } else {
            System.out.println("Request failed with code: " + response.getStatusCode());
        }
    }
}
