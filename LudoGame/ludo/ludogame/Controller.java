package ludogame;

import Managers.BluePlayerManager;
import Managers.GreenPlayerManager;
import Managers.PlayerManager;
import Managers.RedPlayerManager;
import Managers.YellowPlayerManager;
import Players.Player;
import Players.RedPlayer;
import Players.YellowPlayer;
import Players.GreenPlayer;
import Players.BluePlayer;
import Singletons.Singleton;

public class Controller
{
	
	// THIS IS THE CURRENT VALUE OF THE DIE
	private static int	value_from_die;
	
	// INDICATES WHOSE TURN IT IS TO PLAY WITH BLUE BEING 1 AND YELLOW BEING 4
	private static int	person_to_play	= 1;
	
	// INDICATES WHETHER PATH AHEAD OF PLAYER IS BLOCKED
	private boolean		road_blocked;
	
	// constructor
	public Controller()
	{
	}
	
	// moves player forward the value on the die
	public boolean MovePlayerForward(Player player)
	{
		
		// check if die was rolled
		if (CurrentValueOfFDie() > 0)
		{
			int x_cord = player.getX_cordinate();
			int y_cord = player.getY_cordinate();
			int steps = player.getSteps_moved();
			
			// try to get player from color home
			if (attempt2GetPlayerFromHomeSucceds(player))
			{
				return true;
			}
			
			// if player has already reached home do nothing
			if (player.getSteps_moved() >= 58)
			{
				return false;
			}
			
			// if player is inside home stretch he has to play exact number to
			// go home
			else if (player.getSteps_moved() >= 52)
			{
				if (59 - player.getSteps_moved() <= value_from_die)
				{
					return false;
				}
			}
			
			// if player isnt at home move him
			if (player.getSteps_moved() > 0)
			{
				for (int i = 1; i <= (value_from_die); i++)
				{
					player.movePlayerForward();
					
					if (roadBlocked(player))
					{
						road_blocked = true;
					}
					
					player.setHas_not_been_drawn(true);
				}
				
				// roll back changes
				if (road_blocked)
				{
					player.setX_cordinate(x_cord);
					player.setY_cordinate(y_cord);
					player.setSteps_moved(steps);
					// ludo.setDrawRoadBlockedSign(true);
					// println("ROAD BLOCKED");
					road_blocked = false;
					return false;
				}
				PlayPlayerHasMovedMusic();
				// see if player eats someone
				if (GetPerson_to_play() == 1)
				{
					BlueEatsPlayer(player);
				}
				else if (GetPerson_to_play() == 2)
				{
					RedEatsPlayer(player);
				}
				else if (GetPerson_to_play() == 3)
				{
					GreenEatsPlayer(player);
				}
				else if (GetPerson_to_play() == 4)
				{
					YellowEatsPlayer(player);
				}
				if (value_from_die == 6)
				{
					resetDieScore();
					LudoGame.draw_score = false;
					return true;
				}
				// reset variables and change person to play
				resetDieScore();
				LudoGame.draw_score = false;
				ChangePersonToPlay();
				return true;
			}
			// occurs rarely at the begining of the game
			else
			{
				// println("steps less than 0");
				return false;
			}
		}
		else
		{
			// println("PLIZ ROLL DIE FIRST THEN MOVE BUTTON");
			return false;
		}
	}
	
	// rolls the dice
	public void RollDie()
	{
		value_from_die = Singleton.GetDiceInstance().rollDice();
		Singleton.SetDieScore("" + value_from_die);
		LudoGame.draw_score = true;
		
	}
	
	// returns die score to 0
	public static void resetDieScore()
	{
		value_from_die = 0;
	}
	
	// checks if road is blocked
	public boolean roadBlocked(Player player)
	{
		int num = 0;
		int num2 = 0;
		int num3 = 0;
		if (person_to_play == 1)
		{
			num = check4CollisionWithRed(player);
			num2 = check4CollisionWithGreen(player);
			num3 = check4CollisionWithYellow(player);
		}
		else if (person_to_play == 2)
		{
			
			num = check4CollisionWithBlue(player);
			num2 = check4CollisionWithGreen(player);
			num3 = check4CollisionWithYellow(player);
			
		}
		else if (person_to_play == 3)
		{
			num = check4CollisionWithBlue(player);
			num2 = check4CollisionWithRed(player);
			num3 = check4CollisionWithYellow(player);
		}
		else if (person_to_play == 4)
		{
			num = check4CollisionWithBlue(player);
			num2 = check4CollisionWithRed(player);
			num3 = check4CollisionWithGreen(player);
		}
		if (num > 1 || num2 > 1 || num3 > 1)
		{
			return true;
		}
		return false;
	}
	
