
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class WeatherClient {
    public static void main(String[] args) {
        String city = "Delhi"; // Change this to any city you want
        String apiKey = "46638de1da70c8fef22c4f96f9a6b7e6"; // Replace with your OpenWeatherMap API Key
        String apiUrl = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + apiKey + "&units=metric";

        try {
            // Step 1: Create a URL object
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            // Step 2: Read the API response
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                response.append(line);
            }
            br.close();

            // Step 3: Parse JSON response
            JSONObject jsonResponse = new JSONObject(response.toString());
            String weather = jsonResponse.getJSONArray("weather").getJSONObject(0).getString("description");
            double temp = jsonResponse.getJSONObject("main").getDouble("temp");
            double humidity = jsonResponse.getJSONObject("main").getDouble("humidity");

            // Step 4: Display structured output
            System.out.println("Weather in " + city + ":");
            System.out.println("Description: " + weather);
            System.out.println("Temperature: " + temp + "°C");
            System.out.println("Humidity: " + humidity + "%");

            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
