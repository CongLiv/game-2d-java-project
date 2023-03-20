package weapon;

import entity.Entity;
import main.GamePanel;

public class WEAP_Sword2 extends Entity{

	
	public WEAP_Sword2(GamePanel gPanel) {
		super(gPanel);
		name = "Sword 2";
		entityType = swordType;
		idle0 = setupImage("/weapons/sword2");
		attackValue = 4;
		defenseValue = 0;
		weaponL = setupImage("/weapons/sword2_L");
		weaponR = setupImage("/weapons/sword2");
		attackL = setupImage("/weapons/sword_attack_L");
		attackR = setupImage("/weapons/sword_attack");
		description = "[" + name + "]" + "\nThe rare sword found by the \ncrater";
		price = 550;
	}
}