	// determines if a blue player eats any other player
	private void BlueEatsPlayer(Player player)
	{
		Player player_to_be_eaten = null;
		try
		{
			BluePlayer player_to_eat = (BluePlayer) player;
			player_to_be_eaten = checkIfEatsRedPlayer(player_to_eat);
			if (player_to_be_eaten == null)
			{
				player_to_be_eaten = checkIfEatsGreenPlayer(player_to_eat);
				if (player_to_be_eaten == null)
				{
					player_to_be_eaten = checkIfEatsYellowPlayer(player_to_eat);
				}
			}
			if (player_to_be_eaten != null)
			{
				PlayPersonEatenMusic();
				PlayerManager.returnPlayerHome(player_to_be_eaten);
			}
		}
		catch (Exception e)
		{
		}
		return;
	}
	
	// determines if a Green player eats any other player
	private void GreenEatsPlayer(Player player)
	{
		Player player_to_be_eaten = null;
		try
		{
			Player player_to_eat = player;
			player_to_be_eaten = checkIfEatsYellowPlayer(player_to_eat);
			if (player_to_be_eaten == null)
			{
				player_to_be_eaten = checkIfEatsBluePlayer(player_to_eat);
				if (player_to_be_eaten == null)
				{
					player_to_be_eaten = checkIfEatsRedPlayer(player_to_eat);
				}
			}
			// println("green_player_to_be_eaten=" +
			// player_to_be_eaten.toString());
			if (player_to_be_eaten != null)
			{
				PlayPersonEatenMusic();
				PlayerManager.returnPlayerHome(player_to_be_eaten);
			}
		}
		catch (Exception e)
		{
		}
		return;
	}
	
	// determines if a red player eats any other player
	private void RedEatsPlayer(Player player)
	{
		Player player_to_be_eaten = null;
		try
		{
			RedPlayer player_to_eat = (RedPlayer) player;
			player_to_be_eaten = checkIfEatsBluePlayer(player_to_eat);
			if (player_to_be_eaten == null)
			{
				player_to_be_eaten = checkIfEatsGreenPlayer(player_to_eat);
				if (player_to_be_eaten == null)
				{
					player_to_be_eaten = checkIfEatsYellowPlayer(player_to_eat);
				}
			}
			
			// player_to_be_eaten.println("red_player_to_be_eaten" +
			// player_to_be_eaten.toString());
			if (player_to_be_eaten != null)
			{
				PlayPersonEatenMusic();
				PlayerManager.returnPlayerHome(player_to_be_eaten);
			}
		}
		catch (Exception e)
		{
		}
		return;
	}
	
	// determines if a yellow Player eats any other player
	private void YellowEatsPlayer(Player player)
	{
		Player player_to_be_eaten = null;
		try
		{
			YellowPlayer player_to_eat = (YellowPlayer) player;
			player_to_be_eaten = checkIfEatsGreenPlayer(player_to_eat);
			if (player_to_be_eaten == null)
			{
				player_to_be_eaten = checkIfEatsBluePlayer(player_to_eat);
				if (player_to_be_eaten == null)
				{
					player_to_be_eaten = checkIfEatsRedPlayer(player_to_eat);
				}
			}
			// println("yellow_player_to_be_eaten=" +
			// player_to_be_eaten.toString());
			if (player_to_be_eaten != null)
			{
				PlayPersonEatenMusic();
				PlayerManager.returnPlayerHome(player_to_be_eaten);
			}
		}
		catch (Exception e)
		{
		}
		return;
	}
	
