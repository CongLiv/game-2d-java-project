package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Coins extends Entity{

	public OBJ_Coins(GamePanel gPanel) {
		super(gPanel);
		entityType = pickUpOnly;
		name = "Coins";
		idle0 = setupImage("/objects/bag_coins");
		coin = 5;
		
	}
	
	public void use(Entity entity) {
		String text = "+ " + this.coin + " coins";
		entity.coin += this.coin;
		gPanel.ui.addMessage(text);
		
	}
	
	@Override
	public void checkDrop() {
		// TODO Auto-generated method stub
		
		
	}

}
