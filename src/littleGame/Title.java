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
		Lines[3] = "Dear Player ";
		Lines[4] = "";
		Lines[5] = "If you are reading this you are one of the first Alpha testers  ";
		Lines[6] = "of 'Yet another Pingugame'.";
		Lines[7] = "As of now the world pollish is not quite at the point we are ";
		Lines[8] = "striving for yet.";
		Lines[9] = "";
		Lines[10] = "Please help us by reporting bugs, mistakes, and other oddities.";
		Lines[11] = "";
		Lines[12] = "The Demoversion you are playing is designed to introduce you to";
		Lines[13] = "most elements of the game. But you can look up commands on your";
		Lines[14] = "own from the main menu by pressing ESC.";
		Lines[15] = "";
		Lines[16] = "Enjoy the game!              ~Radow";
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
		System.out.println(act);
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
				Lines[5] = "There are dangerous holes in the Groudn in the southern Part. ";
				Lines[6] = "Maybe you can find something to close them so noone will fall ";
				Lines[7] = "inside. But take care not to fall yourself.";
				Lines[8] = "";
				Lines[9] = "In the little house right at the beginign of the village there is the local";
				Lines[10] = "Trader. He's a friend of yours so he will exchange things with you for ";
				Lines[11] = "free.";
				Lines[12] = "Be carefull in the warehouse. There is an octobubble on the loose. If  ";
				Lines[13] = "you encounter it you can defend yourself by pressing SPACE while facing ";
				Lines[14] = " it in meleerange.";
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
				Lines[2] = "the float is littered with odd little ponds, rubble and the ruins of what ";
				Lines[3] = "seems to have been some sort of wall long ago.";
				Lines[4] = " ";
				Lines[5] = "Some belive that these constructions are markings left behind by the ";
				Lines[6] = "Creators.";
				Lines[7] = "";
				Lines[8] = "But a bright little penguin as yourself certainly does not belive in this  ";
				Lines[9] = "kind of superstious hogwash!";
				Lines[10] = "Let's rather move on. There is supposed to be an old forgotten fort just  ";
				Lines[11] = "south of here. And you know what they say about forgotten Forts: There is";
				Lines[12] = " allways a hidden treasure somewhere.";
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
				Lines[5] = "To open it you will probably need some kind of key. Maybe the villagers  ";
				Lines[6] = "will be able to help you?";
				Lines[7] = " ";
				Lines[8] = "In order to interact with something  - say a chest -you usually press f.";
				Lines[9] = "";
				Lines[10] = " ";
				Lines[11] = "";
				Lines[12] = "[If you have trouble possitioning the boulders: pressing SHIFT while ";
				Lines[13] = "moving let's you move much slower]";
				Lines[14] = "";
				Lines[15] = "";
				Lines[16] = "";
				Lines[17] = "";
				Lines[18] = "Press t anytime to hide or display this text. ";
				
				break;
			case 4:
				actT=4;
				it=205;
				Lines[0] = "You opend it! Neat.";
				Lines[1] = "";
				Lines[2] = "Looks like there is a wand inside!";
				Lines[4] = "";
				Lines[5] = "Try and equip it by pressing e when you have selected it in your inventory.";
				Lines[6] = "";
				Lines[7] = "If you have if equiped you can zap foes from range by pressing z.";
				Lines[8] = "";
				Lines[9] = "There seems to be whole bunch of Octobubbles ahead. Be carefull.";
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
			case 5:
				actT=5;
				it=205;
				Lines[0] = "Ahh be carefull now...";
				Lines[1] = "";
				Lines[2] = "This Octobubble ahead seems to be much stronger and even more intellingent";
				Lines[4] = "than the others we have encountered.";
				Lines[5] = "";
				Lines[6] = "It might not allow us to pass without a fight";
				Lines[7] = "";
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
			case 6:
				actT=6;
				it=205;
				Lines[0] = "Yay! You did it again!";
				Lines[1] = "";
				Lines[2] = "Now lets see what we can find inside the Fort!";
				Lines[4] = "";
				Lines[5] = "";
				Lines[6] = "";
				Lines[7] = "";
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
			case 7:
				actT=6;
				it=205;
				Lines[0] = "                   Congratulations!";
				Lines[1] = "";
				Lines[2] = "     You found you way to the mysterious dungeons.";
				Lines[4] = "  This demoversion of 'yet another Pingugame' ends here.";
				Lines[5] = "";
				Lines[6] = "Please let us know what you did or didn't like about the game,";
				Lines[7] = "so we can incooperate your input into the full game or further ";
				Lines[8] = "projects.";
				Lines[9] = "";
				Lines[10] = "     I hope you enjoyed the journey this far. ";
				Lines[11] = "";
				Lines[12] = "                                           ~ Radom, Jan 2017";
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
