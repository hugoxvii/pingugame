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
		
		if (submenu==2){
			if (dir>0)volume++;
			if (dir<0)volume--;
			if(volume >=2) volume =1;
			if(volume <=-4) volume =-3;
			changeVolume();
		}
	}
	public void navigateSound(int dir){
		
			if (dir>0)volume++;
			if (dir<0)volume--;
			if(volume >=2) volume =1;
			if(volume <=-4) volume =-3;
			changeVolume();
		
	}
	
	public void changeVolume(){
		int a=0;
		if(volume==-3)a=-25;
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

			
			g.drawString("w      up", 100, 150);
			g.drawString("a      left", 100, 170);
			g.drawString("s      down", 100, 190);
			g.drawString("d      right", 100, 210);
			g.drawString("SPACE  attack", 100, 230);
			g.drawString("z      zap", 100, 250);
			g.drawString("k      kick", 100, 270);
			g.drawString("f      interact", 100, 290);
			
			g.drawString("e	      equip/disequip", 200, 150);
			g.drawString("SHIFT   move slowly", 200, 170);
			g.drawString("ESC     leave menu", 200, 190);
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
