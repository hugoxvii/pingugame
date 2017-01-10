package littleGame;

import java.awt.Image;
import java.io.File;

import javax.swing.ImageIcon;

public class Item_Fish extends Item {
	
	//Key attributes of any Item
	public static int type = 2;
	public static String discriptionText = "fishy Fish!";
	public static boolean stackable = true;
	//stackable Items
		public int amount = 1;
	
	//Picture
	protected static String ItmPATH="Images/Items/fish.png";
	protected Image ItmImg;
	
	
	

	public Item_Fish() {
		super(type, discriptionText, stackable, ItmPATH);
		
		
		
		
		
		
	}

}
