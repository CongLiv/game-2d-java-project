package monster;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import entity.Entity;
import main.GamePanel;
import object.OBJ_Coins;
import object.OBJ_KeyGold;
import object.OBJ_KeySilver;
import object.OBJ_PotionGreen;
import object.OBJ_PotionRed;
import object.OBJ_PotionYellow;


public class MON_Fly extends Entity{
	
	public MON_Fly(GamePanel gPanel) {
		super(gPanel);
		// TODO Auto-generated constructor stub
		name = "Fly";
		speed = 2;
		maxLife = 12;
		life = maxLife;
		entityType = monsterType;
		alive = true;
		dying = false;
		attack = 1;
		defense = 2;
		exp = 7;
		lastDirection = "right";
		
		solidArea = new Rectangle(9, 9, 30, 25);
		solidAreaDefaultX = 9;
		solidAreaDefaultY = 9;

		getImage();
	}
	
	public void getImage() {
		idle0 = setupImage("/monsters/fly_anim_f0");
		idle1 = setupImage("/monsters/fly_anim_f1");
		run0 = setupImage("/monsters/fly_anim_f2");
		run1 = setupImage("/monsters/fly_anim_f3");
		run2 = setupImage("/monsters/fly_anim_f0");
		run3 = setupImage("/monsters/fly_anim_f1");
		run4 = setupImage("/monsters/fly_anim_f2");
		run5 = setupImage("/monsters/fly_anim_f3");
		
		idle0L = setupImage("/monsters/fly_anim_L_f0");
		idle1L = setupImage("/monsters/fly_anim_L_f1");
		run0L = setupImage("/monsters/fly_anim_L_f2");
		run1L = setupImage("/monsters/fly_anim_L_f3");
		run2L = setupImage("/monsters/fly_anim_L_f0");
		run3L = setupImage("/monsters/fly_anim_L_f1");
		run4L = setupImage("/monsters/fly_anim_L_f2");
		run5L = setupImage("/monsters/fly_anim_L_f3");
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
			if (lastDirection == "right")
			{
				
					if (spriteNum == 0) image = run0;
					if (spriteNum == 1) image = run1;
					if (spriteNum == 2) image = run2;
					if (spriteNum == 3) image = run3;
					if (spriteNum == 4) image = run4;
					if (spriteNum == 5) image = run5;

			}
			
			if (lastDirection == "left")
			{	
					if (spriteNum == 0) image = run0L;
					if (spriteNum == 1) image = run1L;
					if (spriteNum == 2) image = run2L;
					if (spriteNum == 3) image = run3L;
					if (spriteNum == 4) image = run4L;
					if (spriteNum == 5) image = run5L;
			}
			
			
			// DRAW HP BAR FOR MONSTER
			if (entityType == monsterType && (invincible == true || counterdisplayHPBar > 0)) {
				counterdisplayHPBar++;
				if (counterdisplayHPBar > 600) counterdisplayHPBar = 0;
				double oneScale = (double) gPanel.tileSize / this.maxLife;
				double hpBar = oneScale * life;
				
				g2.setColor(new Color(35, 35, 35));
				g2.fillRect(screenX - 2 , screenY - 17, gPanel.tileSize + 4, 14);
				
				g2.setColor(new Color(255, 0, 25));
				g2.fillRect(screenX, screenY - 15, (int)hpBar, 10);
			}
			
			// DRAW TRANSPARENTS FOR MONSTER
			if (invincible == true) {
				counterdisplayHPBar = 1;
				g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
			}
			
			if (dying == true) {
				dyingAnimation(g2);
			}
			
			g2.drawImage(image, screenX, screenY, null);
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
			
		}
		

	}
	
	
	public void setAction() {
		if (onPath == true) {
			searchPath(getGoalCol(gPanel.player), getGoalRow(gPanel.player));
		}
		else getRandomDirection();
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
		
		if (getTileDistance(gPanel.player) <= 3) speed = 3;
		else speed = 2;
		checkStartChasingOrNot(gPanel.player, 5, 200);
		
		checkStopChasingOrNot(gPanel.player, 7, 100);
		
		
	}
	

	
	@Override
	public void checkDrop() {
		
		int i = new Random().nextInt(100) + 1;
		
		if (i <= 60) dropItem(new OBJ_Coins(gPanel));
		
		else if (i <= 65) dropItem(new OBJ_PotionRed(gPanel));
		
		else if (i <= 67) dropItem(new OBJ_PotionGreen(gPanel));
		
		else if (i <= 69) dropItem(new OBJ_PotionYellow(gPanel));
		
		else if (i <= 70) dropItem(new OBJ_KeyGold(gPanel, 3000));
		
		else if (i <= 72) dropItem(new OBJ_KeyGold(gPanel, 6000));
		
		else if (i <= 73) dropItem(new OBJ_KeySilver(gPanel, 3001));
		
		else if (i <= 74) dropItem(new OBJ_KeyGold(gPanel, 7000));
		
		else if (i <= 76) dropItem(new OBJ_KeySilver(gPanel, 7001));
		
		else if (i <= 77) dropItem(new OBJ_KeyGold(gPanel, 8000));
		
	}
}
