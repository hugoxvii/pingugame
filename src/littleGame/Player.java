package littleGame;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;

import javax.swing.ImageIcon;

public class Player {

	//UI
	NotificationPanel nP; 
	StatusPanel sP;
	
	protected World world;
	//private Object boulder;
    private Object[] boulders;
    private Chest[] chests;
	private Fish fishes[];
	private Monster m1;
	private Monster[] monsters;
	private WorldGenerator WG;
	private Trader kng;
	Inventory inv;
	
	private int colWidth=14;
	private int colHight=14;
	public Rectangle playerRect;
	private Image playerImg, backImg, frontImg, leftImg, rightImg;
	private Image rightAtImg, leftAtImg, upAtImg, downAtImg;
	private int attacktimer=0;
	
	protected int x, y, xDirection, yDirection;
	protected int currentDirectionX = 0; 
	protected int currentDirectionY = 0;
	
	//Player status variables
	public int fishCounter=0;
	public int HP=250;
	public int Steps=0;
	public int xP = 0;
	public int lVL = 1;
	public int alive = 1;
	
	protected int spawnX =250;
	protected int spawnY =180;
	
	//Interaktionsvariablen
	protected boolean canTrade = false;
	protected boolean canLoot = false;
	protected Monster currentLootTarget;
	protected Trader currentTrader; 
	protected boolean tradeStatus = false;
	protected boolean inventoryMode= false;
	protected boolean looting =false;
	protected boolean canLootChest;
	protected Chest currentLootChestTarget;
	protected boolean chestLooting;
	
	//Fightkrams
	private Bolt bolt;
	private boolean canZap = false;
	private int zapDmg = 0;
	
	//Richtugnskrams	
	protected int facingDir=2;
	protected final int UP = 0;
	protected final int RIGHT = 1;
	protected final int DOWN = 2;
	protected final int LEFT = 3;
	
	protected Dialog dia;
	
	
	
	
	
	public Player(/*World world, Object[] gboulders, Fish[] gFishes, Trader KNG, Chest[] gChests,Bolt b, WorldGenerator WorldG, Inventory INV,*/ NotificationPanel NP, StatusPanel SP){
		
		this.nP=NP;
		this.sP=SP;
		inv = new Inventory(NP, SP, true, "pingu", false);
		
		String playerPATH = new File ("Images/pingu/pingu-front.png").getAbsolutePath();
		playerPATH = playerPATH.replace("\\", "/");
		playerImg = new ImageIcon(playerPATH).getImage();
		
		//front
		String frontPATH = new File ("Images/pingu/pingu-front.png").getAbsolutePath();
		frontPATH = frontPATH.replace("\\", "/");
		frontImg = new ImageIcon(frontPATH).getImage();
		
		//back
		String backPATH = new File ("Images/pingu/pingu-back.png").getAbsolutePath();
		backPATH = backPATH.replace("\\", "/");
		backImg = new ImageIcon(backPATH).getImage();
		
		//Left
		String leftPATH = new File ("Images/pingu/pingu-left.png").getAbsolutePath();
		leftPATH = leftPATH.replace("\\", "/");
		leftImg = new ImageIcon(leftPATH).getImage();
		
		//right
		String rightPATH = new File ("Images/pingu/pingu-right.png").getAbsolutePath(); 
		rightPATH = rightPATH.replace("\\", "/");
		rightImg = new ImageIcon(rightPATH).getImage();
		
		//attacksequenz
		String rightAtPATH = new File ("Images/pingu/pingu-right-hit.gif").getAbsolutePath(); 
		rightAtPATH = rightAtPATH.replace("\\", "/");
		rightAtImg = new ImageIcon(rightAtPATH).getImage();
		
		String leftAtPATH = new File ("Images/pingu/pingu-left-hit.gif").getAbsolutePath(); 
		leftAtPATH = leftAtPATH.replace("\\", "/");
		leftAtImg = new ImageIcon(leftAtPATH).getImage();
		
		String upAtPATH = new File ("Images/pingu/pingu-back-hit.gif").getAbsolutePath(); 
		upAtPATH = upAtPATH.replace("\\", "/");
		upAtImg = new ImageIcon(upAtPATH).getImage();
		
		String downAtPATH = new File ("Images/pingu/pingu-front-hit.gif").getAbsolutePath(); 
		downAtPATH = downAtPATH.replace("\\", "/");
		downAtImg = new ImageIcon(downAtPATH).getImage();
		
		
		
		playerRect = new Rectangle (spawnX, spawnY, colWidth, colHight);
		
			
	}
	public void inform(World w, Monster[] mons, Object[]gboulders, Chest[] gChests, Fish[] gFish, Bolt b, WorldGenerator WorldG, Trader KNG, Player p, Dialog d){
		this.monsters = mons;
		this.world = w;
		this.boulders = gboulders;
		this.chests = gChests;
		this.fishes = gFish;
		this.bolt =b;
		this.WG = WorldG;
		this.kng = KNG;
		this.bolt = b;
		this.dia = d;
		
		
	}
	
