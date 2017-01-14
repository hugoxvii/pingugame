package littleGame;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;

import javax.swing.ImageIcon;

public class Menu {
	
	protected int submenu = 0;
	protected int selected = 0;
	protected boolean mode = false;
	private Image menuImg;
	private int volume = 0;
	private Display disp;
	
	public Menu(Display d) {
		String menuPATH = new File ("Images/inventory.png").getAbsolutePath();
		menuPATH = menuPATH.replace("\\", "/");
		menuImg = new ImageIcon(menuPATH).getImage();
		
		this.disp = d;
	}
	
	public void openMenu(){
		selected = 0;
		mode = true;
		disp.menuMode = true;
	}
	
	public void closeMenu(){
		submenu = 0;
		selected = 0;
		mode = false;
		disp.menuMode = false;
	}
	
	public void navigate(int dir){
		selected = selected + dir;
		
	}
	
	public void navigateSound(int dir){
		if (submenu==2){
			if (dir>0)volume++;
			if (dir<0)volume--;
			if(volume >=2) volume =1;
			if(volume <=-4) volume =-3;
			changeVolume();
		}
	}
	
	public void changeVolume(){
		int a=0;
		if(volume==-3)a=-75;
		if(volume==-2)a=-10;
		if(volume==-1)a=-5;
		if(volume==0)a=-0;
		if(volume==1)a=6;
		
		disp.volume.set(a);
		
	}
	
	public void back(){
		if (submenu == 0){
			closeMenu();
		}
		else submenu = 0;
	}
	
	public void enter(){
		if(submenu == 0){
			if (selected%2==0) submenu =1;
		}
		if (selected%2==1||selected%2==-1) submenu = 2;
	}

	public void draw(Graphics g){
		g.drawImage(menuImg, 50, 50, null);
		
		//Mainmenu
		if(submenu == 0){
			g.setFont(new Font(g.getFont().getFontName(), Font.PLAIN, g.getFont().getSize() +5));
			g.drawString("Main menu", 100, 100);
			g.setFont(new Font(g.getFont().getFontName(), Font.PLAIN, g.getFont().getSize() -5));
			g.drawString("Controlls", 100, 150);
			if (selected%2==0) g.drawRect(80, 135, 340, 20);
			g.drawString("Sound", 100, 200);
			if (selected%2==1||selected%2==-1) g.drawRect(80, 185, 340, 20);
		}		
		//Controls
		if (submenu == 1){
			g.setFont(new Font(g.getFont().getFontName(), Font.PLAIN, g.getFont().getSize() +5));
			g.drawString("Controlls", 100, 100);
			g.setFont(new Font(g.getFont().getFontName(), Font.PLAIN, g.getFont().getSize() -5));

			
			g.drawString("UP", 100, 150);
			g.drawString("up", 150, 150);

			g.drawString("LEFT", 100, 170);
			g.drawString("left", 150, 170);
			
			g.drawString("DOWN", 100, 190);
			g.drawString("down", 150, 190);
			
			g.drawString("RIGHT", 100, 210);
			g.drawString("right", 150, 210);
			
			g.drawString("SPACE", 100, 230);
			g.drawString("attack", 150, 230);
			
			g.drawString("z", 100, 250);
			g.drawString("zap", 150, 250);
			
			g.drawString("k", 100, 270);
			g.drawString("kick", 150, 270);
			
			g.drawString("f", 100, 290);
			g.drawString("interact", 150, 290);

			g.drawString("c", 100, 310);
			g.drawString("character screen", 150, 310);
			
			g.drawString("e", 250, 150);
			g.drawString("equip & disequip", 300, 150);

			g.drawString("SHIFT", 250, 170);
			g.drawString("move slowly", 300, 170);

			g.drawString("ESC", 250, 190);
			g.drawString("leave menu", 300, 190);

			g.drawString("		", 200, 210);
			g.drawString("	", 200, 230);
			g.drawString("", 200, 250);
			g.drawString("", 200, 270);
			g.drawString("", 200, 290);
			
		}
		
		//Soundmenu
		if (submenu ==2){
			g.setFont(new Font(g.getFont().getFontName(), Font.PLAIN, g.getFont().getSize() +5));
			g.drawString("Sound", 100, 100);
			g.setFont(new Font(g.getFont().getFontName(), Font.PLAIN, g.getFont().getSize() -5));

			
			//g.drawString("Volume: " + disp.volume, 100, 150);
			g.drawRect(160, 230, 20, 20);
			g.drawRect(190, 210, 20, 40);
			g.drawRect(220, 190, 20, 60);
			g.drawRect(250, 170, 20, 80);
			
			if(volume >-3)g.fillRect(160, 230, 20, 20);
			if(volume >-2)g.fillRect(190, 210, 20, 40);
			if(volume >-1)g.fillRect(220, 190, 20, 60);
			if(volume >0)g.fillRect(250, 170, 20, 80);
			
			
		}
		
	}
}
