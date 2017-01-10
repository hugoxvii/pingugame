package littleGame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class NotificationPanel extends JPanel {
	
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
	
	//Message Handling
	private boolean overwrite = false;
	private String overwriteMessageL1;
	private String overwriteMessageL2;
	private String overwriteMessageL3;
		
		public NotificationPanel(){
			setPreferredSize(statusDim);
			setBackground(Color.BLACK);
			
		}
		
		public void updateStatus (int[] status){
			HP = status[0];
			Steps = status[1];
			Fishes = status[2];
			xP = status[3];
			alive = status[4];
			
		}
		
		public void overwriteMessage(String L1, String L2, String L3){
			overwriteMessageL1 = L1;
			overwriteMessageL2 = L2;
			overwriteMessageL3 = L3;
			overwrite = true;
		}
		
		public void cancleOverwrite(){
			overwrite = false;
		}
		

		
		public void draw (Graphics g){
			
			g.setColor(Color.WHITE);
			//g.drawRect(0,0,493,71);
			if(alive==0){
				g.setColor(Color.RED);
				g.drawString("You died with " + xP + "XP!" , 155, 45);
			    
			}
			else if(overwrite){
				g.drawString(overwriteMessageL1,10,20);
				g.drawString(overwriteMessageL2,10,40);
				g.drawString(overwriteMessageL3,10,60);
			}
			else{
				g.drawString("Welcome to 'Yet another Pingugame'! " , 145, 45);
			}
			
					
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
