package States;

import ludogame.Controller;
import ludogame.LudoGame;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import Managers.SoundEffectsManager;
import Mementos.CareTaker;
import Mementos.Originator;
import Singletons.Singleton;

public class Game extends BasicGameState
{
	
	private boolean			first_time_to_undo_in_session	= true;
	private final int		ID								= Singleton.GAME_ID;
	private StateBasedGame	state_based_game;
	private static boolean	first_time=true;
	
	public Game()
	{
		super();
	}
	
	@Override
	public void render(GameContainer game_container, StateBasedGame arg1, Graphics graphics) throws SlickException
	{
		if(first_time)
		{
			SoundEffectsManager.playBackGroundMusic();	
			first_time=false;
		}
		LudoGame.DrawEveryThing(graphics);
	}
	
	@Override
	public void init(GameContainer game_container, StateBasedGame game) throws SlickException
	{
		this.state_based_game = game;
		LudoGame.InitializeGame();
		Originator.SaveStateToMemento();
		
	}
	
	@Override
	public void update(GameContainer game_container, StateBasedGame arg1, int arg2) throws SlickException
	{
		LudoGame.UpdateGame();
	}
	
	@Override
	public void keyReleased(int key, char c)
	{
		boolean do_not_save = false;
		
		switch (key)
		{
		
			case Input.KEY_1:
				// USER WANTS TO MOVE PLAYER 1
				if (Controller.GetPerson_to_play() == 1)
				{
					Singleton.GetControllerInstance().MovePlayerForward(LudoGame.GetBluePlayer("1"));
					first_time_to_undo_in_session = true;
				}
				break;
			
			// USER WANTS TO MOVE BUTON 2
			case Input.KEY_2:
				if (Controller.GetPerson_to_play() == 1)
				{
					Singleton.GetControllerInstance().MovePlayerForward(LudoGame.GetBluePlayer("2"));
					first_time_to_undo_in_session = true;
				}
				break;
			
			// USER WANTS TO MOVE BUTTON 3
			case Input.KEY_3:
				if (Controller.GetPerson_to_play() == 1)
				{
					Singleton.GetControllerInstance().MovePlayerForward(LudoGame.GetBluePlayer("3"));
					first_time_to_undo_in_session = true;
				}
				break;
			
			// USER WANTS TO MOVE BUTTON 4
			case Input.KEY_4:
				if (Controller.GetPerson_to_play() == 1)
				{
					Singleton.GetControllerInstance().MovePlayerForward(LudoGame.GetBluePlayer("4"));
					first_time_to_undo_in_session = true;
				}
				break;
			
			// USER WANTS TO ROLL DIE
			case Input.KEY_R:
				if (Controller.GetPerson_to_play() == 1)
				{
					Singleton.GetControllerInstance().RollDie();
				}
				do_not_save = true;
				break;
			
			// USER WANTS TO CHANGE TURNS
			case Input.KEY_C:
				if (Controller.GetPerson_to_play() == 1)
				{
					Controller.ChangePersonToPlay();
					first_time_to_undo_in_session = true;
				}
				break;
			
			// USER WANTS TO UNO LAST ACTION
			case Input.KEY_U:
				Singleton.PrintOut("Undo pressed");
				if (Controller.GetPerson_to_play() == 1)
				{
					Singleton.PrintOut("Restoring from memento");
					if (first_time_to_undo_in_session)
					{
						CareTaker.GetMostRecentMemento();
						first_time_to_undo_in_session = false;
					}
					Originator.RestoreStateFromMemento(CareTaker.GetMostRecentMemento());
				}
				do_not_save = true;
				break;
			
			// USER WANTS TO ACESS IN GAME MENU
			case Input.KEY_ESCAPE:
				if (Controller.GetPerson_to_play() == 1)
				{
					SoundEffectsManager.StopPlayingMusic();
					first_time=true;
					state_based_game.enterState(InGameMenu.ID, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
				}
				do_not_save = true;
				break;
			
			// DO NOTHING
			default:
				break;
		}
		
		// IF ITS THE COMPUTERS TURN TO PLAY
		if (Controller.GetPerson_to_play() >= 2)
		{
			Singleton.GetAI().decideNextAction();
		}
		
		// SAVE STATE TO MEMENTO
		if (!do_not_save)
		{
			Originator.SaveStateToMemento();
		}
		
	}
	
	@Override
	public int getID()
	{
		return ID;
	}
	
	
	
}
