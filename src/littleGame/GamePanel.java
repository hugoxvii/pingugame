package littleGame;

import java.awt.*;
import javax.swing.JPanel;
import java.awt.event.*;
import java.io.IOException;

public class GamePanel extends JPanel implements Runnable{
	
	String version = "0.5";
	
	//Double buffering
	private Image dbImage;
	private Graphics dbg;
	
	//JPanel variables
	static final int GWIDTH = 500, GHEIGHT=400;
	static final Dimension gameDim = new Dimension(GWIDTH, GHEIGHT);
	
	//Game variables
	private Thread game;
	private Thread music;
	public volatile boolean running = false;
	private long period = 6*1000000; //ms->nano
	private static final int DELAYS_BEFORE_YIELD = 10;
	
	//World Objects
	World world;
	Player player;
	Object boulder;
	Object[] boulders;
	Chest[] chests;
	Fish[] fishes;
	//Fish fish1;
	//Fish fish2;
	Monster monster1;
	Bolt bolt;
	
	//Directions
	static final int STOP = 0;
	static final int UP = -1;
	static final int RIGHT = 1;
	static final int DOWN = 1;
	static final int LEFT = -1;
		
	public int currentDirectionX=0;
	public int currentDirectionY=0;	
	
	//Game Status variables
	//public boolean inventoryMode = false;
	Inventory inv;
	public boolean titleMode = true;
	Title title;
	Trader kng;
	//public boolean trading = false;
	Item testItem;
	private int delay =0;
	private int aftercast =0;
	
	private boolean UP_Pressed = false;
	private boolean DOWN_Pressed = false;
	private boolean LEFT_Pressed = false;
	private boolean RIGHT_Pressed = false;
	private boolean SHIFT_Pressed = false;
	
		
	protected StatusPanel SP = new StatusPanel();
	protected NotificationPanel NP = new NotificationPanel();
	protected WorldGenerator WG = new WorldGenerator(SP,NP);
	protected WorldCoordinator WC;
	protected Scenario tutorial;
	protected Menu menu;
	protected Dialog dia;
	
