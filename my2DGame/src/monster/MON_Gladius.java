package monster;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.imageio.ImageIO;

import entity.Entity;
import main.GamePanel;
import main.UtilityTool;
import object.OBJ_Coins;
import object.OBJ_KeyGold;
import object.OBJ_KeySilver;
import object.OBJ_PotionGreen;
import object.OBJ_PotionRed;
import object.OBJ_PotionYellow;
import weapon.WEAP_Sword3;


public class MON_Gladius extends Entity{

	UtilityTool uTool;
	BufferedImage at1, at2, at3, at4, at5, at6, at7, at8;
	BufferedImage at1L, at2L, at3L, at4L, at5L, at6L, at7L, at8L;
	
	
	boolean talk = false;
	
	public MON_Gladius(GamePanel gPanel) {
		super(gPanel);
		// TODO Auto-generated constructor stub
		name = "Gladius";
		speed = 1;
		maxLife = 200;
		life = maxLife;
		entityType = monsterType;
		alive = true;
		dying = false;
		attack = 2;
		defense = 10;
		exp = 200;
		lastDirection = "right";
		cooldownAttack = 150;
		
		solidArea.x = 25;
		solidArea.y = 14;
		solidArea.width = 33;
		solidArea.height = 30;
		solidAreaDefaultX = 25;
		solidAreaDefaultY = 14;
		
	
		attackArea = new Rectangle(0, 0, 48, 48);
		uTool = new UtilityTool();
		getImage();

	}
	
	public void getImage() {
		idle0 = setupImage("/monsters/gladius_run_f1");
		idle1 = setupImage("/monsters/gladius_run_f1");
		run0 = setupImage("/monsters/gladius_run_f1");
		run1 = setupImage("/monsters/gladius_run_f2");
		run2 = setupImage("/monsters/gladius_run_f3");
		run3 = setupImage("/monsters/gladius_run_f4");
		
		idle0L = setupImage("/monsters/gladius_run_L_f1");
		idle1L = setupImage("/monsters/gladius_run_L_f1");
		run0L = setupImage("/monsters/gladius_run_L_f1");
		run1L = setupImage("/monsters/gladius_run_L_f2");
		run2L = setupImage("/monsters/gladius_run_L_f3");
		run3L = setupImage("/monsters/gladius_run_L_f4");
		
		at1 = setupImage("/monsters/gladius_attack_f1");
		at2 = setupImage("/monsters/gladius_attack_f2");
		at3 = setupImage("/monsters/gladius_attack_f3");
		at4 = setupImage("/monsters/gladius_attack_f4");
		at5 = setupImage("/monsters/gladius_attack_f5");
		at6 = setupImage("/monsters/gladius_attack_f6");
		at7 = setupImage("/monsters/gladius_attack_f7");
		at8 = setupImage("/monsters/gladius_attack_f8");
		
		at1L = setupImage("/monsters/gladius_attack_L_f1");
		at2L = setupImage("/monsters/gladius_attack_L_f2");
		at3L = setupImage("/monsters/gladius_attack_L_f3");
		at4L = setupImage("/monsters/gladius_attack_L_f4");
		at5L = setupImage("/monsters/gladius_attack_L_f5");
		at6L = setupImage("/monsters/gladius_attack_L_f6");
		at7L = setupImage("/monsters/gladius_attack_L_f7");
		at8L = setupImage("/monsters/gladius_attack_L_f8");
		
		
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
			if (canDamage == false) {
				if (lastDirection == "right")
				{
					if (direction == "idle") {
						if (spriteNum % 2 == 0) image = idle0;
						else image = idle1;
					}
					
					else {
						if (spriteNum == 0) image = run0;
						if (spriteNum == 1) image = run1;
						if (spriteNum == 2) image = run2;
						if (spriteNum == 3) image = run3;

					}
				}
				
				if (lastDirection == "left")
				{
					
					if (direction == "idle") {
						if (spriteNum % 2 == 0) image = idle0L;
						else image = idle1L;
						
					}
					
					else {
						
						if (spriteNum == 0) image = run0L;
						if (spriteNum == 1) image = run1L;
						if (spriteNum == 2) image = run2L;
						if (spriteNum == 3) image = run3L;

					}
				}
			}
			
			else {
				if (lastDirection == "right")
				{		
					
					if (counterAttack < 5) image = at1;
					else if (counterAttack < 10) image = at2;
					else if (counterAttack < 15) image = at3;
					else if (counterAttack < 20) image = at4;
					else if (counterAttack < 25) image = at5;
					else if (counterAttack < 30) image = at6;
					else if (counterAttack < 35) image = at7;
					else if (counterAttack < 40) image = at8;


				}
				
				if (lastDirection == "left")
				{
					
					if (counterAttack < 5) image = at1L;
					else if (counterAttack < 10) image = at2L;
					else if (counterAttack < 15) image = at3L;
					else if (counterAttack < 20) image = at4L;
					else if (counterAttack < 25) image = at5L;
					else if (counterAttack < 30) image = at6L;
					else if (counterAttack < 35) image = at7L;
					else if (counterAttack < 40) image = at8L;

				}
			}
			
			
			g2.drawImage(image, screenX, screenY, null);
		}
		
		// DRAW HP BAR FOR MONSTER
		gPanel.ui.drawBossLife(this);
		
