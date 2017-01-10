package littleGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;

import javax.swing.ImageIcon;

public class Title {
	
	private Image Titlescreen;
	private Image Panorama;
	public int it=205;
	public int delay=0;
	private String[] Lines = new String[19];
	private boolean blink = true;
	public int blinkdelay=40;
	public int actT =0;
	
	public Title(){
		String TitlePATH = new File ("Images/inventory.png").getAbsolutePath();
		TitlePATH = TitlePATH.replace("\\", "/");
		Titlescreen = new ImageIcon(TitlePATH).getImage();
		
		String panoPATH = new File ("Images/Title_draft.png").getAbsolutePath();
		panoPATH = panoPATH.replace("\\", "/");
		Panorama = new ImageIcon(panoPATH).getImage();
		
		//Resources
		
		
		
		Lines[0] = "";
		Lines[1] = "";
		Lines[2] = "";
		Lines[3] = "Welcometo Version 0.8 ";
		Lines[4] = "";
		Lines[5] = "\"For years the the little Penguin and the enormous Oktobubble had   ";
		Lines[6] = "lived peacfully door to door on the big ice floe.";
		Lines[7] = "But because of bloodthirsty humans and their ever increasing demand  ";
		Lines[8] = "for complex combat systems this time of innocence is now Over!\"";
		Lines[9] = "";
		Lines[10] = "";
		Lines[11] = "You can now fight the Oktobubble. Press SPACE to attack.";
		Lines[12] = "We don't have enought funds for animations yet, but belive me:";
		Lines[13] = "you really do attack.";
		Lines[14] = "";
		Lines[15] = "";
		Lines[16] = "Good Luck little Pinguin!";
		Lines[17] = "";
		Lines[18] = "Press t anytime to hide or display this text. ";

	}
	
	public void draw(Graphics g){
		g.drawImage(Titlescreen, 50, 50, null); 
		
		
		for(int i=0;i<19; i++){
			g.setColor(Color.WHITE);
			if(i==18){
				if(blinkdelay<100){
					if((65+ 15*i+it)<340) g.drawString(Lines[i] , 65, (70+ 15*i+it));
					if (blinkdelay==0) blinkdelay=200;
				}
				blinkdelay--;
			}
			else if((65+ 15*i+it)<340) g.drawString(Lines[i] , 65, (70+ 15*i+it));

			if(10+it<232&&actT==0) g.drawImage(Panorama, 100, (it-18), null);
			if(delay<0){
			if(it>0) it--;
			delay = 70;
			}else delay--;
		}
		
		
	}
	public void setTutStage(int act){
		switch (act){
			default:
				break;
			case 1:
				actT=1;
				it=205;
				Lines[0] = "You have found the Village!";
				Lines[1] = "";
				Lines[2] = "In the Village there is much to explore, but first you must get inside.";
				Lines[3] = "Theese doors are only made from Wood. Try kicking them in with 'k'.";
				Lines[4] = " ";
				Lines[5] = "There are dangerous holes in the Groudn in the southern Part. Maybe you";
				Lines[6] = "can find something to close them so noone will fall inside. But take care";
				Lines[7] = "not to fall yourself.";
				Lines[8] = "";
				Lines[9] = "In the little house right at the beginign of the village there is the local";
				Lines[10] = "Trader. He's a friend of yours so he will exchange things with you for free.";
				Lines[11] = "";
				Lines[12] = "Be carefull in the warehouse. There is an octobubble on the loose. If you ";
				Lines[13] = "encounter it you can defend yourself by pressing SPACE while facing it in ";
				Lines[14] = "meleerange.";
				Lines[15] = "";
				Lines[16] = "";
				Lines[17] = "";
				Lines[18] = "Press t anytime to hide or display this text. ";
				
				break;
			case 2:
				actT=2;
				it=205;
				Lines[0] = "This is a weired place.";
				Lines[1] = "";
				Lines[2] = "the float is littered with odd little ponds, rubble and the ruins of what seems";
				Lines[3] = "to have been some sort of wall long ago.";
				Lines[4] = " ";
				Lines[5] = "Some belive that these constructions are markings left behind by the Creators.";
				Lines[6] = "";
				Lines[7] = "But a bright little penguin as yourself certainly does not belive in this kind ";
				Lines[8] = "of superstious hogwash!";
				Lines[9] = "";
				Lines[10] = "Let's rather move on. There is supposed to be an old forgotten fort just south of ";
				Lines[11] = "here. And you know what they say about forgotten Forts: There is allways a hidden";
				Lines[12] = "treasure somewhere.";
				Lines[13] = "";
				Lines[14] = "";
				Lines[15] = "";
				Lines[16] = "";
				Lines[17] = "";
				Lines[18] = "Press t anytime to hide or display this text. ";
				
				
				break;
			case 3:
				actT=3;
				it=205;
				Lines[0] = "There we are the forgotten Fort!";
				Lines[1] = "";
				Lines[2] = "See if you can find the tresure.";
				Lines[4] = " ";
				Lines[5] = "To open it you will probably need some kind of key. Maybe the villagers will be able to ";
				Lines[6] = "help you?";
				Lines[7] = " ";
				Lines[8] = "In order to interact with something  - say a chest -you usually press f.";
				Lines[9] = "";
				Lines[10] = " ";
				Lines[11] = "";
				Lines[12] = "";
				Lines[13] = "";
				Lines[14] = "";
				Lines[15] = "";
				Lines[16] = "";
				Lines[17] = "";
				Lines[18] = "Press t anytime to hide or display this text. ";
				
				break;
			case 4:
				actT=3;
				it=205;
				Lines[0] = "Uh carefull now!";
				Lines[1] = "There seems to be a whole bunch of Octobubbles ahead.";
				Lines[2] = "";
				Lines[4] = "";
				Lines[5] = "Try and equip the Wand you found in the Fort.";
				Lines[6] = "";
				Lines[7] = "If you have if equiped you can zap the octobubbles from range by pressing z.";
				Lines[8] = "";
				Lines[9] = "";
				Lines[10] = " ";
				Lines[11] = "";
				Lines[12] = "";
				Lines[13] = "";
				Lines[14] = "";
				Lines[15] = "";
				Lines[16] = "";
				Lines[17] = "";
				Lines[18] = "Press t anytime to hide or display this text. ";
				
				break;
		
		}
	}
}
