package weapon;

import entity.Entity;
import main.GamePanel;

public class WEAP_Sword3 extends Entity{
	
	public WEAP_Sword3(GamePanel gPanel) {
		super(gPanel);
		name = "Sword 3";
		entityType = swordType;
		idle0 = setupImage("/weapons/sword3");
		attackValue = 10;
		defenseValue = 3;
		weaponL = setupImage("/weapons/sword3_L");
		weaponR = setupImage("/weapons/sword3");
		attackL = setupImage("/weapons/sword_attack_L");
		attackR = setupImage("/weapons/sword_attack");
		
		description = "[" + name + "]\nThe strongest sword used by the \nstrongest demon slayer";
		price = 1000;

	}
}
