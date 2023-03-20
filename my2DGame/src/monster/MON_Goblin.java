package monster;

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
import weapon.WEAP_GoblinKnife;

public class MON_Goblin extends Entity{

	int screenX, screenY;
	
	
	
	public MON_Goblin(GamePanel gPanel) {
		super(gPanel);
		// TODO Auto-generated constructor stub
		name = "Goblin";
		speed = 2;
		maxLife = 20;
		life = maxLife;
		entityType = monsterType;
		alive = true;
		dying = false;
		attack = 1;
		defense = 5;
		exp = 12;
		lastDirection = "right";
		cooldownAttack = 200;
		
		solidArea = new Rectangle(10, 16, 28, 26);
		solidAreaDefaultX = 10;
		solidAreaDefaultY = 16;
		
		attackArea = new Rectangle(0, 0, 48, 48);
		
		screenX = worldX - gPanel.player.worldX + gPanel.player.screenX;
		screenY = worldY - gPanel.player.worldY + gPanel.player.screenY;
		
		getImage();
		currentWeapon = new WEAP_GoblinKnife(gPanel);
	}
	
	public void getImage() {
		idle0 = setupImage("/monsters/goblin_idle_anim_f0");
		idle1 = setupImage("/monsters/goblin_idle_anim_f1");
		run0 = setupImage("/monsters/goblin_run_anim_f0");
		run1 = setupImage("/monsters/goblin_run_anim_f1");
		run2 = setupImage("/monsters/goblin_run_anim_f2");
		run3 = setupImage("/monsters/goblin_run_anim_f3");
		run4 = setupImage("/monsters/goblin_run_anim_f4");
		run5 = setupImage("/monsters/goblin_run_anim_f5");
		
		idle0L = setupImage("/monsters/goblin_idle_anim_f0_L");
		idle1L = setupImage("/monsters/goblin_idle_anim_f1_L");
		run0L = setupImage("/monsters/goblin_run_anim_f0_L");
		run1L = setupImage("/monsters/goblin_run_anim_f1_L");
		run2L = setupImage("/monsters/goblin_run_anim_f2_L");
		run3L = setupImage("/monsters/goblin_run_anim_f3_L");
		run4L = setupImage("/monsters/goblin_run_anim_f4_L");
		run5L = setupImage("/monsters/goblin_run_anim_f5_L");
	}	
	
	
	@Override
	public void draw(Graphics2D g2) {
		super.draw(g2);
	
		// DRAW WEAPON
		
		screenX = worldX - gPanel.player.worldX + gPanel.player.screenX;
		screenY = worldY - gPanel.player.worldY + gPanel.player.screenY;
		if (currentWeapon.name != "Unknown") 
		{
			if (canDamage == false)
			{
				if (lastDirection == "left") {
					drawSword(currentWeapon.weaponL, g2, screenX + 5, screenY + 10);
					g2.drawImage(image, screenX, screenY, null);
				}
							
				if (lastDirection == "right")
				{
					g2.drawImage(image, screenX, screenY, null);
					drawSword(currentWeapon.weaponR, g2, screenX + 15, screenY + 10);
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
					
	
		}
	}
	
	public void drawSword(BufferedImage image, Graphics2D g2, int X, int Y) {
		
		g2.drawImage(image, X, Y, 30, 30, null);
	}
	
	public void drawAttack(BufferedImage image,  Graphics2D g2, int X) {
		
		g2.drawImage(image, X, screenY, null);
		
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
		
		if (attacking == true) counterAttack++;
		if (counterAttack > 5) {
			canDamage = false;

		}
		
		if (counterAttack == cooldownAttack) {
			counterAttack = 0;
			attacking = false;
		}
		
		
		checkStartChasingOrNot(gPanel.player, 3, 100);
		
		checkStopChasingOrNot(gPanel.player, 5, 100);
		
		checkAttackOrNot(150, gPanel.tileSize * 2, gPanel.tileSize);
		
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
		
		else if (i <= 77) dropItem(new OBJ_KeyGold(gPanel, 6001));
		
		else if (i <= 78) dropItem(new OBJ_KeySilver(gPanel, 8001));
		
		else if (i <= 80) dropItem(new OBJ_KeyGold(gPanel, 8000));
	}
}