	// gets a player from home and places him on starting pad
	private boolean attempt2GetPlayerFromHomeSucceds(Player player)
	{
		boolean blu = false;
		boolean red = false;
		boolean green = false;
		boolean yellow = false;
		if (player.getSteps_moved() == 0 && CurrentValueOfFDie() == 6)
		{
			if (GetPerson_to_play() == 1)
			{
				blu = try2GetBluePlayerFromHome(player);
				if (blu)
				{
					BlueEatsPlayer(player);
				}
			}
			else if (GetPerson_to_play() == 2)
			{
				red = try2GetRedPlayerFromHome(player);
				if (red)
				{
					RedEatsPlayer(player);
				}
			}
			else if (GetPerson_to_play() == 3)
			{
				green = try2GetGreenPlayerFromHome(player);
				if (green)
				{
					GreenEatsPlayer(player);
				}
			}
			else if (GetPerson_to_play() == 4)
			{
				yellow = try2GetYellowPlayerFromHome(player);
				if (yellow)
				{
					YellowEatsPlayer(player);
				}
			}
			
			if (blu || red || green || yellow)
			{
				value_from_die = 0;
				LudoGame.draw_score = false;
				PlayPlayerHasMovedMusic();
				return true;
			}
		}
		return false;
	}
	
	// gets a blue player from home and places him on starting pad
	private boolean try2GetBluePlayerFromHome(Player player)
	{
		try
		{
			BluePlayer bluePlayer = (BluePlayer) player;
			if (!BluePlayerManager.ColorHasFinished())
			{
				PlayerManager.getPlayerFromHome(bluePlayer);
				return true;
			}
			return false;
		}
		catch (Exception e)
		{
			return false;
		}
	}
	
	// attempts to get a red player form home and places him on starting pad
	private boolean try2GetRedPlayerFromHome(Player player)
	{
		try
		{
			RedPlayer red_player = (RedPlayer) player;
			if (!RedPlayerManager.ColorHasFinished())
			{
				PlayerManager.getPlayerFromHome(red_player);
				return true;
			}
			return false;
		}
		catch (Exception e)
		{
			return false;
		}
	}
	
	// attempts to get a green player form home and places him on starting pad
	private boolean try2GetGreenPlayerFromHome(Player player)
	{
		try
		{
			GreenPlayer greenPlayer = (GreenPlayer) player;
			if (!GreenPlayerManager.ColorHasFinished())
			{
				PlayerManager.getPlayerFromHome(greenPlayer);
				return true;
			}
			return false;
		}
		catch (Exception e)
		{
			return false;
		}
	}
	
	// attempts to get a yellow player form home and places him on starting pad
	private boolean try2GetYellowPlayerFromHome(Player player)
	{
		try
		{
			YellowPlayer yellow_player = (YellowPlayer) player;
			if (!YellowPlayerManager.ColorHasFinished())
			{
				PlayerManager.getPlayerFromHome(yellow_player);
				return true;
			}
			return false;
		}
		catch (Exception e)
		{
			return false;
		}
	}
	
	// return the current value of the dice
	public static int CurrentValueOfFDie()
	{
		return value_from_die;
	}
	
	// automatically changes the person to play
	public static void ChangePersonToPlay()
	{
		if (person_to_play == 1)
		{
			person_to_play = 2;
		}
		else if (person_to_play == 2)
		{
			person_to_play = 3;
		}
		else if (person_to_play == 3)
		{
			person_to_play = 4;
		}
		else if (person_to_play == 4)
		{
			person_to_play = 1;
		}
	}
	
	// collision detection method
	private static int collisionDetection(int x, int y, int z, int w)
	{
		int collision = 0;
		int radius_of_player = Player.center_of_player;
		if ((x + radius_of_player) >= z && (x - radius_of_player) <= z)
		{
			if ((y + radius_of_player) >= w && (y - radius_of_player) <= w)
			{
				collision = 1;
				return collision;
			}
		}
		return collision;
	}
	
	// checks for collision between 2 players
	private static int CollisionBetween2RedPlayers(Player player)
	{
		int total_collisions = 0;
		try
		{
			RedPlayer red_player = (RedPlayer) player;
			total_collisions += check4CollisionWithRed(red_player);
			player.setCollisions(total_collisions);
		}
		catch (Exception e)
		{
			return -1;
		}
		return total_collisions;
	}
	
