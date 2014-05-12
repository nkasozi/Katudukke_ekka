package Players;

//~--- non-JDK imports --------------------------------------------------------

import javax.xml.bind.annotation.XmlRootElement;

import org.newdawn.slick.Color;

@XmlRootElement(name = "YellowPlayer")
@SuppressWarnings("serial")
public class YellowPlayer extends Player
{
	
	// constructor
	public YellowPlayer(int button_num)
	{
		super();
		setColorToYellow();
		setInit_button_number("Y");
		setStarting_x_cordinate(getWidth_of_box() * 6 + 1); // 128
		setStarting_y_cordinate(getWidth_of_box() * 13 + 3); // 57
		
		// initial position of buttons
		int x_init = (int) (0.102 * getScreenWidth());
		int y_init = (int) (0.7046153846153846 * getScreenHeight());
		
		switch (button_num)
		{
			case 1:
				setX_cordinate((int) x_init);
				setY_cordinate((int) y_init);
				
				setInitial_x(getX_cordinate());
				setInitial_y(getY_cordinate());
				setButton_number("Y");
				
				break;
			
			case 2:
				x_cordinate = x_init + (int) ((getWidth_of_box() * 2) - (int) ((0.9933774834437086 / 100) * getScreenWidth()));
				y_cordinate = y_init;
				setButton_number("Y");
				setInitial_x(getX_cordinate());
				setInitial_y(getY_cordinate());
				
				break;
			
			case 3:
				x_cordinate = x_init;
				y_cordinate = y_init + (int) ((getWidth_of_box() * 2) - (int) ((0.9933774834437086 / 100) * getScreenHeight()));
				setButton_number("Y");
				setInitial_x(getX_cordinate());
				setInitial_y(getY_cordinate());
				
				break;
			
			case 4:
				x_cordinate = x_init + (int) ((getWidth_of_box() * 2) - (int) ((0.9933774834437086 / 100) * getScreenWidth()));
				y_cordinate = y_init + (int) ((getWidth_of_box() * 2) - (int) ((0.9933774834437086 / 100) * getScreenHeight()));
				setButton_number("Y");
				setInitial_x(getX_cordinate());
				setInitial_y(getY_cordinate());
				
				break;
			
			default:
				
				// println("YOU CAN ONLY HAVE 4 BUTTONS");
		}
	}
	
	// COPY CONSTRUCTOR
	public YellowPlayer(YellowPlayer yellowPlayer)
	{
		super(yellowPlayer);
	}
	
	// sets color to green
	private void setColorToYellow()
	{
		PLAYER_COLOR = Color.yellow;
	}
	
	// moves red player forward 1 step
	@Override
	public void movePlayerForward()
	{
		if (has_not_been_drawn)
		{
			
			// going thru blue
			if ((getSteps_moved() >= 0) && (getSteps_moved() <= 5))
			{
				movePlayerUpwards();
				incrementSteps();
			}
			
			// going to red
			else if (getSteps_moved() == 6)
			{
				x_cordinate -= (int) getWidth_of_box() + 1;
				y_cordinate -= (int) getWidth_of_box() + 2;
				incrementSteps();
			}
			else if ((getSteps_moved() >= 7) && (getSteps_moved() <= 11))
			{
				movePlayerLeftSideWays();
				incrementSteps();
			}
			else if (((getSteps_moved() > 11) && (getSteps_moved() < 14)))
			{
				movePlayerUpwards();
				incrementSteps();
			}
			
			// at red
			// go to green
			else if ((getSteps_moved() >= 14) && (getSteps_moved() <= 18))
			{
				movePlayerRightSideWays();
				incrementSteps();
			}
			else if (getSteps_moved() == 19)
			{
				x_cordinate += (getWidth_of_box()) + 1;
				y_cordinate -= (getWidth_of_box()) + 1;
				incrementSteps();
			}
			else if ((getSteps_moved() > 19) && (getSteps_moved() <= 24))
			{
				movePlayerUpwards();
				incrementSteps();
			}
			else if ((getSteps_moved() >= 25) && (getSteps_moved() <= 26))
			{
				movePlayerRightSideWays();
				incrementSteps();
			}
			
			// move thru green
			else if (getSteps_moved() == 27)
			{
				movePlayerDownWards();
				incrementSteps();
			}
			else if ((getSteps_moved() > 27) && (getSteps_moved() <= 31))
			{
				movePlayerDownWards();
				incrementSteps();
			}
			
			// go to yellow
			else if (getSteps_moved() == 32)
			{
				x_cordinate += (int) (getWidth_of_box()) + 2;
				y_cordinate += (int) (getWidth_of_box()) + 1;
				incrementSteps();
			}
			else if ((getSteps_moved() > 32) && (getSteps_moved() <= 37))
			{
				movePlayerRightSideWays();
				incrementSteps();
			}
			else if ((getSteps_moved() >= 38) && (getSteps_moved() <= 39))
			{
				movePlayerDownWards();
				incrementSteps();
			}
			
			// move thru yellow
			else if ((getSteps_moved() >= 40) && (getSteps_moved() <= 44))
			{
				movePlayerLeftSideWays();
				incrementSteps();
			}
			
			// go to blue
			else if (getSteps_moved() == 45)
			{
				x_cordinate -= (int) (getWidth_of_box()) + 2;
				y_cordinate += (int) getWidth_of_box() + 2;
				incrementSteps();
			}
			else if ((getSteps_moved() >= 46) && (getSteps_moved() <= 50))
			{
				movePlayerDownWards();
				incrementSteps();
			}
			else if ((getSteps_moved() >= 51) && (getSteps_moved() < 52))
			{
				movePlayerLeftSideWays();
				incrementSteps();
			}
			else if ((getSteps_moved() >= 52) && (getSteps_moved() <= 57))
			{
				movePlayerUpwards();
				incrementSteps();
				
				if (getSteps_moved() == 58)
				{
					finished = true;
				}
			}
		}
	}
	
	// RETURNS A NEW COPY OF THE YELLOW PLAYER
	@Override
	public Object clone() throws CloneNotSupportedException
	{
		return new YellowPlayer(this);
	}
}

// ~ Formatted by Jindent --- http://www.jindent.com
