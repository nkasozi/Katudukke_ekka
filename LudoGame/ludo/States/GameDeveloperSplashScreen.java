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

import Singletons.Singleton;

public class GameDeveloperSplashScreen extends BasicGameState
{
	
	private static final String	PATH_TO_LOGO	= Singleton.getResourcesPath() + "images\\misfits.jpg";
	private StateBasedGame		game;
	private Image				logo_image;
	
	@Override
	public void init(GameContainer arg0, StateBasedGame game) throws SlickException
	{
		this.game = game;
		logo_image = new Image(PATH_TO_LOGO);
	}
	
	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics graphics) throws SlickException
	{
		graphics.drawImage(logo_image, 0, 0);
	}
	
	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException
	{
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public int getID()
	{
		return Singleton.GAME_DEVELOPER_SPLASH_SCREEN_ID;
	}
	
	@Override
	public void keyReleased(int key, char c)
	{
		game.enterState(Singleton.getMainMenu().getID(), new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
	}
	
}
