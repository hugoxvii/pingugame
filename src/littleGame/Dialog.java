package littleGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;

import javax.swing.ImageIcon;

public class Dialog {
	
	private Image dialogImg, pingImg, kngImg;
	private String[] dialog;
	private String[] options;
	private int nav = 0;
	private int stage = 0;
	private int[] decisions;
	private int conv;
	private Player player;
	protected boolean mode = false;
	
	public Dialog(Player p) {
		String menuPATH = new File ("Images/inventory.png").getAbsolutePath();
		menuPATH = menuPATH.replace("\\", "/");
		dialogImg = new ImageIcon(menuPATH).getImage();
		String avakngPATH = new File ("Images/kng_ava.png").getAbsolutePath();
		avakngPATH = avakngPATH.replace("\\", "/");
		kngImg = new ImageIcon(avakngPATH).getImage();
		String avapingPATH = new File ("Images/Pingu_ava.png").getAbsolutePath();
		avapingPATH = avapingPATH.replace("\\", "/");
		pingImg = new ImageIcon(avapingPATH).getImage();
		
		this.player=p;
		
		dialog = new String [8];
		for(int i=0; i<dialog.length; i++){
			dialog[i]="";
		}
		options= new String[2];
		for(int i=0; i<options.length; i++){
			options[i]="";
		}
	}
	
	public void push(String newline){
		System.out.println("d "+ dialog[7]);
		if(dialog[7]!= ""){
			for(int i=0; i<dialog.length-1; i++){
				dialog[i]=dialog[i+1];
			}
			dialog[7]=newline;
		}
		else{
			boolean done = false;
			for(int i=0; i<dialog.length; i++){
				if(dialog[i]==""&&!done){
					dialog[i]=newline;
					done=true;
				}
			}
		}
	}
	public void navigat(){
		if (nav==0) nav=1;
		else if (nav==1) nav=0;
	}
	
	public void enter(){
		doKNGDialog();
	}
	
	public void closeDialog(){
		mode=false;
		stage=0;
		dialog = new String [8];
		for(int i=0; i<dialog.length; i++){
			dialog[i]="";
		}
		options= new String[2];
		for(int i=0; i<options.length; i++){
			options[i]="";
		}
	}
	public void openDialog(){
		mode=true;
		doKNGDialog();
	}
	
	public void draw(Graphics g){
		//System.out.println(nav);
		
		g.drawImage(dialogImg, 50, 50, null);
		
		g.fillRect(20, 90, 100, 120);
		g.drawImage(kngImg, 25,95,null);
		g.fillRect(380, 140, 100, 120);
		g.drawImage(pingImg, 385, 145, null);
		
		g.setFont(new Font(g.getFont().getFontName(), Font.PLAIN, g.getFont().getSize() +3));
		g.drawString("Kangoroo", 55, 80);
		g.drawString("Pingu", 385, 130);
		g.setFont(new Font(g.getFont().getFontName(), Font.PLAIN, g.getFont().getSize() -3));
		
		//Dialog
		
		g.drawString(dialog[0], 130, 100);
		g.drawString(dialog[1], 130, 120);
		g.drawString(dialog[2], 130, 140);
		g.drawString(dialog[3], 130, 160);
		g.drawString(dialog[4], 130, 180);
		g.drawString(dialog[5], 130, 200);
		g.drawString(dialog[6], 130, 220);
		g.drawString(dialog[7], 130, 240);
		
		//Choices
		if(nav==0){
			g.fillRect(120, 285, 260, 20);
			g.setColor(Color.BLACK);
		}
		g.drawString(options[0], 130, 300);
		g.setColor(Color.white);
		
		if(nav==1){
			g.fillRect(120, 315, 260, 20);
			g.setColor(Color.BLACK);
		}
		g.drawString(options[1], 130, 330);
		g.setColor(Color.BLACK);
		
		}
	
	private void doKNGDialog(){
		if(stage ==0){
			push("Hi Pingu. Havn't seen you in a while!");
			options[0]= "Hi kang. Not much time, can we trade?";
			options[1]= "Hey! Where is everyone else?";
			stage++;
			decisions = new int[6];
		}
		else if(stage ==1){
			push("\t \t \t \t \t \t \t \t \t \t"+options[nav]);
			decisions[stage-1]=nav;
			if(decisions[0]==0) {
				player.initiateTrade();
				closeDialog();
			}
			if(decisions[0]==1){
				push("Oh you havn't heard?");
				options[0]= "I don't actually have much time, let's trade.";
				options[1]= "Heared what?";
				stage++;
			}
			
		}
		else if(stage ==2){
			push("\t \t \t \t \t \t \t \t \t \t"+options[nav]);
			decisions[stage-1]=nav;
			if(decisions[1]==0) {
				player.initiateTrade();
				closeDialog();
			}
			if(decisions[1]==1){
				push("They all left, because of the Octobubbles.");
				options[0]= "Oktobubbles? Sell me some weapons, quick!";
				options[1]= "What are Oktobubbles?";
				stage++;
			}
	
		}
		else if(stage ==3){
			push("\t \t \t \t \t \t \t \t \t \t"+options[nav]);
			decisions[stage-1]=nav;
			if(decisions[2]==0) {
				player.initiateTrade();
				closeDialog();
			}
			if(decisions[2]==1){
				push("Those little agressive creatures everywhere.");
				push("They are rather dangerous.");
				options[0]= "kthx. Can we trade now?";
				options[1]= "Anything else?";
				stage++;
			}
	
		}
		else if(stage ==4){
			push("\t \t \t \t \t \t \t \t \t \t"+options[nav]);
			decisions[stage-1]=nav;
			if(decisions[3]==0) {
				player.initiateTrade();
				closeDialog();
			}
			if(decisions[3]==1){
				push("Well there are rumors about a Tressure.");
				push("It is supposed to be burried under in the southern Fort.");
				options[0]= "loot Fort, got it. Let's trade.";
				options[1]= "Sounds like I should check it out!";
				stage++;
			}
		}
		else if(stage ==5){
			push("\t \t \t \t \t \t \t \t \t \t"+options[nav]);
			decisions[stage-1]=nav;
			if(decisions[4]==0) {
				player.initiateTrade();
				closeDialog();
			}
			if(decisions[4]==1){
				push("Well you allways were a foolish adventurer at heart.");
				push("Take care, my little Friend.");
				options[0]= "Wait I need some supplies!";
				options[1]= "Thanks, Ill be back with the tressure.";
				stage++;
			}
			}
		else if(stage ==6){
				push("\t \t \t \t \t \t \t \t \t \t"+options[nav]);
				decisions[stage-1]=nav;
				if(decisions[5]==0) {
					player.initiateTrade();
					closeDialog();
				}
				if(decisions[5]==1){
					closeDialog();
					
				}
	

	}

}
}
//use \t \t \t \t \t \t \t \t \t \t in from of pinguinputs