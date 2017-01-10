package littleGame;

public class Scenario {

	private Player player;
	private Title title;
	
	private int scenario;
	
	private NotificationPanel np;
	private StatusPanel sp;
	private GamePanel gp;
	private WorldCoordinator wc;
	
	private int act=0;
	
	public Scenario(Player p, Title t,NotificationPanel n, StatusPanel s, int scen, GamePanel g, WorldCoordinator WC ){
		this.player =p;
		this.title=t;
		this.scenario=scen;
		this.sp=s;
		this.np=n;	
		this.gp=g;
		this.wc=WC;
		
		//prepare scenario
		if (scenario==1){
			
			//place Items in inventories
			Item a = new Item_Wand();
			WC.chests[0].Inv.addItem(a);
			WC.kng.Inv.addItem(a);
			
			//Spawn a bossmonster that is not painted in the map
			Monster[]monsters=WC.WG.getMonsters();
			Monster[] newmonsters = new Monster[monsters.length+1];
			for (int i=0;i<monsters.length;i++){
				newmonsters[i]=monsters[i];
			}
			newmonsters[monsters.length] = new Bossmonster(620, 1540, np, sp, monsters[monsters.length-1].id+1);
			WC.monsters=newmonsters;
			WC.inform();
		}
	}
	public void update(){
		if (scenario==1){
			
			updateTut();
		}
	}
	public void updateTut(){
		
		if (player.playerRect.x-player.world.xFromHome>660&&act==0){
			act=1;
			title.setTutStage(1);
			gp.titleMode = true;
		}
		if (player.playerRect.x-player.world.xFromHome>1420&&act==1){
			act=2;
			title.setTutStage(2);
			gp.titleMode = true;
		}
		if (player.playerRect.x-player.world.xFromHome>1660&&player.playerRect.y-player.world.yFromHome>560&&act==2){
			act=3;
			title.setTutStage(3);
			gp.titleMode = true;
		}
		if (wc.chests[0].open&&act==3){
			act=4;
			title.setTutStage(4);
			gp.titleMode = true;
		}
		
	}
	
}
