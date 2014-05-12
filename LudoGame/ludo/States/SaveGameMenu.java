package States;

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
import Storage.DataStoreHandler;

public class SaveGameMenu extends BasicGameState
{
	
	private static final String	PATH_TO_BG_IMAGE	= Singleton.getResourcesPath() + "images\\background.jpg";
	public static final int		ID					= Singleton.SAVE_GAME_MENU_ID;
	private StateBasedGame		game;
	private boolean				draw_progress_bar	= false;
	private final int			bar_height			= 30;
	private final int			bar_width			= Singleton.GetScreenWidth() - 100;
	private int					fill_width			= 0;
	private int					counter				= 0;
	
	public SaveGameMenu()
	{
		
	}
	
	@Override
	public void init(GameContainer game_container, StateBasedGame game) throws SlickException
	{
		this.game = game;
	}
	
	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics graphics) throws SlickException
	{
		int screen_middle = (Singleton.GetScreenWidth() / 2) - 90;
		graphics.drawImage(Singleton.ResizeToScreen(new Image(PATH_TO_BG_IMAGE)), 0, 0);
		
		graphics.setColor(Color.green);
		graphics.drawString("Save Game Using", screen_middle, 140);
		
		graphics.drawString("1. XML", screen_middle, 190);
		graphics.drawString("2. JSON", screen_middle, 210);
		graphics.drawString("3. Cancel", screen_middle, 230);
		
		if (draw_progress_bar)
		{
			graphics.drawString("Saving game.Please wait. ...", screen_middle, 280);
			graphics.drawRect(50, 300, bar_width, bar_height);
			graphics.fillRect(50, 300, fill_width, bar_height);
		}
	}
	
	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException
	{
		if (draw_progress_bar)
		{
			if (counter >= 100)
			{
				fill_width += 30;
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
				// USER WANTS TO SAVE USING XML
				draw_progress_bar = true;
				DataStoreHandler.SaveStateToXML();
				
				break;
			
			// USER WANTS TO SAVE USING JSON
			case Input.KEY_2:
				draw_progress_bar = true;
				DataStoreHandler.SaveStateToJSON();
				break;
			
			// USER WANTS TO RESUME GAME
			case Input.KEY_3:
				game.enterState(Singleton.getInGameMenu().getID(), new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
				break;
			
			// DO NOT HANDLE OTHER BUTTON PRESSES
			default:
				break;
		}
	}
	
}