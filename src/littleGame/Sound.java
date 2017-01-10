package littleGame;

import java.io.*;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound implements Runnable {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File Music = new File ("Song1.WAV");
		music(Music);


		

	}
	  public static void music(File Music) {
	      
	        try {
	            Clip clip = AudioSystem.getClip();
	            clip.open(AudioSystem.getAudioInputStream(Music));
	            clip.start();
	 	        Thread.sleep(clip.getMicrosecondLength()/1000);

	        } catch (Exception error) {
	        	 error.printStackTrace();
	        }

	       
	    }
	@Override
	public void run() {
		File Music = new File ("Song1.WAV");
		while(true){
			music(Music);
		}
		
	}
}
