import java.applet.Applet;
import java.applet.AudioClip;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;

import sun.audio.*;

/**
 * A simple Java sound file example (i.e., Java code to play a sound file).
 * AudioStream and AudioPlayer code comes from a javaworld.com example.
 * @author alvin alexander, devdaily.com.
 */
public class JavaAudioPlaySoundExample
{
	AudioClip eagleSound;
	URL url = null;
	final static File file = new File("C:/Users/Ayam/Documents/RMIT/Sem 3/OOSD/Assignment/EaglesvsSharks/src/eagle.wav");
	//AudioClip eagleSound = Applet.newAudioClip(JavaAudioPlaySoundExample.class.getResource("eagle.wav"));
	public JavaAudioPlaySoundExample()
	{
		try
		{
			url = file.toURI().toURL();
		}
		catch(MalformedURLException e)
		{
			e.printStackTrace();
		}
		eagleSound = Applet.newAudioClip(url);
	}
	
  public static void main(String[] args) 
  throws Exception
  {
	  JavaAudioPlaySoundExample ja = new JavaAudioPlaySoundExample();
	  
	  if(file.exists())
	  {
		  System.out.println("Play now");
		  ja.eagleSound.play();  
		  System.in.read();
		  ja.eagleSound.stop();
	  }
	  else
	  {
		  System.out.println("No such file");
	  }
	  
  }
}