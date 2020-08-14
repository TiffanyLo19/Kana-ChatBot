// Author:		Tiffany Lo
// Course:		CS2336.OU1
// Date:		07/05/2020
// Assignment:	Project 1
// Compiler:	Eclipse 2020

// Description:      
// This class will connect the bot to the IRC server.

// Include imports 
public class ConnectBot
{
   public static void main(String[] args) throws Exception
   {
	   // Create bot
       ChatBot ChatBot = new ChatBot();
       ChatBot.setVerbose(true);
       ChatBot.connect("irc.freenode.net"); 
       
       // Name of channel
       ChatBot.joinChannel("#TiffanysBot"); 

       // Displays default message from bot
       ChatBot.sendMessage("#TiffanysBot", "Hey there, for information on today's weather, try typing \"weather_cityname\" "
               + "or for help on word definitions, type \"define_word\".");
       ChatBot.sendMessage("#TiffanysBot", "For more help, type \"commands\".");
        
   }
}
