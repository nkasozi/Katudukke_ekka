package States;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import Singletons.Singleton;

public class Main extends StateBasedGame
{
	
	public Main(String name)
	{
		super(name);
	}
	
	@Override
	public void initStatesList(GameContainer game_container) throws SlickException
	{
		addState(Singleton.getSplashScreen());
		addState(Singleton.getGameDeveloperSplashScreen());
		addState(Singleton.getMainMenu());
		addState(Singleton.getGame());
		addState(Singleton.getInGameMenu());
		addState(Singleton.getSaveGameMenu());
		addState(Singleton.getLoadGameMenu());
		
	}
	
	public static void main(String[] args)
	{
		try
		{
			AppGameContainer app = new AppGameContainer(new Main(Singleton.GetGameTitle()));
			app.setDisplayMode(Singleton.GetScreenWidth(), Singleton.GetScreenHeight(), false);
			app.setShowFPS(false);
			app.start();
		}
		catch (Exception e)
		{
			Singleton.PrintOut(e.getMessage());
		}
		
	}
	
}
