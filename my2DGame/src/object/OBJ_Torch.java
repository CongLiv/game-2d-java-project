package object;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;




import entity.Entity;
import main.GamePanel;

public class OBJ_Torch extends Entity{
	public OBJ_Torch(GamePanel gPanel) {
		super(gPanel);

		name = "Torch";
		collision = true;
		entityType = obstacleType;
		haveAnimation = true;
		
		
		description = "[" + name + "]" + "\nLight up the way!";
		getImage();
	}
	
	public void getImage() {
		
		
			run0 = setupImage("/objects/torch_anim_f0");
			run1 = setupImage("/objects/torch_anim_f1");
			run2 = setupImage("/objects/torch_anim_f2");
			run3 = setupImage("/objects/torch_anim_f3");
			run4 = setupImage("/objects/torch_anim_f4");
			run5 = setupImage("/objects/torch_anim_f5");
			
		
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
			BufferedImage image = null;
			if (spriteNum == 0) image = run0;
			else if (spriteNum == 1) image = run1;
			else if (spriteNum == 2) image = run2;
			else if (spriteNum == 3) image = run3;
			else if (spriteNum == 4) image = run4;
			else if (spriteNum == 5) image = run5;
			
			g2.drawImage(image, screenX, screenY, null);
			
		}
		
	}
	
	public void update() {

		spriteCounter++;
		if (spriteCounter > 5) {
			spriteNum++;
			if (spriteNum > 5) {
				spriteNum = 0;
			}
			spriteCounter = 0;
		}
	}
}
