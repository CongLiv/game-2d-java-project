package weapon;

import entity.Entity;
import main.GamePanel;

public class WEAP_Shield1 extends Entity{

	
	public WEAP_Shield1(GamePanel gPanel) {
		super(gPanel);
		name = "Shield 1";
		entityType = shieldType;
		idle0 = setupImage("/weapons/shield1");
		attackValue = 0;
		defenseValue = 3;
		weaponL = setupImage("/weapons/shield1");
		weaponR = setupImage("/weapons/shield1");
		
		description = "[" + name + "]" + "\nThe shield is made of wood";
		price = 150;
	}
}
