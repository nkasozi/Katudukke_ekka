package Players;

import javax.xml.bind.annotation.XmlRootElement;

import org.newdawn.slick.Color;

@XmlRootElement(name = "BluePlayer")
@SuppressWarnings("serial")
public class BluePlayer extends Player
{
	
	// CONSTRUCTOR
	public BluePlayer(int button_num)
	{
		super();
		
		// SET THE PLAYER COLOR
		PLAYER_COLOR = Color.blue;
		setInit_button_number("B");
		
		// SET THE POSITION THAT THE BUTTONS TAKE WHEN THEY EXIT THE SCHOOL PAD
		setStarting_x_cordinate((int) (getWidth_of_box())); // 20
		setStarting_y_cordinate((int) (getWidth_of_box() * 6 + 1)); // 128
		
		// INITIAL POSITION OF BUTTONS WHILE IN SCHOOL PAD
		int x_init = (int) (0.102 * getScreenWidth());
		int y_init = (int) (0.1076923076923077 * getScreenHeight());
		
		// BASING ON THE BUTTON ID SET THE POSITION OF THE BUTTON IN THE SCHOOL
		// PAD
		switch (button_num)
		{
			case 1:
				setX_cordinate((int) x_init);
				setY_cordinate((int) y_init);
				
				setButton_number("1");
				setInitial_x(getX_cordinate());
				setInitial_y(getY_cordinate());
				break;
			
			case 2:
				x_cordinate = x_init + (int) ((getWidth_of_box() * 2) - (int) ((0.9933774834437086 / 100) * getScreenWidth()));
				y_cordinate = y_init;
				setButton_number("2");
				setInitial_x(getX_cordinate());
				setInitial_y(getY_cordinate());
				break;
			
			case 3:
				x_cordinate = x_init;
				y_cordinate = y_init + (int) ((getWidth_of_box() * 2) - (int) ((0.9933774834437086 / 100) * getScreenHeight()));
				setButton_number("3");
				setInitial_x(getX_cordinate());
				setInitial_y(getY_cordinate());
				break;
			
			case 4:
				x_cordinate = x_init + (int) ((getWidth_of_box() * 2) - (int) ((0.9933774834437086 / 100) * getScreenWidth()));
				y_cordinate = y_init + (int) ((getWidth_of_box() * 2) - (int) ((0.9933774834437086 / 100) * getScreenHeight()));
				setButton_number("4");
				setInitial_x(getX_cordinate());
				setInitial_y(getY_cordinate());
				
				break;
			
			default:
				
				// ("YOU CAN ONLY HAVE 4 BUTTONS");
		}
	}
	
	// COPY CONSTRUCTOR
	public BluePlayer(BluePlayer bluePlayer)
	{
		super(bluePlayer);
	}
	
	// THIS MOVES A BLUE PLAYER ONE STEP FORWARD
	@Override
	public void movePlayerForward()
	{
		if (isHas_not_been_drawn())
		{
			
			// going thru blue
			if ((getSteps_moved() >= 0) && (getSteps_moved() <= 5))
			{
				movePlayerRightSideWays();
				incrementSteps();
			}
			
			// going to red
			else if (getSteps_moved() == 6)
			{
				setX_cordinate((int) (getWidth_of_box()) * 6);
				setY_cordinate((int) (getWidth_of_box()) * 5);
				incrementSteps();
			}
			else if ((getSteps_moved() >= 7) && (getSteps_moved() <= 11))
			{
				movePlayerUpwards();
				incrementSteps();
			}
			else if (((getSteps_moved() > 11) && (getSteps_moved() < 13)))
			{
				movePlayerRightSideWays();
				incrementSteps();
			}
			
			// at red
			else if (getSteps_moved() == 13)
			{
				setX_cordinate((int) (getWidth_of_box() * 8 + 1));
				incrementSteps();
			}
			
			// go to green
			else if ((getSteps_moved() > 13) && (getSteps_moved() <= 18))
			{
				movePlayerDownWards();
				incrementSteps();
			}
			
			// correct box position
			else if (getSteps_moved() == 19)
			{
				setX_cordinate((int) (getWidth_of_box() * 9 + 2));
				setY_cordinate((int) (getWidth_of_box()) * 6 + 1);
				incrementSteps();
			}
			else if ((getSteps_moved() > 19) && (getSteps_moved() <= 24))
			{
				if ((getSteps_moved() == 23))
				{
					setX_cordinate(getX_cordinate() + 2);
				}
				movePlayerRightSideWays();
				incrementSteps();
			}
			else if ((getSteps_moved() >= 25) && (getSteps_moved() <= 26))
			{
				movePlayerDownWards();
				incrementSteps();
			}
			
			// move thru green
			else if (getSteps_moved() == 27)
			{
				
				setY_cordinate((int) (getWidth_of_box() * 8 + 1));
				movePlayerLeftSideWays();
				incrementSteps();
			}
			else if ((getSteps_moved() > 27) && (getSteps_moved() <= 31))
			{
				if ((getSteps_moved() == 28))
				{
					setX_cordinate(getX_cordinate() - 2);
				}
				movePlayerLeftSideWays();
				incrementSteps();
			}
			
			// go to yellow
			else if (getSteps_moved() == 32)
			{
				setX_cordinate((int) ((getWidth_of_box() * 8 + 1)));
				setY_cordinate((int) (getY_cordinate() + (getWidth_of_box() + 2)));
				incrementSteps();
			}
			else if ((getSteps_moved() > 32) && (getSteps_moved() <= 37))
			{
				movePlayerDownWards();
				incrementSteps();
			}
			else if ((getSteps_moved() >= 38) && (getSteps_moved() <= 39))
			{
				setY_cordinate(getY_cordinate());
				movePlayerLeftSideWays();
				incrementSteps();
			}
			
			// move thru
			// yellow
			else if ((getSteps_moved() >= 40) && (getSteps_moved() <= 44))
			{
				movePlayerUpwards();
				incrementSteps();
			}
			
			// go to blue
			else if (getSteps_moved() == 45)
			{
				setX_cordinate((int) ((getWidth_of_box() * 5)));
				setY_cordinate((int) (getY_cordinate() - (getWidth_of_box() + 2)));
				incrementSteps();
			}
			else if ((getSteps_moved() >= 46) && (getSteps_moved() <= 50))
			{
				movePlayerLeftSideWays();
				incrementSteps();
			}
			else if ((getSteps_moved() >= 51) && (getSteps_moved() < 52))
			{
				movePlayerUpwards();
				incrementSteps();
			}
			else if ((getSteps_moved() >= 52) && (getSteps_moved() <= 57))
			{
				movePlayerRightSideWays();
				incrementSteps();
				
				if (getSteps_moved() >= 58)
				{
					finished = true;
				}
			}
		}
	}
	
	// THIS RETURNS A NEW COPY OF THE BLUE PLAYER
	@Override
	public Object clone() throws CloneNotSupportedException
	{
		return new BluePlayer(this);
	}
	
}
