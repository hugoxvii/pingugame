package littleGame;

import java.awt.Color;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Maploader {
	
	private World world;
	private BufferedImage map;
	String mapPATH;
	String map2PATH;
	
	//Values Dependend on Map
	private int mapWidth;
	private int mapHight;
	private String boulderlist="";
	private String chestlist="";
	private String monsterlist="";
	private WorldGenerator WG;
	public boolean cave = false;
	
	public Maploader() {
	
		
		
		
		mapPATH = new File ("Images/map.bmp").getAbsolutePath();
		mapPATH = mapPATH.replace("\\", "/");
		map2PATH = new File ("Images/map2.bmp").getAbsolutePath();
		map2PATH = map2PATH.replace("\\", "/");

		
	}
	
	public World loadWorld(WorldGenerator wg){
		World world = new World(0,0);
		boulderlist="";
		chestlist="";
		monsterlist="";
		
		WG = wg;
		try {		
			
			
		File f = new File(mapPATH);
		BufferedImage map = ImageIO.read(f);
		mapWidth= map.getWidth(null);
		mapHight= map.getHeight(null);


		System.out.println(mapWidth + "+" + mapHight +"+");
		System.out.println(map.getRGB(2,2));
		 
		//Create World Object
		world = new World(mapWidth, mapHight);
		if (cave) world.location = "cave/cave-";
		
		//print Tiles
		for(int y=0; y<world.arrayY;y++){
			for(int x=0; x<world.arrayX;x++){
				if (/*is the Pixel black?*/ map.getRGB(x,y)==Color.BLACK.getRGB()){
					//Rivertiles
					world.isSolid[x][y] = true;
					world.isMovable[x][y] = false;
					world.isSafe[x][y] = true;
					world.isDoor[x][y] = false;
					world.isKickable[x][y] = false;

					world.tiles[x][y] = new Rectangle(world.ix,world.iy,20,20);
					
					
					//Es gibt verschiedene Rivertiles
					String codeword = "";
					
					//Wir lesen die nachbarfelder
					//oben
					if(map.getRGB(x, Math.min(99,(Math.max(0,y-1))))==Color.BLACK.getRGB()){
						codeword = codeword + "R";
					}
					else{
						codeword = codeword + "E";
					}
					
					//rechts oben
					if(map.getRGB(Math.min(99,(Math.max(0,x+1))), Math.min(99,(Math.max(0,y-1))))==Color.BLACK.getRGB()){
						codeword = codeword + "x";
						//System.out.println("w");
					}
					else{
						codeword = codeword + "_";
						//System.out.println("w");
					}
					
					//rechts
					if(map.getRGB(Math.min(99,(Math.max(0,x+1))),y)==Color.BLACK.getRGB()){
						codeword = codeword + "R";
						//System.out.println("e");
					}
					else{
						codeword = codeword + "E";
						//System.out.println("e");
					}
					
					//rechts unten
					if(map.getRGB(Math.min(99,(Math.max(0,x+1))), Math.min(99,(Math.max(0,y+1))))==Color.BLACK.getRGB()){
						codeword = codeword + "x";
						//System.out.println("r");
					}
					else{
						codeword = codeword + "_";
						//System.out.println("r");
					}
					
					//unten
					if(map.getRGB(x, Math.min(99,(Math.max(0,y+1))))==Color.BLACK.getRGB()){
						codeword = codeword + "R";
						//System.out.println("t");
					}
					else{
						codeword = codeword + "E";
						//System.out.println("t");
					}
					
					//links unten
					if(map.getRGB(Math.min(99,(Math.max(0,x-1))), Math.min(99,(Math.max(0,y+1))))==Color.BLACK.getRGB()){
						codeword = codeword + "x";
						//System.out.println("z");
					}
					else{
						codeword = codeword + "_";
						//System.out.println("z");
					}
					
					//links
					if(map.getRGB(Math.min(99,(Math.max(0,x-1))),y)==Color.BLACK.getRGB()){
						codeword = codeword + "R";
						//System.out.println("u");
					}
					else{
						codeword = codeword + "E";
						//System.out.println("u");
					}
					
					//links oben
					if(map.getRGB(Math.min(99,(Math.max(0,x-1))), Math.min(99,(Math.max(0,y-1))))==Color.BLACK.getRGB()){
						codeword = codeword + "x";
						//System.out.println("i");
					}
					else{
						codeword = codeword + "_";
						//System.out.println("i");
					}
					
					/*
					 * 	hänge 1. buchstaben hinten nochmal an
					 *						
					 *	lösche alle nachbarn von E
					 *
					 *	lösche letzten buchstaben
					 * String.charAt();
					 */
					codeword = codeword + codeword.charAt(0);
					String codeword2 = "";
					boolean r = false;
					for(int i=0;i<=8;i=i+2){
						if(i==8){
							if(codeword.charAt(i)=='R'&& r){
								codeword2 = codeword2 + codeword.charAt(i-1);
							}
						}
						else if(codeword.charAt(i)=='E'){
							codeword2 = codeword2 + "E";
							r = false;
						}
						else if(codeword.charAt(i)=='R'){
							if (r){
								codeword2 = codeword2 + codeword.charAt(i-1);
								codeword2 = codeword2 + "R";
								r = true;
							}
							else{
								codeword2 = codeword2 + "R";
								r = true;
							}
						}
					}
					//System.out.println(codeword2);
					world.tileImg[x][y] = world.getImage(codeword2);

				}else if(map.getRGB(x,y)==-8355712){
					//Pit Tiles
					world.tileImg[x][y] = world.TILE_PIT;
					world.isSolid[x][y] = false;
					world.isMovable[x][y] = false;
					world.isDoor[x][y] = false;
					world.isKickable[x][y] = false;
					world.isSafe[x][y] = false;
					world.tiles[x][y] = new Rectangle(world.ix,world.iy,20,20);
					
				}
				//map.getRGB(Math.min(99,(Math.max(0,x-1))), Math.min(99,(Math.max(0,y-1))))==Color.BLACK.getRGB()
				else if(map.getRGB(x, y)==-38400){
					// Türen
					world.isSolid[x][y] = true;
					world.isMovable[x][y] = false;
					world.isSafe[x][y] = true;
					world.isDoor[x][y] = true;
					world.isKickable[x][y] = false;
					world.tiles[x][y] = new Rectangle(world.ix,world.iy,20,20);
					if(map.getRGB(x, Math.min(99,(Math.max(0,y-1))))==Color.BLACK.getRGB()||map.getRGB(x, Math.min(99,(Math.max(0,y-1))))==-38400||map.getRGB(x, Math.min(99,(Math.max(0,y-1))))==-10240||map.getRGB(x, Math.min(99,(Math.max(0,y+1))))==Color.BLACK.getRGB()||map.getRGB(x, Math.min(99,(Math.max(0,y+1))))==-38400||map.getRGB(x, Math.min(99,(Math.max(0,y+1))))==-10240){
						//Vertikal
						world.tileImg[x][y] = world.TILE_DOOR_V;
					}
					else{
						//Horizontal
						world.tileImg[x][y] = world.TILE_DOOR_H;
					}
				}
				else if(map.getRGB(x, y)==-10240){
					// Wände
					world.isSolid[x][y] = true;
					world.isMovable[x][y] = false;
					world.isSafe[x][y] = true;
					world.isDoor[x][y] = false;
					world.isKickable[x][y] = false;
					world.tiles[x][y] = new Rectangle(world.ix,world.iy,20,20);
					
					if(map.getRGB(x, Math.min(99,(Math.max(0,y-1))))==-38400||map.getRGB(x, Math.min(99,(Math.max(0,y-1))))==-10240||map.getRGB(x, Math.min(99,(Math.max(0,y+1))))==-38400||map.getRGB(x, Math.min(99,(Math.max(0,y+1))))==-10240){
						//nicht Horizontal
						if(map.getRGB(Math.min(99,(Math.max(0,x-1))), y)==-38400||map.getRGB(Math.min(99,(Math.max(0,x-1))), y)==-10240||map.getRGB(Math.min(99,(Math.max(0,x+1))), y)==-38400||map.getRGB(Math.min(99,(Math.max(0,x+1))), y)==-10240){
							//Kreutzung
							world.tileImg[x][y] = world.TILE_WALL_SEC;
						}
						else{
							//Vertikal
							world.tileImg[x][y] = world.TILE_WALL_V;
						}
					}
					else{
						//Horizontal
						world.tileImg[x][y] = world.TILE_WALL_H;
						
					}
					
				}
				else{
					//Empty Tiles
					world.tileImg[x][y] = world.TILE_EMPTY;
					world.isSolid[x][y] = false;
					world.isMovable[x][y] = false;
					world.isDoor[x][y] = false;
					world.isSafe[x][y] = true;
					world.isKickable[x][y] = false;
					world.tiles[x][y] = new Rectangle(world.ix,world.iy,20,20);
					
					// Objectspawns
					if(map.getRGB(x, y)==-65536){
						if(x<10) boulderlist = boulderlist +"00"+x;
						else if(x<100) boulderlist = boulderlist +"0"+x;
						else boulderlist = boulderlist +x;
						if(y<10) boulderlist = boulderlist +"00"+y;
						else if(y<100) boulderlist = boulderlist +"0"+y;
						else boulderlist = boulderlist +y;
					}
					//CHestspawns 
					if(map.getRGB(x, y)==-16711681){
						if(x<10) chestlist = chestlist +"00"+x;
						else if(x<100) chestlist = chestlist +"0"+x;
						else chestlist = chestlist +x;
						if(y<10) chestlist = chestlist +"00"+y;
						else if(y<100) chestlist = chestlist +"0"+y;
						else chestlist = chestlist +y;
					}
					//Monsterspawns
					if(map.getRGB(x, y)==-16739073){
						if(x<10) monsterlist = monsterlist +"00"+x;
						else if(x<100) monsterlist = monsterlist +"0"+x;
						else monsterlist = monsterlist +x;
						if(y<10) monsterlist = monsterlist +"00"+y;
						else if(y<100) monsterlist = monsterlist +"0"+y;
						else monsterlist = monsterlist +y;
					}
					
				}
				world.ix+=20;
			}
			world.ix=0;
			world.iy+=20;
		}
		
		
	} catch (IOException ioe) {
		   ioe.printStackTrace();
		}
		WG.readBoulders(boulderlist, world);
		WG.readChests(chestlist, world);
		WG.readMonsters(monsterlist, world);
		return world;
	}
	
}
