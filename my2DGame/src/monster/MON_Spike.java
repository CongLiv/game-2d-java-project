package monster;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import entity.Entity;
import main.GamePanel;
import object.OBJ_Coins;
import object.OBJ_PotionGreen;
import object.OBJ_PotionRed;
import object.OBJ_PotionYellow;

public class MON_Spike extends Entity{
	
	public MON_Spike(GamePanel gPanel) {
		super(gPanel);
		// TODO Auto-generated constructor stub
		name = "Spike";
		speed = 0;
		maxLife = 999;
		life = maxLife;
		entityType = monsterType;
		alive = true;
		dying = false;
		attack = 1;
		defense = 999;
		collision = false;
		getImage();
	}
	
	public void getImage() {
		idle0 = setupImage("/monsters/spikes_anim_f0");
		idle1 = setupImage("/monsters/spikes_anim_f1");
		run0 = setupImage("/monsters/spikes_anim_f2");
		run1 = setupImage("/monsters/spikes_anim_f3");
		run2 = setupImage("/monsters/spikes_anim_f4");
		run3 = setupImage("/monsters/spikes_anim_f5");
		run4 = setupImage("/monsters/spikes_anim_f6");
		run5 = setupImage("/monsters/spikes_anim_f7");
		
		idle0L = setupImage("/monsters/spikes_anim_f8");
		idle1L = setupImage("/monsters/spikes_anim_f9");

	}

	
	public void setAction() {
		if (onPath == true) {
			int goalCol = (gPanel.player.worldX + gPanel.player.solidArea.x) / gPanel.tileSize;
			int goalRow = (gPanel.player.worldY + gPanel.player.solidArea.y) / gPanel.tileSize;
			searchPath(goalCol, goalRow);
		}
		
		else {
			actionLockCounter++;
			if (actionLockCounter > 120)
			{
				Random random = new Random();
				int ranNum = random.nextInt(5) + 1;
				if (ranNum == 1) direction = "up";
				else if (ranNum == 2) direction = "down";
				else if (ranNum == 3) direction = "left";
				else if (ranNum == 4) direction = "right";
				else direction = "idle";
				actionLockCounter = 0;
			}
		}
	}
	
	@Override
	public void damageReaction() {
		
		actionLockCounter++;
		direction = gPanel.player.lastDirection;
		onPath = true;
	}
	
	@Override
	public void update() {
		super.update();
		
		spriteNum++;
		
		if (spriteNum <= 70 || spriteNum >= 140) solidArea = new Rectangle(0, 0, 0, 0);
		else if (spriteNum < 135) solidArea = new Rectangle(3, 3, 45, 45);
		
		if (spriteNum == 250) spriteNum = 0;
	}
	

	
	@Override
	public void draw(Graphics2D g2) {
		
		BufferedImage image = null;
		int screenX = worldX - gPanel.player.worldX + gPanel.player.screenX;
		int screenY = worldY - gPanel.player.worldY + gPanel.player.screenY;
		
		if (worldX + gPanel.tileSize > gPanel.player.worldX - gPanel.player.screenX &&
				worldX - gPanel.tileSize < gPanel.player.worldX + gPanel.player.screenX &&
				worldY + gPanel.tileSize > gPanel.player.worldY - gPanel.player.screenY &&
				worldY - gPanel.tileSize < gPanel.player.worldY + gPanel.player.screenY
				) 
		{
			if (spriteNum < 10) image = idle0;
			else if (spriteNum < 20) image = idle1; 
			else if (spriteNum < 30) image = run0;
			else if (spriteNum < 40) image = run1;
			else if (spriteNum < 50) image = run2;
			else if (spriteNum < 60) image = run3;
			else if (spriteNum < 70) image = run4;
			else if (spriteNum < 120) image = run5;
			else if (spriteNum < 130) image = idle0L;
			else if (spriteNum < 140) image = idle1L;
			else image = idle0;
		}
			
		g2.drawImage(image, screenX, screenY, null);
		
//		// DRAW SOLID AREA
//		g2.setColor(new Color(255, 0, 0, 100));
//		g2.fillRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);
	}
}
