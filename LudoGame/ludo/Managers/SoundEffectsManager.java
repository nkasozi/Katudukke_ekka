package Managers;

import Interfaces.SoundEffectsInterface;
import Singletons.Singleton;
import Sound.Sound;
import Sound.SoundPlayer;

//handles all sound effects in katuduke ekka
public class SoundEffectsManager implements SoundEffectsInterface
{
	
	private static final String	EAT_PLAYER_SOUND	= Singleton.getResourcesPath() + "eaten.mp3";
	private static final String	GAME_OVER_SOUND		= Singleton.getResourcesPath() + "no_mercy.mp3";
	private static final String	INTRO_SOUND			= Singleton.getResourcesPath() + "intro.mp3";
	private static final String	MOVING_SOUND		= Singleton.getResourcesPath() + "moving.mp3";
	private static final String	BACKGROUND_SOUND	= Singleton.getResourcesPath() + "in_game_music.mp3";
	
	private static SoundPlayer	sound_playing_thread;
	
	public SoundEffectsManager()
	{
		sound_playing_thread = new SoundPlayer(new Sound(MOVING_SOUND));
	}
	
	// PLAYS A SOUND WHEN A PLAYER IS EATEN
	@Override
	public void playSoundForEatingAnotherPlayer()
	{
		try
		{
			Sound mp3 = new Sound(EAT_PLAYER_SOUND);
			
			sound_playing_thread = new SoundPlayer(mp3);
			
			sound_playing_thread.start();
		}
		catch (Exception e)
		{
			Singleton.PrintOut(e.getMessage());
		}
	}
	
	// PLAYS A SOUND WHEN PLAYER MOVES
	@Override
	public void playSoundForMovingPlayer()
	{
		try
		{
			Singleton.PrintOut("MOVING SOUND=" + MOVING_SOUND);
			sound_playing_thread = new SoundPlayer(new Sound(MOVING_SOUND));
			sound_playing_thread.start();
		}
		catch (Exception e)
		{
			Singleton.PrintOut(e.getMessage());
		}
	}
	
	// PLAYS A SOUND WHEN THE GAME IS OVER
	public void playGameOverSound()
	{
		try
		{
			sound_playing_thread = new SoundPlayer(new Sound(GAME_OVER_SOUND));
			sound_playing_thread.start();
		}
		catch (Exception e)
		{
			Singleton.PrintOut(e.getMessage());
		}
	}
	
	public static void playBackGroundMusic()
	{
		
		try
		{
			Sound mp3 = new Sound(BACKGROUND_SOUND);
			
			sound_playing_thread = new SoundPlayer(mp3);
			
			sound_playing_thread.start();
			
		}
		catch (Exception e)
		{
			Singleton.PrintOut(e.getMessage());
		}
	}
	
	// PLAYS THE INTRO MUSIC
	public static void playIntroSound()
	{
		try
		{
			Sound mp3 = new Sound(INTRO_SOUND);
			
			Singleton.PrintOut("name=" + System.getProperty("user.dir"));
			Singleton.PrintOut("sound_path=" + INTRO_SOUND);
			
			sound_playing_thread = new SoundPlayer(mp3);
			
			sound_playing_thread.start();
			
		}
		catch (Exception e)
		{
			Singleton.PrintOut(e.getMessage());
		}
	}
	
	public static void StopPlayingMusic()
	{
		try
		{
			if (sound_playing_thread != null)
			{
				sound_playing_thread.StopThread();
			}
		}
		catch (Exception e)
		{
			Singleton.PrintOut(e.getMessage());
		}
	}
	
}
