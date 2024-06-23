package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class WeatherChatbot {

    public static void main(String[] args) {
        WeatherService weatherService = new WeatherService();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        System.out.println("Welcome to the Weather Chatbot!");
        
        while (true) {
            try {
                System.out.print("Enter a city name to get the weather (or 'exit' to quit): ");
                String city = reader.readLine();
                
                if (city.equalsIgnoreCase("exit")) {
                    break;
                }
                
                String weather = weatherService.getWeather(city);
                System.out.println(weather);
            } catch (IOException e) {
                System.err.println("Error fetching weather data: " + e.getMessage());
            }
        }
        
        System.out.println("Goodbye!");
    }
}
