package ludogame;

//~--- non-JDK imports --------------------------------------------------------

import Managers.*;
import Players.Player;
import Singletons.Singleton;

import org.newdawn.slick.*;

public class LudoGame
{
	
	private static final Color	TEXT_COLOR				= Color.black;
	public static boolean		draw_score				= false;
	private static boolean		draw_road_blocked_sign	= false;
	private static boolean		draw_whose_turn			= false;
	
	// DRAWS EVERYTHING ONTO THE SCREEN
	public static void DrawEveryThing(Graphics graphics)
	{
		// PAINT BACKGROUND BLACK
		SetBackgroundColor(graphics);
		
		DrawLudoBoard(graphics);
		DrawPlayers(graphics);
		DrawDieScore(graphics);
		DrawRoadBlockedSign(graphics);
		DrawWhoseTurnItIs(graphics);
		DrawNumberOfPlayers(graphics);
	}
	
	// PAINTS THE BACKGROUND BLACK
	public static void SetBackgroundColor(Graphics graphics)
	{
		graphics.setColor(Color.black);
		graphics.fillRect(0, 0, Singleton.GetScreenWidth(), Singleton.GetScreenHeight());
	}
	
	// DRAWS THE BACKGROUND IMAGE I.E LUDO BOARD
	public static void DrawLudoBoard(Graphics graphics)
	{
		graphics.drawImage(Singleton.GetLudoBoardInstance(), 0, 0);
	}
	
	// DRAWS ALL PLAYERS ON TO THE SCREEN
	private static void DrawPlayers(Graphics graphics)
	{
		DrawBluePlayers(graphics);
		DrawRedPlayers(graphics);
		DrawGreenPlayers(graphics);
		DrawYellowPlayers(graphics);
	}
	
	// DRAWS BLUE PLAYERS ONTO THE SCREEN
	private static void DrawBluePlayers(Graphics graphics)
	{
		BluePlayerManager.DrawAllPlayers(graphics);
	}
	
	// DRAWS RED PLAYERS ONTO THE SCREEN
	private static void DrawRedPlayers(Graphics graphics)
	{
		RedPlayerManager.DrawAllPlayers(graphics);
	}
	
	// DRAWS GREEN PLAYERS ONTO THE SCREEN
	private static void DrawGreenPlayers(Graphics graphics)
	{
		GreenPlayerManager.DrawAllPlayers(graphics);
	}
	
	// DRAWS YELLOW PLAYERS ONTO THE SCREEN
	private static void DrawYellowPlayers(Graphics graphics)
	{
		YellowPlayerManager.DrawAllPlayers(graphics);
	}
	
	// DRAWS THE DIE SCORE ONTO THE SCREEN
	private static void DrawDieScore(Graphics graphics)
	{
		if (draw_score)
		{
			graphics.setColor(TEXT_COLOR);
			graphics.drawString(Singleton.GetDieScore(), (int) ((Singleton.GetScreenWidth() / 2 * 1.00) - 3), (int) ((Singleton.GetScreenHeight() / 2 * 1.00)));
		}
	}
	
	// DRAWS THE NUMBER OF PLAYERS OF ONE COLOR IN ONE POSITION ONTO THE SCREEN
	public static void DrawNumberOfPlayers(Graphics g)
	{
		DrawNumberOfRedPlayers();
		DrawNumberOfGreenPlayers();
		DrawNumberOfYellowPlayers();
	}
	
