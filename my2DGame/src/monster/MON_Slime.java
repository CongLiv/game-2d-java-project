package monster;

import java.awt.Font;
import java.util.Random;

import entity.Entity;
import main.GamePanel;
import object.OBJ_Coins;
import object.OBJ_KeyGold;
import object.OBJ_PotionGreen;
import object.OBJ_PotionRed;
import object.OBJ_PotionYellow;

public class MON_Slime extends Entity{

	public MON_Slime(GamePanel gPanel) {
		super(gPanel);
		// TODO Auto-generated constructor stub
		name = "Slime";
		speed = 1;
		maxLife = 4;
		life = maxLife;
		entityType = monsterType;
		alive = true;
		dying = false;
		attack = 1;
		defense = 2;
		exp = 3;
		
		solidArea.x = 3;
		solidArea.y = 10;
		solidArea.width = 42;
		solidArea.height = 30;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
		getImage();
	}
	
	public void getImage() {
		idle0 = setupImage("/monsters/slime_idle_anim_f0");
		idle1 = setupImage("/monsters/slime_idle_anim_f3");
		run0 = setupImage("/monsters/slime_run_anim_f0");
		run1 = setupImage("/monsters/slime_run_anim_f1");
		run2 = setupImage("/monsters/slime_run_anim_f2");
		run3 = setupImage("/monsters/slime_run_anim_f3");
		run4 = setupImage("/monsters/slime_run_anim_f4");
		run5 = setupImage("/monsters/slime_run_anim_f5");
		
		idle0L = setupImage("/monsters/slime_idle_anim_L_f0");
		idle1L = setupImage("/monsters/slime_idle_anim_L_f3");
		run0L = setupImage("/monsters/slime_run_anim_L_f0");
		run1L = setupImage("/monsters/slime_run_anim_L_f1");
		run2L = setupImage("/monsters/slime_run_anim_L_f2");
		run3L = setupImage("/monsters/slime_run_anim_L_f3");
		run4L = setupImage("/monsters/slime_run_anim_L_f4");
		run5L = setupImage("/monsters/slime_run_anim_L_f5");
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
		
		checkStartChasingOrNot(gPanel.player, 3, 150);
		
		checkStopChasingOrNot(gPanel.player, 5, 100);
	}
	
	@Override
	public void checkDrop() {
		
		int i = new Random().nextInt(100) + 1;
		
		if (i <= 30) dropItem(new OBJ_Coins(gPanel));
		
		else if (i <= 40) dropItem(new OBJ_PotionRed(gPanel));
		
		else if (i <= 41) dropItem(new OBJ_PotionGreen(gPanel));
		
		else if (i <= 42) dropItem(new OBJ_PotionYellow(gPanel));
		
		else if (i <= 45) dropItem(new OBJ_KeyGold(gPanel, 6000));
		
		else if (i <= 46) dropItem(new OBJ_KeyGold(gPanel, 3000));
		
		
	}
}
