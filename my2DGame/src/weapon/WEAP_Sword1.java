package weapon;


import entity.Entity;
import main.GamePanel;

public class WEAP_Sword1 extends Entity{

	
	public WEAP_Sword1(GamePanel gPanel) {
		super(gPanel);
		name = "Sword 1";
		entityType = swordType;
		idle0 = setupImage("/weapons/sword1");
		attackValue = 1;
		defenseValue = 0;
		weaponL = setupImage("/weapons/sword1_L");
		weaponR = setupImage("/weapons/sword1");
		attackL = setupImage("/weapons/sword_attack_L");
		attackR = setupImage("/weapons/sword_attack");
		description = "[" + name + "]" + "\nThe fine sword makes you feel \nstronger";
		price = 200;
	}

	
}
