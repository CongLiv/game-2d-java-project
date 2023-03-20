package entity;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import main.GamePanel;
import main.KeyHandler;

import weapon.*;
import object.*;
import projectile.PROJ_Fireball;
import tile.TileManager;

public class Player extends Entity{

//	GamePanel gPanel;
	KeyHandler keyHandler;
	
	
	public final int screenX;
	public final int screenY;

	
	// ATTACK COUNTER
	public int counterAttack = 0;
	public int cooldownAttack;			// affect by dexterity -->    -= 3 * dexterity
	boolean canDamage;		// Only put dame on first 5/60 sec when attack
	
	// HEALING COUNTER
	int counterHealing = 0;
	public int cooldownHealing;		// affect by strength -->    -= 5 * strength


	// BUFF
	public int greenPotionCounter = 0;
	public int yellowPotionCounter = 0;
	int buffDefense;
	int buffAttack;
	
	// EFFECT
	BufferedImage levelup1, levelup2, levelup3;

	
	
	public Player(GamePanel gPanel, KeyHandler keyHandler) {
		super(gPanel);
		this.gPanel = gPanel;
		this.keyHandler = keyHandler;
		entityType = playerType;
		
		screenX = gPanel.screenWidth / 2 - gPanel.tileSize / 2;
		screenY = gPanel.screenHeight / 2 - gPanel.tileSize / 2;
		
		solidArea = new Rectangle(8, 16, 32, 26);
		solidAreaDefaultX = 8;
		solidAreaDefaultY = 16;
		
		attackArea.width = gPanel.tileSize;
		attackArea.height = gPanel.tileSize;
		setDefaultValues();
		
	}
	
	
	public void getEffectImage() {
		levelup1 = setupImage("/effects/levelup_effect_anim_f0");
		levelup2 = setupImage("/effects/levelup_effect_anim_f1");
		levelup3 = setupImage("/effects/levelup_effect_anim_f2");
		
	}
	public void setDefaultValues() {
		
		setDefaultPositions();
		
		speed = 4;
		cooldownAttack = 90;
		cooldownHealing = 300;
		
		// PLAYER STATUS
		level = 1;
		maxLife = maxPlayerLife;
		life = maxLife;
		dexterity = 1;
		strength = 1;
		exp = 0;
		nextLevelExp = 5;
		coin = 0;
		
		currentWeapon = new WEAP_NoSword(gPanel);
		currentShield = new WEAP_NoShield(gPanel);
		
		attack = getAttack();
		defense = getDefense();
		
		yellowPotionCounter = 0;
		greenPotionCounter = 0;
		
		
//		projectile = new PROJ_Fireball(gPanel);		// TEST
		
		getPlayerImage();
		getEffectImage();
		setItems();
	} 
	
	
	public void setDefaultPositions() {
		worldX = gPanel.tileManager.getDefaultXInCurrentMap(gPanel.currentMap);
		worldY = gPanel.tileManager.getDefaultYInCurrentMap(gPanel.currentMap);
		direction = "down";
	}
	
	public void restoreStatus() {
		life = maxLife;
		invincible = false;
		attacking = false;
		canDamage = false;
		counterAttack = 0;
		counterHealing = 0;
		yellowPotionCounter = 0;
		greenPotionCounter = 0;
	}
	
	public int getAttack() {
		return attack = strength + currentWeapon.attackValue + currentShield.attackValue + buffAttack;
	}
	