	public GamePanel(Display d) throws IOException{
		
		//trying WC version
		player=new Player(NP, SP);
		dia = new Dialog(player);
		WC = new WorldCoordinator(player, NP, SP, dia);
		title = new Title();
		tutorial = new Scenario(player, title,NP, SP, 1,this, WC);
		menu = new Menu(d);
		
		
		
		
		setPreferredSize(gameDim);
		setBackground(Color.WHITE);
		setFocusable(true);
		requestFocus();
		
		addKeyListener(new KeyAdapter(){
			@Override
			public void keyPressed(KeyEvent e){
				if(e.getKeyCode() == KeyEvent.VK_RIGHT){
					player.setCurrentDirectionX(RIGHT);
					RIGHT_Pressed = true;
					if(SHIFT_Pressed){
						player.update();
						player.setCurrentDirectionX(STOP);
					}
					if(player.getInvStatus()) player.inv.navigate(2);		
					if(player.getTradeStatus()) player.currentTrader.Inv.navigate(2);
					if(player.getlootStatus()) player.currentLootTarget.Inv.navigate(2);
					if(player.getChestlootStatus()) player.currentLootChestTarget.Inv.navigate(2);
					if(menu.mode) menu.navigateSound(1);
				}
				if(e.getKeyCode() == KeyEvent.VK_LEFT){
					player.setCurrentDirectionX(LEFT);
					LEFT_Pressed = true;
					if(SHIFT_Pressed){
						player.update();
						player.setCurrentDirectionX(STOP);
					}
					if(player.getInvStatus()) player.inv.navigate(-2);
					if(player.getTradeStatus()) player.currentTrader.Inv.navigate(-2);
					if(player.getlootStatus()) player.currentLootTarget.Inv.navigate(-2);
					if(player.getChestlootStatus()) player.currentLootChestTarget.Inv.navigate(-2);
					if(menu.mode) menu.navigateSound(-1);
				}
				if(e.getKeyCode() == KeyEvent.VK_DOWN){
					player.setCurrentDirectionY(DOWN);
					DOWN_Pressed = true;
					if(SHIFT_Pressed){
						player.update();
						player.setCurrentDirectionY(STOP);
					}
					if(player.getInvStatus()) player.inv.navigate(1);
					if(player.getTradeStatus()) player.currentTrader.Inv.navigate(1);
					if(player.getlootStatus()) player.currentLootTarget.Inv.navigate(1);
					if(player.getChestlootStatus()) player.currentLootChestTarget.Inv.navigate(1);
					if(menu.mode) menu.navigate(1);
					if(dia.mode) {
						
						dia.navigat();
					}
				}
				if(e.getKeyCode() == KeyEvent.VK_UP){
					player.setCurrentDirectionY(UP);
					UP_Pressed = true;
					if(SHIFT_Pressed){
						player.update();
						player.setCurrentDirectionY(STOP);
					}
					if(player.getInvStatus()) player.inv.navigate(-1);
					if(player.getTradeStatus()) player.currentTrader.Inv.navigate(-1);
					if(player.getlootStatus()) player.currentLootTarget.Inv.navigate(-1);
					if(player.getChestlootStatus()) player.currentLootChestTarget.Inv.navigate(-1);
					if(menu.mode) menu.navigate(-1);
					if(dia.mode) dia.navigat();
				}
				if(e.getKeyChar() == 'r'){
					player.respawn();
				}
				if(e.getKeyCode() == KeyEvent.VK_SHIFT){
					SHIFT_Pressed = true;
				}
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					if(menu.mode) menu.enter();
					if(dia.mode) dia.enter();
				}
				
				if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
					
					if(!player.getInvStatus()&&!player.getTradeStatus()&&!player.getlootStatus()&&!player.getChestlootStatus()&&!titleMode){
						if(!menu.mode) menu.openMenu();
						else menu.back();
					}
					
					player.stopTrade();
					player.closeInventory();
					player.stopLooting();
					player.stopChestLooting();
					titleMode=false;
					NP.cancleOverwrite();
					dia.closeDialog();
				}
			
				if(e.getKeyCode() == KeyEvent.VK_SPACE){
					System.out.println("try to attack");
					if(aftercast <=0) {
						player.attack();
						aftercast=50;
						System.out.println("attack!");
					}
				}
				if(e.getKeyChar() == 'q'){
					
					if(player.getTradeStatus() && delay<=0){
						player.openInventory();
						player.stopTrade();
						delay = 70;
						
					}
					else if (player.getInvStatus()&&player.canTrade&&delay<=0){
						player.closeInventory();
						player.initiateTrade();
						delay = 70;
						
					}
				}
				
			
				

			}
			@Override
			public void keyReleased(KeyEvent e){
				if(e.getKeyCode() == KeyEvent.VK_RIGHT){
					RIGHT_Pressed = false;
					if((!LEFT_Pressed)){
						player.setCurrentDirectionX(STOP);
					}
					else
					{
						player.setCurrentDirectionX(LEFT);
					}
				}
				if(e.getKeyCode() == KeyEvent.VK_LEFT){
					LEFT_Pressed = false;
					if((!RIGHT_Pressed)){
						player.setCurrentDirectionX(STOP);
					}
					else
					{
						player.setCurrentDirectionX(RIGHT);
					}
				}
				if(e.getKeyCode() == KeyEvent.VK_DOWN){
					DOWN_Pressed = false;
					if((!UP_Pressed)){
						player.setCurrentDirectionY(STOP);
					}
					else
					{
						player.setCurrentDirectionY(UP);
					}
				}
				if(e.getKeyCode() == KeyEvent.VK_UP){
					UP_Pressed = false;
					if((!DOWN_Pressed)){
						player.setCurrentDirectionY(STOP);
					}
					else
					{
						player.setCurrentDirectionY(DOWN);
					}
				}
				if(e.getKeyCode() == KeyEvent.VK_SHIFT){
					SHIFT_Pressed = false;
				}
			}
			@Override
			public void keyTyped(KeyEvent e){
				if(e.getKeyChar() == 'i'){
					if(!player.getTradeStatus()){
					if(player.getInvStatus()){
						player.closeInventory();
						NP.cancleOverwrite();
					}
					else if(!player.getInvStatus()) player.openInventory();
					}

				}
				if(e.getKeyChar() == 't'){
					if(titleMode) titleMode=false;
					else titleMode=true;
				}
				if(e.getKeyChar() == 'f'&&delay<=0){
					player.act();
					delay=70;
				}
				if(e.getKeyChar() == 'k'){
					player.kick();
				}
				if(e.getKeyChar() == 'z'&&delay<=0){
					player.fire();
					delay=120;
				}
				if(e.getKeyChar() == 'e'&&player.getInvStatus()&&delay<=0){
					player.equip();
					delay = 70;
				}
				
			}
		});
	}
	
	private void startGame(){
		if(game == null|| !running){
			game = new Thread(this);
			game.start();
			running = true;
			
			
			
			
			
		}
	}
	
	public void stopGame(){
		if(running){
			running=false;			
		}
	}
	
	public boolean getGamestatus(){
		if (running){
			return true;
		}
		else
			return false;
	}

	@Override
	public void run() {
		long beforeTime, afterTime, diff, sleepTime, overSleepTime = 0;
		int delays = 0;
		while(running){
			
			beforeTime = System.nanoTime();
			gameUpdate();
			gameRender();
			paintScreen();
			
			SP.updateStatus(player.getStatus());
			SP.statusRender();
			SP.paintStatusScreen();
			NP.updateStatus(player.getStatus());
			NP.statusRender();
			NP.paintStatusScreen();
			
			afterTime = System.nanoTime();
			diff=afterTime - beforeTime;
			sleepTime = period - diff - overSleepTime;
			//happy sleep time
			if(sleepTime < period && sleepTime > 0){
				
				try{
					game.sleep(sleepTime/1000000L);
					overSleepTime = 0;
				}
				catch(InterruptedException e){
					//blah
				}
			}
			//the diff was greater than the period
			else if (diff > period){
				overSleepTime = diff - period;
			}
			// Accumulate the amount of delays and eventually yield
			else if (++delays >= DELAYS_BEFORE_YIELD){
				game.yield();
				delays = 0;		
			}		
			else{
				overSleepTime = 0;
				// Print game stat
				log(
						"BeforeTime:    " + beforeTime +"\n"+
						"AfterTime:     " + afterTime + "\n" +
						"Diff:          " + diff + "\n" +
						"SleepTime:     " + sleepTime / 1000000L + "\n" +
						"OverSleepTime: " + overSleepTime / 1000000L + "\n" +
						"Delays:        " + delays + "\n" 
						);
			}
		}
	}
	
	private void gameUpdate(){
		if(running && game != null){
			tutorial.update();
			if (delay>0) delay--;
			if (aftercast>0) aftercast--;
			
			//trading = player.getTradeStatus();
			//update Game state
			if(!player.getInvStatus()&&!player.getTradeStatus()&&!player.getlootStatus()&&!player.getChestlootStatus()&&!menu.mode&&!dia.mode){
				WC.update();
			}
			else{
				
			}
		}
	}
	
	private void gameRender(){
		if(dbImage == null){
			dbImage = createImage(GWIDTH, GHEIGHT);
			if(dbImage == null){
				System.err.println("dbImage is still null!");
				return;
			}
			else{
				dbg = dbImage.getGraphics();
			}
		}
		//Clear the screen
		dbg.setColor(Color.WHITE);
		dbg.fillRect(0,0, GWIDTH, GHEIGHT);
		//Draw Game Elements
		draw(dbg);		
	}
	
	public void draw (Graphics g){
		
		WC.draw(g);

		//world.draw(g);
		//WG.drawFishes(fishes, g);
		//WG.drawChests(chests, g);
		//WG.drawBoulders(boulders, g);
		//player.draw(g);
		//monster1.draw(g);
		//kng.draw(g);
		
		//if(bolt.active)bolt.draw(g);
		if(player.getInvStatus()) player.inv.draw(g);
		if(player.getTradeStatus()) player.currentTrader.Inv.draw(g);
		if(player.getlootStatus()) player.currentLootTarget.Inv.draw(g);
		if(player.getChestlootStatus()) player.currentLootChestTarget.Inv.draw(g);
		if(titleMode) title.draw(g);
		if(menu.mode) menu.draw(g);
		if (dia.mode) dia.draw(g);
		
		
	}
	
	private void paintScreen(){
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
	
	protected void setPanels(StatusPanel sp,NotificationPanel np){
		this.SP = sp;
		this.NP = np;
		player.inv.setPanels(sp, np);
	}
	
	@Override
	public void addNotify(){
		super.addNotify();
		startGame();
	}
	
	public static void main(String[] args) {
		
	}

	private void log(String s){
		//System.out.println(s);
	}
}

