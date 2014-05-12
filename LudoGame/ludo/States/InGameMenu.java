package States;

import ludogame.LudoGame;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import Singletons.Singleton;

public class InGameMenu extends BasicGameState
{
	
	public static final int		ID					= Singleton.IN_GAME_MENU_ID;
	private static final String	PATH_TO_BG_IMAGE	= Singleton.getResourcesPath() + "images\\background.jpg";
	private StateBasedGame		game;
	private GameContainer		game_container;
	private boolean				draw_progress_bar	= false;
	private final int			bar_height			= 30;
	private final int			bar_width			= Singleton.GetScreenWidth() - 100;
	private int					fill_width			= 0;
	private int					counter				= 0;
	
	public InGameMenu()
	{
		
	}
	
	@Override
	public void init(GameContainer game_container, StateBasedGame game) throws SlickException
	{
		this.game = game;
		this.game_container = game_container;
	}
	
	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics graphics) throws SlickException
	{
		int screen_middle = (Singleton.GetScreenWidth() / 2) - 60;
		graphics.drawImage(Singleton.ResizeToScreen(new Image(PATH_TO_BG_IMAGE)), 0, 0);
		graphics.setColor(Color.green);
		graphics.drawString("Katuduke Ekka", screen_middle, 140);
		
		graphics.drawString("1. New Game", screen_middle, 190);
		graphics.drawString("2. Save Game", screen_middle, 210);
		graphics.drawString("3. Load Game", screen_middle, 230);
		graphics.drawString("4. Cancel", screen_middle, 250);
		graphics.drawString("5. Exit", screen_middle, 270);
		
		if (draw_progress_bar)
		{
			int y = 290;
			graphics.drawString("Creating New game.Please wait. ...", screen_middle, y += 20);
			graphics.drawRect(50, y += 20, bar_width, bar_height);
			graphics.fillRect(50, y, fill_width, bar_height);
		}
	}
	
	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException
	{
		if (draw_progress_bar)
		{
			if (counter >= 100)
			{
				fill_width += 10;
				counter = 0;
				if (fill_width >= bar_width)
				{
					draw_progress_bar = false;
					fill_width = 0;
					game.enterState(Singleton.getGame().getID(), new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
				}
			}
			counter++;
		}
	}
	
	@Override
	public int getID()
	{
		return ID;
	}
	
	@Override
	public void keyReleased(int key, char c)
	{
		switch (key)
		{
		
			case Input.KEY_1:
				// USER WANTS A NEW GAME
				LudoGame.RestartGame();
				draw_progress_bar = true;
				
				break;
			
			// USER WANTS TO SAVE GAME
			case Input.KEY_2:
				game.enterState(Singleton.getSaveGameMenu().getID(), new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
				break;
			
			// USER WANTS TO LOAD GAME
			case Input.KEY_3:
				game.enterState(Singleton.getLoadGameMenu().getID(), new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
				break;
			
			// USER WANTS TO RESUME GAME
			case Input.KEY_4:
				game.enterState(Singleton.getGame().getID(), new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
				break;
			
			// USER WANTS TO RESUME GAME
			case Input.KEY_5:
				game_container.exit();
				break;
			
			// DO NOT HANDLE OTHER BUTTON PRESSES
			default:
				break;
		}
	}
	
}