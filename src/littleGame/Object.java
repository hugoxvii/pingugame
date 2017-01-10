package littleGame;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import javafx.scene.shape.*;

import javax.swing.ImageIcon;

public class Object {

private World world;
private Object[] Boulders;
	
public Rectangle objectRect;
public Circle objCir;
private int id;

private Image objectImg;
	
protected int x, y, xDirection, yDirection;
protected int currentDirectionX = 0; 
protected int currentDirectionY = 0;

protected boolean status = true;

public Object(World world, int x, int y, int idd, Object[] otherBoulders){
	//System.out.println("hallo Boulder");
	this.world = world;
	this.id = idd;
	this.Boulders = otherBoulders;
	String objectPATH = new File ("Images/stone.png").getAbsolutePath();
	objectPATH = objectPATH.replace("\\", "/");
	objectImg = new ImageIcon(objectPATH).getImage();
	objectRect = new Rectangle (x, y, 20, 20);
	objCir = new Circle ();
	objCir.setCenterX(x+10);
	objCir.setCenterY(y+10);
	objCir.setRadius(10.0f);
}
public void updateBoulders(Object[] otherBoulders){
	Boulders = otherBoulders;
}

public boolean move(int dirX, int dirY){
	xDirection = dirX;
	yDirection = dirY;
	boolean a = checkForCollision();
	objectRect.x += xDirection;
	objectRect.y += yDirection;
	return a;
}
public void forcedMove(int dirX, int dirY){
	objectRect.x += dirX;
	objectRect.y += dirY;
}

private boolean checkForCollision(){
	boolean status = true;
	Rectangle testRectX;
	Rectangle testRectY;
	testRectX = new Rectangle(objectRect.x+xDirection, objectRect.y, 20,20);
	
			//Making this more efficient
			int estmPlayerTileX = (objectRect.x-world.xFromHome)/20;
			int estmPlayerTileY = (objectRect.y-world.yFromHome)/20;
			
			for(int y=Math.max(0, estmPlayerTileY-5); y<Math.min(estmPlayerTileY+5,world.arrayY);y++){
				for(int x=Math.max(0,estmPlayerTileX-5); x<Math.min(estmPlayerTileX+5,world.arrayX);x++){
			if(world.isSolid[x][y]&& testRectX.intersects(world.tiles[x][y])){
				xDirection = 0;
				yDirection = 0;
				//System.out.println("Boulder Collide!");
				status = false;
			}
			testRectY = new Rectangle(objectRect.x+xDirection, objectRect.y+yDirection, 20,20);	
			
			if(world.isSolid[x][y]&& testRectY.intersects(world.tiles[x][y])){
				yDirection = 0;
				xDirection = 0;
			//System.out.println("Boulder Collide!");
			status = false;
			}
						
		
			testRectY = new Rectangle(objectRect.x+xDirection, objectRect.y+yDirection, 20,20);
			Rectangle a = new Rectangle(world.tiles[x][y].x+4,world.tiles[x][y].y+4,world.tiles[x][y].width-8,world.tiles[x][y].height-8);
			
			if(!world.isSafe[x][y]&&testRectY.intersects(a)){
				
				destroy();
				world.isSafe[x][y]=true;
				world.tileImg[x][y]=world.TILE_PITFULL;
			}
			}
			
		}

		testRectY = new Rectangle(objectRect.x+xDirection, objectRect.y+yDirection, 20,20);	
		for(int i=0;i<Boulders.length;i++){
	
			if(i!=id){
		if(testRectY.intersects(Boulders[i].objectRect)){
			System.out.println("in in");
			yDirection = 0;
			xDirection = 0;
				System.out.println("Boulder Boulder!");
			status = false;
		}
		}

		}
		//System.out.println("after");
		return status;
		
}

public void draw(Graphics g){
	g.drawImage(objectImg, objectRect.x, objectRect.y, null); 
}

public void destroy(){
	objectRect.x = -100;
	objectRect.y = -100;
	objectRect.width = 0;
	objectRect.height = 0;
	status = false;
	world.globalxP =+ 10;
}

}
