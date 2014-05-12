/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

//~--- non-JDK imports --------------------------------------------------------

import Players.Player;

import org.newdawn.slick.Graphics;

public interface ManagerInterface
{
	
	int	maximum_number_of_players	= 4;
	
	void CreateNewPlayer();
	
	void CreateAllPlayers();
	
	Player GetPlayer(String id);
	
	Player[] GetAllPlayers();
	
	void DrawPlayer(Player player, Graphics g);
	
	boolean ColorHasFinished();
	
	void DrawAllPlayers(Graphics g);
}

// ~ Formatted by Jindent --- http://www.jindent.com
