package Factories;

import Players.BluePlayer;
import Players.GreenPlayer;
import Players.Player;
import Players.RedPlayer;
import Players.YellowPlayer;

public class PlayerFactory
{
	
	public static Player createNewPlayer(String color, int button_id)
	{
		
		if (color.equalsIgnoreCase("Yellow"))
		{
			return new YellowPlayer(button_id);
		}
		else if (color.equalsIgnoreCase("Red"))
		{
			return new RedPlayer(button_id);
		}
		else if (color.equalsIgnoreCase("Blue"))
		{
			return new BluePlayer(button_id);
		}
		else if (color.equalsIgnoreCase("Green"))
		{
			return new GreenPlayer(button_id);
		}
		
		return null;
	}
}
