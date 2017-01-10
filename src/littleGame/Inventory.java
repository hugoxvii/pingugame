package littleGame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;

import javax.swing.ImageIcon;

public class Inventory {
	
	private String InvBackPATH;
	private Image  InvBackImg;
	private Image InvBackPlImg;
	
	protected boolean visible;
	protected int row = 0;
	protected int collumn = 0;
	private int maxrow = 3;
	private int maxcollumn = 4;
	protected boolean canSell = false;
	
	
	private StatusPanel SP;
	static NotificationPanel NP;
	
	protected Item[] inventory;
	private int size=20;
	
	private boolean playerInv;
	private String Owner;
	private boolean lootInv;
	
	public Inventory(NotificationPanel np, StatusPanel sp, boolean pI, String owner, boolean lI){
		
		this.SP = sp;
		this.NP = np;
		this.playerInv = pI;
		this.Owner = owner;
		this.lootInv = lI;

		String InvBackPATH = new File ("Images/inventory.png").getAbsolutePath();
		InvBackPATH = InvBackPATH.replace("\\", "/");
		InvBackImg = new ImageIcon(InvBackPATH).getImage();
		String InvBackPlPATH = new File ("Images/inventory_pl.png").getAbsolutePath();
		InvBackPlPATH = InvBackPlPATH.replace("\\", "/");
		InvBackPlImg = new ImageIcon(InvBackPlPATH).getImage();
		
		inventory = new Item[size];
		
		for(int i=0; i<size;i++){
			inventory[i] = new Item_Empty();
		}
		
	}
	
	public void navigate(int dir){
		if(dir==1||dir==-1){
			row += dir;
			if (row >maxrow) row=0;
			if (row <0) row=maxrow;
		}
		if(dir==2||dir==-2){
			collumn += dir/2;
			if (collumn >maxcollumn) collumn=0;
			if (collumn <0) collumn = maxcollumn;
		}
		
	}
	
	public void update(){
		
	}
	
	public Item produceItem(){
		
		Item a = inventory[row*(maxcollumn+1)+collumn];
		
		if(a.stackable&&a.amount>1) {
			a.amount--;
			Item b = getFreshItem(a.type);
			a=b;
		}
		else{
			inventory[row*(maxcollumn+1)+collumn] = new Item_Empty();
		}
		return a;
	}
	public Item produceItemXY(int row, int collumn){
		
		Item a = inventory[row*(maxcollumn+1)+collumn];
		
		if(a.stackable&&a.amount>1) {
			a.amount--;
			Item b = getFreshItem(a.type);
			a=b;
		}
		else{
			inventory[row*(maxcollumn+1)+collumn] = new Item_Empty();
		}
		return a;
	}
	public int[] searchItem(Item a){
		int[] b = new int[2];
		b[0]=-1;
		b[1]=-1;
		
		for(int i=0; i<size;i++){
			if (inventory[i].type==a.type) {
				b[0] = i%(maxcollumn+1);
				b[1] = i/(maxcollumn+1);
			}
		}
		
		return b;
		
	}
	
	public Item getItem(int ROW, int COLLUMN){
		return inventory[ROW*(maxcollumn+1)+COLLUMN];
	}
	public void destroyItem(int ROW, int COLLUMN){
		inventory[ROW*(maxcollumn+1)+COLLUMN] = new Item_Empty();;
	}
	public boolean gotSpace(){
		boolean a = false;
		for(int i=0; i<size;i++){
			if (inventory[i].type==0) a=true;
		}
		return a;
	}
	public void addItem(Item a){
		boolean done = false;
		for(int i=0; i<size;i++){
			if(inventory[i].type==a.type&&a.stackable&&!done){
				inventory[i].amount++;
				done=true;
			}
		}
		for(int i=0; i<size;i++){
			if (inventory[i].type==0&&!done ){
				inventory[i] = a;
				done=true;
			}
		}
	}
	public void equip(){
		if( getItem(row, collumn).equipable&&playerInv){
			getItem(row, collumn).equiped=true;
			//System.out.println(getItem(row, collumn).equiped);
		}
	}
	
	
	public void draw(Graphics g){
		if(playerInv)g.drawImage(InvBackPlImg, 50, 50, null); 
		else g.drawImage(InvBackImg, 50, 50, null); 
		g.setColor(Color.WHITE);
		g.drawRect(62+collumn*75,62+row*75,50,50); 
		if(playerInv)NP.overwriteMessage("Thise is your Inventory", "", inventory[row*(maxcollumn+1)+collumn].discriptionText);
		else if (lootInv)NP.overwriteMessage("You can loot " + Owner + "s body.", "", inventory[row*(maxcollumn+1)+collumn].discriptionText);
		else NP.overwriteMessage("This is what" + Owner + " has to offer!", "", inventory[row*(maxcollumn+1)+collumn].discriptionText);
		
		for(int i=0;i<size;i++){
			g.drawImage(inventory[i].ItmImg, 62+(i%(maxcollumn+1))*75, 62+(i/(maxcollumn+1))*75, null); 
			if(inventory[i].stackable) g.drawString(inventory[i].amount + "", 62+(i%(maxcollumn+1))*75+35, 62+(i/(maxcollumn+1))*75+50);
			if(inventory[i].equiped){
				g.setColor(Color.PINK);
				 g.drawRect(62+(i%(maxcollumn+1))*75, 62+(i/(maxcollumn+1))*75, 50,50); 
				g.setColor(Color.WHITE);
			}
			}
		if(canSell&&playerInv)g.drawString("Press f to sell items. Press q to switch to your Inventory and sell items.", 55, 345);
		if(canSell&&!playerInv)g.drawString("Press f to buy items. Press q to switch to buy items from " + Owner+ ".", 55, 345);
			
			
		}
	
	public Item getFreshItem(int type){
		Item a;
		switch(type){
		default:
			a = new Item_Empty();
			break;
		case 0:
			a = new Item_Empty();
			break;
		case 1:
			a = new Item_Key();			
			break;
		case 2:
			a = new Item_Fish();
			break;
			}
		return a;
	}
		
		
	
	protected void setPanels(StatusPanel sp,NotificationPanel np){
		this.SP = sp;
		this.NP = np;
	}

}
