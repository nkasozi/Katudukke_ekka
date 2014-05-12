package Managers;

//~--- non-JDK imports --------------------------------------------------------

import Factories.PlayerFactory;
import Players.Player;
import Players.RedPlayer;

import org.newdawn.slick.Graphics;

//~--- JDK imports ------------------------------------------------------------

import java.util.Enumeration;
import java.util.Hashtable;

public class RedPlayerManager extends PlayerManager
{
	
	private static final String					PLAYER_COLOR				= "Red";
	private static Hashtable<String, Player>	players_hashtable			= new Hashtable<String, Player>(4);
	private static final int					maximum_number_of_players	= 4;
	private static int							player_id					= 1;
	
	public static void CreateNewPlayer()
	{
		if (player_id <= (maximum_number_of_players))
		{
			RedPlayer red_player = (RedPlayer) PlayerFactory.createNewPlayer(PLAYER_COLOR, player_id);
			
			players_hashtable.put("" + player_id, red_player);
		}
	}
	
	public static void CreateAllPlayers()
	{
		players_hashtable = new Hashtable<String, Player>(4);
		player_id = 1;
		
		for (int i = 1; i <= (maximum_number_of_players); i++)
		{
			CreateNewPlayer();
			RedPlayerManager.player_id++;
		}
	}
	
	public static RedPlayer GetPlayer(String id)
	{
		return (RedPlayer) players_hashtable.get(id);
	}
	
	public static RedPlayer[] GetAllPlayers() throws CloneNotSupportedException
	{
		RedPlayer[] players_array = new RedPlayer[players_hashtable.size()];
		
		for (int i = 1; i <= players_hashtable.size(); i++)
		{
			RedPlayer player = (RedPlayer) players_hashtable.get("" + i);
			
			players_array[i - 1] = (RedPlayer) GetClone(player);
		}
		
		return players_array;
	}
	
	public static void SetAllPlayers(Player[] players)
	{
		RedPlayerManager.players_hashtable = new Hashtable<String, Player>(4);
		
		for (int i = 0; i < players.length; i++)
		{
			if (players[i] != null)
			{
				RedPlayerManager.players_hashtable.put("" + (i + 1), players[i]);
			}
		}
	}
	
	public static void DrawPlayer(Player player, Graphics g)
	{
		player.drawPlayer(g);
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
