package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_PotionGreen extends Entity{

	
	public OBJ_PotionGreen(GamePanel gPanel) {
		super(gPanel);
		name = "Green Potion";
		entityType = consumableType;
		idle0 = setupImage("/objects/potion_green");
		description = "[" + name + "]" + "\n+5 your defense point!";
		price = 100;
		stackable = true;
	}
	@Override
	public void use(Entity entity) {
		String text = "You drink the Green Potion!";
		gPanel.player.greenPotionCounter = 3600;
		gPanel.ui.addMessage(text);
	}
}
