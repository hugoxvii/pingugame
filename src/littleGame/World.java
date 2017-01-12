package littleGame;

// hallo Git

import java.awt.*;
import java.io.File;

import javax.swing.*;

public class World {
	public Rectangle[][] tiles;
	public boolean[][] isSolid;
	public boolean[][] isMovable;
	public boolean[][] isSafe;
	protected Image[][] tileImg;
	protected boolean[][] isDoor;
	protected boolean[][] isKickable;
	protected boolean[][] isChest;
	protected boolean[][] isOpen;
	//public final int arrayX=50;
	//public final int arrayY=20;
	public final int arrayX;
	public final int arrayY;
	
	protected int xFromHome=0;
	protected int yFromHome=0;
	int n = 0;
	
	//Fluff
	public int globalxP = 0;
	protected String location = "river/";
	
	//Tile images
	protected Image TILE_EMPTY, TILE_STONE, TILE_RIVER, TILE_PIT,TILE_PITFULL, TILE_IGLU, TILE_CHEST_C, TILE_CHEST_O;
	protected Image TILE_DOOR_H, TILE_DOOR_V,TILE_WALL_SEC, TILE_WALL_H, TILE_WALL_V, TILE_DOOR_H_O, TILE_DOOR_V_O;
	protected int ix, iy, xDirection, yDirection;
	
	//Map navigation
	static final int PAN_UP = 0, PAN_DOWN = 1, PAN_LEFT = 2, PAN_RIGHT = 3;
	
	public World(int hight, int width){

		this.arrayX = width;
		this.arrayY = hight;
		
		String emptyPATH = new File ("Images/"+ location + "empty.png").getAbsolutePath();
		String stonePATH = new File ("Images/stone.png").getAbsolutePath();
		String riverPATH = new File ("Images/river.png").getAbsolutePath();
		String pitPATH = new File ("Images/pit.png").getAbsolutePath();
		String pitfullPATH = new File ("Images/pitfull.png").getAbsolutePath();
		String doorVPATH = new File ("Images/walls/door-vertical-closed.png").getAbsolutePath();
		String doorHPATH = new File ("Images/walls/door-horizontal-closed.png").getAbsolutePath();
		String doorHOPATH = new File ("Images/walls/door-horizontal-open.png").getAbsolutePath();
		String doorVOPATH = new File ("Images/walls/door-vertical-open.png").getAbsolutePath();
		String wallSecPATH = new File ("Images/walls/wall-cross.png").getAbsolutePath();
		String wallHPATH = new File ("Images/walls/wall-horizontal.png").getAbsolutePath();
		String wallVPATH = new File ("Images/walls/wall-vertical.png").getAbsolutePath();
		String igluPATH = new File ("Images/iglu.png").getAbsolutePath();
		String ChestCPATH = new File ("Images/iglu.png").getAbsolutePath();
		String ChestOPATH = new File ("Images/iglu.png").getAbsolutePath();
		
		emptyPATH = emptyPATH.replace("\\", "/");
		stonePATH = stonePATH.replace("\\", "/");
		riverPATH = riverPATH.replace("\\", "/");
		pitPATH = pitPATH.replace("\\", "/");
		pitfullPATH = pitfullPATH.replace("\\", "/");
		wallVPATH = wallVPATH.replace("\\", "/");
		wallHPATH = wallHPATH.replace("\\", "/");
		wallSecPATH = wallSecPATH.replace("\\", "/");
		doorHPATH = doorHPATH.replace("\\", "/");
		doorVPATH = doorVPATH.replace("\\", "/");
		doorHOPATH = doorHOPATH.replace("\\", "/");
		doorVOPATH = doorVOPATH.replace("\\", "/");
		igluPATH = igluPATH.replace("\\", "/");
		ChestCPATH = ChestCPATH.replace("\\", "/");
		ChestOPATH = ChestOPATH.replace("\\", "/");
		
		TILE_EMPTY = new ImageIcon(emptyPATH).getImage();
		TILE_STONE = new ImageIcon(stonePATH).getImage();
		TILE_RIVER = new ImageIcon(riverPATH).getImage();
		TILE_PIT = new ImageIcon(pitPATH).getImage();
		TILE_PITFULL = new ImageIcon(pitfullPATH).getImage();
		TILE_DOOR_H = new ImageIcon(doorHPATH).getImage();
		TILE_DOOR_V = new ImageIcon(doorVPATH).getImage();
		TILE_DOOR_H_O = new ImageIcon(doorHOPATH).getImage();
		TILE_DOOR_V_O = new ImageIcon(doorVOPATH).getImage();
		TILE_WALL_SEC = new ImageIcon(wallSecPATH).getImage();
		TILE_WALL_H = new ImageIcon(wallHPATH).getImage();
		TILE_WALL_V = new ImageIcon(wallVPATH).getImage();
		TILE_IGLU = new ImageIcon(igluPATH).getImage();
		TILE_CHEST_O = new ImageIcon(ChestOPATH).getImage();
		TILE_CHEST_C = new ImageIcon(ChestCPATH).getImage();
		tiles = new Rectangle[arrayX][arrayY];
		tileImg = new Image[arrayX][arrayY];
		isSolid = new boolean[arrayX][arrayY];
		isMovable = new boolean[arrayX][arrayY];
		isSafe = new boolean[arrayX][arrayY];
		isDoor = new boolean[arrayX][arrayY];
		isKickable = new boolean[arrayX][arrayY];
		isChest = new boolean[arrayX][arrayY];
		isOpen = new boolean[arrayX][arrayY];
		
		//Spawn New World
		//loadArrays();
		
		//Spawn New Objects - atm in GP
		//boulder = new Object(World(this), 100, 100);
	}
	
