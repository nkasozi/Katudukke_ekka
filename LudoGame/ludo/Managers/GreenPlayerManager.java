package Managers;

//~--- non-JDK imports --------------------------------------------------------

import Factories.PlayerFactory;
import Players.GreenPlayer;
import Players.Player;

import org.newdawn.slick.Graphics;

//~--- JDK imports ------------------------------------------------------------

import java.util.Enumeration;
import java.util.Hashtable;

public class GreenPlayerManager extends PlayerManager
{
	
	private static final String					PLAYER_COLOR				= "Green";
	private static Hashtable<String, Player>	players_hashtable			= new Hashtable<String, Player>(4);
	private static final int					maximum_number_of_players	= 4;
	private static int							player_id					= 1;
	
	public static void CreateNewPlayer()
	{
		if (player_id <= (maximum_number_of_players))
		{
			GreenPlayer green_player = (GreenPlayer) PlayerFactory.createNewPlayer(PLAYER_COLOR, player_id);
			
			players_hashtable.put("" + player_id, green_player);
		}
	}
	
	public static void CreateAllPlayers()
	{
		players_hashtable = new Hashtable<String, Player>(4);
		player_id = 1;
		
		for (int i = 1; i <= (maximum_number_of_players); i++)
		{
			CreateNewPlayer();
			GreenPlayerManager.player_id++;
		}
	}
	
	public static GreenPlayer GetPlayer(String id)
	{
		return (GreenPlayer) players_hashtable.get(id);
	}
	
	public static GreenPlayer[] GetAllPlayers() throws CloneNotSupportedException
	{
		GreenPlayer[] players_array = new GreenPlayer[players_hashtable.size()];
		
		for (int i = 1; i <= players_hashtable.size(); i++)
		{
			GreenPlayer player = (GreenPlayer) players_hashtable.get("" + i);
			
			players_array[i - 1] = (GreenPlayer) GetClone(player);
		}
		
		return players_array;
	}
	
	public static void SetAllPlayers(Player[] players)
	{
		GreenPlayerManager.players_hashtable = new Hashtable<String, Player>(4);
		System.out.println("LENGTH=" + players.length);
		
		for (int i = 0; i < players.length; i++)
		{
			if (players[i] != null)
			{
				GreenPlayerManager.players_hashtable.put("" + (i + 1), players[i]);
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
