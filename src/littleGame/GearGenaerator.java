package littleGame;

public class GearGenaerator {

	
	
	
	public Item getNewGear (int lvlpoints){
		Item a = new Item(lvlpoints, null, false, null);
		
		/* Step 1 let's decide what Type of Gear we will generate
		 * ## lvlpts need to be at leas as hight as the requirement 
		 * 
		 * Helmet (+armor)
		 * Armor (+armor)
		 * 
		 * 
		 * Cape (+random)
		 * Ring (+random)
		 * Amulet (+random)
		 * 
		 * Sword (+meleedmg)
		 * Shield(+armor)
		 * 
		 * Wand (+zapdmg)
		*/
		
		/* Step 2 let's decide wheather the Gear will have bonus effects
		 * 
		 */
		
		/*Step 3 lets decide what (additional)effects the Gear will have
		 * 
		 * +HP
		 * +Str
		 * +Dex
		 * +Wisdom
		 * 
		 * +Armor
		 * +meleedmg
		 * +zapdmg
		 */
		
		/*Step 4 Lets calculate the values
		 * 
		 */
		
		return a;
	}
	
}
