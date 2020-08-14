// Author:		Tiffany Lo
// Course:		CS2336.OU1
// Date:		07/05/2020
// Assignment:	Project 1
// Compiler:	Eclipse 2020

// Description:      
// This class will create the dictionary API by
// making a GET request to the endpoint and parsing
// the desired information and returning it to the ChatBot.

// Include imports
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.*;


public class DictionaryAPI
{
    private JsonArray wordDefinition;
    
    public void myDictionary(String input)
    {
        try
        {
            String line;
            StringBuilder content = new StringBuilder();
            
            // Makes a GET request to the api from endpoint
            URL url = new URL("https://www.dictionaryapi.com/api/v3/references/collegiate/json/" + input
                    + "?key=fcff4351-aada-4a55-b006-3d866d7bcf1e"); // API key
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setRequestMethod("GET");
            
            // Returns read data from API
            BufferedReader reader = new BufferedReader(new InputStreamReader(http.getInputStream()));
            while ((line = reader.readLine()) != null)
            {
                content.append(line);
            }
            
            // Return parsed data
            String json1;
            json1 = content.toString();
            parseDictionary(json1);
        } 
        catch (Exception e)
        {
        	// Display error message if data cannot be returned
            System.out.println("There was an error receiving the data.");
            e.printStackTrace();

        }
    }
    
    // Function will parse JSON and return the data
    public void parseDictionary(String json1)
    {
    	// Parse JSON array
        JsonArray array = new JsonParser().parse(json1).getAsJsonArray();
        JsonObject object = array.get(0).getAsJsonObject();
        JsonArray shortdef = object.getAsJsonArray("shortdef"); // Get the definition from array object
   
        forParse(shortdef); // Return data
    }
    
    public void forParse(JsonArray shortdef)
    {
    	// Returns definition as parsed to original
        this.wordDefinition = shortdef; 
    }
    
    
    
    public String dictPrint()
    {
    	// Displays the definition
        return String.format("Definition: %s.", wordDefinition);
    }
}
