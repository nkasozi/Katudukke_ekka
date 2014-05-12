package Managers;

import Players.Player;

public class PlayerManager
{
	
	// gets a player from home and places him on home pad
	public static void getPlayerFromHome(Player player)
	{
		if (player.getSteps_moved() == 0)
		{
			player.setX_cordinate((int) player.getStarting_x_cordinate());
			player.setY_cordinate((int) player.getStarting_y_cordinate());
			player.setSteps_moved(2);
		}
	}
	
	// returns an eaten player home
	public static void returnPlayerHome(Player player_to_be_eaten)
	{
		player_to_be_eaten.setX_cordinate(player_to_be_eaten.getInitial_x());
		player_to_be_eaten.setY_cordinate(player_to_be_eaten.getInitial_y());
		player_to_be_eaten.setSteps_moved(0);
	}
	
	public static Player GetClone(Player player)
	{
		Player cloned_player = org.apache.commons.lang3.SerializationUtils.clone(player);
		return cloned_player;
	}
}

// ~ Formatted by Jindent --- http://www.jindent.com