	// DRAWS A STRING STATING WHOSE TURN IT IS ONTO THE SCREEN
	public static void DrawWhoseTurnItIs(Graphics g)
	{
		if (draw_whose_turn)
		{
			int x_pos = ((Singleton.GetScreenWidth() / 2));
			int y_pos = ((Singleton.GetScreenHeight() / 2));
			
			String whose_turn = "";
			
			g.setColor(TEXT_COLOR);
			
			if (Controller.GetPerson_to_play() == 1)
			{
				whose_turn = "YOUR TURN";
				g.drawString(whose_turn, x_pos, y_pos);
			}
			else if (Controller.GetPerson_to_play() == 2)
			{
				whose_turn = "RED's TURN";
				g.drawString(whose_turn, x_pos, y_pos);
			}
			
			else if (Controller.GetPerson_to_play() == 3)
			{
				whose_turn = "GREEN's TURN";
				g.drawString(whose_turn, x_pos, y_pos);
			}
			else if (Controller.GetPerson_to_play() == 4)
			{
				whose_turn = "YELLOW's TURN";
				g.drawString(whose_turn, x_pos, y_pos);
			}
		}
		
	}
	
	// DRAWS THE NUMBER OF RED PLAYERS IN ONE POSITION ONTO THE SCREEN
	public static void DrawNumberOfRedPlayers()
	{
		if (GetRedPlayer("1").getCollisions() > 1)
		{
			GetRedPlayer("1").setButton_numbers("" + GetRedPlayer("1").getCollisions() + "R");
		}
		else if (GetRedPlayer("1").getCollisions() <= 1)
		{
			GetRedPlayer("1").setButton_numbers("R");
		}
		
		if (GetRedPlayer("2").getCollisions() > 1)
		{
			GetRedPlayer("2").setButton_numbers("" + GetRedPlayer("2").getCollisions() + "R");
		}
		else if (GetRedPlayer("2").getCollisions() <= 1)
		{
			GetRedPlayer("2").setButton_numbers("R");
		}
		
		if (GetRedPlayer("3").getCollisions() > 1)
		{
			GetRedPlayer("3").setButton_numbers("" + GetRedPlayer("3").getCollisions() + "R");
		}
		else if (GetRedPlayer("3").getCollisions() <= 1)
		{
			GetRedPlayer("3").setButton_numbers("R");
		}
		
		if (GetRedPlayer("4").getCollisions() > 1)
		{
			GetRedPlayer("4").setButton_numbers("" + GetRedPlayer("4").getCollisions() + "R");
		}
		else if (GetRedPlayer("4").getCollisions() <= 1)
		{
			GetRedPlayer("4").setButton_numbers("R");
		}
	}
	
	// DRAWS THE NUMBER OF GREEN PLAYERS IN ONE POSITION ONTO THE SCREEN
	public static void DrawNumberOfGreenPlayers()
	{
		if (GetGreenPlayer("1").getCollisions() > 1)
		{
			GetGreenPlayer("1").setButton_numbers("" + GetGreenPlayer("1").getCollisions() + "G");
		}
		else if (GetGreenPlayer("1").getCollisions() == 1)
		{
			GetGreenPlayer("1").setButton_numbers("G");
		}
		
		if (GetGreenPlayer("2").getCollisions() > 1)
		{
			GetGreenPlayer("2").setButton_numbers("" + GetGreenPlayer("2").getCollisions() + "G");
		}
		else if (GetGreenPlayer("2").getCollisions() == 1)
		{
			GetGreenPlayer("2").setButton_numbers("G");
		}
		
		if (GetGreenPlayer("3").getCollisions() > 1)
		{
			GetGreenPlayer("3").setButton_numbers("" + GetGreenPlayer("3").getCollisions() + "G");
		}
		else if (GetGreenPlayer("3").getCollisions() == 1)
		{
			GetGreenPlayer("3").setButton_numbers("G");
		}
		
		if (GetGreenPlayer("4").getCollisions() > 1)
		{
			GetGreenPlayer("4").setButton_numbers("" + GetGreenPlayer("4").getCollisions() + "G");
		}
		else if (GetGreenPlayer("4").getCollisions() == 1)
		{
			GetGreenPlayer("4").setButton_numbers("G");
		}
	}
	
