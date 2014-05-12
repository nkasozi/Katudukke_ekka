package States;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import Managers.SoundEffectsManager;
import Singletons.Singleton;

public class SplashScreen extends BasicGameState
{
	
	private static final String	PATH_TO_SPLASH_SCREEN	= Singleton.getResourcesPath() + "images\\ludo_splash.png";
	private static int			ID						= Singleton.SPLASH_SCREEN_ID;
	private StateBasedGame		game;
	
	public SplashScreen()
	{
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void init(GameContainer game_container, StateBasedGame game) throws SlickException
	{
		this.game = game;
		Singleton.GetAudioPlayer();
		SoundEffectsManager.playIntroSound();
	}
	
	@Override
	public void render(GameContainer game_container, StateBasedGame game, Graphics graphics) throws SlickException
	{
		graphics.drawImage(Singleton.ResizeToScreen(new Image(PATH_TO_SPLASH_SCREEN)), 0, 0);
	}
	
	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException
	{
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public int getID()
	{
		return ID;
	}
	
	@Override
	public void keyReleased(int key, char c)
	{
		game.enterState(Singleton.getGameDeveloperSplashScreen().getID(), new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
	}
	
}