	public int getDefense() {
		return defense = dexterity + currentWeapon.defenseValue + currentShield.defenseValue + buffDefense;
	}
	
	
	public void setItems() {
		
		inventory.clear();
		if (currentShield.name != "Unknown") inventory.add(currentShield);
		if (currentWeapon.name != "Unknown") inventory.add(currentWeapon);

		
	}
	public void getPlayerImage()
	{
		idle0 = setupImage("/player/knight_idle_anim_f0");
		idle1 = setupImage("/player/knight_idle_anim_f3");
		run0 = setupImage("/player/knight_run_anim_f0");
		run1 = setupImage("/player/knight_run_anim_f1");
		run2 = setupImage("/player/knight_run_anim_f2");
		run3 = setupImage("/player/knight_run_anim_f3");
		run4 = setupImage("/player/knight_run_anim_f4");
		run5 = setupImage("/player/knight_run_anim_f5");
		
		
		idle0L = setupImage("/player/knight_idle_anim_f0_L");
		idle1L = setupImage("/player/knight_idle_anim_f3_L");
		run0L = setupImage("/player/knight_run_anim_f0_L");
		run1L = setupImage("/player/knight_run_anim_f1_L");
		run2L = setupImage("/player/knight_run_anim_f2_L");
		run3L = setupImage("/player/knight_run_anim_f3_L");
		run4L = setupImage("/player/knight_run_anim_f4_L");
		run5L = setupImage("/player/knight_run_anim_f5_L");
		
	}
	

	
	
	
	public void update() {
		
		if (keyHandler.spacePressed == true)
		{
			attacking = true;
			canDamage = true;
			Attack();
		}
		
		
		if (keyHandler.upPressed == true || keyHandler.downPressed == true || keyHandler.leftPressed == true || keyHandler.rightPressed == true || 
				keyHandler.enterPressed == true) {
			if (keyHandler.upPressed == true) {
				direction = "up";
				lastDirection2 = "up";
			}
			if (keyHandler.downPressed == true) {
				direction = "down";
				lastDirection2 = "down";
			}
			if (keyHandler.leftPressed == true) {
				direction = "left";
				lastDirection = "left";
				lastDirection2 = "left";
			}
			if (keyHandler.rightPressed == true) {
				direction = "right";
				lastDirection = "right";
				lastDirection2 = "right";
			}
			
			// CHECK TILE COLLISION
			collisionOn = false;
			gPanel.cChecker.checkTile(this);
			
			// CHECK OBJECT COLLISION
			int objIndex = gPanel.cChecker.checkObject(this, true);
			
			pickUpObject(objIndex);
			
			// CHECK EVENT
			gPanel.eHandler.checkEvent();
			
			// CHECK NPC COLLISION
			int npcIndex = gPanel.cChecker.checkEntity(this, gPanel.npc);
			interactNPC(npcIndex);
			
			// CHECK MONSTER COLLISION
			int monsterIndex = gPanel.cChecker.checkEntity(this, gPanel.monster);
			contactMonster(monsterIndex);
			
			
			if (collisionOn == false && keyHandler.enterPressed == false)
			{
				
				if(direction == "up") worldY -= speed; 
				if(direction == "down") worldY += speed; 
				if(direction == "left") worldX -= speed; 
				if(direction == "right") worldX += speed; 
				
			}
			
			spriteCounter++;
			if (spriteCounter > 8) {
				spriteNum++;
				if (spriteNum == 5) spriteNum = 0;
				spriteCounter = 1;
			}
		}
		else {
			direction = "idle";
			spriteCounter++;
			if (spriteCounter > 10) {
				spriteNum++;
				if (spriteNum == 5) spriteNum = 0;
				spriteCounter = 1;
			}
		}
		
		// COUNTER INVINCIBLE TIME
		if (invincible == true)
		{
			invincibleCounter++;
			if (invincibleCounter > 90) {
				invincible = false;
				invincibleCounter = 0;
			}
		}
		
		
		// COUNTER HEALING
		if (life < maxLife) {
			counterHealing++;
			if (counterHealing > cooldownHealing) {
				life++;
				counterHealing = 0;
			}
		}
		
		if (life <= 0) {
			gPanel.stopMusic();
			gPanel.playSoundEffect(12);
			gPanel.ui.commandNum--;
			gPanel.gameState = gPanel.gameOverState;
			
		}
		
		// TEST FIREBALL
		if (gPanel.keyHandler.shotKeyPressed == true && projectile.alive == false)
		{
			projectile.set(worldX, worldY, direction, true, this);
			gPanel.projectileList.add(projectile);
		}
		
		
		// BUFF EFFECT
		if (yellowPotionCounter > 0) {
			buffAttack = 5;
			getAttack();
			yellowPotionCounter--;
		}
		else {
			buffAttack = 0;
			getAttack();
		}
		
		if (greenPotionCounter > 0) {
			buffDefense = 5;
			getDefense();
			greenPotionCounter--;
		}
		else {
			buffDefense = 0;
			getDefense();
		}
		
	}
	