	// DRAWS THE NUMBER OF YELLOW PLAYERS IN ONE POSITION ONTO THE SCREEN
	public static void DrawNumberOfYellowPlayers()
	{
		if (GetYellowPlayer("1").getCollisions() > 1)
		{
			GetYellowPlayer("1").setButton_numbers("" + GetYellowPlayer("1").getCollisions() + "Y");
		}
		else if (GetYellowPlayer("1").getCollisions() == 1)
		{
			GetYellowPlayer("1").setButton_numbers("Y");
		}
		
		if (GetYellowPlayer("2").getCollisions() > 1)
		{
			GetYellowPlayer("2").setButton_numbers("" + GetYellowPlayer("2").getCollisions() + "Y");
		}
		else if (GetYellowPlayer("2").getCollisions() == 1)
		{
			GetYellowPlayer("2").setButton_numbers("Y");
		}
		
		if (GetYellowPlayer("3").getCollisions() > 1)
		{
			GetYellowPlayer("3").setButton_numbers("" + GetYellowPlayer("3").getCollisions() + "Y");
		}
		else if (GetYellowPlayer("3").getCollisions() == 1)
		{
			GetYellowPlayer("3").setButton_numbers("Y");
		}
		
		if (GetYellowPlayer("4").getCollisions() > 1)
		{
			GetYellowPlayer("4").setButton_numbers("" + GetYellowPlayer("4").getCollisions() + "Y");
		}
		else if (GetYellowPlayer("4").getCollisions() == 1)
		{
			GetYellowPlayer("4").setButton_numbers("Y");
		}
	}
	
	// DRAWS A STRING INDICATING THAT THE ROAD IS BLOCKED ONTO THE SCREEN
	private static void DrawRoadBlockedSign(Graphics g)
	{
		if (draw_road_blocked_sign)
		{
			int x_pos = ((Singleton.GetScreenWidth() / 2) - 35);
			int y_pos = 15;
			String message = "";
			g.setColor(TEXT_COLOR);
			
			if (Controller.GetPerson_to_play() == 1)
			{
				
				message = "ROAD BLOCKED";
				g.drawString(message, x_pos, y_pos);
			}
		}
	}
	
	// THIS IS CALLED ONCE AT THE START OF THE APP TO INITIALIZE THE GAME
	public static void InitializeGame()
	{
		Singleton.CreateLudoBoardInstance();
		BluePlayerManager.CreateAllPlayers();
		RedPlayerManager.CreateAllPlayers();
		GreenPlayerManager.CreateAllPlayers();
		YellowPlayerManager.CreateAllPlayers();
	}
	
	// RETURNS THE RED PLAYER MATCHING A GIVEN ID OR NULL IF ID > 4 OR ID < 1
	public static Player GetRedPlayer(String id)
	{
		return RedPlayerManager.GetPlayer(id);
	}
	
	// RETURNS THE BLUE PLAYER MATCHING THE GIVEN ID OR NULL IF ID > 4 OR ID < 1
	public static Player GetBluePlayer(String id)
	{
		return BluePlayerManager.GetPlayer(id);
	}
	
	// RETURNS THE GREEN PLAYER MATCHING THE GIVEN ID OR NULL IF ID > 4 OR ID <
	// 1
	public static Player GetGreenPlayer(String id)
	{
		return GreenPlayerManager.GetPlayer(id);
	}
	
	// RETURNS THE YELLOW PLAYER MATCHING THE GIVEN ID OR NULL IF ID > 4 OR ID <
	// 1
	public static Player GetYellowPlayer(String id)
	{
		return YellowPlayerManager.GetPlayer(id);
	}
	
	// THIS IS WHERE ALL THE UPDATE GAME LOGIC GOES
	public static void UpdateGame()
	{
		// CHECK FOR COLLISIONS BETWEEN PLAYERS
		Controller.check4Collisions();
	}
	
	public static void RestartGame()
	{
		InitializeGame();
	}
}
