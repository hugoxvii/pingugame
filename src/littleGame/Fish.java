package littleGame;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;

import javax.swing.ImageIcon;

public class Fish {

	public Rectangle objectRect;
	private Image objectImg;
	private Image objectdelImg;
	protected boolean status = true;

	public Fish(int x, int y){
		String objectPATH = new File ("Images/fish.png").getAbsolutePath();
		objectPATH = objectPATH.replace("\\", "/");
		objectImg = new ImageIcon(objectPATH).getImage();
		String objectdelPATH = new File ("Images/ghost.png").getAbsolutePath();
		objectdelPATH = objectdelPATH.replace("\\", "/");
		objectdelImg = new ImageIcon(objectdelPATH).getImage();
		objectRect = new Rectangle (x, y, 20, 20);
	}
	
	public void draw(Graphics g){
		g.drawImage(objectImg, objectRect.x, objectRect.y, null); 
	}
	public void delFish(){
		objectRect = new Rectangle (0,0,0,0);
		status = false;
	}
	public void move(int dirX, int dirY){
		objectRect.x += dirX;
		objectRect.y += dirY;
	}
}
