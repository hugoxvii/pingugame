package littleGame;

import java.awt.Graphics;

public class WorldCoordinator {

	//getting
	protected Player player;
	protected NotificationPanel np;
	protected StatusPanel sp;
	
	//
	protected Maploader ML;
	protected WorldGenerator WG;
	
	//world
	protected World world;
	protected Chest[] chests;
	protected Fish[] fish;
	protected Object[] boulders;
	protected Monster[] monsters;
	
	//will be [] later on
	protected Trader kng;
	protected Monster monster;
	
	//might be implimented diffrently
	protected Bolt bolt;
	
	
	public WorldCoordinator(Player p, NotificationPanel nP, StatusPanel sP ){
		
		//gets all the existing WorldElements - just the player, and access to the other Panels
		this.player = p;
		this.np = nP;
		this.sp = sP;
		
		// will call for everything necessary to create the new World
		WG = new WorldGenerator(sp,np);
		ML = new Maploader();
		
		
		world = ML.loadWorld(WG);
		
		boulders = WG.getBoulders();
		chests = WG.getChests();
		monsters = WG.getMonsters();
		
		fish = new Fish[20];
		fish = WG.genFishes(20,world);
		kng = new Trader(np,sp,860,260);
		bolt = new Bolt(world);
		
		//monster = new Monster(1020, 260, np, sp);
		
		//temporary stuff for testing
		Item_Key testItem = new Item_Key();
		kng.Inv.addItem(testItem);
		
		//inform all of each other
		
		player.inform(world, monsters, boulders, chests, fish, bolt, WG, kng, player);
		WG.informMonsters(world, monsters, boulders, chests, fish, bolt, kng, player);
		WG.informBoulders(boulders);
		
		//spawning Iglu		
		int estmPlayerTileX = (player.playerRect.x-world.xFromHome)/20;
		int estmPlayerTileY = (player.playerRect.y-world.yFromHome)/20;
		world.isSolid[estmPlayerTileX-1][estmPlayerTileY]=true;
		world.tileImg[estmPlayerTileX-1][estmPlayerTileY]=world.TILE_IGLU;
		
		
	}
	public void update(){
		world.moveMap();
		player.update();
		if(bolt.active) bolt.update();
		//if(monster.alive) monster.update();
		WG.updateMonsters(monsters);
		
	}
	public void inform(){
		player.inform(world, monsters, boulders, chests, fish, bolt, WG, kng, player);
		WG.informMonsters(world, monsters, boulders, chests, fish, bolt, kng, player);
		WG.informBoulders(boulders);
	}
	
	public void draw (Graphics g){
		
		world.draw(g);
		WG.drawFishes(fish, g);
		WG.drawChests(chests, g);
		WG.drawBoulders(boulders, g);
		WG.drawMonsters(monsters, g);
		player.draw(g);
		//monster.draw(g);
		kng.draw(g);
		
		if(bolt.active)bolt.draw(g);
		
		
		
	}
}
