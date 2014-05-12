package Mementos;

//~--- non-JDK imports --------------------------------------------------------

import javax.xml.bind.annotation.XmlRootElement;

import Players.BluePlayer;
import Players.GreenPlayer;
import Players.RedPlayer;
import Players.YellowPlayer;

@XmlRootElement(name = "Memento")
public final class Memento
{
	
	private BluePlayer[]	blue_players;
	private RedPlayer[]		red_players;
	private GreenPlayer[]	green_players;
	private YellowPlayer[]	yellow_players;
	
	public Memento(BluePlayer[] blue_players, RedPlayer[] red_players, GreenPlayer[] green_players, YellowPlayer[] yellow_players)
	{
		setBlue_players(blue_players);
		setGreen_players(green_players);
		setRed_players(red_players);
		setYellow_players(yellow_players);
	}
	
	/**
	 * @return the blue_players
	 */
	public BluePlayer[] getBlue_players()
	{
		return blue_players;
	}
	
	/**
	 * @param blue_players
	 *            the blue_players to set
	 */
	public void setBlue_players(BluePlayer[] blue_players)
	{
		this.blue_players = blue_players;
	}
	
	/**
	 * @return the red_players
	 */
	public RedPlayer[] getRed_players()
	{
		return red_players;
	}
	
	/**
	 * @param red_players
	 *            the red_players to set
	 */
	public void setRed_players(RedPlayer[] red_players)
	{
		this.red_players = red_players;
	}
	
	/**
	 * @return the green_players
	 */
	public GreenPlayer[] getGreen_players()
	{
		return green_players;
	}
	
	/**
	 * @param green_players
	 *            the green_players to set
	 */
	public void setGreen_players(GreenPlayer[] green_players)
	{
		this.green_players = green_players;
	}
	
	/**
	 * @return the yellow_players
	 */
	public YellowPlayer[] getYellow_players()
	{
		return yellow_players;
	}
	
	/**
	 * @param yellow_players
	 *            the yellow_players to set
	 */
	public void setYellow_players(YellowPlayer[] yellow_players)
	{
		this.yellow_players = yellow_players;
	}
}

// ~ Formatted by Jindent --- http://www.jindent.com
