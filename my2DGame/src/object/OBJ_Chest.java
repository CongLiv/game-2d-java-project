package object;


import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import entity.Entity;
import main.GamePanel;

public class OBJ_Chest extends Entity{

	
	
	public OBJ_Chest(GamePanel gPanel, int code) {
		super(gPanel);
		this.code = code;
		name = "Chest";
		collision = true;
		entityType = obstacleType;
		haveAnimation = true;
		solidArea = new Rectangle(0, 16, 48, 32);
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
		description = "[" + name + "]" + "\nSomething inside!";
		getImage();
	}
	
	public void getImage() {
		
		idle0 = setupImage("/objects/chest_closed_anim_f0");
		idle1 = setupImage("/objects/chest_closed_anim_f1");
		run0 = setupImage("/objects/chest_closed_anim_f2");
		run1 = setupImage("/objects/chest_closed_anim_f3");
		run2 = setupImage("/objects/chest_closed_anim_f4");
		run3 = setupImage("/objects/chest_closed_anim_f5");
		run4 = setupImage("/objects/chest_closed_anim_f6");
		run5 = setupImage("/objects/chest_closed_anim_f7");
		
		idle0L = setupImage("/objects/chest_opened");
	}
	
	public boolean interact() {
		
		if (open == false) {
			for (int i = 0; i < gPanel.player.inventory.size(); i++)
			{
				if (gPanel.player.inventory.get(i).name == "Key Silver" && gPanel.player.inventory.get(i).decode == code) {
					
					open = true;
					gPanel.player.inventory.remove(i);
					gPanel.ui.addMessage("You opened the chest!"); 
					
					if (gPanel.player.canObtainItem(loot) == false) {
						gPanel.ui.addMessage("But you can't carry anymore!");
						gPanel.playSoundEffect(8);
					}
					else {
						gPanel.ui.addMessage("You obtain the " + loot.name + "!");
						this.loot = null;
						gPanel.playSoundEffect(13);
						gPanel.playSoundEffect(1);
						
					}
		
					return true;
				}
			}
			
			gPanel.gameState = gPanel.dialogueState;
			gPanel.ui.currentDialogue = "You need a key to open this chest!";
			return false;
		}
		
		else {
			loot = null;
			return true;
		}
		
	}
	
	@Override
	public void draw(Graphics2D g2) {
		// TODO Auto-generated method stub
		BufferedImage image = null;
		int screenX = worldX - gPanel.player.worldX + gPanel.player.screenX;
		int screenY = worldY - gPanel.player.worldY + gPanel.player.screenY;
		
		if (worldX + gPanel.tileSize > gPanel.player.worldX - gPanel.player.screenX &&
				worldX - gPanel.tileSize < gPanel.player.worldX + gPanel.player.screenX &&
				worldY + gPanel.tileSize > gPanel.player.worldY - gPanel.player.screenY &&
				worldY - gPanel.tileSize < gPanel.player.worldY + gPanel.player.screenY
				) 
		{
			
			if (spriteNum == 0) image = idle0;
			if (spriteNum == 1) image = idle1;
			if (spriteNum == 2) image = run0;
			if (spriteNum == 3) image = run1;
			if (spriteNum == 4) image = run2;
			if (spriteNum == 5) image = run3;
			if (spriteNum == 6) image = run4;
			if (spriteNum == 7) image = run5;
			if (spriteNum > 7) image = idle0;
			
			
		}
		g2.drawImage(image, screenX, screenY, null);
		
//		// DRAW SOLID AREA
//		g2.setColor(new Color(255, 0, 0, 100));
//		g2.fillRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);
	}
	
	@Override
	public void update() {
	
		checkCollision();
		if (collisionOn == false)
		{
						
			if(direction == "up") worldY -= speed; 
			if(direction == "down") worldY += speed; 
			if(direction == "left") {worldX -= speed; lastDirection = "left";}
			if(direction == "right") {worldX += speed; lastDirection = "right";}
						
		}
		spriteCounter++;
		if (spriteCounter > 5) {
			spriteNum++;
			if (spriteNum == 80) spriteNum = 0;
			spriteCounter = 0;
		}
		
		if (open == true) {
			idle0 = idle0L;
			idle1 = idle0L;
			run0 = idle0L;
			run1 = idle0L;
			run2 = idle0L;
			run3 = idle0L;
			run4 = idle0L;
			run5 = idle0L;
		
		}
	}

	
}
