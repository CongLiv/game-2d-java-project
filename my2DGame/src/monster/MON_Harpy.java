package monster;

import java.util.Random;

import entity.Entity;
import main.GamePanel;
import object.OBJ_Coins;
import object.OBJ_KeyGold;
import object.OBJ_KeySilver;
import object.OBJ_PotionGreen;
import object.OBJ_PotionRed;
import object.OBJ_PotionYellow;
import projectile.PROJ_Fireball;

public class MON_Harpy extends Entity{
	
	public MON_Harpy(GamePanel gPanel) {
		super(gPanel);
		// TODO Auto-generated constructor stub
		name = "Harpy";
		speed = 2;
		maxLife = 10;
		life = maxLife;
		entityType = monsterType;
		alive = true;
		dying = false;
		attack = 1;
		defense = 3;
		exp = 6;
		projectile = new PROJ_Fireball(gPanel);
		
		solidArea.x = 3;
		solidArea.y = 10;
		solidArea.width = 42;
		solidArea.height = 30;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
		getImage();
	}
	
	public void getImage() {
		idle0 = setupImage("/monsters/harpy_idle_f0");
		idle1 = setupImage("/monsters/harpy_idle_f2");
		run0 = setupImage("/monsters/harpy_idle_f0");
		run1 = setupImage("/monsters/harpy_idle_f1");
		run2 = setupImage("/monsters/harpy_idle_f2");
		run3 = setupImage("/monsters/harpy_idle_f3");
		run4 = setupImage("/monsters/harpy_idle_f0");
		run5 = setupImage("/monsters/harpy_idle_f1");
		
		idle0L = setupImage("/monsters/harpy_idle_f0_L");
		idle1L = setupImage("/monsters/harpy_idle_f2_L");
		run0L = setupImage("/monsters/harpy_idle_f0_L");
		run1L = setupImage("/monsters/harpy_idle_f1_L");
		run2L = setupImage("/monsters/harpy_idle_f2_L");
		run3L = setupImage("/monsters/harpy_idle_f3_L");
		run4L = setupImage("/monsters/harpy_idle_f0_L");
		run5L = setupImage("/monsters/harpy_idle_f1_L");
	}

	
	public void setAction() {
		if (onPath == true) {
			searchPath(getGoalCol(gPanel.player), getGoalRow(gPanel.player));
		}
		else getRandomDirection();
		checkStartChasingOrNot(gPanel.player, 5, 150);
		checkStopChasingOrNot(gPanel.player, 3, 150);
		checkShotOrNot(100, 60);
	}
	
	@Override
	public void damageReaction() {
		
		actionLockCounter++;
		direction = gPanel.player.lastDirection;
	}
	
	public void checkDrop() {
		int i = new Random().nextInt(100) + 1;
		
		if (i <= 50) dropItem(new OBJ_Coins(gPanel));
		
		else if (i <= 65) dropItem(new OBJ_PotionRed(gPanel));
		
		else if (i <= 67) dropItem(new OBJ_PotionGreen(gPanel));
		
		else if (i <= 69) dropItem(new OBJ_PotionYellow(gPanel));
		
		else if (i <= 70) dropItem(new OBJ_KeyGold(gPanel, 3000));
		
		else if (i <= 72) dropItem(new OBJ_KeyGold(gPanel, 6000));
		
		else if (i <= 73) dropItem(new OBJ_KeySilver(gPanel, 3001));
		
		else if (i <= 74) dropItem(new OBJ_KeyGold(gPanel, 7000));
		
	}
}
