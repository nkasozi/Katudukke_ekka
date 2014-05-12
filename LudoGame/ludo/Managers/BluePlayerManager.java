package Managers;

import Factories.PlayerFactory;
import Players.BluePlayer;
import Players.Player;
import Singletons.Singleton;

import org.newdawn.slick.Graphics;

import java.util.Enumeration;
import java.util.Hashtable;

public class BluePlayerManager extends PlayerManager
{
	
	private static final String					PLAYER_COLOR				= "Blue";
	private static final int					maximum_number_of_players	= 4;
	private static int							player_id					= 1;
	private static Hashtable<String, Player>	players_hashtable			= new Hashtable<String, Player>(4);
	
	public static void CreateNewPlayer()
	{
		System.out.println("player_id=" + player_id);
		
		if (player_id <= (maximum_number_of_players))
		{
			BluePlayer blue_player = (BluePlayer) PlayerFactory.createNewPlayer(PLAYER_COLOR, player_id);
			
			players_hashtable.put("" + player_id, blue_player);
		}
	}
	
	public static void CreateAllPlayers()
	{
		players_hashtable = new Hashtable<String, Player>(4);
		player_id = 1;
		
		for (int i = 1; i <= (maximum_number_of_players); i++)
		{
			System.out.println("Creating BluePlayer".toUpperCase() + i);
			
			CreateNewPlayer();
			
			BluePlayerManager.player_id++;
		}
	}
	
	public static BluePlayer GetPlayer(String id)
	{
		return (BluePlayer) BluePlayerManager.players_hashtable.get(id);
	}
	
	public static BluePlayer[] GetAllPlayers() throws CloneNotSupportedException
	{
		BluePlayer[] players_array = new BluePlayer[players_hashtable.size()];
		
		for (int i = 1; i <= players_hashtable.size(); i++)
		{
			BluePlayer player = (BluePlayer) players_hashtable.get("" + (i));
			
			players_array[i - 1] = (BluePlayer) GetClone(player);
		}
		
		return players_array;
	}
	
	public static void SetAllPlayers(Player[] players)
	{
		BluePlayerManager.players_hashtable = new Hashtable<String, Player>(4);
		
		for (int i = 0; i < players.length; i++)
		{
			if (players[i] != null)
			{
				Singleton.PrintOut("Player " + i + "restored to " + players[i]);
				
				BluePlayerManager.players_hashtable.put("" + (i + 1), players[i]);
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
		DrawPlayer(GetPlayer("1"), g);
		DrawPlayer(GetPlayer("2"), g);
		DrawPlayer(GetPlayer("3"), g);
		DrawPlayer(GetPlayer("4"), g);
	}
}
