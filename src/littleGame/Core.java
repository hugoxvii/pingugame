package littleGame;



import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.*;

public class Core extends JFrame 
	implements KeyListener,
	ActionListener //,
//	Runnable
	{

	private static final long serialVersionUID = 1L;
	public static int[][] map = new int[10][10];
	public static int playerX;
	public static int oldPlayerX;
	public static int playerY;
	public static int oldPlayerY;
	public static String currentMessage = "";
	
	private Frame mainFrame;
	private static Label headerLabel;
	private static JPanel gamePanel;


	   
	public Core(){		   
	      prepareGUI();
	  }
	
	
	
	public static void main(String [] args){

	//	Core frame = new Core();
		
	//	(new Thread(new Core())).start();
		createNewMap();
		update();
		//move(1);
		
		
	}
	
	public static void start(){
		createNewMap();
		update();
	}
	public static void move (int dir){
		oldPlayerX=playerX;
		oldPlayerY=playerY;
		
		//dir: 1=right 2=down 3=left 4=up
		
		if(dir==1){
			playerX++;
			currentMessage="You have moved right!";
		}
		if(dir==2){
			playerY++;
			currentMessage="You have moved down!";
		}
		if(dir==3){
			playerX--;
			currentMessage="You have moved left!";
		}
		if(dir==4){
			playerY--;
			currentMessage="You have moved up!";
		}
		
		//update the Map
		map[oldPlayerX][oldPlayerY]=0;
		map[playerX][playerY]=1;
		update();
	}
		
	public static void createNewMap(){
		
		currentMessage="A New Map has been Created";
			
			// Create an Empty Map
		int x=0;
		int y=0;
			
		while (y<10){
			while (x<10){
				map [x][y]=0;
				x++;
			}
			x=0;
			y++;
		}
				
		//Set Player Spawn
		playerX=0;
		playerY=0;
		map[playerX][playerY]=1;		
	}
	
	
	public static void update(){
		System.out.println(currentMessage);
		headerLabel.setText(currentMessage);
		System.out.println("");
		String mapCode="<html>";
		BufferedImage empty = null;
		BufferedImage penguin = null;
		try{
			empty = ImageIO.read(new File("test.jpg"));
			penguin = ImageIO.read(new File("test.jpg"));
		}
		catch (IOException ex) {
            // handle exception...
			ex.printStackTrace();
       }
		
		int x=0;
		int y=0;
		while (y<10){
			while (x<10){
				if(map[x][y]==0){
					//System.out.print("*");
					//mapCode= mapCode + "*";
					JLabel img = new JLabel(new ImageIcon(empty));
					gamePanel.add(img);
					
					
				}
				else if (map[x][y]==1){
					//System.out.print("A");
					//mapCode= mapCode + "<img src=\" C:/Users/Kangoo/workspace/littleGame/test.jpg\" alt=\"B\" style= \"width:32px;height:32px;\">";
					JLabel img = new JLabel(new ImageIcon(penguin));
					gamePanel.add(img);
					
				}
				x++;				
			}
			x=0;
			y++;
			//System.out.println("");
			mapCode= mapCode + "<br>";
			
		}
		mapCode= mapCode + "</html>";
		//gamePanel.setText(mapCode);
		System.out.println(mapCode);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyChar()=='d'){
			move(1);
			System.out.println("moved right!");
		}
		if(e.getKeyChar()=='s'){
			move(2);
			System.out.println("moved down!");
		}
		if(e.getKeyChar()=='a'){
			move(3);
			System.out.println("moved left!");
		}
		if(e.getKeyChar()=='w'){
			move(4);
			System.out.println("moved up!");
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	 private void prepareGUI(){
	      mainFrame = new Frame("Yet another Pingugame");
	      mainFrame.addKeyListener(this);
	      mainFrame.setSize(600,600);
	      mainFrame.setLayout(new GridLayout(3, 3));
	      mainFrame.addWindowListener(new WindowAdapter() {
	         public void windowClosing(WindowEvent windowEvent){
	            System.exit(0);
	         }        
	      });    
	   
	      headerLabel = new Label();
	      headerLabel.setAlignment(Label.CENTER);
	      gamePanel = new JPanel();        
	      gamePanel.setAlignmentX(Label.CENTER);
	      gamePanel.setSize(2,1);

	      mainFrame.add(headerLabel);
	      mainFrame.add(gamePanel);
	      mainFrame.setVisible(true);  
	   }



	 /*	@Override
	public void run() {
		//Backround music
		System.out.println("Musik!");
		File Music = new File ("Song1.WAV");
		Sound.music(Music);
		
	} */
	 
}