	public int[] getStatus(){
		int[] status = new int[6];
		status[0]=HP;
		status[1]=Steps;
		status[2]=fishCounter;
		status[3]=xP;
		status[4]=alive;
		status[5]=lVL;
		return status;
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
	
	public void equip (){
		if (inv.isEquiped()) deequip();
		else{
		int[] effects =inv.equip();
		
		if(effects[0]==1){
			canZap =true;
			zapDmg=effects[1];
		}
		}
	}
	
	public void deequip (){
		int[] effects =inv.deequip();
		
		if(effects[0]==1){
			canZap = false;
			zapDmg = 0;
		}
	}

	public void hurt(int dmg){
		HP=HP-dmg;
	}

	public void levelup(){
		lVL++;
	}
	
	public void checkLvl(){
		/* Lvl 1 = 0xp
		 * Lvl 2 = 100xp
		 * Lvl 3 = 200xp
		 * Lvl 4 = 400xp
		 */
		
		while(xP>=(50* Math.pow(2,lVL))){
			levelup();
		}
	}
	
	public void gainXP(int xp){
		xP = xP+ xp;
		checkLvl();
	}
	
	public void update (){
		if(attacktimer>0)attacktimer--;
		
		if(alive==1) gainXP(world.globalxP);
		world.globalxP=0;
		
		if(HP<=0){
			respawn();
		}				
		move();
	}

	public void respawn(){
		//move the Player
		playerRect.x = spawnX;
		playerRect.y = spawnY;
		//move the world
		int[] a = new int[2];
		a = world.resetPos();
		//move the monster
		//m1.playerRect.x -=a[0];
		//m1.playerRect.y -=a[1];
		WG.moveMonsters(monsters,(-1*a[0]),(-1*a[1]));
		//now Monsters, Boulders & fishes
		kng.NRect.x -=a[0];
		kng.NRect.y -=a[1];
		WG.moveFishes(fishes,(-1*a[0]),(-1*a[1]));
		WG.moveChests(chests,(-1*a[0]),(-1*a[1]));
		WG.moveBoulders(boulders,(-1*a[0]),(-1*a[1]));
		//Reset HP
		HP=250;
		
		if(fishCounter>0){
			fishCounter--;
		}
		else{
			alive =0;
		}
	}
	
	private void move(){
		xDirection=currentDirectionX;
		yDirection=currentDirectionY;
	//System.out.println(attacktimer);

		if(attacktimer>0){
			if (facingDir==RIGHT) playerImg = rightAtImg;
			if (facingDir==LEFT) playerImg = leftAtImg;
			if (facingDir==UP) playerImg = upAtImg;
			if (facingDir==DOWN) playerImg = downAtImg;
		}
		else{
		if(yDirection>0){
			playerImg = frontImg;
			facingDir = DOWN;
			
		}
		else if (yDirection<0){
			playerImg = backImg;
			facingDir = UP;
			

		}
		else if (xDirection<0){
			playerImg = leftImg;
			facingDir = LEFT;
		

		}
		else if (xDirection>0){
			playerImg = rightImg;
			facingDir = RIGHT;
		}
		else{
			if (facingDir==RIGHT)playerImg = rightImg;
			if (facingDir==LEFT)playerImg = leftImg;
			if (facingDir==UP)playerImg = backImg;
			if (facingDir==DOWN)playerImg = frontImg;
		}
		}
		
		checkForCollision();
		checkForMapmovement();
		playerRect.x += xDirection;
		playerRect.y += yDirection;
		

		//insert gravity if needed
	}
	
	public Rectangle getActionRect(){
		int estmPlayerTileX = (playerRect.x-world.xFromHome)/20;
		int estmPlayerTileY = (playerRect.y-world.yFromHome)/20;
		
		int widthmodifier;
		int heightmodifier;
		int startlocationx;
		int startlocationy;
		switch(facingDir){
		 	default:
		 		widthmodifier=0;
		 		heightmodifier=0;
		 		startlocationx=0;
		 		startlocationy=0;
		 		break;
		 	case UP:
		 		widthmodifier=0;
		 		heightmodifier=10;
		 		startlocationx=0;
		 		startlocationy=10;
		 		
		 		break;
		 	case RIGHT:
		 		
		 		widthmodifier=10;
		 		heightmodifier=0;
		 		startlocationx=0;
		 		startlocationy=0;
		 		
		 		break;
		 	case DOWN:
		 		widthmodifier=0;
		 		heightmodifier=10;
		 		startlocationx=0;
		 		startlocationy=0;
		
		 		break;
		 	case LEFT:
		 		widthmodifier=10;
		 		heightmodifier=0;
		 		startlocationx=10;
		 		startlocationy=0;
		
		 		break;
		}
		
		
		Rectangle actionRect = new Rectangle(playerRect.x+xDirection-startlocationx, playerRect.y+yDirection-startlocationy, colWidth+widthmodifier,colHight+heightmodifier);
		return actionRect;
		
	}

	public void kick(){
				
		Rectangle actionRect = getActionRect();
		int estmPlayerTileX = (playerRect.x-world.xFromHome)/20;
		int estmPlayerTileY = (playerRect.y-world.yFromHome)/20;
		
		System.out.println("facing "+ facingDir);
		System.out.println("Aktion " + actionRect.x + " " + actionRect.y + " " + actionRect.width+ " " + actionRect.height);
		System.out.println("Player "+ playerRect.x + " " + playerRect.y + " " + playerRect.width+ " " + playerRect.height);
		
		for(int y=Math.max(0, estmPlayerTileY-5); y<Math.min(estmPlayerTileY+5,world.arrayY);y++){
			for(int x=Math.max(0,estmPlayerTileX-5); x<Math.min(estmPlayerTileX+5,world.arrayX);x++){
				if(world.isDoor[x][y]&&actionRect.intersects(world.tiles[x][y])){
					world.isDoor[x][y]=false;
					world.isSolid[x][y]=false;
					
					if(world.tileImg[x][y]==world.TILE_DOOR_H){
						world.tileImg[x][y]=world.TILE_DOOR_H_O;
					}
					else world.tileImg[x][y]=world.TILE_DOOR_V_O;
				}
			}
		}		
	}
	
	public void attack(){
		attacktimer=100;
		Rectangle actionRect = getActionRect();
		
		for(int i=0; i<monsters.length; i++)
		if(actionRect.intersects(monsters[i].playerRect)){
			monsters[i].hurt(10);
		}
		
	}
	
	public void fire(){
		if(canZap){
		bolt.fire(playerRect.x, playerRect.y,facingDir, zapDmg);		
		}
		
	}
	
	private void checkForCollision(){
		Rectangle testRectX;
		Rectangle testRectY;
		Rectangle testRectXY;
		testRectX = new Rectangle(playerRect.x+xDirection, playerRect.y, colWidth,colHight);
		testRectY = new Rectangle(playerRect.x, playerRect.y+yDirection, colWidth,colHight);
		testRectXY = new Rectangle(playerRect.x+xDirection, playerRect.y+yDirection, colWidth,colHight);	
		
		Rectangle actionRect = getActionRect();
		

		//Making this more efficient
		int estmPlayerTileX = (playerRect.x-world.xFromHome)/20;
		int estmPlayerTileY = (playerRect.y-world.yFromHome)/20;
		
		for(int y=Math.max(0, estmPlayerTileY-5); y<Math.min(estmPlayerTileY+5,world.arrayY);y++){
			for(int x=Math.max(0,estmPlayerTileX-5); x<Math.min(estmPlayerTileX+5,world.arrayX);x++){
				
				//Worldcollision
				if(world.isSolid[x][y]&& testRectX.intersects(world.tiles[x][y])){
					xDirection = 0;
				}
				
				if(world.isSolid[x][y]&& testRectY.intersects(world.tiles[x][y])){
					yDirection = 0;
				}
				testRectXY = new Rectangle(playerRect.x+xDirection, playerRect.y+yDirection, colWidth,colHight);
				
				int a=(world.tiles[x][y].x);
				int b=(world.tiles[x][y].y);
				int c=(world.tiles[x][y].width);
				int d=(world.tiles[x][y].height);
			
				
				Rectangle r = new Rectangle(a+4,b+4,c-8,d-8);
				if((!world.isSafe[x][y])&&testRectXY.intersects(r)){
					respawn();

				}
				//Aktionsmöglichkeiten I
				
			}
		}
		testRectXY = new Rectangle(playerRect.x+xDirection, playerRect.y+yDirection, colWidth,colHight);
				//Bouldercollision X Richtung
		
				for( int i=0; i<boulders.length; i++){
		
					if (testRectXY.intersects(new Rectangle (boulders[i].objectRect.x, boulders[i].objectRect.y+1,boulders[i].objectRect.width,boulders[i].objectRect.height-2))){
						//Versuche zu schieben
						if (!boulders[i].move(xDirection, 0)){
							//yDirection = 0;
							xDirection = 0;
						}
					}//~ Y Richtung
					if (testRectXY.intersects(new Rectangle (boulders[i].objectRect.x+1, boulders[i].objectRect.y,boulders[i].objectRect.width-2,boulders[i].objectRect.height))){
						//Versuche zu schieben
						if (!boulders[i].move(0, yDirection)){
							yDirection = 0;
							//xDirection = 0;
						}
					}
				}
				
				
				//Monstercollision
				for( int i=0; i<monsters.length; i++){
				if(testRectX.intersects(monsters[i].playerRect)){
					xDirection = 0;
					
				}
				if(testRectY.intersects(monsters[i].playerRect)){
					yDirection = 0;
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
				
				//Has a Step been taken?
				if(yDirection!=0||xDirection!=0){
					Steps++;
				}
				
				//Fishcollision
				for( int i=0; i<fishes.length; i++){
					if(testRectXY.intersects(fishes[i].objectRect)){
					fishes[i].delFish();
					fishCounter++;
					Item_Fish a;
					inv.addItem(a = new Item_Fish());
					//if(alive==1)xP=xP + 10;
					
					}
				}
				
				//Action möglichkeiten II
				//actionRect = new Rectangle(playerRect.x+xDirection-10, playerRect.y+yDirection-10, colWidth+20,colHight+20);
				
					//Trader
					if ( actionRect.intersects(kng.NRect)) {
						canTrade=true;
						currentTrader = kng;
						inv.canSell = true;
					}
					else {
						canTrade=false;
						inv.canSell=false;
					}
					//loot
					canLoot = false;
					for(int i = 0; i<monsters.length; i++){
					if ( actionRect.intersects(monsters[i].playerRect)&&!monsters[i].alive){
						canLoot = true;
						currentLootTarget = monsters[i];
					}
					}
					//loot a chest
					canLootChest = false;
					for(int i=0;i<chests.length;i++){
						if ( actionRect.intersects(chests[i].NRect)){
							canLootChest = true;
							currentLootChestTarget = chests[i];
							System.out.println("can loot!");
					}
					
					}
					

			
		
		
	}
	
	public void checkForMapmovement(){
		
		Rectangle testRect = new Rectangle(playerRect.x+xDirection, playerRect.y+yDirection, colWidth,colHight);
		Rectangle rightBorder = new Rectangle(400,0,100,400);
		Rectangle leftBorder = new Rectangle(0,0,100,400);
		Rectangle topBorder = new Rectangle(0,0,500,100);
		Rectangle lowBorder = new Rectangle(0,300,500,100);
		
		if(testRect.intersects(rightBorder)||testRect.intersects(leftBorder)){
			// if P is moving outside Map center: M bewegt sich in (-1)*dir, alle Monster, Fishe, Objekte Bewegen sich auch (-1*dir)
			
			//Move World under Player -- working
			world.setXDirection((-1)*xDirection);
			world.moveMap();
			world.stopMoveMap();
			
			//move Monsters to new Worldposition
			WG.moveMonsters(monsters,((-1)*xDirection),0);
			
			//move Boulders
			WG.moveBoulders(boulders,((-1)*xDirection),0);

			//move Fishes
			WG.moveFishes(fishes,((-1)*xDirection),0);
			
			//move NPCS
			kng.forcedMove(((-1)*xDirection),0);
			WG.moveChests(chests,((-1)*xDirection),0);
			
			//set the Player movement to 0
			xDirection = 0;
			
		}
		if(testRect.intersects(topBorder)||testRect.intersects(lowBorder)){
			// if P is moving outside Map center: M bewegt sich in (-1)*dir, alle Monster, Fishe, Objekte Bewegen sich auch (-1*dir)
			
			//Move World under Player -- working
			world.setYDirection((-1)*yDirection);
			world.moveMap();
			world.stopMoveMap();
			
			//move Monsters to new Worldposition
			WG.moveMonsters(monsters,0,((-1)*yDirection));
			
			//move Boulders
			WG.moveBoulders(boulders,0,((-1)*yDirection));

			//move Fishes
			WG.moveFishes(fishes,0,((-1)*yDirection));
			
			//move NPCS
			kng.forcedMove(0,((-1)*yDirection));
			WG.moveChests(chests,0,((-1)*yDirection));
			
			
			
			//set the Player movement to 0
			yDirection = 0;
						
		}
		  
	}
	
	public void draw(Graphics g){
		g.drawImage(playerImg, playerRect.x-((20-colWidth)/2), playerRect.y-((20-colHight)/2), null); 
	}
	
	public void act(){
		if (canLootChest&&!chestLooting&&!tradeStatus&&!looting){
			
			if(currentLootChestTarget.open){
				startChestLooting();
			}
			else{
			Item_Key a;
			if(inv.searchItem(a = new Item_Key())[0]!=-1){
				int c=inv.searchItem(a = new Item_Key())[0];
				int r=inv.searchItem(a = new Item_Key())[1];
				inv.produceItemXY(r, c);
				currentLootChestTarget.openChest();
				startChestLooting();
			}
			}
		}
		else if (canTrade&&!tradeStatus&&!inventoryMode&&!chestLooting){
			dia.openDialog();
		}
		else if (canLoot&&!tradeStatus&&!inventoryMode&&!looting&&!chestLooting){
			startLooting();
		}
		else if (tradeStatus){//Kaufen
			if(inv.gotSpace()){
				inv.addItem(currentTrader.Inv.produceItem());
			}
		}
		else if (inventoryMode&&canTrade){ //Verkaufen
			currentTrader.Inv.addItem(inv.produceItem());
		}
		else if (looting){//Looten
			if(inv.gotSpace()){
				inv.addItem(currentLootTarget.Inv.produceItem());
			}
		}
		else if (chestLooting){ //Chestlooting
			if(inv.gotSpace()){
				inv.addItem(currentLootChestTarget.Inv.produceItem());
			}
		}
		
	}

	public boolean getTradeStatus(){
		return tradeStatus;
	}

	public void initiateTrade(){
		tradeStatus = true;
	}

	public void stopTrade(){
		tradeStatus = false;
		nP.cancleOverwrite();
	}
	
	public boolean getInvStatus(){
		return inventoryMode;
	}

	public void openInventory(){
		inventoryMode = true;
	}

	public void closeInventory(){
		inventoryMode = false;
		nP.cancleOverwrite();
	}
	
	public void startLooting(){
		looting = true;
	}
	
	public void startChestLooting(){
		chestLooting = true;
	}
	
	public void stopLooting(){
		looting = false;
		nP.cancleOverwrite();
	}
	
	public void stopChestLooting(){
		chestLooting = false;
		nP.cancleOverwrite();
	}
	
	public boolean getlootStatus(){
		return looting;
	}
	
	public boolean getChestlootStatus(){
		return chestLooting;
	}


}
