package Sound;

public class SoundPlayer extends Thread implements Runnable
{
	
	final Sound	mp3;
	
	public SoundPlayer(Sound mp3)
	{
		this.mp3 = mp3;
	}
	
	@Override
	public void run()
	{
		mp3.play();
		
	}
	
	public void StopThread()
	{
		mp3.Stop();
	}
}
