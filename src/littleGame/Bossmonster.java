package littleGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.File;
import java.util.Random;

import javax.swing.ImageIcon;

public class Bossmonster extends Monster{
	
	private boolean inReach=false;

	public Bossmonster(int sX, int sY, NotificationPanel NP, StatusPanel SP, int i) {
		super(sX, sY, NP, SP, i);
		

		String playerPATH = new File ("Images/monster/monster2.png").getAbsolutePath();
		playerPATH = playerPATH.replace("\\", "/");
		playerImg = new ImageIcon(playerPATH).getImage();
		aliveImg = new ImageIcon(playerPATH).getImage();
		String deadPATH = new File ("Images/monster/monster2-dead.png").getAbsolutePath();
		deadPATH = deadPATH.replace("\\", "/");
		deadImg = new ImageIcon(deadPATH).getImage();
		String hitPATH = new File ("Images/monster/monster2_hitshadow.png").getAbsolutePath();
		hitPATH = hitPATH.replace("\\", "/");
		hitImg = new ImageIcon(hitPATH).getImage();
				
		playerRect = new Rectangle (SpawnX, SpawnY, 40, 40);
		Inv = new Inventory(NP, SP, false, "Megabubble",true);
		HP=500;
		maxHP=500;
		dMG=5;
		width=40;
		height=40;
		xp = 350;
	}
	@Override
	public void update (){
		
		if (hurttime<=0&&playerImg==hitImg){
			playerImg= aliveImg;
		}
		if(hurttime>0) hurttime--;

		Rectangle seeRect = new Rectangle(playerRect.x-200,playerRect.y-200,440, 440);
		//System.out.println(p1.playerRect.x + " " + p1.playerRect.y);
		//System.out.println("b" + seeRect.x + " " + seeRect.y);
		if(seeRect.intersects(p1.playerRect)&&counter<=0){
			System.out.println("I see you");
			if(p1.playerRect.x < playerRect.x) setCurrentDirectionX(-1);
			if(p1.playerRect.x > playerRect.x) setCurrentDirectionX(1);
			if(p1.playerRect.y < playerRect.y) setCurrentDirectionY(-1);
			if(p1.playerRect.y > playerRect.y) setCurrentDirectionY(1);
			counter=2;
			inReach=true;
		}
	else if (counter<=0){
			Random rand = new Random();
			int randX=rand.nextInt(5)-2;
			int randY=rand.nextInt(5)-2;;
			setCurrentDirectionX(randX);
			setCurrentDirectionY(randY);
			counter=100;
			inReach = false;
		}
			
		counter--;	
		if(counter%3==0){
			move();
		}
		
		
	}
	
	@Override
	public void draw(Graphics g){
		g.drawImage(playerImg, playerRect.x, playerRect.y, null); 
		
		if(inReach&&alive){
			g.setColor(Color.white);
			g.fillRect(100, 360, 300, 15);
			g.setColor(Color.red);
			g.fillRect(100, 360, (300*HP/maxHP), 15);
			g.setColor(Color.black);
			g.drawRect(100, 360, 300, 15);
		}
		
	}
}
