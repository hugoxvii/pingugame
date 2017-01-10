package littleGame;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;

import javax.swing.ImageIcon;

public class NPC {
	
	private String NPATH;
	protected Image NImg;
	protected int xLoc, yLoc;
	protected Rectangle NRect;

	public NPC(String PATH, int x, int y){
		this.NPATH = new File (PATH).getAbsolutePath();
		this.xLoc = x;
		this.yLoc = y;
		
		NPATH = NPATH.replace("\\", "/");
		NImg = new ImageIcon(NPATH).getImage();
		NRect = new Rectangle(x,y,20,20);
		
	}
	
	public void draw(Graphics g){
		g.drawImage(NImg, NRect.x, NRect.y, null); 
	}
	public void forcedMove(int dirX, int dirY){
		NRect.x += dirX;
		NRect.y += dirY;
	}
}