	// checks for a collision with blue players
	private int check4CollisionWithBlue(Player player)
	{
		int number_of_collisions = 0;
		int x_cord = getMidPoint(player.x_cordinate);
		int y_cord = getMidPoint(player.y_cordinate);
		int blux1 = getMidPoint(BluePlayerManager.GetPlayer("1").x_cordinate);// blue_player1.x_cordinate;
		int blux2 = getMidPoint(BluePlayerManager.GetPlayer("2").x_cordinate);
		int blux3 = getMidPoint(BluePlayerManager.GetPlayer("3").x_cordinate);// blue_player3.x_cordinate;
		int blux4 = getMidPoint(BluePlayerManager.GetPlayer("4").x_cordinate);// blue_player4.x_cordinate;
		int bluy1 = getMidPoint(BluePlayerManager.GetPlayer("1").y_cordinate);// blue_player1.y_cordinate;
		int bluy2 = getMidPoint(BluePlayerManager.GetPlayer("2").y_cordinate);// blue_player2.y_cordinate;
		int bluy3 = getMidPoint(BluePlayerManager.GetPlayer("3").y_cordinate);// blue_player3.y_cordinate;
		int bluy4 = getMidPoint(BluePlayerManager.GetPlayer("4").y_cordinate);// blue_player4.y_cordinate;
		number_of_collisions += collisionDetection(x_cord, y_cord, blux1, bluy1);
		number_of_collisions += collisionDetection(x_cord, y_cord, blux2, bluy2);
		number_of_collisions += collisionDetection(x_cord, y_cord, blux3, bluy3);
		number_of_collisions += collisionDetection(x_cord, y_cord, blux4, bluy4);
		return number_of_collisions;
	}
	
	// checks for collision with red players
	private static int check4CollisionWithRed(Player player)
	{
		
		int number_of_collisions = 0;
		int x_cord = getMidPoint(player.getX_cordinate());
		int y_cord = getMidPoint(player.getY_cordinate());
		int redx1 = getMidPoint(RedPlayerManager.GetPlayer("1").x_cordinate);
		int redx2 = getMidPoint(RedPlayerManager.GetPlayer("2").x_cordinate);
		int redx3 = getMidPoint(RedPlayerManager.GetPlayer("3").x_cordinate);
		int redx4 = getMidPoint(RedPlayerManager.GetPlayer("4").x_cordinate);
		int redy1 = getMidPoint(RedPlayerManager.GetPlayer("1").y_cordinate);
		int redy2 = getMidPoint(RedPlayerManager.GetPlayer("2").y_cordinate);
		int redy3 = getMidPoint(RedPlayerManager.GetPlayer("3").y_cordinate);
		int redy4 = getMidPoint(RedPlayerManager.GetPlayer("4").y_cordinate);
		number_of_collisions += collisionDetection(x_cord, y_cord, redx1, redy1);
		number_of_collisions += collisionDetection(x_cord, y_cord, redx2, redy2);
		number_of_collisions += collisionDetection(x_cord, y_cord, redx3, redy3);
		number_of_collisions += collisionDetection(x_cord, y_cord, redx4, redy4);
		return number_of_collisions;
	}
	
	// checks 4 collision with red players
	private static int check4CollisionWithGreen(Player player)
	{
		int number_of_collisions = 0;
		int x_cord = getMidPoint(player.getX_cordinate());
		int y_cord = getMidPoint(player.getY_cordinate());
		int greenx1 = getMidPoint(GreenPlayerManager.GetPlayer("1").x_cordinate);
		int greenx2 = getMidPoint(GreenPlayerManager.GetPlayer("2").x_cordinate);
		int greenx3 = getMidPoint(GreenPlayerManager.GetPlayer("3").x_cordinate);
		int greenx4 = getMidPoint(GreenPlayerManager.GetPlayer("4").x_cordinate);
		int greeny1 = getMidPoint(GreenPlayerManager.GetPlayer("1").y_cordinate);
		int greeny2 = getMidPoint(GreenPlayerManager.GetPlayer("2").y_cordinate);
		int greeny3 = getMidPoint(GreenPlayerManager.GetPlayer("3").y_cordinate);
		int greeny4 = getMidPoint(GreenPlayerManager.GetPlayer("4").y_cordinate);
		number_of_collisions += collisionDetection(x_cord, y_cord, greenx1, greeny1);
		number_of_collisions += collisionDetection(x_cord, y_cord, greenx2, greeny2);
		number_of_collisions += collisionDetection(x_cord, y_cord, greenx3, greeny3);
		number_of_collisions += collisionDetection(x_cord, y_cord, greenx4, greeny4);
		return number_of_collisions;
	}
	
