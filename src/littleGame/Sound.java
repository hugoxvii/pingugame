package littleGame;

import java.io.*;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Sound implements Runnable {

	protected static Clip clip;
	protected static long time=0;
	public static boolean change =false; 
	public static float volume= 0.0f;
	protected Display disp;
	
public Sound(Display d){
	this.disp = d;
}
	
	public void main(String[] args) {
		// TODO Auto-generated method stub
		File Music = new File ("Song1.WAV");
		music(Music);


		

	}
	  public void music(File Music) {
	      	float a = disp.volume.floatValue();
		  
	        try {
	        	if(System.nanoTime()>=time){
	        		System.nanoTime();
	            clip = AudioSystem.getClip();
	            clip.open(AudioSystem.getAudioInputStream(Music));
	            clip.start();
	            time = System.nanoTime()+ clip.getMicrosecondLength()*1000;
	        	}
	        	FloatControl gainControl = 
	    			    (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
	        	
	        	gainControl.setValue(a);
	        	if (disp.menuMode) Thread.sleep(1000);
	        	else Thread.sleep(5000);
	        	gainControl.setValue(a);
	        
	        	   	
	        		
	        		       	System.out.println(a);

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

