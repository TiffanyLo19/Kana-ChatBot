// Author:		Tiffany Lo
// Course:		CS2336.OU1
// Date:		07/05/2020
// Assignment:	Project 1
// Compiler:	Eclipse 2020

// Description:      
// This class will connect the weather and dictionary API to
// freenode and display commands, weather, and the definitions.

// Include imports
import org.jibble.pircbot.*;

public class ChatBot extends PircBot
{
	// Calling functions for weather and dictionary
    WeatherAPI getWeather = new WeatherAPI();
    DictionaryAPI getDef = new DictionaryAPI();

public ChatBot()
{
	// Constructor
    this.setName("Kana");
}

public void onMessage(String channel, String sender, String login, String hostname, String message)
 {
    message.toLowerCase(); // Let uppercase also be lowercase
    String userCity;
    String userWord;

    // For weather API
    if((message.contains("weather")) && !message.contains(" "))
    {
    	// Split user input using "_"
        String userInput []=(message.split("_")); 
        if(userInput[0].equals("weather"))
        {
        	// Read in user location input
            userCity = userInput[1];
            try {
            	// Call weather function and display output
                getWeather.myWeather(userCity);
                String output = getWeather.weatherInfo();
                sendMessage(channel, output);
                } 
            catch (Exception e) 
            {
            	// If error in receiving data
                System.out.println("There was an error receiving the data.");
                e.printStackTrace();
            }
        }
    } 
    else if((message.contains("weather")) && message.contains(" "))
    {
        // Display error message if user uses " " instead of "_"
        sendMessage(channel, sender + ": I'm sorry. I can't quite read your message. "
                + "Try using the command \"weather_(city name)\", the underscore is important! :)");
        sendMessage(channel, "Here's an example: weather_houston");
    } 
    
    // For dictionary API
    if (message.contains("define") && !message.contains(" "))
    {
    	// Splits user input using "_"
        String input[] = (message.split("_"));
        if (input[0].equals("define"))
        {
        	// Read in user word
            userWord = input[1];
            try
            {
            	// Call dictionary function and display output
                getDef.myDictionary(userWord);
                String output1 = getDef.dictPrint();
                sendMessage(channel, output1);
            } 
            catch (Exception e)
            {
            	// If error receiving data
                System.out.println("There was an error receiving the data.");
                e.printStackTrace();
            }
        }
    }
    else if((message.contains("define")) && message.contains(" "))
    {
        // Display error message if user uses " " instead of "_"
        sendMessage(channel, sender + ": I'm sorry. I can't quite understand your message. "
                + "Try using the command \"define_(word)\", the underscore is important! :)");
        sendMessage(channel, "Here's an example: define_happy");
    } 
    
    // Display greetings and responses
    if (message.equalsIgnoreCase("Hi") || message.equalsIgnoreCase("Hey") || message.equalsIgnoreCase("Hello"))
    {
        sendMessage(channel, sender + ": Hey! How are you doing today?");
        sendMessage(channel, "For a list of command, please enter \"commands\".");
    }
    if (((message.contains("good")) || (message.contains("great")) || (message.contains("fine"))) && 
            ((message.contains("how")) || (message.contains("what"))))
    {
        sendMessage(channel, sender + ": I'm doing great, thank you.");
    }
    
    // Disconnect the bot
    if(message.equalsIgnoreCase("Exit") || message.equalsIgnoreCase("quit"))
    {
        this.disconnect();
    }
    
    // Display current time
    if (message.equalsIgnoreCase("time")) 
    {
        String time = new java.util.Date().toString();
        sendMessage(channel, sender + ": The time is " + time);
    }
    
    // Display user commands
    if (message.equalsIgnoreCase("Commands"))
    {
        sendMessage(channel, "1. To make a friendly conversation with the bot, type \"Hi\", \"Hello\", or \"Hey\".");
        sendMessage(channel, "2. For finding weather in your area, try typing the following: \"weather_(city name)\".");
        sendMessage(channel, "3. For finding the definition of a word, try typing the following: \"define_(word)\".");
        sendMessage(channel, "5. Type: \"time\" to get the time of the day.");
        sendMessage(channel, "4. Type: \"exit\" or \"quit\" to disconnect the bot from this channel.");
        sendMessage(channel, "Make sure not to forget the \"_\" inbetween!");
    }
    
}
}