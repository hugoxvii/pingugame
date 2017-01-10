package littleGame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;


import javax.swing.JPanel;

public class StatusPanel extends JPanel {
	
	private Image dbImage;
	private Graphics dbg;
	
	//JPanel variables
	static final int SWIDTH = 500, SHEIGHT=75;
	static final Dimension statusDim = new Dimension(SWIDTH, SHEIGHT);
	
	//Player Attributes
	private int HP = 250;
	private int Steps = 0;
	private int Fishes = 0;
	private int xP = 0;
	private int alive = 1;
	private int LVL = 1;
		
		public StatusPanel(){
			setPreferredSize(statusDim);
			setBackground(Color.BLACK);
			
		}
		
		public void updateStatus (int[] status){
			HP = status[0];
			Steps = status[1];
			Fishes = status[2];
			xP = status[3];
			alive = status[4];
			LVL = status[5];
			
		}
		
		public void draw (Graphics g){
			
			g.setColor(Color.WHITE);
			//g.drawRect(0,0,493,71);
			g.drawString("HP: " + HP, 10, 20);
			g.drawString("Steps taken: " + Steps, 10, 35);
			g.drawString("XP: " + xP, 10, 50);
			
			g.drawString("Level: " + LVL, 210, 20);
			g.drawString("Version 0.0.9", 400, 20);
			
					
			
		}
		
		protected void statusRender(){
			if(dbImage == null){
				dbImage = createImage(SWIDTH, SHEIGHT);
				if(dbImage == null){
					System.err.println("dbImage is still null!");
					return;
				}
				else{
					dbg = dbImage.getGraphics();
				}
			}
			//Clear the screen
			dbg.setColor(Color.BLACK);
			dbg.fillRect(0,0, SWIDTH, SHEIGHT);
			//Draw Game Elements
			draw(dbg);		
		}
		
		protected void paintStatusScreen(){
			Graphics g;
			try{
				g = this.getGraphics();
				if(dbImage != null && g!= null){
					g.drawImage(dbImage,  0,  0, null);
				}
				Toolkit.getDefaultToolkit().sync(); //For Linux
				g.dispose();
			}
			catch(Exception e){
				System.err.println(e);
			}
		}

}
