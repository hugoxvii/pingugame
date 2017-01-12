package littleGame;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class WorldGenerator{
	
	private World world;
	private StatusPanel sp;
	private NotificationPanel np;
	
	public WorldGenerator( StatusPanel SP, NotificationPanel NP){
		
		this.sp=SP;
		this.np=NP;
		//this.world=w;
	}
	public Object[] Boulders;
	public Object[] fakeBoulders = new Object[1];
	public Chest[] Chests;
	public Monster[] Monsters;
	public Monster[] fakeMonster = new Monster[1];
	

	public Fish[] genFishes(int n, World world){
		Random rand = new Random();
		Fish[] gen = new Fish[n];
		for(int i=0; i<n; i++){
				
			//collisioncheck
			int randX=rand.nextInt(2000);
			int randY=rand.nextInt(2000);
			Rectangle testRect = new Rectangle (randX,randY,20,20);
			for(int y=0; y<world.arrayY;y++){
				for(int x=0; x<world.arrayX;x++){
					
					//Worldcollision
					if(testRect.intersects(world.tiles[x][y])){
						if(world.isSolid[x][y]){
							i--;
							y=world.arrayY;
							x=world.arrayX;
						}	
						else{
							gen[i] = new Fish(randX, randY);
						}
					}
				}
			}
			
			
		}
		return gen;
	}
	
	public Object[] genBoulders(int n, World world){
		Random rand = new Random();
		Object[] gen = new Object[n];
		for(int i=0; i<n; i++){
				
			//collisioncheck
			int randX=rand.nextInt(2000);
			int randY=rand.nextInt(2000);
			Rectangle testRect = new Rectangle (randX,randY,20,20);
			for(int y=0; y<world.arrayY;y++){
				for(int x=0; x<world.arrayX;x++){
					
					//Worldcollision
					if(testRect.intersects(world.tiles[x][y])){
						if(world.isSolid[x][y]||!world.isSafe[x][y]){
							i--;
							y=world.arrayY;
							x=world.arrayX;
						}	
						else{
							gen[i] = new Object(world, randX, randY, i, fakeBoulders);
						}
					}
				}
			}
			
			
		}
		return gen;
	}
	//String.charAt()
	public void readBoulders(String codeword, World world){
		
		if(codeword.length()<6){
			Boulders = new Object[1];
			Boulders[0] = new Object(world,-100,-100,0,fakeBoulders);
			
		}else{
			
		Boulders = new Object[codeword.length()/6];
		
		int a = codeword.length();
		for(int i=1; i<=a/6;i++){
			
			int x= Character.getNumericValue(codeword.charAt(i*6-6))*100+Character.getNumericValue(codeword.charAt(i*6-5))*10+Character.getNumericValue(codeword.charAt(i*6-4));
			int y= Character.getNumericValue(codeword.charAt(i*6-3))*100+Character.getNumericValue(codeword.charAt(i*6-2))*10+Character.getNumericValue(codeword.charAt(i*6-1));
			x=x*20;
			y=y*20;
			Boulders[i-1] = new Object(world,x,y,i-1,fakeBoulders);
			
		}
		}
	}
	
	public void readChests(String codeword, World world){
		
		if(codeword.length()<6){
			Chests = new Chest[1];
			Chests[0] = new Chest(np, sp, -100, -100);
			
		}else{
			
		Chests = new Chest[codeword.length()/6];
		
		int a = codeword.length();
		for(int i=1; i<=a/6;i++){
			
			int x= Character.getNumericValue(codeword.charAt(i*6-6))*100+Character.getNumericValue(codeword.charAt(i*6-5))*10+Character.getNumericValue(codeword.charAt(i*6-4));
			int y= Character.getNumericValue(codeword.charAt(i*6-3))*100+Character.getNumericValue(codeword.charAt(i*6-2))*10+Character.getNumericValue(codeword.charAt(i*6-1));
			x=x*20;
			y=y*20;
			Chests[i-1] = new Chest(np,sp,x,y);
			
		}
		}
	}
	
	public void readMonsters(String codeword, World world){
		
		if(codeword.length()<6){
			Monsters = new Monster[1];
			Monsters[0] = new Monster(-100, -100, np, sp, 0);
		
		}else{
			
		Monsters = new Monster[codeword.length()/6];
		
		int a = codeword.length();
		for(int i=1; i<=a/6;i++){
			
			int x= Character.getNumericValue(codeword.charAt(i*6-6))*100+Character.getNumericValue(codeword.charAt(i*6-5))*10+Character.getNumericValue(codeword.charAt(i*6-4));
			int y= Character.getNumericValue(codeword.charAt(i*6-3))*100+Character.getNumericValue(codeword.charAt(i*6-2))*10+Character.getNumericValue(codeword.charAt(i*6-1));
			x=x*20;
			y=y*20;
			Monsters[i-1] = new Monster(x,y,np,sp,i-1);
			
		}
		}
	}
	
	public Object[] getBoulders(){
		return Boulders;
	}
	
	public Chest[] getChests(){
		return Chests;
	}
	public Monster[] getMonsters(){
		return Monsters;
	}
	
	public void drawFishes(Fish[] fishes, Graphics g){
		for(int i=0;i<fishes.length;i++){
			if(fishes[i].status){
				fishes[i].draw(g);
			}
		}
	}
	
	public void drawChests(Chest[] chests, Graphics g){
		for(int i=0;i<chests.length;i++){
				chests[i].draw(g);
			
		}
	}
	
	public void drawBoulders(Object[] boulders, Graphics g){
		for(int i=0;i<boulders.length;i++){
			if(boulders[i].status){
				boulders[i].draw(g);
			}
		}
	}
	public void drawMonsters(Monster[] mons, Graphics g){
		for(int i=0;i<mons.length;i++){
			mons[i].draw(g);
			
		}
	}
	public void updateMonsters(Monster[] mons){
		for(int i=0;i<mons.length;i++){
			if(mons[i].alive){
				mons[i].update();
			}
		}
	}
	
	public void moveFishes(Fish[] fishes, int dirX, int dirY){
		for(int i=0;i<fishes.length;i++){
			fishes[i].move(dirX,dirY);
		}
	}
	
	public void moveChests(Chest[] chests, int dirX, int dirY){
		for(int i=0;i<chests.length;i++){
			chests[i].forcedMove(dirX,dirY);
		}
	}
	
	public void moveBoulders(Object[] boulders, int dirX, int dirY){
		for(int i=0;i<boulders.length;i++){
			boulders[i].forcedMove(dirX,dirY);
		}
	}
	public void moveMonsters(Monster[] mons, int dirX, int dirY){
		for(int i=0;i<mons.length;i++){
			mons[i].forcedMove(dirX,dirY);
		}
	}

	public void informBoulders(Object[] boulders){
		for(int i=0; i<boulders.length;i++){
			boulders[i].updateBoulders(boulders);
		}
	}
	//(world, monster, boulders, chests, fish, bolt, WG, kng, player);
	public void informMonsters(World w,Monster[] mons,Object[] boulders,Chest[] chests,Fish[] fish,Bolt bolt, Trader kng, Player player ){
		for(int i=0; i<mons.length;i++){
			mons[i].inform(w, mons, boulders, chests, fish, bolt, this, kng, player);
		}
	}
}
