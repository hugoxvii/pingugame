package littleGame;

import java.awt.Image;
import java.awt.Rectangle;

public class Trader extends NPC {
	
	protected Inventory Inv;
	
	
	//Picture 
	protected static String PATH="Images/kng.png";
	protected Image ItmImg;
	
	public Trader (NotificationPanel NP, StatusPanel SP, int x, int y){
		super(PATH, x, y);
		Inv = new Inventory(NP, SP, false, "Kangaroo", false);
		Inv.canSell=true;
		
	}
	


}
