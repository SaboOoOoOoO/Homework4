package com.example;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;

public class WeatherService {
    private static final String API_KEY = "394296f5448356fa1dca6ba8a6c2cedc\r\n"
    		+ ""; 
    private static final String BASE_URL = "http://api.openweathermap.org/data/2.5/weather";

    private final OkHttpClient httpClient = new OkHttpClient();

    public String getWeather(String city) throws IOException {
        String url = BASE_URL + "?q=" + city + "&appid=" + API_KEY + "&units=metric";
        
        Request request = new Request.Builder().url(url).build();
        try (Response response = httpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            
            String responseBody = response.body().string();
            JsonObject json = JsonParser.parseString(responseBody).getAsJsonObject();
            
            String weatherDescription = json.getAsJsonArray("weather").get(0).getAsJsonObject().get("description").getAsString();
            double temperature = json.getAsJsonObject("main").get("temp").getAsDouble();
            
            return "The weather in " + city + " is currently " + weatherDescription + " with a temperature of " + temperature + "°C.";
        }
    }
}
