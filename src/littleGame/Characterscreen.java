package littleGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;

import javax.swing.ImageIcon;

public class Characterscreen {
	
	
	protected boolean mode = false;
	private Image menuImg;
	private Player player;
	
	private int col=0;
	private int row=1;
	
	//temp attributes while leveling
	private int tempStr, tempDex, tempWisdom;
	private int tempSP;
	
	private boolean enter = false;
	private boolean acept = true;
	
	public Characterscreen(Player p) {
		String menuPATH = new File ("Images/inventory.png").getAbsolutePath();
		menuPATH = menuPATH.replace("\\", "/");
		menuImg = new ImageIcon(menuPATH).getImage();
		this.player = p;
		
		this.tempStr = player.strength;
		this.tempDex = player.dexterity;
		this.tempWisdom = player.strength;
		this.tempSP = player.skillpoints;
		
		

	}
	
	public void openMenu(){
	
		enter=false;
		mode = true;
		tempStr = player.strength;
		tempDex = player.dexterity;
		tempWisdom = player.strength;
		tempSP = player.skillpoints;
	
	}
	
	public void closeMenu(){

		mode = false;
		tempStr = player.strength;
		tempDex = player.dexterity;
		tempWisdom = player.strength;
		tempSP = player.skillpoints;
	
	}
	public void enter(){

		if(enter&& acept){
			player.strength = tempStr;
			player.dexterity = tempDex;
			player.wisdom = tempWisdom;
			player.skillpoints = tempSP;
			enter = false;
		}
		else if (enter&& !acept){
			openMenu();
		}
		else{
			enter=true;
		}
	
	}
	
	public void navigate(int dir){
		if(!enter){
		row += dir;
		if(row <1) row=3;
		if(row >3) row=1;
		}
		}
	
	public void increase(int dir){
		if (enter&&acept) acept=false;
		else if (enter&&!acept) acept=true;
		else{
		if (tempSP>0&&dir>0){
			if(row==1) tempStr++;
			if(row==2) tempDex++;
			if(row==3) tempWisdom++;
			tempSP--;
		}
		if (tempSP<player.skillpoints&&dir<0){
			if(row==1&&tempStr>player.strength){
				tempStr--;
				tempSP++;
			}
			if(row==2&&tempDex>player.dexterity){
				tempDex--;
				tempSP++;
			}
			if(row==3&&tempWisdom>player.wisdom){
				tempWisdom--;
				tempSP++;
			}
			
		}
		}
	}
	

public void draw(Graphics g){
	g.drawImage(menuImg, 50, 50, null);
	
	//Mainmenu
	
		g.setFont(new Font(g.getFont().getFontName(), Font.PLAIN, g.getFont().getSize() +5));
		g.drawString("Characterscreen", 100, 100);
		g.setFont(new Font(g.getFont().getFontName(), Font.PLAIN, g.getFont().getSize() -5));
		
		//Attributes
		g.setFont(new Font(g.getFont().getFontName(), Font.PLAIN, g.getFont().getSize() +3));
		g.drawString("Attributes", 100, 130);
		g.setFont(new Font(g.getFont().getFontName(), Font.PLAIN, g.getFont().getSize() -3));
		
		g.drawString("HP:" + player.HP + "/" + player.maxHP, 100, 160);
		if(tempStr>player.strength) g.setColor(Color.green);
		g.drawString("Strength:" + tempStr, 100, 180);
		g.setColor(Color.white);
		if(tempDex>player.dexterity) g.setColor(Color.green);
		g.drawString("Dexterity:"+ tempDex, 100, 200);
		g.setColor(Color.white);
		if(tempWisdom>player.wisdom) g.setColor(Color.green);
		g.drawString("Wisdom:"+ tempWisdom, 100, 220);
		g.setColor(Color.white);
		g.drawString("", 100, 240);
		
		//Combat
		g.setFont(new Font(g.getFont().getFontName(), Font.PLAIN, g.getFont().getSize() +3));
		g.drawString("Combat", 200, 130);
		g.setFont(new Font(g.getFont().getFontName(), Font.PLAIN, g.getFont().getSize() -3));
		
		if(tempStr>player.strength) g.setColor(Color.green);
		g.drawString("Melee Dmg:" + 10 + tempStr/10, 200, 160);
		g.setColor(Color.white);
		if(tempDex>player.dexterity) g.setColor(Color.green);
		g.drawString("Melee Critchance:" + tempDex + "%", 200, 180);
		g.setColor(Color.white);
		if(tempStr>player.strength) g.setColor(Color.green);
		g.drawString("Melee CritDmg: +" + tempStr, 200, 200);
		g.setColor(Color.white);
		
		g.drawString("", 200, 220);
		g.setColor(Color.white);
		if(tempWisdom>player.wisdom) g.setColor(Color.green);
		if(player.canZap)g.drawString("Zap Dmg:" + (player.zapDmg + tempWisdom/5), 200, 240);
		else {
			g.setColor(Color.white);
			g.drawString("Zap -- not learned yet", 200, 240);
		}
		g.setColor(Color.white);
		
		//Statistics
		g.setFont(new Font(g.getFont().getFontName(), Font.PLAIN, g.getFont().getSize() +3));
		g.drawString("Statistics", 350, 130);
		g.setFont(new Font(g.getFont().getFontName(), Font.PLAIN, g.getFont().getSize() -3));
		
		g.drawString("XP:" + player.xP, 350, 160);
		g.drawString("LVL:" + player.lVL, 350, 180);
		g.drawString("Steps:" + player.Steps, 350, 200);
		g.drawString("Skillpoints:" +tempSP + "/"+ player.skillpoints, 350, 220);
		g.drawString("", 350, 240);
		
		//Selected
		if(col==0)g.drawRect(85, 150+20*row, 10, 10);
		if(col==0&&player.skillpoints>0)g.drawString("+" , 87, 160+20*row);
		
		//making changes
		if(enter){
		g.setColor(Color.white);
		g.fillRect(150, 150, 200, 100);
		g.setColor(Color.black);
		g.fillRect(155, 155, 190, 90);
		g.setColor(Color.white);
		g.drawString("Do you want to make your " , 180, 185);
		g.drawString("changes permanent?" , 190, 200);
		
		if (acept){
			g.setColor(Color.white);
			g.fillRect(180, 220, 60, 20);
			g.drawString("Cancle" , 270, 235);
			g.setColor(Color.black);
			g.drawString("Accept" , 190,235);
		}
		else{
			g.setColor(Color.white);
			g.fillRect(260, 220, 60, 20);
			g.drawString("Accept" , 190,235);
			g.setColor(Color.black);
			g.drawString("Cancle" , 270, 235);
		}
		
		
		}
		
	}
	
}