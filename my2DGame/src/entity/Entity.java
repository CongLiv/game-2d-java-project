package entity;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;
import projectile.Projectile;

public class Entity {
	
	public GamePanel gPanel;
	UtilityTool uTool = new UtilityTool();
	
	public int worldX, worldY;
	
	
	
	// CHARACTER IMAGES 
	public BufferedImage idle0, idle1, run0, run1, run2, run3, run4, run5;
	public BufferedImage idle0L, idle1L, run0L, run1L, run2L, run3L, run4L, run5L;
	
	// WEAPON IMAGES
	public BufferedImage weaponL, weaponR, attackL, attackR;
	
	//	DIRECTION
	public String lastDirection = "right";
	public String lastDirection2 = "right";
	public String direction = "idle";
	
	//  COUNTER
	public int spriteNum = 0;
	public int spriteCounter = 0;
	public int actionLockCounter = 0;
	public int invincibleCounter = 0;
	public int dyingCounter = 0;
	public int counterdisplayHPBar = 0;
	public int animationCounter = 0;
	public int shotCounter = 0;
	
	
	// STATUS
	public boolean invincible = false;
	public boolean attacking = false;
	public boolean alive = true;
	public boolean dying = false;
	public boolean onPath = false;
	
	//ATTACK STATUS
	public boolean canDamage = false;
	public int cooldownAttack;
	public int counterAttack = 0;
	
	// PLAYER ATTRIBUTES
	public int maxLife;
	public final int maxPlayerLife = 4;
	public int life;
	public String name;
	public int speed;
	public int level;
	public int strength;
	public int dexterity;
	public int attack;
	public int defense;
	public int exp;
	public int nextLevelExp;
	public int coin;
	public Entity currentWeapon;
	public Entity currentShield;
	public Projectile projectile;
	
	// 	INVENTORY
	public ArrayList<Entity> inventory = new ArrayList<>();
	public final int maxInventorySize = 25;
	
	
	// CHEST AND DOOR ATTRIBUTE
	public Entity loot;
	public boolean open = false;
	
	// ITEM ATTRIBUTES
	public int attackValue;
	public int defenseValue;
	public String description = "";
	public int price;
	public boolean haveAnimation = false;
	public boolean canUse = true;
	public int code, decode;			// FOR KEY, CHEST AND DOOR
	public boolean stackable = false;
	public int amount = 1;
	
	// ENTITY TYPE
	public int entityType;
	public final int playerType = 0;
	public final int npcType = 1;
	public final int monsterType = 2;
	public final int swordType = 3;
	public final int shieldType = 4;
	public final int consumableType = 5;
	public final int pickUpOnly = 6;
	public final int obstacleType = 7;
	
	
	
	public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
	public int solidAreaDefaultX, solidAreaDefaultY;
	public boolean collisionOn = false;
	public Rectangle attackArea = new Rectangle(0, 0, 0, 0);
	
	// DIALOGUES
	String dialogues[] = new String[20];
	int dialogueIndex = 0;
	
	
	
	
	// OBJ TRAIT
	public BufferedImage image, image1, image2;
	public boolean collision = false;	
	
	public Entity(GamePanel gPanel) {
		// TODO Auto-generated constructor stub
		this.gPanel = gPanel;
	}
	
	public int getXDistance(Entity target) {
		return Math.abs(worldX - target.worldX);
	}
	
	public int getYDistance(Entity target) {
		return Math.abs(worldY - target.worldY);
	}
	
	public int getTileDistance(Entity target) {
		return (getXDistance(target) + getYDistance(target)) / gPanel.tileSize; 
	}
	
	public int getGoalCol(Entity target) {
		return (target.worldX + target.solidArea.x) / gPanel.tileSize;
	}
	
	public int getGoalRow(Entity target) {
		return (target.worldY + target.solidArea.y) / gPanel.tileSize;
	}
	
	public void checkStopChasingOrNot(Entity target, int distance, int rate) {
		if (getTileDistance(target) > distance) {
			int i = new Random().nextInt(rate);
			if (i == 0) onPath = false;
		}
	}
	
	public void checkStartChasingOrNot(Entity target, int distance, int rate) {
		if (getTileDistance(target) < distance) {
			int i = new Random().nextInt(rate);
			if (i == 0) onPath = true;
		}
	}
	
