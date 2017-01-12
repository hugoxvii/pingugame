package littleGame;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.util.Random;

import javax.swing.ImageIcon;

public class Monster {

	public int id;	
	
	private World world;
	//private Object boulder;
	private Object[] boulders;
	private Fish fishes[];
	protected Player p1;
	private Trader kng;
	private Chest[] chests;
	private Bolt bolt;
	private Monster[] monsters;
	
	protected Inventory Inv;
	
	protected Rectangle playerRect;
	protected Image playerImg, deadImg ,hitImg, aliveImg;
	protected int counter = 0;

	
	protected int x, y, xDirection, yDirection;
	protected int currentDirectionX = 0; 
	protected int currentDirectionY = 0;
	protected int SpawnX, SpawnY;
	public int fishCounter=0;
	protected int HP=100;
	protected int maxHP=150;
	protected int dMG = 1;
	protected boolean alive = true;
	protected int width=20;
	protected int height=20;
	private WorldGenerator WG;
	protected int xp = 50;
	
	protected int hurttime = 0;
	
	
	public Monster(int sX, int sY,/*, World world, Object[] gBoulders, Fish[] gFishes,Chest[] gChests, Bolt b, Player player1, Trader KNG, */NotificationPanel NP, StatusPanel SP, int i){
		//this.world = world;
		//this.boulder = boulder;
		//this.boulders = gBoulders;
		this.SpawnX = sX;
		this.SpawnY = sY;		
		this.id = i;
		//this.fishes = gFishes;
		//this.p1 = player1;
		//this.kng = KNG;
		//this.chests = gChests;
		//this.bolt = b;
		String playerPATH = new File ("Images/monster/monster.png").getAbsolutePath();
		playerPATH = playerPATH.replace("\\", "/");
		aliveImg = new ImageIcon(playerPATH).getImage();
		playerImg = new ImageIcon(playerPATH).getImage();
		String deadPATH = new File ("Images/monster/monster-back.png").getAbsolutePath();
		deadPATH = deadPATH.replace("\\", "/");
		deadImg = new ImageIcon(deadPATH).getImage();
		String hitPATH = new File ("Images/monster/monster_hitshadow.png").getAbsolutePath();
		hitPATH = hitPATH.replace("\\", "/");
		hitImg = new ImageIcon(hitPATH).getImage();
		
		
		playerRect = new Rectangle (SpawnX, SpawnY, 20, 20);
		Inv = new Inventory(NP, SP, false, "Oktobubble",true);
	}
	


public void inform(World w, Monster[] mons, Object[]gboulders, Chest[] gChests, Fish[] gFish, Bolt b, WorldGenerator WorldG, Trader KNG, Player p){
	this.monsters = mons;
	this.world = w;
	this.boulders = gboulders;
	this.chests = gChests;
	this.fishes = gFish;
	this.bolt =b;
	this.WG = WorldG;
	this.kng = KNG;
	this.p1=p;
	

	
}
	
	protected void setXDirection (int d){
		xDirection = d;
	}
	protected void setYDirection (int d){
		yDirection = d;
	}
	public void setCurrentDirectionX (int d){
		currentDirectionX = d;
	}
	public void setCurrentDirectionY (int d){
		currentDirectionY = d;
	}
	
	public void hurt(int dmg){
		HP -= dmg;
		if(alive) playerImg = hitImg;
		hurttime = 5;
		if(HP<=0&&alive) kill();
	}
	public void kill(){
		playerImg = deadImg;
		alive = false;
		p1.gainXP(xp);
	}
	public void heal(int heal){
		if(HP<maxHP){
			HP=Math.min(HP+heal, maxHP);
		}
	}
	
	
	
