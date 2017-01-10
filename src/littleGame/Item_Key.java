package littleGame;

import java.awt.Image;
import java.io.File;

import javax.swing.ImageIcon;

public class Item_Key extends Item {
	
	//Key attributes of any Item
	public static int type = 1;
	public static String discriptionText = "a Key!";
	public static boolean stackable = false;
	//stackable Items
	public int amount = 1;
	
	//Picture
	protected static String ItmPATH="Images/Items/key.png";
	protected Image ItmImg;
	

	public Item_Key() {
		super(type, discriptionText, stackable, ItmPATH);
		
		
		
		
		
		
	}

}