	public void pickUpObject(int i) {
		if (i != 999)
		{
			// PICK UP ONLY
			if (gPanel.objs[gPanel.currentMap][i] != null && gPanel.objs[gPanel.currentMap][i].entityType == pickUpOnly) {
				
				gPanel.objs[gPanel.currentMap][i].use(this);
				gPanel.playSoundEffect(1);
				gPanel.objs[gPanel.currentMap][i] = null;
			}
			// OBSTACLE
			else if (gPanel.objs[gPanel.currentMap][i] != null && gPanel.objs[gPanel.currentMap][i].entityType == obstacleType){
				if (keyHandler.enterPressed == true) {
				gPanel.objs[gPanel.currentMap][i].interact();
				}
			}
			
			// CONSUMABLE ITEM
			else {
				String text;
				if (canObtainItem(gPanel.objs[gPanel.currentMap][i]) == true)
				{
					gPanel.playSoundEffect(1);
					text = "Got a " + gPanel.objs[gPanel.currentMap][i].name + "!";
					gPanel.objs[gPanel.currentMap][i] = null;
					gPanel.ui.addMessage(text);
				}
				
				else {
					gPanel.gameState = gPanel.dialogueState;
					gPanel.ui.currentDialogue = "Your bag is full!";
				}
				
			}
		}
		
	}
	
	
	public void interactNPC(int npcIndex)
	{
		if (npcIndex != 999) {
			if (gPanel.keyHandler.enterPressed == true) {
				gPanel.gameState = gPanel.dialogueState;
				gPanel.npc[gPanel.currentMap][npcIndex].speak();
			}
			gPanel.keyHandler.enterPressed = false;
			
		}
	}
	
	public void contactMonster(int monsterIndex)
	{
		if (monsterIndex != 999)
		{
			if (invincible == false && gPanel.monster[gPanel.currentMap][monsterIndex].dying == false && gPanel.monster[gPanel.currentMap][monsterIndex].name != "Barrel")
				
			{
				int percentBlock = defense * 4;							// CHECK BLOCK
				if (uTool.checkPercentage(percentBlock) == false) {
					gPanel.playSoundEffect(5);
					life -= gPanel.monster[gPanel.currentMap][monsterIndex].attack;
					counterHealing = 0;
				}
					
				else {
					gPanel.ui.addMessage("Blocked!");
					gPanel.playSoundEffect(6);
				}
				invincible = true;
				
			}
			
		}
	}
	
	public void Attack() {

		counterAttack++;
		if (counterAttack > 5) {
			canDamage = false;
		}
		if (counterAttack == cooldownAttack) {
			counterAttack = 0;
			//canAttack = true;
			keyHandler.spacePressed = false;
		}
		
		if (canDamage == true) {
			int currentWorldX = worldX;
			int currentWorldY = worldY;
			int solidAreaWidth = solidArea.width;
			int solidAreaHeight = solidArea.height;
			
			if (lastDirection == "left") worldX -= attackArea.width;
			else if (lastDirection == "right") worldX += attackArea.width;
			
			solidArea.width = attackArea.width;
			solidArea.height = attackArea.height;
			int monsterIndex = gPanel.cChecker.checkEntity(this, gPanel.monster);
			damageMonster(monsterIndex);
			
			worldX = currentWorldX;
			worldY = currentWorldY;
			solidArea.width = solidAreaWidth;
			solidArea.height = solidAreaHeight;
		}
		
		
		
	}
	
