package Players;

import java.io.Serializable;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import Interfaces.PlayerInterface;
import Singletons.Singleton;

@SuppressWarnings("serial")
public abstract class Player implements PlayerInterface, Serializable
{
	
	// WIDTH OF ONE BOX
	private static double	width_of_box			= 15;
	
	// THE CORDINATE INDICATING THE CENTER OF THE PLAYER
	public static int		center_of_player		= (int) getWidth_of_box() / 2;
	
	// X CORDINATE OF THE CURRENT POSITION OF THE PLAYER
	public int				x_cordinate				= 5;
	
	// Y CORDINATE OF THE CURRENT POSITION OF THE PLAYER
	public int				y_cordinate				= 128;
	
	// X CORDINATE OF THE FIRST BOX THE PLAYER STEPS ON AFTER EXITING HOME
	private double			starting_x_cordinate	= 0;
	
	// Y CORDINATE OF THE FIRST BOX THE PLAYER STEPS ON AFTER EXITING HOME
	private double			starting_y_cordinate	= 0;
	
	// STEPS MOVED BY PLAYER :MAX BUMBER IS 58
	private int				steps_moved				= 0;
	
	// INDICATES HOW MANY PLAYERS ARE IN ANYONE BOX
	private int				collisions				= 0;
	
	// THIS THE X CORDINATE OF THE PLAYER WHILE AT SCHOOL PAD
	private int				initial_x				= 0;
	
	// THIS IS THE Y CORDINATE OF THE PLAYER WHILE AT SCHOOL PAD
	private int				initial_y				= 0;
	
	// INDICATES WHETHER THIS IS THE FIRST TIME THE GAME IS RUNNING
	// private boolean first_time = true;
	
	// INDICATES WHETHER THE PLAYER HAS BEEN DRAWN
	public boolean			has_not_been_drawn		= true;
	
	//
	private String			button_number			= "1";
	private String			init_button_number		= null;
	
	// INDICATES WHETHER THIS PLAYER HAS REACHED HOME
	protected boolean		finished				= false;
	
	// INDICATES THE COLOR OF THE PLAYER
	protected Color			PLAYER_COLOR;
	
	// constructor
	public Player()
	{
		width_of_box = (getScreenWidth() / 15);
	}
	
	// copy constructor
	public Player(Player player)
	{
		Player.width_of_box = getWidth_of_box();
		this.x_cordinate = player.x_cordinate;
		this.y_cordinate = player.y_cordinate;
		this.starting_x_cordinate = player.starting_x_cordinate;
		this.starting_y_cordinate = player.starting_y_cordinate;
		this.steps_moved = player.steps_moved;
		this.collisions = player.collisions;
		this.initial_x = player.initial_x;
		this.initial_y = player.initial_y;
		this.has_not_been_drawn = player.has_not_been_drawn;
		this.button_number = new String(player.button_number);
		this.init_button_number = new String(player.init_button_number);
		this.finished = player.finished;
		this.PLAYER_COLOR = player.PLAYER_COLOR;
	}
	
	// MOVES PLAYER ONE STEP FORWARD
	@Override
	public abstract void movePlayerForward();
	
	// INCREMENTS THE STEPS THE PLAYER HAS MOVED
	public void incrementSteps()
	{
		setHas_not_been_drawn(false);
		setSteps_moved(getSteps_moved() + 1);
	}
	
	// MOVES PLAYER RIGHT SIDE WAYS ONE STEP
	@Override
	public void movePlayerRightSideWays()
	{
		setX_cordinate((int) (getX_cordinate() + (int) getWidth_of_box()));
	}
	
	// MOVES PLAYERS LEFT SIDEWAYS ONE STEP
	@Override
	public void movePlayerLeftSideWays()
	{
		setX_cordinate((int) (getX_cordinate() - (int) getWidth_of_box()));
	}
	
	// MOVES PLAYER DOWN ONE STEP
	@Override
	public void movePlayerDownWards()
	{
		setY_cordinate((int) (getY_cordinate() + (int) getWidth_of_box()));
	}
	
	// MOVES PLAYER UP ONE STEP
	@Override
	public void movePlayerUpwards()
	{
		setY_cordinate((int) (getY_cordinate() - (int) getWidth_of_box()));
	}
	