	public void checkShotOrNot(int rate, int shotInterval) {
		shotCounter++;
		int i = new Random().nextInt(rate) + 1;
		if (i < 2 && projectile.alive == false && shotCounter >= shotInterval) {
			projectile.set(worldX, worldY, direction ,true, this);
			gPanel.projectileList.add(projectile);
			shotCounter = 0;
		}
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
				canDamage = true; 
				attacking = true;
				attack++;
				Attack();
				attack--;
				
			}
		}
	}
	
	public void Attack() {				// ATTACK BY WEAPOON FOR MONSTER

		if (canDamage == true) {
			int currentWorldX = worldX;
			int currentWorldY = worldY;
			int solidAreaWidth = solidArea.width;
			int solidAreaHeight = solidArea.height;
			
			if (lastDirection == "left") worldX -= attackArea.width;
			else if (lastDirection == "right") worldX += attackArea.width;
			
			solidArea.width = attackArea.width;
			solidArea.height = attackArea.height;
			checkCollision();
			
			worldX = currentWorldX;
			worldY = currentWorldY;
			solidArea.width = solidAreaWidth;
			solidArea.height = solidAreaHeight;
		}
		
	}
	
	public void getRandomDirection() {
		actionLockCounter++;
		if (actionLockCounter > 120)
		{
			Random random = new Random();
			int ranNum = random.nextInt(4);
			if (ranNum == 0) direction = "up";
			else if (ranNum == 1) direction = "down";
			else if (ranNum == 2) direction = "left";
			else if (ranNum == 3) direction = "right";
			else direction = "idle";
			actionLockCounter = 0;
		}
	}
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
				if (direction == "idle") {
					if (spriteNum % 2 == 0) image = idle0;
					else image = idle1;
				}
				
				else {
					if (spriteNum == 0) image = run0;
					if (spriteNum == 1) image = run1;
					if (spriteNum == 2) image = run2;
					if (spriteNum == 3) image = run3;
					if (spriteNum == 4) image = run4;
					if (spriteNum == 5) image = run5;
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
					if (spriteNum == 4) image = run4L;
					if (spriteNum == 5) image = run5L;
				}
				
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
			
			
//			// DRAW SOLID AREA
//			g2.setColor(new Color(255, 0, 0, 100));
//			g2.fillRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);
			
		}
	}
	
	
	
	public void setAction() {}
	public void damageReaction() {}
	
	
	public void dyingAnimation(Graphics2D g2) {
		dyingCounter++;
		
		int tmp = 5;
		
		if (dyingCounter <= tmp) g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
		else if (dyingCounter <= tmp * 2) g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0f));
		else if (dyingCounter <= tmp * 3) g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
		else if (dyingCounter <= tmp * 4) g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0f));
		else if (dyingCounter <= tmp * 5) g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
		else if (dyingCounter <= tmp * 6) g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0f));
		else if (dyingCounter <= tmp * 7) g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
		else if (dyingCounter <= tmp * 8) g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0f));
		else if (dyingCounter <= tmp * 9) g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
		else {
			alive = false;
		}
		
	}
	
	public void speak() {
		
		if (dialogues[dialogueIndex] == null) dialogueIndex = 0;
		gPanel.ui.currentDialogue = dialogues[dialogueIndex]; 
		dialogueIndex++;
		
		switch (gPanel.player.direction) {
		case "left": 
			this.lastDirection = "right";
			break;
		case "right": 
			this.lastDirection  = "left";
			break;
		
		
		}
	}
	
	public void use(Entity entity) {}
	public void checkDrop() {}
	
	public void dropItem(Entity droppedItem) {
		for (int i = 0; i <gPanel.objs[1].length; i++)
		{
			if (gPanel.objs[gPanel.currentMap][i] == null) {
				gPanel.objs[gPanel.currentMap][i] = droppedItem;
				gPanel.objs[gPanel.currentMap][i].worldX = worldX;
				gPanel.objs[gPanel.currentMap][i].worldY = worldY;
				break;
			}
		}
	}
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
			if (spriteNum == 5) spriteNum = 0;
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
	}
	
	public void damagePlayer(int attack) {					// ATTACK BY COLLISION FOR MONSTER
		int percentBlock = gPanel.player.defense * 4;				// CHECK BLOCK
		if (uTool.checkPercentage(percentBlock) == false) {
			gPanel.playSoundEffect(5);
			gPanel.player.life -= this.attack;
			gPanel.player.counterHealing = 0;
		}
			
		else {
			gPanel.ui.addMessage("Blocked!");
			gPanel.playSoundEffect(6);
		}
		gPanel.player.invincible = true;
	}
	
	public void checkCollision() {
		
		collisionOn = false;
		gPanel.cChecker.checkTile(this);
		gPanel.cChecker.checkObject(this, false);
		gPanel.cChecker.checkEntity(this, gPanel.npc);
		gPanel.cChecker.checkEntity(this, gPanel.monster);
		boolean contactPlayer = gPanel.cChecker.checkPlayer(this);
		
		if (contactPlayer == true && this.entityType == monsterType && gPanel.player.invincible == false)		// FOR MONSTER ENTITY TO PLAYER
		{
			damagePlayer(attack);
		}
	}
	
	public void searchPath(int goalCol, int goalRow) {
		
		
		int startCol = (worldX + solidArea.x) / gPanel.tileSize;
		int startRow = (worldY + solidArea.y) / gPanel.tileSize;
		
		gPanel.pFinder.setNodes(startCol, startRow, goalCol, goalRow, this);
		
		if (gPanel.pFinder.search() == true) {
			
			// Next worldX & worldY
			int nextX = gPanel.pFinder.pathList.get(0).col * gPanel.tileSize;
			int nextY = gPanel.pFinder.pathList.get(0).row * gPanel.tileSize;
			
			// Entity's solidArea position
			int enLeftX = worldX + solidArea.x;
			int enRightX = worldX + solidArea.x + solidArea.width;
			int enTopY = worldY + solidArea.y;
			int enBottomY = worldY + solidArea.y + solidArea.height;
			
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
	
	public void drawSolidArea(Graphics2D g2) {
		
		int screenX = worldX - gPanel.player.worldX + gPanel.player.screenX;
		int screenY = worldY - gPanel.player.worldY + gPanel.player.screenY;
		g2.setColor(new Color(255, 0, 0, 100));
		g2.fillRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);
	}
	
	public boolean interact() {
		 return false;
	}
	
	public void setLoot(Entity loot) {
		this.loot = loot;
	}
	
	public BufferedImage setupImage(String imagePath)
	{
		
		BufferedImage image = null;
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
			image = uTool.scaleImage(image, gPanel.tileSize, gPanel.tileSize);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return image;
	}
}