	// checks 4 collision with yellow players
	private static int check4CollisionWithYellow(Player player)
	{
		int number_of_collisions = 0;
		int x_cord = getMidPoint(player.getX_cordinate());
		int y_cord = getMidPoint(player.getY_cordinate());
		int yellowx1 = getMidPoint(YellowPlayerManager.GetPlayer("1").x_cordinate);
		int yellowx2 = getMidPoint(YellowPlayerManager.GetPlayer("2").x_cordinate);
		int yellowx3 = getMidPoint(YellowPlayerManager.GetPlayer("3").x_cordinate);
		int yellowx4 = getMidPoint(YellowPlayerManager.GetPlayer("4").x_cordinate);
		int yellowy1 = getMidPoint(YellowPlayerManager.GetPlayer("1").y_cordinate);
		int yellowy2 = getMidPoint(YellowPlayerManager.GetPlayer("2").y_cordinate);
		int yellowy3 = getMidPoint(YellowPlayerManager.GetPlayer("3").y_cordinate);
		int yellowy4 = getMidPoint(YellowPlayerManager.GetPlayer("4").y_cordinate);
		number_of_collisions += collisionDetection(x_cord, y_cord, yellowx1, yellowy1);
		number_of_collisions += collisionDetection(x_cord, y_cord, yellowx2, yellowy2);
		number_of_collisions += collisionDetection(x_cord, y_cord, yellowx3, yellowy3);
		number_of_collisions += collisionDetection(x_cord, y_cord, yellowx4, yellowy4);
		return number_of_collisions;
		
	}
	
	// gets the midpoint of a given x_cordinate
	private static int getMidPoint(int cordinate)
	{
		int mid_point = (int) ((cordinate + Player.getWidth_of_box()) / 2);
		return mid_point;
	}
	
	// checks if given player eats a red player
	private Player checkIfEatsRedPlayer(Player player)
	{
		int x_cord = getMidPoint(player.x_cordinate);// player.x_cordinate;
		int y_cord = getMidPoint(player.y_cordinate);// player.y_cordinate;
		int redx1 = getMidPoint(RedPlayerManager.GetPlayer("1").x_cordinate);
		int redx2 = getMidPoint(RedPlayerManager.GetPlayer("2").x_cordinate);
		int redx3 = getMidPoint(RedPlayerManager.GetPlayer("3").x_cordinate);
		int redx4 = getMidPoint(RedPlayerManager.GetPlayer("4").x_cordinate);
		int redy1 = getMidPoint(RedPlayerManager.GetPlayer("1").y_cordinate);
		int redy2 = getMidPoint(RedPlayerManager.GetPlayer("2").y_cordinate);
		int redy3 = getMidPoint(RedPlayerManager.GetPlayer("3").y_cordinate);
		int redy4 = getMidPoint(RedPlayerManager.GetPlayer("4").y_cordinate);
		if (collisionDetection(x_cord, y_cord, redx1, redy1) == 1)
		{
			return RedPlayerManager.GetPlayer("1");
		}
		else if (collisionDetection(x_cord, y_cord, redx2, redy2) == 1)
		{
			return RedPlayerManager.GetPlayer("2");
		}
		else if (collisionDetection(x_cord, y_cord, redx3, redy3) == 1)
		{
			return RedPlayerManager.GetPlayer("3");
		}
		else if (collisionDetection(x_cord, y_cord, redx4, redy4) == 1)
		{
			return RedPlayerManager.GetPlayer("4");
		}
		else
		{
			return null;
		}
	}
	
