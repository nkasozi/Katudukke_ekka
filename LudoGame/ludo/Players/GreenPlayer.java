package Players;

import javax.xml.bind.annotation.XmlRootElement;

import org.newdawn.slick.Color;

@XmlRootElement(name = "GreenPlayer")
@SuppressWarnings("serial")
public class GreenPlayer extends Player
{
	
	// constructor
	public GreenPlayer(int button_num)
	{
		super();
		setColorToGreen();
		setInit_button_number("G");
		setStarting_x_cordinate(getWidth_of_box() * 13 + 2); // 128
		setStarting_y_cordinate(getWidth_of_box() * 8 + 2); // 57
		
		// initial position of buttons
		int x_init = (int) (0.7046153846153846 * getScreenWidth());
		int y_init = (int) (0.7046153846153846 * getScreenWidth());
		
		switch (button_num)
		{
			case 1:
				setX_cordinate((int) x_init);
				setY_cordinate((int) y_init);
				setInitial_x(getX_cordinate());
				setInitial_y(getY_cordinate());
				setButton_number("G");
				
				break;
			
			case 2:
				x_cordinate = x_init + (int) ((getWidth_of_box() * 2) - (int) ((0.9933774834437086 / 100) * getScreenWidth()));
				y_cordinate = y_init;
				setButton_number("G");
				setInitial_x(getX_cordinate());
				setInitial_y(getY_cordinate());
				
				break;
			
			case 3:
				x_cordinate = x_init;
				y_cordinate = y_init + (int) ((getWidth_of_box() * 2) - (int) ((0.9933774834437086 / 100) * getScreenHeight()));
				setButton_number("G");
				setInitial_x(getX_cordinate());
				setInitial_y(getY_cordinate());
				
				break;
			
			case 4:
				x_cordinate = x_init + (int) ((getWidth_of_box() * 2) - (int) ((0.9933774834437086 / 100) * getScreenWidth()));
				y_cordinate = y_init + (int) ((getWidth_of_box() * 2) - (int) ((0.9933774834437086 / 100) * getScreenHeight()));
				setButton_number("G");
				setInitial_x(getX_cordinate());
				setInitial_y(getY_cordinate());
				
				break;
			
			default:
				
				// println("YOU CAN ONLY HAVE 4 BUTTONS");
		}
	}
	
	// COPY CONSTRUCTOR
	public GreenPlayer(GreenPlayer greenPlayer)
	{
		super(greenPlayer);
	}
	
	// SETS THE PLAYER BUTTON COLOR TO GREEN
	private void setColorToGreen()
	{
		PLAYER_COLOR = Color.green;
	}
	
	// MOVES GREEN PLAYER 1 STEP FORWARD
	@Override
	public void movePlayerForward()
	{
		if (has_not_been_drawn)
		{
			
			// going thru blue
			if ((getSteps_moved() >= 0) && (getSteps_moved() <= 5))
			{
				movePlayerLeftSideWays();
				incrementSteps();
			}
			
			// going to red
			else if (getSteps_moved() == 6)
			{
				x_cordinate -= (int) getWidth_of_box() + 1;
				y_cordinate += (int) getWidth_of_box() + 1;
				incrementSteps();
			}
			else if ((getSteps_moved() >= 7) && (getSteps_moved() <= 11))
			{
				movePlayerDownWards();
				incrementSteps();
			}
			else if (((getSteps_moved() > 11) && (getSteps_moved() < 14)))
			{
				movePlayerLeftSideWays();
				incrementSteps();
			}
			
			// at red
			// go to green
			else if ((getSteps_moved() >= 14) && (getSteps_moved() <= 18))
			{
				movePlayerUpwards();
				incrementSteps();
			}
			else if (getSteps_moved() == 19)
			{
				x_cordinate -= (int) (getWidth_of_box() + 1);
				y_cordinate -= (int) (getWidth_of_box() + 2);
				incrementSteps();
			}
			else if ((getSteps_moved() > 19) && (getSteps_moved() <= 24))
			{
				movePlayerLeftSideWays();
				incrementSteps();
			}
			else if ((getSteps_moved() >= 25) && (getSteps_moved() <= 26))
			{
				movePlayerUpwards();
				incrementSteps();
			}
			
			// move thru green
			else if (getSteps_moved() == 27)
			{
				movePlayerRightSideWays();
				incrementSteps();
			}
			else if ((getSteps_moved() > 27) && (getSteps_moved() <= 31))
			{
				movePlayerRightSideWays();
				incrementSteps();
			}
			
			// go to yellow
			else if (getSteps_moved() == 32)
			{
				x_cordinate += (int) (getWidth_of_box() + 1);
				y_cordinate -= (int) (getWidth_of_box()) + 1;
				incrementSteps();
			}
			else if ((getSteps_moved() > 32) && (getSteps_moved() <= 37))
			{
				movePlayerUpwards();
				incrementSteps();
			}
			else if ((getSteps_moved() >= 38) && (getSteps_moved() <= 39))
			{
				movePlayerRightSideWays();
				incrementSteps();
			}
			
			// move thru yellow
			else if ((getSteps_moved() >= 40) && (getSteps_moved() <= 44))
			{
				movePlayerDownWards();
				incrementSteps();
			}
			
			// go to blue
			else if (getSteps_moved() == 45)
			{
				x_cordinate += (int) (getWidth_of_box()) + 2;
				y_cordinate += (int) getWidth_of_box();
				incrementSteps();
			}
			else if ((getSteps_moved() >= 46) && (getSteps_moved() <= 50))
			{
				movePlayerRightSideWays();
				incrementSteps();
			}
			else if ((getSteps_moved() >= 51) && (getSteps_moved() < 52))
			{
				movePlayerDownWards();
				incrementSteps();
			}
			else if ((getSteps_moved() >= 52) && (getSteps_moved() <= 57))
			{
				movePlayerLeftSideWays();
				incrementSteps();
				
				if (getSteps_moved() == 58)
				{
					finished = true;
				}
			}
		}
	}
	
	// THIS CREATES A NEW COPY OF THE GREEN PLAYER
	@Override
	public Object clone() throws CloneNotSupportedException
	{
		return new GreenPlayer(this);
	}
	
}
