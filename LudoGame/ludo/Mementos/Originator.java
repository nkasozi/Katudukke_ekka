package Mementos;

import Managers.BluePlayerManager;
import Managers.GreenPlayerManager;
import Managers.RedPlayerManager;
import Managers.YellowPlayerManager;
import Players.BluePlayer;
import Players.GreenPlayer;
import Players.RedPlayer;
import Players.YellowPlayer;
import Singletons.Singleton;

public class Originator
{
	
	public static Memento	state;
	
	public static void SetState(Memento state)
	{
		Originator.state = state;
	}
	
	public static Memento GetState()
	{
		return state;
	}
	
	public static void SaveStateToMemento()
	{
		try
		{
			BluePlayer[] blue_players = BluePlayerManager.GetAllPlayers();
			RedPlayer[] red_players = RedPlayerManager.GetAllPlayers();
			GreenPlayer[] green_players = GreenPlayerManager.GetAllPlayers();
			YellowPlayer[] yellow_players = YellowPlayerManager.GetAllPlayers();
			Memento memento = new Memento(blue_players, red_players, green_players, yellow_players);
			
			CareTaker.AddMemento(memento);
			// Singleton.PrintOut("State Saved");
		}
		catch (Exception e)
		{
			Singleton.PrintOut(e.getMessage());
		}
		
	}
	
	public static void RestoreStateFromMemento(Memento memento)
	{
		if (memento != null)
		{
			BluePlayer[] blue_players = memento.getBlue_players();
			RedPlayer[] red_players = memento.getRed_players();
			GreenPlayer[] green_players = memento.getGreen_players();
			YellowPlayer[] yellow_players = memento.getYellow_players();
			
			BluePlayerManager.SetAllPlayers(blue_players);
			Singleton.PrintOut("Blue Players restored");
			RedPlayerManager.SetAllPlayers(red_players);
			Singleton.PrintOut("Red Players restored");
			GreenPlayerManager.SetAllPlayers(green_players);
			Singleton.PrintOut("Green Players restored");
			YellowPlayerManager.SetAllPlayers(yellow_players);
			Singleton.PrintOut("Yellow Players restored");
			
		}
	}
	
}