	// checks if given player eats blue player
	private Player checkIfEatsBluePlayer(Player player)
	{
		int x_cord = getMidPoint(player.x_cordinate);
		int y_cord = getMidPoint(player.y_cordinate);
		int blux1 = getMidPoint(BluePlayerManager.GetPlayer("1").x_cordinate);
		int blux2 = getMidPoint(BluePlayerManager.GetPlayer("2").x_cordinate);
		int blux3 = getMidPoint(BluePlayerManager.GetPlayer("3").x_cordinate);
		int blux4 = getMidPoint(BluePlayerManager.GetPlayer("4").x_cordinate);
		int bluy1 = getMidPoint(BluePlayerManager.GetPlayer("1").y_cordinate);
		int bluy2 = getMidPoint(BluePlayerManager.GetPlayer("2").y_cordinate);
		int bluy3 = getMidPoint(BluePlayerManager.GetPlayer("3").y_cordinate);
		int bluy4 = getMidPoint(BluePlayerManager.GetPlayer("4").y_cordinate);
		if (collisionDetection(x_cord, y_cord, blux1, bluy1) == 1)
		{
			return BluePlayerManager.GetPlayer("1");
		}
		else if (collisionDetection(x_cord, y_cord, blux2, bluy2) == 1)
		{
			return BluePlayerManager.GetPlayer("2");
		}
		else if (collisionDetection(x_cord, y_cord, blux3, bluy3) == 1)
		{
			return BluePlayerManager.GetPlayer("3");
		}
		else if (collisionDetection(x_cord, y_cord, blux4, bluy4) == 1)
		{
			return BluePlayerManager.GetPlayer("4");
		}
		else
		{
			return null;
		}
	}
	
	// checks if given player Eats Green player
	private Player checkIfEatsGreenPlayer(Player player)
	{
		int x_cord = getMidPoint(player.x_cordinate);
		int y_cord = getMidPoint(player.y_cordinate);
		int green_x1 = getMidPoint(GreenPlayerManager.GetPlayer("1").x_cordinate);
		int green_x2 = getMidPoint(GreenPlayerManager.GetPlayer("2").x_cordinate);
		int green_x3 = getMidPoint(GreenPlayerManager.GetPlayer("3").x_cordinate);
		int green_x4 = getMidPoint(GreenPlayerManager.GetPlayer("4").x_cordinate);
		int green_y1 = getMidPoint(GreenPlayerManager.GetPlayer("1").y_cordinate);
		int green_y2 = getMidPoint(GreenPlayerManager.GetPlayer("2").y_cordinate);
		int green_y3 = getMidPoint(GreenPlayerManager.GetPlayer("3").y_cordinate);
		int green_y4 = getMidPoint(GreenPlayerManager.GetPlayer("4").y_cordinate);
		if (collisionDetection(x_cord, y_cord, green_x1, green_y1) == 1)
		{
			return GreenPlayerManager.GetPlayer("1");
		}
		else if (collisionDetection(x_cord, y_cord, green_x2, green_y2) == 1)
		{
			return GreenPlayerManager.GetPlayer("2");
		}
		else if (collisionDetection(x_cord, y_cord, green_x3, green_y3) == 1)
		{
			return GreenPlayerManager.GetPlayer("3");
		}
		else if (collisionDetection(x_cord, y_cord, green_x4, green_y4) == 1)
		{
			return GreenPlayerManager.GetPlayer("4");
		}
		else
		{
			return null;
		}
	}
	
	// checks if given player eats Yellow Player
	private Player checkIfEatsYellowPlayer(Player player)
	{
		int x_cord = getMidPoint(player.x_cordinate);
		int y_cord = getMidPoint(player.y_cordinate);
		int yellow_x1 = getMidPoint(YellowPlayerManager.GetPlayer("1").x_cordinate);
		int yellow_x2 = getMidPoint(YellowPlayerManager.GetPlayer("2").x_cordinate);
		int yellow_x3 = getMidPoint(YellowPlayerManager.GetPlayer("3").x_cordinate);
		int yellow_x4 = getMidPoint(YellowPlayerManager.GetPlayer("4").x_cordinate);
		int yellow_y1 = getMidPoint(YellowPlayerManager.GetPlayer("1").y_cordinate);
		int yellow_y2 = getMidPoint(YellowPlayerManager.GetPlayer("2").y_cordinate);
		int yellow_y3 = getMidPoint(YellowPlayerManager.GetPlayer("3").y_cordinate);
		int yellow_y4 = getMidPoint(YellowPlayerManager.GetPlayer("4").y_cordinate);
		if (collisionDetection(x_cord, y_cord, yellow_x1, yellow_y1) == 1)
		{
			return YellowPlayerManager.GetPlayer("1");
		}
		else if (collisionDetection(x_cord, y_cord, yellow_x2, yellow_y2) == 1)
		{
			return YellowPlayerManager.GetPlayer("2");
		}
		else if (collisionDetection(x_cord, y_cord, yellow_x3, yellow_y3) == 1)
		{
			return YellowPlayerManager.GetPlayer("3");
		}
		else if (collisionDetection(x_cord, y_cord, yellow_x4, yellow_y4) == 1)
		{
			return YellowPlayerManager.GetPlayer("4");
		}
		else
		{
			return null;
		}
	}
	
