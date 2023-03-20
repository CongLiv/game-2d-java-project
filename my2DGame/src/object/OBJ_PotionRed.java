package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_PotionRed extends Entity{
	
	public OBJ_PotionRed(GamePanel gPanel) {
		super(gPanel);
		name = "Red Potion";
		entityType = consumableType;
		idle0 = setupImage("/objects/potion_red");
		description = "[" + name + "]" + "\nHeals full your life!";
		price = 75;
		stackable = true;
	}
	
	
	public void use(Entity entity) {
		String text = "You drink the Red Potion!";
		entity.life = entity.maxLife;
		gPanel.ui.addMessage(text);
		
	}
}
