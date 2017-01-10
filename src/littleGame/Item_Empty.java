package littleGame;

import java.awt.Image;
import java.io.File;

import javax.swing.ImageIcon;

public class Item_Empty extends Item {
	
	//Key attributes of any Item
	public static int type = 0;
	public static String discriptionText = "empty";
	public static boolean stackable = false;
	//stackable Items
	public int amount = 1;
	
	//Picture
	protected static String ItmPATH="Images/Items/empty.png";
	protected Image ItmImg;
	

	public Item_Empty() {
		super(type, discriptionText, stackable, ItmPATH);
		
		
		
		
		
		
	}

}
