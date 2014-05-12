package Singletons;

import ludogame.ArtficialIntelligence;
import ludogame.Controller;
import ludogame.Die;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Interfaces.ArtificialIntelligenceInterface;
import Interfaces.DieInterface;
import Managers.SoundEffectsManager;
import States.Game;
import States.GameDeveloperSplashScreen;
import States.InGameMenu;
import States.LoadGameMenu;
import States.MainMenu;
import States.SaveGameMenu;
import States.SplashScreen;

public class Singleton
{
	
	// THIS IS PATH TO DIRECTORY WHERE THE APP STARTS FROM
	private static final String								STARTUP_PATH					= System.getProperty("user.dir");
	
	// THIS IS THE PATH TO THE RESOURCES FOLDER
	private static final String								RESOURCES_PATH					= STARTUP_PATH + "\\res\\";
	
	// THIS IS THE PATH TO THE LUDO IMAGE IN THE RESOURCES FOLDER
	private static final String								PATH_TO_LUDO_IMAGE				= RESOURCES_PATH + "\\images\\Ludo_Board.jpg";
	
	// THIS IS THE TITLE OF THE GAME
	private static final String								GAME_TITLE						= "Katuduke Ekka";
	
	// THIS IS THE CONTROLLER OBJECT THAT RESPONDS TO USER INPUT
	private static final Controller							CONTROLLER						= new Controller();
	
	// THIS IS THE A.I THAT PLAYS THE USER
	private static final ArtificialIntelligenceInterface	KASOZI							= new ArtficialIntelligence();
	
	// THIS IS THE SPLASH SCREEN LOADED FIRST WHEN GAME IS STARTING
	private static SplashScreen								SPLASH_SCREEN					= new SplashScreen();
	
	// THIS IS THE SPLASH SCREEN LOADED FIRST WHEN GAME IS STARTING
	private static final GameDeveloperSplashScreen			GAME_DEVELOPER_SPLASH_SCREEN	= new GameDeveloperSplashScreen();
	
	// THIS IS THE ACTUAL RUNNING GAME
	private static Game										GAME							= new Game();
	
	// THIS IS THE MENU THAT LOADS BEFORE THE GAME STARTS
	private static MainMenu									MAIN_MENU						= new MainMenu();
	
	// THIS IS THE IN GAME MENU CALLED WHEN USER PRESSES ESCAPE
	private static InGameMenu								IN_GAME_MENU					= new InGameMenu();
	
	// THIS IS THE MENU CALLED WHEN USER WANTS TO SAVE GAME
	private static SaveGameMenu								SAVE_GAME_MENU					= new SaveGameMenu();
	
	// THIS IS THE MENU CALLED WHEN USER WANTS TO LOAD GAME
	private static LoadGameMenu								LOAD_GAME_MENU					= new LoadGameMenu();
	
	// THIS IS THE DIE USED IN THE GAME
	private static final DieInterface						DIE								= new Die();
	
	// THESE ARE THE SOUND EFFECTS USED IN THE GAME
	private static final SoundEffectsManager				AUDIO_PLAYER					= new SoundEffectsManager();
	
	// THIS IS THE WIDTH OF THE GAME WINDOW
	private static final int								SCREEN_WIDTH					= 650;
	
	// THIS IS THE HEIGHT OF THE GAME WINDOW
	private static final int								SCREEN_HEIGHT					= 650;
	
	// THIS IS THE LUDO BOARD
	private static Image									ludo_board						= null;
	
	// THIS IS THE DIE SCORE WHENEVER THE USER ROLLS THE DIE
	private static String									die_score						= null;
	
	// THESE ARE UNIQUE IDS FOR THE DIFFERENT GAME STATES
	public static final int									SPLASH_SCREEN_ID				= 0;
	public static final int									GAME_DEVELOPER_SPLASH_SCREEN_ID	= 1;
	public static final int									MAIN_MENU_ID					= 2;
	public static final int									GAME_ID							= 3;
	public static final int									IN_GAME_MENU_ID					= 4;
	public static final int									SAVE_GAME_MENU_ID				= 5;
	public static final int									LOAD_GAME_MENU_ID				= 6;
	
	public static Controller GetControllerInstance()
	{
		return CONTROLLER;
	}
	
	public static ArtificialIntelligenceInterface GetAI()
	{
		return KASOZI;
	}
	
	public static Image GetLudoBoardInstance()
	{
		return ludo_board;
	}
	
	public static void CreateLudoBoardInstance()
	{
		try
		{
			Image ludo_board = ResizeToScreenAndRotate(new Image(PATH_TO_LUDO_IMAGE));
			
			Singleton.ludo_board = ludo_board;
		}
		catch (SlickException ex)
		{
			PrintOut(ex.getMessage());
		}
	}
	
	public static String GetDieScore()
	{
		return die_score;
	}
	
	public static void SetDieScore(String score)
	{
		Singleton.die_score = score;
	}
	
	public static SoundEffectsManager GetAudioPlayer()
	{
		return AUDIO_PLAYER;
	}
	
	public static DieInterface GetDiceInstance()
	{
		return DIE;
	}
	
	public static void PrintOut(String message)
	{
		System.out.println(message);
	}
	
	public static int GetScreenWidth()
	{
		return SCREEN_WIDTH;
	}
	
	public static int GetScreenHeight()
	{
		return SCREEN_HEIGHT;
	}
	
	public static String GetGameTitle()
	{
		return GAME_TITLE;
	}
	
	public static Image ResizeToScreenAndRotate(Image originalImage)
	{
		originalImage = originalImage.getScaledCopy(Singleton.GetScreenWidth(), Singleton.GetScreenHeight());
		originalImage.rotate(180);
		return originalImage;
	}
	
	public static Image ResizeToScreen(Image originalImage)
	{
		originalImage = originalImage.getScaledCopy(Singleton.GetScreenWidth(), Singleton.GetScreenHeight());
		
		return originalImage;
	}
	
	public static Game getGame()
	{
		return GAME;
	}
	
	public static void setGame(Game game)
	{
		Singleton.GAME = game;
	}
	
	public static MainMenu getMainMenu()
	{
		return MAIN_MENU;
	}
	
	public static InGameMenu getInGameMenu()
	{
		return IN_GAME_MENU;
	}
	
	public static SaveGameMenu getSaveGameMenu()
	{
		return SAVE_GAME_MENU;
	}
	
	public static LoadGameMenu getLoadGameMenu()
	{
		return LOAD_GAME_MENU;
	}
	
	public static SplashScreen getSplashScreen()
	{
		return SPLASH_SCREEN;
	}
	
	public static String getResourcesPath()
	{
		return RESOURCES_PATH;
	}
	
	public static GameDeveloperSplashScreen getGameDeveloperSplashScreen()
	{
		return GAME_DEVELOPER_SPLASH_SCREEN;
	}
	
}
