package littleGame;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Chest extends NPC {
	
	protected boolean open = false;
	protected Inventory Inv;
	protected Image OImg;
	
	
	//Picture 
	protected static String PATH="Images/chest_closed.png";
	protected static String OPATH="Images/chest_open.png";
	
	
	public Chest (NotificationPanel NP, StatusPanel SP, int x, int y){
		super(PATH, x, y);
		Inv = new Inventory(NP, SP, false, "wooden Chest", false);
		Inv.canSell=false;
		
		OPATH = OPATH.replace("\\", "/");
		OImg = new ImageIcon(OPATH).getImage();
		
		
	}
	
	public void openChest(){
		open = true;
		this.NImg = OImg;
	}
	


}