		// DRAW TRANSPARENTS FOR MONSTER
		if (invincible == true) {
			counterdisplayHPBar = 1;
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
		}
					
		if (dying == true) {
			dyingAnimation(g2);
			gPanel.winBoss1 = 1;
		}
					
		g2.drawImage(image, screenX, screenY, null);
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

	}
	
	
	@Override
	public void update() {
		setAction();
		checkCollision();
			
		if (collisionOn == false)
		{
							
			if(direction == "up") {worldY -= speed; lastDirection2 = "up";}
			if(direction == "down") {worldY += speed; lastDirection2 = "down";}
			if(direction == "left") {worldX -= speed; lastDirection = "left"; lastDirection2 = "left";}
			if(direction == "right") {worldX += speed; lastDirection = "right"; lastDirection2 = "right";}
							
		}

			
		spriteCounter++;
		if (spriteCounter > 8) {
			spriteNum++;
			if (spriteNum == 3) spriteNum = 0;
			spriteCounter = 1;
		}
		
		// COUNTER INVINCIBLE TIME
		if (invincible == true)
		{
			invincibleCounter++;
			if (invincibleCounter > 40) {
				invincible = false;
				invincibleCounter = 0;
			}
		}
		
		
		// 	ATTACK 
		if (attacking == true) counterAttack++;
		if (counterAttack < 15) canDamage = false;
		else if (counterAttack < 39) {
			canDamage = true;
			attack++;
			Attack();
			attack--;
		}
		else if (counterAttack > 40)
		{
			canDamage = false;
		}
		if (counterAttack == cooldownAttack) {
			counterAttack = 0;
			attacking = false;
		}
		
		if (life > 70) {
			checkStartChasingOrNot(gPanel.player, 3, 100);
			if (lastDirection == "right") checkAttackOrNot(50, gPanel.tileSize * 2, gPanel.tileSize * 1);
			else if (lastDirection == "left") checkAttackOrNot(50, gPanel.tileSize * 1, gPanel.tileSize * 1);
			checkStopChasingOrNot(gPanel.player, 4, 50);
		}
		else {
			checkStartChasingOrNot(gPanel.player, 3, 50);
			if (lastDirection == "right") checkAttackOrNot(30, gPanel.tileSize * 2, gPanel.tileSize * 1);
			else if (lastDirection == "left") checkAttackOrNot(30, gPanel.tileSize * 1, gPanel.tileSize * 1);
			checkStopChasingOrNot(gPanel.player, 4, 50);
		}
		
		
	}
	
	public void checkSpeedUp(int rate, int distance) {
		boolean targetInRange = false;
		
		int tileDistance = getTileDistance(gPanel.player);
		
		
		if (tileDistance <= distance) 
			targetInRange = true;
	
		if (targetInRange == true && counterAttack == 0) {
			int i = new Random().nextInt(rate);
			if (i == 0) {speed = 2;}
		}
			
	}
	
	public void checkSpeedDown(int rate, int distance) {
		boolean targetInRange = false;
		
		int tileDistance = getTileDistance(gPanel.player);
		
		
		if (tileDistance > distance) 
			targetInRange = true;
	
		if (targetInRange == true && counterAttack == 0) {
			int i = new Random().nextInt(rate);
			if (i == 0) {speed = 1;}
		}
			
	}
	@Override
	public void setAction() {
		if (onPath == true) {
			searchPath(getGoalCol(gPanel.player), getGoalRow(gPanel.player));
			checkSpeedUp(80, 2);
			checkSpeedDown(40, 3);
		}
		else getRandomDirection();
	}
	
	public void checkAttackOrNot(int rate, int straight, int horizontal) {
		boolean targetInRange = false;
		
		int xDis = getXDistance(gPanel.player);
		int yDis = getYDistance(gPanel.player);
		
		switch(direction) {
		case "left": 
			if (gPanel.player.worldX < worldX && xDis < straight && yDis < horizontal) {
				targetInRange = true;
			}
			break;
			
		case "right":
			if (gPanel.player.worldX > worldX && xDis < straight && yDis < horizontal) {
				targetInRange = true;
			}
			break;
		}
		
		if (targetInRange == true && counterAttack == 0) {
			int i = new Random().nextInt(rate);
			if (i == 0) {				
				attacking = true;
			}
		}
	}
	
	public void Attack() {				// ATTACK BY WEAPOON FOR MONSTER

		if (canDamage == true) {
			int currentWorldX = worldX;
			int currentWorldY = worldY;
			int solidAreaWidth = solidArea.width;
			int solidAreaHeight = solidArea.height;
			
			if (lastDirection == "left") worldX -= 24;
			else if (lastDirection == "right") worldX += 72;
			
			solidArea.width = attackArea.width;
			solidArea.height = attackArea.height;
			checkCollision();
			
			worldX = currentWorldX;
			worldY = currentWorldY;
			solidArea.width = solidAreaWidth;
			solidArea.height = solidAreaHeight;
		}
		
	}
	public BufferedImage setupImage(String imagePath) {
		BufferedImage image = null;
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
			image = uTool.scaleImage(image, gPanel.tileSize * 2, gPanel.tileSize);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return image;
	}
	
	@Override
	public void checkDrop() {
		
		dropItem(new WEAP_Sword3(gPanel));
	}
}
