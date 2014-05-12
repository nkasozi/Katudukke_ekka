package Sound;

import java.io.BufferedInputStream;
import java.io.FileInputStream;

import javazoom.jl.player.Player;

public class Sound
{
	
	private String	filename;
	private Player	player;
	
	// constructor that takes the name of an MP3 file
	public Sound(String filename)
	{
		this.filename = filename;
	}
	
	// play the MP3 file
	public void play()
	{
		try
		{
			FileInputStream fis = new FileInputStream(filename);
			BufferedInputStream bis = new BufferedInputStream(fis);
			player = new Player(bis);
			player.play();
		}
		catch (Exception e)
		{
			System.out.println("Problem playing file " + filename);
			System.out.println(e);
		}
		
	}
	
	public void Stop()
	{
		try
		{
			if (player != null)
			{
				player.close();
			}
		}
		catch (Exception e)
		{
			// TODO: handle exception
		}
	}
}