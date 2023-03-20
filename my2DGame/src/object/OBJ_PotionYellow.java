package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_PotionYellow extends Entity{
	
	public OBJ_PotionYellow(GamePanel gPanel) {
		super(gPanel);
		name = "Yellow Potion";
		entityType = consumableType;
		idle0 = setupImage("/objects/potion_yellow");
		description = "[" + name + "]" + "\n+5 your attack point!";
		price = 100;
		stackable = true;
	}
	
	public void use(Entity entity) {
		String text = "You drink the Yellow Potion!";
		gPanel.player.yellowPotionCounter = 3600;
		gPanel.ui.addMessage(text);
		
	}
}