	// DRAWS PLAYER ONTO SCREEN
	@Override
	public void drawPlayer(Graphics g)
	{
		g.setAntiAlias(true);
		g.setColor(PLAYER_COLOR);
		g.fillOval(getX_cordinate(), getY_cordinate(), (float) getWidth_of_box(), (float) getWidth_of_box());
		g.setColor(Color.black);
		g.drawOval(getX_cordinate(), getY_cordinate(), (float) getWidth_of_box(), (float) getWidth_of_box());
		g.setColor(Color.black);
		g.drawString(getButton_number(), getX_cordinate() + (center_of_player + center_of_player + 2), getY_cordinate() + (center_of_player + center_of_player - 2));
	}
	
	// DRAWS THE NUMBER OF PLAYERS OF ONE COLOR IN ONE POSITION ONTO SCREEN
	public void drawNumber(Graphics g)
	{
		if (collisions > 1)
		{
			int x_pos = this.x_cordinate;
			int y_pos = this.y_cordinate;
			
			g.setColor(Color.white);
			g.drawString(collisions + "R", x_pos, y_pos);
		}
	}
	
	// CLONE METHOD MUST BE IMPLEMENTED BY SUBCLASSES
	@Override
	protected abstract Object clone() throws CloneNotSupportedException;
	
	public String getInit_button_number()
	{
		return init_button_number;
	}
	
	public void setInit_button_number(String init_button_number)
	{
		this.init_button_number = init_button_number;
	}
	
	// setter 4 x_cordinate
	public void setX_cordinate(int m)
	{
		this.x_cordinate = m;
	}
	
	// setter 4 y_cordinate
	public void setY_cordinate(int m)
	{
		this.y_cordinate = m;
	}
	
	// getter 4 steps_moved
	public int getSteps_moved()
	{
		return steps_moved;
	}
	
	public void setButton_numbers(String string)
	{
		if (this.getCollisions() > 0)
		{
			if (this.getCollisions() == 1)
			{
				this.button_number = getInit_button_number();
				
				return;
			}
			
			this.button_number = string;
		}
	}
	
	// SETS THE STEPS MOVED BY THE PLAER
	public void setSteps_moved(int steps_moved)
	{
		this.steps_moved = steps_moved;
	}
	
	// RETURNS X CORDINATE
	public int getX_cordinate()
	{
		return x_cordinate;
	}
	
	// RETURNS Y CORDINATE
	public int getY_cordinate()
	{
		return y_cordinate;
	}
	
	public boolean isHas_not_been_drawn()
	{
		return has_not_been_drawn;
	}
	
	public void setHas_not_been_drawn(boolean has_not_been_drawn)
	{
		this.has_not_been_drawn = has_not_been_drawn;
	}
	
	public String getButton_number()
	{
		return button_number;
	}
	
	public void setButton_number(String button_number)
	{
		this.button_number = button_number;
	}
	
	public static double getWidth_of_box()
	{
		return width_of_box;
	}
	
	public static void setWidth_of_box(int width_of_box)
	{
		Player.width_of_box = width_of_box;
	}
	
	public int getInitial_x()
	{
		return initial_x;
	}
	
	public void setInitial_x(int initial_x)
	{
		this.initial_x = initial_x;
	}
	
	public int getInitial_y()
	{
		return initial_y;
	}
	
	public void setInitial_y(int initial_y)
	{
		this.initial_y = initial_y;
	}
	
	public int getCollisions()
	{
		return collisions;
	}
	
	public void setCollisions(int collisions)
	{
		this.collisions = collisions;
	}
	
	public int getScreenHeight()
	{
		return Singleton.GetScreenHeight();
	}
	
	public int getScreenWidth()
	{
		return Singleton.GetScreenWidth();
	}
	
	public double getStarting_x_cordinate()
	{
		return starting_x_cordinate;
	}
	
	public void setStarting_x_cordinate(double starting_x_cordinate)
	{
		this.starting_x_cordinate = starting_x_cordinate;
	}
	
	public double getStarting_y_cordinate()
	{
		return starting_y_cordinate;
	}
	
	public void setStarting_y_cordinate(double starting_y_cordinate)
	{
		this.starting_y_cordinate = starting_y_cordinate;
	}
	
	public boolean isFinished()
	{
		return finished;
	}
	
	public void setFinished(boolean finished)
	{
		this.finished = finished;
	}
}
