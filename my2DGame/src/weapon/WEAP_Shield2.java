package weapon;

import entity.Entity;
import main.GamePanel;

public class WEAP_Shield2 extends Entity{
	

	public WEAP_Shield2(GamePanel gPanel) {
		super(gPanel);
		name = "Shield 2";
		entityType = shieldType;
		idle0 = setupImage("/weapons/shield2");
		attackValue = 1;
		defenseValue = 6;
		weaponL = setupImage("/weapons/shield2");
		weaponR = setupImage("/weapons/shield2");
		description = "[" + name + "]" + "\nThe durable shield made of metal";
		price = 350;
	}
}