	// checks 4 collisions btn players
	private static int check4CollisionsBtnPlayers(Player player)
	{
		int collisions_red = CollisionBetween2RedPlayers(player);
		int collisions_green = CollisionBetween2GreenPlayers(player);
		int collisions_yellow = CollisionBetween2YellowPlayers(player);
		// player.setCollisions(collisions_red);
		if (collisions_red > 0)
		{
			player.setCollisions(collisions_red);
			return collisions_red;
		}
		if (collisions_green > 0)
		{
			player.setCollisions(collisions_green);
			return collisions_green;
		}
		if (collisions_yellow > 0)
		{
			player.setCollisions(collisions_yellow);
			return collisions_yellow;
		}
		return -1;
	}
	
	// checks for collision
	public static void check4Collisions()
	{
		check4CollisionsBtnPlayers(RedPlayerManager.GetPlayer("1"));
		check4CollisionsBtnPlayers(RedPlayerManager.GetPlayer("2"));
		check4CollisionsBtnPlayers(RedPlayerManager.GetPlayer("3"));
		check4CollisionsBtnPlayers(RedPlayerManager.GetPlayer("4"));
		check4CollisionsBtnPlayers(GreenPlayerManager.GetPlayer("1"));
		check4CollisionsBtnPlayers(GreenPlayerManager.GetPlayer("2"));
		check4CollisionsBtnPlayers(GreenPlayerManager.GetPlayer("3"));
		check4CollisionsBtnPlayers(GreenPlayerManager.GetPlayer("4"));
		check4CollisionsBtnPlayers(YellowPlayerManager.GetPlayer("1"));
		check4CollisionsBtnPlayers(YellowPlayerManager.GetPlayer("2"));
		check4CollisionsBtnPlayers(YellowPlayerManager.GetPlayer("3"));
		check4CollisionsBtnPlayers(YellowPlayerManager.GetPlayer("4"));
	}
	
	private static int CollisionBetween2GreenPlayers(Player player)
	{
		int total_collisions = 0;
		try
		{
			GreenPlayer green_player = (GreenPlayer) player;
			total_collisions += check4CollisionWithGreen(green_player);
			player.setCollisions(total_collisions);
		}
		catch (Exception e)
		{
			return -1;
		}
		return total_collisions;
	}
	
	private static int CollisionBetween2YellowPlayers(Player player)
	{
		int total_collisions = 0;
		try
		{
			YellowPlayer yellow_player = (YellowPlayer) player;
			total_collisions += check4CollisionWithYellow(yellow_player);
			player.setCollisions(total_collisions);
		}
		catch (Exception e)
		{
			return -1;
		}
		return total_collisions;
	}
	
	private void PlayPlayerHasMovedMusic()
	{
		if (GetPerson_to_play() == 1)
		{
			Singleton.GetAudioPlayer().playSoundForMovingPlayer();
		}
		
	}
	
	public void PlayPersonEatenMusic()
	{
		Singleton.GetAudioPlayer().playSoundForEatingAnotherPlayer();
	}
	
	// returns person to play
	public static int GetPerson_to_play()
	{
		return person_to_play;
	}
	
	// sets person to play
	public static void SetPerson_to_play(int aPerson_to_play)
	{
		person_to_play = aPerson_to_play;
	}
	
}
