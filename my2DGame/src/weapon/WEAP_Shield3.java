package weapon;

import entity.Entity;
import main.GamePanel;

public class WEAP_Shield3 extends Entity{
	

	public WEAP_Shield3(GamePanel gPanel) {
		super(gPanel);
		name = "Shield 3";
		entityType = shieldType;
		idle0 = setupImage("/weapons/shield3");
		attackValue = 3;
		defenseValue = 12;
		weaponL = setupImage("/weapons/shield3");
		weaponR = setupImage("/weapons/shield3");
		description = "[" + name + "]" + "\nExquisite shield crafted by the \nmost skilled blacksmiths";
		price = 800;
	}
}
