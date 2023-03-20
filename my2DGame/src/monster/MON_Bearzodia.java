package monster;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.lang.model.element.Element;

import entity.Entity;
import main.GamePanel;
import main.UtilityTool;

public class MON_Bearzodia extends Entity{

	boolean talk = false;
	
	UtilityTool uTool;
	BufferedImage at1, at2, at3, at4, at5, at6, at7, at8;
	BufferedImage at1L, at2L, at3L, at4L, at5L, at6L, at7L, at8L;
	
	public MON_Bearzodia(GamePanel gPanel) {
		super(gPanel);
		// TODO Auto-generated constructor stub
		name = "Bearzodia";
		speed = 1;
		maxLife = 300;
		life = maxLife;
		entityType = monsterType;
		alive = true;
		dying = false;
		attack = 3;
		defense = 15;
		exp = 400;
		lastDirection = "right";
		cooldownAttack = 200;
		
		
		solidArea.x = 100;
		solidArea.y = 10;
		solidArea.width = 90;
		solidArea.height = 78;
		solidAreaDefaultX = 100;
		solidAreaDefaultY = 10;
		
	
		attackArea = new Rectangle(0, 0, 384, 192);
		uTool = new UtilityTool();
		getImage();

	}
	
	public void getImage() {
		idle0 = setupImage("/monsters/bearzodia_run_f1");
		idle1 = setupImage("/monsters/bearzodia_run_f1");
		run0 = setupImage("/monsters/bearzodia_run_f1");
		run1 = setupImage("/monsters/bearzodia_run_f2");
		run2 = setupImage("/monsters/bearzodia_run_f3");
		run3 = setupImage("/monsters/bearzodia_run_f4");
		
		idle0L = setupImage("/monsters/bearzodia_run_L_f1");
		idle1L = setupImage("/monsters/bearzodia_run_L_f1");
		run0L = setupImage("/monsters/bearzodia_run_L_f1");
		run1L = setupImage("/monsters/bearzodia_run_L_f2");
		run2L = setupImage("/monsters/bearzodia_run_L_f3");
		run3L = setupImage("/monsters/bearzodia_run_L_f4");
		
		at1 = setupImage("/monsters/bearzodia_attack_f1");
		at2 = setupImage("/monsters/bearzodia_attack_f2");
		at3 = setupImage("/monsters/bearzodia_attack_f3");
		at4 = setupImage("/monsters/bearzodia_attack_f4");
		at5 = setupImage("/monsters/bearzodia_attack_f5");
		at6 = setupImage("/monsters/bearzodia_attack_f6");
		at7 = setupImage("/monsters/bearzodia_attack_f7");
		at8 = setupImage("/monsters/bearzodia_attack_f8");
		
		at1L = setupImage("/monsters/bearzodia_attack_L_f1");
		at2L = setupImage("/monsters/bearzodia_attack_L_f2");
		at3L = setupImage("/monsters/bearzodia_attack_L_f3");
		at4L = setupImage("/monsters/bearzodia_attack_L_f4");
		at5L = setupImage("/monsters/bearzodia_attack_L_f5");
		at6L = setupImage("/monsters/bearzodia_attack_L_f6");
		at7L = setupImage("/monsters/bearzodia_attack_L_f7");
		at8L = setupImage("/monsters/bearzodia_attack_L_f8");
		
		
	}