	public void damageMonster(int i) {
		
		if (i != 999) {
			if (gPanel.monster[gPanel.currentMap][i].invincible == false 
					&& gPanel.monster[gPanel.currentMap][i].name != "Spike"
					)
			{
				
				int percentBlock = gPanel.monster[gPanel.currentMap][i].defense * 4;		// CHECK BLOCK
				if (uTool.checkPercentage(percentBlock) == false)
				{
					gPanel.monster[gPanel.currentMap][i].life -= attack;
					gPanel.monster[gPanel.currentMap][i].damageReaction();
				}
				else {
					if (gPanel.monster[gPanel.currentMap][i].name != "Barrel") gPanel.ui.addMessage("Monster block!");
					gPanel.playSoundEffect(11);
				}
				gPanel.monster[gPanel.currentMap][i].invincible = true;
				if (gPanel.monster[gPanel.currentMap][i].life <= 0)
				{
					gPanel.monster[gPanel.currentMap][i].dying = true;
					if (gPanel.monster[gPanel.currentMap][i].name != "Barrel") {
						gPanel.ui.addMessage("EXP + " + gPanel.monster[gPanel.currentMap][i].exp);
						exp += gPanel.monster[gPanel.currentMap][i].exp;
					}
					
					checkLevelUp();
				}
			}
		}
		
	}
	
	public void checkLevelUp() {
		if (exp >= nextLevelExp)
		{
			
			level++;
			nextLevelExp *= 3;
			strength++;
			dexterity++;
			
			attack = getAttack();
			defense = getDefense();
			
			cooldownAttack -= dexterity * 3;
			if (cooldownAttack <= 30) cooldownAttack = 30;		// MINIMUM COOLDOWNATTACK = 30
			cooldownHealing -= strength * 5;
			if (cooldownHealing <= 100) cooldownHealing = 100;		// MINIMUM COOLDOWNHEALING = 100
			
			gPanel.playSoundEffect(7);
			
			gPanel.ui.addMessage("Level Up!");
			
			animationCounter++;		// play level up effect
			
			
		}
	}
	
	public void levelupAnimation(Graphics2D g2) {
		
		animationCounter++;
		int tmp = 20;
		
		if (animationCounter <= tmp && animationCounter > 0) g2.drawImage(levelup1, screenX, screenY, 48, 48, null);
		else if (animationCounter <= tmp * 2) g2.drawImage(levelup2, screenX, screenY, 48, 48, null);
		else if (animationCounter <= tmp * 3) g2.drawImage(levelup3, screenX, screenY, 48, 48, null);
		else {
			animationCounter = 0;
		}
		
	}
	
	public void selectItem() {
		int itemIndex = gPanel.ui.getItemIndexOnSlot(gPanel.ui.playerSlotCol, gPanel.ui.playerSlotRow);
		
		if (itemIndex < inventory.size())
		{
			Entity selectedItem = inventory.get(itemIndex);
			if (selectedItem.entityType == swordType)
			{
				if (currentWeapon == selectedItem)
				{
					currentWeapon = new WEAP_NoSword(gPanel);
				}
				else currentWeapon = selectedItem;
			}
			if (selectedItem.entityType == shieldType)
			{
				if (currentShield == selectedItem)
				{
					currentShield = new WEAP_NoShield(gPanel);
				}
				else currentShield = selectedItem;
			}
			
			if (selectedItem.entityType == consumableType && selectedItem.canUse == true)
			{
				selectedItem.use(this);
				if (selectedItem.amount > 1) {
					selectedItem.amount--;
				}
				else {
					inventory.remove(itemIndex);
				}
				
				
				
			}
			
			gPanel.playSoundEffect(9);
			attack = getAttack();
			defense = getDefense();
		}
	}
	
	public int searchItemInInventory(String itemName) {
		int itemIndex = 999;
		
		for (int i = 0; i < inventory.size(); i++) {
			if (inventory.get(i).name.equals(itemName)) {
				itemIndex = i;
				break;
			}
		}
		
		return itemIndex; 
	}
	
	
	public int getCurrentWeaponSlot() {
		int currentWeaponSlot = -1;
		for (int i = 0; i < inventory.size(); i++)
		{
			if (inventory.get(i) == currentWeapon)
			{
				currentWeaponSlot = i;
				break;
			}
		}
		return currentWeaponSlot;
	}
	
