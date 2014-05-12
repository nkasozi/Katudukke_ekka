/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

//~--- non-JDK imports --------------------------------------------------------

import org.newdawn.slick.Graphics;

public interface PlayerInterface
{
	
	// moves player one step forward
	public void movePlayerForward();
	
	// moves player sideways right one step
	public void movePlayerRightSideWays();
	
	// moves player left sideways one step
	public void movePlayerLeftSideWays();
	
	// moves player down one step
	public void movePlayerDownWards();
	
	// moves player up one step
	public void movePlayerUpwards();
	
	// draws player onto screen
	public void drawPlayer(Graphics g);
}

// ~ Formatted by Jindent --- http://www.jindent.com
