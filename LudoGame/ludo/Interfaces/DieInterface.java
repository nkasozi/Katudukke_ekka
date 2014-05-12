package Interfaces;

public interface DieInterface
{
	
	// returns value from rolled dice
	public abstract int rollDice();
	
	/**
	 * @return the value_from_dice
	 */
	public abstract int getValue_from_dice();
	
	/**
	 * @param value_from_dice
	 *            the value_from_dice to set
	 */
	public abstract void setValue_from_dice(int value_from_dice);
	
}