	public int getCurrentShieldSlot() {
		int currentShieldSlot = -1;
		for (int i = 0; i < inventory.size(); i++)
		{
			if (inventory.get(i) == currentShield)
			{
				currentShieldSlot = i;
				break;
			}
		}
		return currentShieldSlot;
	}
	
	public boolean canObtainItem(Entity item) {
		boolean canObtain = false;
		
		// CHECK IF STACKABLE
		if (item.stackable == true) {
			int index = searchItemInInventory(item.name);
			if (index != 999) {
				inventory.get(index).amount++;
				canObtain = true;
			}
			
			else {
				if (inventory.size() != maxInventorySize) {
					inventory.add(item);
					canObtain = true;
				}
			}
		}
		
		else {
			if (inventory.size() != maxInventorySize) {
				inventory.add(item);
				canObtain = true;
			}
		}
		
		return canObtain;
	}
	
	public void usePotion(String potionName) {
		for (int i = 0; i < inventory.size(); i++)
		{
			if (inventory.get(i).name == potionName) {
				inventory.get(i).use(this);
				gPanel.playSoundEffect(10);
				if (inventory.get(i).amount > 1) inventory.get(i).amount--;
				else inventory.remove(i);
				return;
			}
		}
		
		gPanel.ui.addMessage("You no have " + potionName + "!");
		gPanel.playSoundEffect(8);
	}
	
	
	
	
	public void draw(Graphics2D g2) {
//		g2.setColor(Color.red);
//
//		g2.fillRect(X, Y, gPanel.tileSize, gPanel.tileSize);
		
		BufferedImage image = null;
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
		
		if (invincible == true) {
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
		}
		
		
		
		
		//g2.drawImage(image, screenX, screenY, null);
		
		// DRAW WEAPON
		
		if (currentShield.name != "Unknown" && lastDirection == "right") {drawShield(currentShield.weaponL, g2);}
		if (currentWeapon.name != "Unknown" || currentShield.name != "Unknown") 
		{
			if (canDamage == false)
			{
				if (lastDirection == "left") {
					drawSword(currentWeapon.weaponL, g2, screenX - 5, screenY + 15);
					g2.drawImage(image, screenX, screenY, null);
				}
					
				if (lastDirection == "right")
				{
					g2.drawImage(image, screenX, screenY, null);
					drawSword(currentWeapon.weaponR, g2, screenX + 20, screenY + 10);
				}
			}
				
			else {
				if (lastDirection == "left") {
					drawAttack(currentWeapon.attackL, g2, screenX - 25);
					g2.drawImage(image, screenX, screenY, null);
				}
				if (lastDirection == "right") {
					g2.drawImage(image, screenX, screenY, null);
					drawAttack(currentWeapon.attackR, g2, screenX + 20);
				}
			}
			
			if (currentShield.name != "Unknown" && lastDirection == "left") {drawShield(currentShield.weaponL, g2);}
		}
			
			
		// TRANSPARENTS COUNTER
		else {
			if (invincible == true) {
				g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
			}
			g2.drawImage(image, screenX, screenY, null);
		}
		
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
		
		// LEVEL UP EFFECT
		if (animationCounter > 0) {
			levelupAnimation(g2);
		}
		
		
//		// DRAW SOLID AREA
//		g2.setColor(new Color(255, 0, 0, 100));
//		g2.fillRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);
	}
	
	
	public void drawSword(BufferedImage image, Graphics2D g2, int X, int Y) {
		
		g2.drawImage(image, X, Y, 30, 30, null);
	}
	
	public void drawAttack(BufferedImage image,  Graphics2D g2, int X) {
		
		g2.drawImage(image, X, screenY, null);
		
	}
	
	public void drawShield(BufferedImage image, Graphics2D g2) {
		
		g2.drawImage(image, screenX + 17, screenY + 17, 30, 30, null);
	}
	
}
