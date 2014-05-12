package Managers;

//~--- non-JDK imports --------------------------------------------------------

import Factories.PlayerFactory;
import Players.Player;
import Players.YellowPlayer;

import org.newdawn.slick.Graphics;

//~--- JDK imports ------------------------------------------------------------

import java.util.Enumeration;
import java.util.Hashtable;

public class YellowPlayerManager extends PlayerManager
{
	
	private static final String					PLAYER_COLOR				= "Yellow";
	private static Hashtable<String, Player>	players_hashtable			= new Hashtable<String, Player>(4);
	private static final int					maximum_number_of_players	= 4;
	private static int							player_id					= 1;
	
	public static void CreateNewPlayer()
	{
		
		// int id = Integer.parseInt(player_id);
		if (player_id <= (maximum_number_of_players))
		{
			YellowPlayer yellow_player = (YellowPlayer) PlayerFactory.createNewPlayer(PLAYER_COLOR, player_id);
			
			players_hashtable.put("" + player_id, yellow_player);
		}
	}
	
	public static void CreateAllPlayers()
	{
		players_hashtable = new Hashtable<String, Player>(4);
		player_id = 1;
		
		for (int i = 1; i <= (maximum_number_of_players); i++)
		{
			CreateNewPlayer();
			YellowPlayerManager.player_id++;
		}
	}
	
	public static YellowPlayer GetPlayer(String id)
	{
		return (YellowPlayer) players_hashtable.get(id);
	}
	
	public static YellowPlayer[] GetAllPlayers() throws CloneNotSupportedException
	{
		YellowPlayer[] players_array = new YellowPlayer[players_hashtable.size()];
		
		for (int i = 1; i <= players_hashtable.size(); i++)
		{
			YellowPlayer player = (YellowPlayer) players_hashtable.get("" + i);
			
			players_array[i - 1] = (YellowPlayer) GetClone(player);
		}
		
		return players_array;
	}
	
	public static void DrawPlayer(Player player, Graphics g)
	{
		player.drawPlayer(g);
	}
	
	public static void SetAllPlayers(Player[] players)
	{
		YellowPlayerManager.players_hashtable = new Hashtable<String, Player>(4);
		
		for (int i = 0; i < players.length; i++)
		{
			if (players[i] != null)
			{
				YellowPlayerManager.players_hashtable.put("" + (i + 1), players[i]);
			}
		}
	}
	
	public static boolean ColorHasFinished()
	{
		for (Enumeration<Player> enumerator = players_hashtable.elements(); enumerator.hasMoreElements();)
		{
			Player player = (Player) enumerator.nextElement();
			
			if (!player.isFinished())
			{
				return false;
			}
		}
		
		return true;
	}
	
	public static void DrawAllPlayers(Graphics g)
	{
		for (Enumeration<Player> enumerator = players_hashtable.elements(); enumerator.hasMoreElements();)
		{
			Player player = (Player) enumerator.nextElement();
			
			DrawPlayer(player, g);
		}
	}
}

// ~ Formatted by Jindent --- http://www.jindent.com
