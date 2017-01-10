package littleGame;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Bolt {
	private World world;
	private Monster monster;
	private int defX=-100;
	private int defY=-100;
	private int dur=0;
	private int dir;
	protected Rectangle sRect;
	protected boolean active = false;
	protected Image SHImg;
	protected Image SVImg;
	protected Image SImg;
	
	public Bolt(World w){
		this.world = w;
		sRect = new Rectangle (defX,defY,20,20);
		String SVPATH="Images/lightning-vertical.png";
		String SHPATH="Images/lightning-horizontal.png";
		SVPATH = SVPATH.replace("\\", "/");
		SHPATH = SHPATH.replace("\\", "/");
		SHImg = new ImageIcon(SVPATH).getImage();
		SVImg = new ImageIcon(SHPATH).getImage();
		SImg = SHImg;
	}
	public void fire(int x, int y, int d){
		active = true;
		sRect.x = x;
		sRect.y = y;
		dur=120;
		dir=d;
		if(dir==0) {
			sRect.y+=-5;
			SImg=SHImg;
		}
		if(dir==1) {
			sRect.x+=5;
			SImg=SVImg;
		}
		if(dir==2) {
			sRect.y+=5;
			SImg=SHImg;
		}
		if(dir==3) {
			sRect.x+=-5;
			SImg=SVImg;
		}
		
	}
	public void update(){
		if(dur >0){
			dur--;
			move();
		}
		else vanish();
	}
	public void vanish(){
		active = false;
		sRect.x = defX;
		sRect.y = defY;
	}
	public void move(){
		if(dir==0) {
			sRect.y--;
			sRect.y--;
		}
		if(dir==1) {
			sRect.x++;
			sRect.x++;
		}
		if(dir==2) {
			sRect.y++;
			sRect.y++;
		}
		if(dir==3) {
			sRect.x--;
			sRect.x--;
		}
		
		
		
		int estmSpellTileX = (sRect.x-world.xFromHome)/20;
		int estmSpellTileY = (sRect.y-world.yFromHome)/20;
		
		//Worldcollision
		for(int y=Math.max(0, estmSpellTileY-5); y<Math.min(estmSpellTileY+5,world.arrayY);y++){
			for(int x=Math.max(0,estmSpellTileX-5); x<Math.min(estmSpellTileX+5,world.arrayX);x++){
				
				
				if(world.isSolid[x][y]&& sRect.intersects(world.tiles[x][y])){
					vanish();
				}
			}
		}
		//Monstercollision
		
		}
		public void draw(Graphics g){
			g.drawImage(SImg, sRect.x, sRect.y, null); 
		}
		
	
}
