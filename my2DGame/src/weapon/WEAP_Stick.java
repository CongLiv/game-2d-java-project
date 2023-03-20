package weapon;

import entity.Entity;
import main.GamePanel;

public class WEAP_Stick extends Entity{

	
	public WEAP_Stick(GamePanel gPanel) {
		super(gPanel);
		name = "Stick";
		entityType = swordType;
		idle0 = setupImage("/weapons/stick");
		attackValue = 0;
		defenseValue = 0;
		weaponL = setupImage("/weapons/stick_L");
		weaponR = setupImage("/weapons/stick");
		attackL = setupImage("/weapons/stick_attack_L");
		attackR = setupImage("/weapons/stick_attack");
		description = "[" + name + "]" + "\nA normal stick";
		price = 10;
	}

}
