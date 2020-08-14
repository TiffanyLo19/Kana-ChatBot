// Author:		Tiffany Lo
// Course:		CS2336.OU1
// Date:		07/05/2020
// Assignment:	Project 1
// Compiler:	Eclipse 2020

// Description:      
// This class will create the weather API by
// making a GET request to the endpoint and parsing
// the desired information and returning it to the ChatBot.

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class WeatherAPI
{
	// List necessary variables
    private String userCity; // Holds user city
    private String weather; // Holds the weather in the city
    private double currentTempF; // Holds current temperature in F
    private double highTempF; // Holds the temperature high in F
    private double lowTempF; // Holds the temperature low in F

    public void myWeather(String input)
    {
        try 
        {
            String line;
            StringBuilder content = new StringBuilder();
            
            // Make a GET request to weather API endpoint
            URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=" + input + "&APPID=748eb2d8d2052e795518ffebd7fbe6bc");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection(); 
            conn.setRequestMethod("GET");
            
            // Return the read data from API
            BufferedReader reader =  new BufferedReader(new InputStreamReader(conn.getInputStream()));
            while ((line = reader.readLine()) != null) 
            {
                content.append(line);
            }
            
            // Return the data after parsing
            String json;
            json = content.toString();
            parseJSON(json);
        } 
        catch (Exception e) 
        {
        	// Displays error if data cannot be returned
            System.out.println("There was an error receiving the data.");
            e.printStackTrace();
        }
    }
    
    public void parseJSON(String json)
    {
    	// Parse JSON object
        JsonObject obj = new JsonParser().parse(json).getAsJsonObject();
        
        // Gets the data from "main"
        JsonObject obj_main = obj.getAsJsonObject("main");
        // Gets temperatures from "main" and converts temperature from Kelvin to Fahrenheit 
        double cTempF = (1.8*(obj_main.get("temp").getAsDouble()-273)+32);
        double hTempF = (1.8*(obj_main.get("temp_max").getAsDouble()-273)+32);
        double lTempF = (1.8*(obj_main.get("temp_min").getAsDouble()-273)+32);

        // Get the verbal weather description
        String weatherDesc = obj.getAsJsonArray("weather").get(0).getAsJsonObject().get("description").getAsString();

        // Get the name of the city
        String userCity = obj.get("name").getAsString();

        // Return the parsed data
        linkParse(cTempF, hTempF, lTempF, weatherDesc, userCity);
    }

    public void linkParse(double currentTempF, double highTempF, double lowTempF, String weather, String userCity)
    {
    	// Return the parsed data to original
        this.currentTempF = currentTempF;
        this.highTempF = highTempF;
        this.lowTempF = lowTempF;
        this.weather = weather;
        this.userCity = userCity;
    }
    
    public String weatherInfo()
    {
    	// Display the weather
        return String.format("The weather today in %s is %s. The temperature is %.2f degrees (F), with a high of %.2f "
                + "degrees (F) and a low of %.2f degrees (F).", 
                        userCity, weather, currentTempF, highTempF , lowTempF);
    }
}