	@Override
	public void draw(Graphics2D g2) {
		// TODO Auto-generated method stub
		BufferedImage image = null;
		int screenX = worldX - gPanel.player.worldX + gPanel.player.screenX;
		int screenY = worldY - gPanel.player.worldY + gPanel.player.screenY;
		
		if (worldX + gPanel.tileSize * 2 > gPanel.player.worldX - gPanel.player.screenX &&
				worldX - gPanel.tileSize * 2 < gPanel.player.worldX + gPanel.player.screenX &&
				worldY + gPanel.tileSize * 2 > gPanel.player.worldY - gPanel.player.screenY &&
				worldY - gPanel.tileSize * 2 < gPanel.player.worldY + gPanel.player.screenY
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
//		if (entityType == monsterType && (invincible == true || counterdisplayHPBar > 0)) {
//			counterdisplayHPBar++;
//			if (counterdisplayHPBar > 600) counterdisplayHPBar = 0;
			gPanel.ui.drawBossLife(this);
//		}
		
		// DRAW TRANSPARENTS FOR MONSTER
		if (invincible == true) {
			counterdisplayHPBar = 1;
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
		}
					
		if (dying == true) {
			dyingAnimation(g2);
			gPanel.winBoss2 = 1;
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
		
		if (life > 100) {
			checkStartChasingOrNot(gPanel.player, 5, 100);
			if (lastDirection == "left") checkAttackOrNot(100, gPanel.tileSize * 1, gPanel.tileSize * 2);
			else if (lastDirection == "right") checkAttackOrNot(90, gPanel.tileSize * 6, gPanel.tileSize * 2);
			checkStopChasingOrNot(gPanel.player, 10, 100);
		}
		else {
			checkStartChasingOrNot(gPanel.player, 5, 75);
			if (lastDirection == "left") checkAttackOrNot(80, gPanel.tileSize * 1, gPanel.tileSize * 2);
			else if (lastDirection == "right") checkAttackOrNot(70, gPanel.tileSize * 6, gPanel.tileSize * 2);
			checkStopChasingOrNot(gPanel.player, 10, 50);
		}
		
	}
	
	@Override
	public void setAction() {
		if (onPath == true) {
			searchPath(getGoalCol(gPanel.player), getGoalRow(gPanel.player));
		}
		else getRandomDirection();
	}
	
	@Override
	public void searchPath(int goalCol, int goalRow) {
		
		
		int startCol = (worldX + 80) / gPanel.tileSize;
		int startRow = (worldY + 5) / gPanel.tileSize;
		
		gPanel.pFinder.setNodes(startCol, startRow, goalCol, goalRow, this);
		
		if (gPanel.pFinder.search() == true) {
			
			// Next worldX & worldY
			int nextX = gPanel.pFinder.pathList.get(0).col * gPanel.tileSize;
			int nextY = gPanel.pFinder.pathList.get(0).row * gPanel.tileSize;
			
			// Entity's solidArea position
			int enLeftX = worldX + 80;
			int enRightX = worldX + 80 + 30;
			int enTopY = worldY + 5;
			int enBottomY = worldY + 5 + 42;
			
			if (enTopY > nextY && enLeftX >= nextX && enRightX < nextX + gPanel.tileSize) direction = "up";
			else if (enTopY < nextY && enLeftX >= nextX && enRightX < nextX + gPanel.tileSize) direction = "down";
			else if (enTopY >= nextY && enBottomY < nextY + gPanel.tileSize)
			{
				if (enLeftX > nextX) direction = "left"; 
				if (enLeftX < nextX) direction = "right";
			}
			
			else if (enTopY > nextY && enLeftX > nextX) {
				direction = "up";
				checkCollision();
				if (collisionOn == true) {
					direction = "left";
				}
			}
			
			else if (enTopY > nextY && enLeftX < nextX) {
				direction = "up";
				checkCollision();
				if (collisionOn == true) {
					direction = "right";
				}
			}
			
			else if (enTopY < nextY && enLeftX > nextX) {
				direction = "down";
				checkCollision();
				if (collisionOn == true) {
					direction = "left";
				}
			}
			else if (enTopY < nextY && enLeftX < nextX) {
				direction = "down";
				checkCollision();
				if (collisionOn == true) {
					direction = "right";
				}
				
			}
					
			
		}
	}

	
	public void Attack() {				// ATTACK BY WEAPOON FOR MONSTER

		if (canDamage == true) {
			int currentWorldX = worldX;
			int currentWorldY = worldY;
			int solidAreaWidth = solidArea.width;
			int solidAreaHeight = solidArea.height;
			
			if (lastDirection == "left") worldX -= 96;
			else if (lastDirection == "right") worldX += 0;
			
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
			image = uTool.scaleImage(image, gPanel.tileSize * 6, gPanel.tileSize * 2);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return image;
	}
}
