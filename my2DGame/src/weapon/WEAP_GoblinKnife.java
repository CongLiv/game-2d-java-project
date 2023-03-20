package weapon;

import entity.Entity;
import main.GamePanel;

public class WEAP_GoblinKnife extends Entity{
	
	public WEAP_GoblinKnife(GamePanel gPanel) {
		super(gPanel);
		name = "Golbin Knife";
		entityType = swordType;
		idle0 = setupImage("/weapons/goblin_knife");
		attackValue = 0;
		defenseValue = 3;
		weaponL = setupImage("/weapons/goblin_knife_L");
		weaponR = setupImage("/weapons/goblin_knife");
		attackL = setupImage("/weapons/sword_attack_L");
		attackR = setupImage("/weapons/sword_attack");
		description = "[" + name + "]" + "\naA goblin's blunt knife";
		price = 20;
	}
}