	public void reloadImages(){
		String emptyPATH = new File ("Images/"+ location + "empty.png").getAbsolutePath();
		String stonePATH = new File ("Images/stone.png").getAbsolutePath();
		String riverPATH = new File ("Images/river.png").getAbsolutePath();
		String pitPATH = new File ("Images/pit.png").getAbsolutePath();
		String pitfullPATH = new File ("Images/pitfull.png").getAbsolutePath();
		String doorVPATH = new File ("Images/walls/door-vertical-closed.png").getAbsolutePath();
		String doorHPATH = new File ("Images/walls/door-horizontal-closed.png").getAbsolutePath();
		String doorHOPATH = new File ("Images/walls/door-horizontal-open.png").getAbsolutePath();
		String doorVOPATH = new File ("Images/walls/door-vertical-open.png").getAbsolutePath();
		String wallSecPATH = new File ("Images/walls/wall-cross.png").getAbsolutePath();
		String wallHPATH = new File ("Images/walls/wall-horizontal.png").getAbsolutePath();
		String wallVPATH = new File ("Images/walls/wall-vertical.png").getAbsolutePath();
		String igluPATH = new File ("Images/iglu.png").getAbsolutePath();
		String ChestCPATH = new File ("Images/iglu.png").getAbsolutePath();
		String ChestOPATH = new File ("Images/iglu.png").getAbsolutePath();
		
		emptyPATH = emptyPATH.replace("\\", "/");
		stonePATH = stonePATH.replace("\\", "/");
		riverPATH = riverPATH.replace("\\", "/");
		pitPATH = pitPATH.replace("\\", "/");
		pitfullPATH = pitfullPATH.replace("\\", "/");
		wallVPATH = wallVPATH.replace("\\", "/");
		wallHPATH = wallHPATH.replace("\\", "/");
		wallSecPATH = wallSecPATH.replace("\\", "/");
		doorHPATH = doorHPATH.replace("\\", "/");
		doorVPATH = doorVPATH.replace("\\", "/");
		doorHOPATH = doorHOPATH.replace("\\", "/");
		doorVOPATH = doorVOPATH.replace("\\", "/");
		igluPATH = igluPATH.replace("\\", "/");
		ChestCPATH = ChestCPATH.replace("\\", "/");
		ChestOPATH = ChestOPATH.replace("\\", "/");
		
		TILE_EMPTY = new ImageIcon(emptyPATH).getImage();
		TILE_STONE = new ImageIcon(stonePATH).getImage();
		TILE_RIVER = new ImageIcon(riverPATH).getImage();
		TILE_PIT = new ImageIcon(pitPATH).getImage();
		TILE_PITFULL = new ImageIcon(pitfullPATH).getImage();
		TILE_DOOR_H = new ImageIcon(doorHPATH).getImage();
		TILE_DOOR_V = new ImageIcon(doorVPATH).getImage();
		TILE_DOOR_H_O = new ImageIcon(doorHOPATH).getImage();
		TILE_DOOR_V_O = new ImageIcon(doorVOPATH).getImage();
		TILE_WALL_SEC = new ImageIcon(wallSecPATH).getImage();
		TILE_WALL_H = new ImageIcon(wallHPATH).getImage();
		TILE_WALL_V = new ImageIcon(wallVPATH).getImage();
		TILE_IGLU = new ImageIcon(igluPATH).getImage();
		TILE_CHEST_O = new ImageIcon(ChestOPATH).getImage();
		TILE_CHEST_C = new ImageIcon(ChestCPATH).getImage();
	}
	
