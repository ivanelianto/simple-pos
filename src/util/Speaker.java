package util;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class Speaker
{
	private static VoiceManager voiceManager;
	
	private static Voice voice;
	
	static 
	{
		voiceManager = VoiceManager.getInstance();
		voice = voiceManager.getVoice("kevin16");
		voice.setRate(180f);
		voice.allocate();
	}
	
	public static void speak(String words)
	{
		new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				try
				{
					voice.speak(words);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		}).start();
	}
}
