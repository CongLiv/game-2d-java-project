package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_KeyGold extends Entity{
	
	
	public OBJ_KeyGold(GamePanel gPanel, int decode) {
		super(gPanel);
		name = "Key Gold";
		this.decode = decode;
		entityType = consumableType;
		canUse = false;
		idle0 = setupImage("/objects/key_gold");
		description = "[" + name + "]" + "\nYou can unlock the door!";	
	}
	
	public void use() {
		
		gPanel.ui.addMessage(description);
	}
}
