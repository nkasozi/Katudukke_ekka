package ludogame;

import Interfaces.ArtificialIntelligenceInterface;
import Managers.GreenPlayerManager;
import Managers.RedPlayerManager;
import Managers.YellowPlayerManager;
import Players.GreenPlayer;
import Players.RedPlayer;
import Players.YellowPlayer;
import Singletons.Singleton;

//this class should control all the other 3 colors
public class ArtficialIntelligence implements ArtificialIntelligenceInterface
{
	
	private boolean	die_has_been_rolled	= false;
	
	// constructor
	public ArtficialIntelligence()
	{
	}
	
	// decides the next action to be taken by computer
	/*
	 * (non-Javadoc)
	 * 
	 * @see ludogame.ArtificialIntelligenceInterface#decideNextAction()
	 */
	@Override
	public void decideNextAction()
	{
		rollDie();
		
		if (Controller.GetPerson_to_play() == 2)
		{
			decideNextRedAction();
			
			if (Controller.GetPerson_to_play() == 2)
			{
				decideNextAction();
			}
		}
		
		rollDie();
		
		if (Controller.GetPerson_to_play() == 3)
		{
			decideNextGreenAction();
			
			if (Controller.GetPerson_to_play() == 3)
			{
				decideNextAction();
			}
		}
		
		rollDie();
		
		if (Controller.GetPerson_to_play() == 4)
		{
			decideNextYellowAction();
			
			if (Controller.GetPerson_to_play() == 4)
			{
				decideNextAction();
			}
		}
		
		Controller.resetDieScore();
		LudoGame.draw_score = false;
	}
	
	// controls all red buttons
	private void decideNextRedAction()
	{
		if (isDie_has_been_rolled())
		{
			if (Controller().MovePlayerForward(GetRedPlayer("1")))
			{
				return;
			}
			else if (Controller().MovePlayerForward(GetRedPlayer("2")))
			{
				return;
			}
			else if (Controller().MovePlayerForward(GetRedPlayer("3")))
			{
				return;
			}
			else if (Controller().MovePlayerForward(GetRedPlayer("4")))
			{
				return;
			}
			else
			{
				Controller.ChangePersonToPlay();
			}
		}
	}
	
	// rolls the die
	private void rollDie()
	{
		Controller().RollDie();
		setDie_has_been_rolled(true);
	}
	
	// controls all green buttons
	private void decideNextGreenAction()
	{
		if (isDie_has_been_rolled())
		{
			if (Controller().MovePlayerForward(GetGreenPlayer("1")))
			{
				return;
			}
			else if (Controller().MovePlayerForward(GetGreenPlayer("2")))
			{
				return;
			}
			else if (Controller().MovePlayerForward(GetGreenPlayer("3")))
			{
				return;
			}
			else if (Controller().MovePlayerForward(GetGreenPlayer("4")))
			{
				return;
			}
			else
			{
				Controller.ChangePersonToPlay();
			}
		}
	}
	
	// controls all yellow buttons
	private void decideNextYellowAction()
	{
		if (isDie_has_been_rolled())
		{
			if (Controller().MovePlayerForward(GetYellowPlayer("1")))
			{
				return;
			}
			else if (Controller().MovePlayerForward(GetYellowPlayer("2")))
			{
				return;
			}
			else if (Controller().MovePlayerForward(GetYellowPlayer("3")))
			{
				return;
			}
			else if (Controller().MovePlayerForward(GetYellowPlayer("4")))
			{
				return;
			}
			else
			{
				Controller.ChangePersonToPlay();
			}
		}
	}
	
	public RedPlayer GetRedPlayer(String id)
	{
		return RedPlayerManager.GetPlayer(id);
	}
	
	public GreenPlayer GetGreenPlayer(String id)
	{
		return GreenPlayerManager.GetPlayer(id);
	}
	
	public YellowPlayer GetYellowPlayer(String id)
	{
		return YellowPlayerManager.GetPlayer(id);
	}
	
	public boolean isDie_has_been_rolled()
	{
		return die_has_been_rolled;
	}
	
	public void setDie_has_been_rolled(boolean die_has_been_rolled)
	{
		this.die_has_been_rolled = die_has_been_rolled;
	}
	
	private Controller Controller()
	{
		return Singleton.GetControllerInstance();
	}
}
