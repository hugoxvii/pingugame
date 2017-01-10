package littleGame;

import java.awt.Image;
import java.io.File;

import javax.swing.ImageIcon;

public class Item {
	
	
	public int type;
	public String discriptionText;
	public boolean stackable;
	public boolean equiped = false;
	public boolean equipable = false;
	//stackable Items
	public int amount = 1;
	
	//Picture
		private String ItmPATH;
		protected Image ItmImg;
		
	
	public Item(int t, String text, boolean stack, String PATH){
		this.type = t;
		this.discriptionText = text;
		this.stackable = stack;

				
		this.ItmPATH = new File (PATH).getAbsolutePath();
		ItmPATH = ItmPATH.replace("\\", "/");
		ItmImg = new ImageIcon(ItmPATH).getImage();
		
	}
	public int getAmount(){
		return amount;
	}
	public void setAmount(int a){
		amount=+a;
	}

}