	public Image getImage(String name){
		
		String PATH = new File ("Images/"+ location +name+".png").getAbsolutePath();
		PATH = PATH.replace("\\", "/");
		//System.out.println(PATH);
		Image TILE = new ImageIcon(PATH).getImage();
		
		return TILE;
		
	}
	
	private void loadArrays(){
		//Sets up the World
		for(int y=0; y<arrayY;y++){
			for(int x=0; x<arrayX;x++){
				if (x<2||x>47||y<2||y>17){
					tileImg[x][y] = TILE_RIVER;
					isSolid[x][y] = true;
					isMovable[x][y] = false;
					tiles[x][y] = new Rectangle(ix,iy,20,20);
				}
				else if (false){
					tileImg[x][y] = TILE_STONE;
					isSolid[x][y] = true;
					isMovable[x][y] = true;
					tiles[x][y] = new Rectangle(ix,iy,20,20);
				} 
				else{
					tileImg[x][y] = TILE_EMPTY;
					isSolid[x][y] = false;
					isMovable[x][y] = false;
					tiles[x][y] = new Rectangle(ix,iy,20,20);
				}
				ix+=20;
			}
			ix=0;
			iy+=20;
		}
		
	}
			
	public void draw(Graphics g){
		for(int y=0; y<arrayY;y++){
			for(int x=0; x<arrayX;x++){
				g.drawImage(tileImg[x][y], tiles[x][y].x, tiles[x][y].y, null);
			}
			
		}
	}
	
	public void moveMap(){
		xFromHome+=xDirection;
		yFromHome+=yDirection;
		
		for(int y=0; y<arrayY;y++){
			for(int x=0; x<arrayX;x++){
				tiles[x][y].x += xDirection;
				tiles[x][y].y += yDirection;		
			}
		}
	}
		
	
	
	public void stopMoveMap(){
		setXDirection(0);
		setYDirection(0);
	}
	
	public void setXDirection(int dir){
		xDirection = dir;
	}
	
	public void setYDirection(int dir){
		yDirection = dir;
	}

	public void navigateMap(int nav){
		switch(nav){
		default:
			System.out.println("default case entered .... Doing nothing");
			break;
		case PAN_UP:
			setYDirection(-1);
			break;
		case PAN_DOWN:
			setYDirection(1);			
			break;
		case PAN_LEFT:
			setXDirection(-1);
			break;
		case PAN_RIGHT:
			setYDirection(1);
			break;
		}
	}
	
	public void destroyTile(int tileX, int tileY){
		tiles[tileX][tileY] = new Rectangle (-100, -100,0,0);
		isSolid[tileX][tileY] = false;
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public int[] resetPos(){
		int[] a = new int[2];
		System.out.println(xFromHome);
		a[0]=xFromHome;
		a[1]=yFromHome;
		System.out.println(a[1]);
		
		for(int y=0; y<arrayY;y++){
			for(int x=0; x<arrayX;x++){
				System.out.print(xFromHome);
				tiles[x][y].x -= xFromHome;
				tiles[x][y].y -= yFromHome;				
			}
		}
		xFromHome=0;
		yFromHome=0;
		return a;
		
	}

}
