package littleGame;

import java.awt.Image;
import java.io.File;

import javax.swing.ImageIcon;

public class Item_Wand extends Item {
	
	//Key attributes of any Item
	public static int type = 2;
	public static String discriptionText = "looks like...a magic wand?!";
	public static boolean stackable = false;
	//stackable Items
	public int amount = 1;
	
	
	
	//Picture
	protected static String ItmPATH="Images/Items/wand.png";
	protected Image ItmImg;
	
	

	public Item_Wand() {
		super(type, discriptionText, stackable, ItmPATH);
		//equipable items
		equipable =true;
		
		
		
		
		
	}

}
