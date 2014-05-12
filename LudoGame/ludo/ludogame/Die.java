package ludogame;

import java.util.Random;

import Interfaces.DieInterface;

public class Die implements DieInterface
{
	
	// integers
	private int		value_from_dice		= 0;
	private int		starting_num		= 1;
	private Random	dieRoller			= null;
	private int		ending_num			= 6;
	
	// booleans we need
	private boolean	bias_dice			= false;
	private boolean	was_die_rolled		= false;
	
	// floats
	private int		num_4_rand_function	= 7;
	
	// constructor
	public Die()
	{
		dieRoller = new Random(0);
	}
	
	// returns value from rolled dice
	@Override
	public int rollDice()
	{
		return getDieRoller().nextInt(ending_num) + 1;
	}
	
	// helps to ensure u get a certain value
	public int biasDice(int number_u_want)
	{
		setValue_from_dice(number_u_want);
		setBias_dice(true);
		
		return number_u_want;
	}
	
	// returns value from dice
	public int getDiceValue()
	{
		return getValue_from_dice();
	}
	
	// sets boolean was_die_rolled
	public void setWasDieRolled(boolean m)
	{
		setWas_die_rolled(m);
	}
	
	// returns the value of the die
	@Override
	public int getValue_from_dice()
	{
		return value_from_dice;
	}
	
	// sets the value of the die
	@Override
	public void setValue_from_dice(int value_from_dice)
	{
		this.value_from_dice = value_from_dice;
	}
	
	/**
	 * @return the starting_num
	 */
	public int getStarting_num()
	{
		return starting_num;
	}
	
	/**
	 * @param starting_num
	 *            the starting_num to set
	 */
	public void setStarting_num(int starting_num)
	{
		this.starting_num = starting_num;
	}
	
	/**
	 * @return the bias_dice
	 */
	public boolean isBias_dice()
	{
		return bias_dice;
	}
	
	/**
	 * @param bias_dice
	 *            the bias_dice to set
	 */
	public void setBias_dice(boolean bias_dice)
	{
		this.bias_dice = bias_dice;
	}
	
	/**
	 * @return the was_die_rolled
	 */
	public boolean isWas_die_rolled()
	{
		return was_die_rolled;
	}
	
	/**
	 * @param was_die_rolled
	 *            the was_die_rolled to set
	 */
	public void setWas_die_rolled(boolean was_die_rolled)
	{
		this.was_die_rolled = was_die_rolled;
	}
	
	/**
	 * @return the num_4_rand_function
	 */
	public int getNum_4_rand_function()
	{
		return num_4_rand_function;
	}
	
	/**
	 * @param num_4_rand_function
	 *            the num_4_rand_function to set
	 */
	public void setNum_4_rand_function(int num_4_rand_function)
	{
		this.num_4_rand_function = num_4_rand_function;
	}
	
	/**
	 * @return the dieRoller
	 */
	public Random getDieRoller()
	{
		return dieRoller;
	}
	
	/**
	 * @param dieRoller
	 *            the dieRoller to set
	 */
	public void setDieRoller(Random dieRoller)
	{
		this.dieRoller = dieRoller;
	}
	
}
