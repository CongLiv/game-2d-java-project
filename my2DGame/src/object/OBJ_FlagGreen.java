package object;

import java.awt.Graphics2D;

import entity.Entity;
import main.GamePanel;

public class OBJ_FlagGreen extends Entity{
	
	public OBJ_FlagGreen(GamePanel gPanel) {
		super(gPanel);

		name = "Green Flag";
		collision = true;
		entityType = obstacleType;
		
		description = "[" + name + "]" + "\nGreen Flat";
		getImage();
	}
	
	public void getImage() {

		idle0 = setupImage("/objects/flag_green");
	}
	
	
	@Override
	public void draw(Graphics2D g2) {
		// TODO Auto-generated method stub
		int screenX = worldX - gPanel.player.worldX + gPanel.player.screenX;
		int screenY = worldY - gPanel.player.worldY + gPanel.player.screenY;
		
		if (worldX + gPanel.tileSize > gPanel.player.worldX - gPanel.player.screenX &&
				worldX - gPanel.tileSize < gPanel.player.worldX + gPanel.player.screenX &&
				worldY + gPanel.tileSize > gPanel.player.worldY - gPanel.player.screenY &&
				worldY - gPanel.tileSize < gPanel.player.worldY + gPanel.player.screenY
				) 
		{
		g2.drawImage(idle0, screenX, screenY, null);
			
		}
		
	}
	
	public void update() {

	}
}
