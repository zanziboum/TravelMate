import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

public class OpenTripMapController {

    public static void main(String[] args) {
        OkHttpClient client = new OkHttpClient();

        // Replace with your API key
        String apiKey = "YOUR_API_KEY";

        // Replace with the latitude and longitude of the location you want to search near
        double latitude = 40.7128;
        double longitude = -74.0060;

        // Set the radius (in meters) within which to search for attractions
        int radius = 5000;

        // Build the API request URL
        String apiUrl = String.format("https://api.opentripmap.com/0.1/en/places/radius?radius=%d&lon=%f&lat=%f&format=json&apikey=%s", radius, longitude, latitude, apiKey);

        Request request = new Request.Builder()
                .url(apiUrl)
                .build();

        try {
            Response response = client.newCall(request).execute();

            if (response.isSuccessful()) {
                String responseBody = response.body().string();

                // Parse the JSON response
                JSONArray attractions = new JSONArray(responseBody);

                for (int i = 0; i < attractions.length(); i++) {
                    JSONObject attraction = attractions.getJSONObject(i);
                    String name = attraction.getString("name");
                    String type = attraction.getString("kinds");

                    System.out.println("Name: " + name);
                    System.out.println("Type: " + type);
                    System.out.println();
                }
            } else {
                System.out.println("Request failed with code: " + response.code());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
