package object;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import entity.Entity;
import main.GamePanel;

public class OBJ_BookShelf extends Entity{
	public OBJ_BookShelf(GamePanel gPanel) {
		super(gPanel);

		name = "Book Shelf";
		collision = true;
		entityType = obstacleType;
		description = "[" + name + "]" + "\nRead some books?";
		
		solidArea = new Rectangle(0, 5, 48, 43);
		getImage();
	}
	
	public void getImage() {

		idle0 = setupImage("/objects/bookshelf");
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