	public void update (){
		
		if (hurttime<=0&&playerImg==hitImg){
			playerImg= aliveImg;
		}
		if(hurttime>0) hurttime--;

		if (counter<=0){
			Random rand = new Random();
			int randX=rand.nextInt(5)-2;
			int randY=rand.nextInt(5)-2;;
			setCurrentDirectionX(randX);
			setCurrentDirectionY(randY);
			counter=100;
		}
			
		counter--;	
		if(counter%4==0){
			move();
		}
	}
	
	protected void move(){
		xDirection=currentDirectionX;
		yDirection=currentDirectionY;
		
		checkForCollision();
		playerRect.x += xDirection;
		playerRect.y += yDirection;
		//insert gravity if needed
	}
	
	public void forcedMove(int dirX, int dirY){
		playerRect.x += dirX;
		playerRect.y += dirY;
	}
	
	private void checkForCollision(){
		Rectangle testRectX;
		Rectangle testRectY;
		testRectX = new Rectangle(playerRect.x+xDirection, playerRect.y, width, height);
		
		//Making this more efficient
				int estmPlayerTileX = (playerRect.x-world.xFromHome)/20;
				int estmPlayerTileY = (playerRect.y-world.yFromHome)/20;
				
				for(int y=Math.max(0, estmPlayerTileY-5); y<Math.min(estmPlayerTileY+5,world.arrayY);y++){
					for(int x=Math.max(0,estmPlayerTileX-5); x<Math.min(estmPlayerTileX+5,world.arrayX);x++){
				
				//Worldcollision
				if(world.isSolid[x][y]&& testRectX.intersects(world.tiles[x][y])){
					xDirection = 0;
				}
				
				testRectY = new Rectangle(playerRect.x+xDirection, playerRect.y+yDirection, width,height);	
				
				if(world.isSolid[x][y]&& testRectY.intersects(world.tiles[x][y])){
					yDirection = 0;
				}
				//Bouldercollision
				for( int i=0; i<boulders.length; i++){
				if (testRectY.intersects(new Rectangle (boulders[i].objectRect.x, boulders[i].objectRect.y+1,boulders[i].objectRect.width,boulders[i].objectRect.height-2))){
					//Versuche zu schieben
					if (!boulders[i].move(xDirection, 0)){
						//yDirection = 0;
						xDirection = 0;
					}
				}//~ Y Richtung
				if (testRectY.intersects(new Rectangle (boulders[i].objectRect.x+1, boulders[i].objectRect.y,boulders[i].objectRect.width-2,boulders[i].objectRect.height))){
					//Versuche zu schieben
					if (!boulders[i].move(0, yDirection)){
						yDirection = 0;
						//xDirection = 0;
					}
				}
				}
				
				
				//Fishcollision
				for( int i=0; i<fishes.length; i++){
					if(testRectY.intersects(fishes[i].objectRect)){
					fishes[i].delFish();
					fishCounter++;
					heal(10);
					}
				}
				//Playercollision 
				if(testRectY.intersects(p1.playerRect)){
					yDirection = 0;
					xDirection = 0;
					p1.setXDirection(0);
					p1.setYDirection(0);
					p1.hurt(dMG);
				}
				//Monstercollision
				//
				for(int i=0; i<monsters.length; i++){
				if(testRectY.intersects(monsters[i].playerRect)&&i!=id){
					yDirection = 0;
					xDirection = 0;
				}
				}
				//Tradercollision
				if(testRectX.intersects(kng.NRect)){
					xDirection = 0;
					
				}
				if(testRectY.intersects(kng.NRect)){
					yDirection = 0;
					
				}
				//Chestcollision
				for( int i=0; i<chests.length; i++){
					if(testRectX.intersects(chests[i].NRect)){
						xDirection = 0;
					
					}
					if(testRectY.intersects(chests[i].NRect)){
						yDirection = 0;
					
					}
				}
				if(bolt.sRect.intersects(playerRect)){
					hurt(bolt.damage);
					bolt.vanish();
				}
			}
		}
		
	}
	
	public void draw(Graphics g){
		g.drawImage(playerImg, playerRect.x, playerRect.y, null); 
	}
}