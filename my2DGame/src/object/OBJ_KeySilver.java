package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_KeySilver extends Entity{
	
	
	public OBJ_KeySilver(GamePanel gPanel, int decode) {
		super(gPanel);
		name = "Key Silver";
		this.decode = decode;
		entityType = consumableType;
		canUse = false;
		idle0 = setupImage("/objects/key_silver");
		description = "[" + name + "]" + "\nYou can unlock the chest!";	
	}
	
	public void use() {
		
		gPanel.ui.addMessage(description);
	}
}
