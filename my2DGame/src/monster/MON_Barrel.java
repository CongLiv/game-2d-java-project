package monster;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

import entity.Entity;
import main.GamePanel;
import object.OBJ_Coins;
import object.OBJ_PotionGreen;
import object.OBJ_PotionRed;
import object.OBJ_PotionYellow;
import projectile.PROJ_Explosion;

public class MON_Barrel extends Entity{
	public MON_Barrel(GamePanel gPanel) {
		super(gPanel);
		// TODO Auto-generated constructor stub
		name = "Barrel";
		speed = 0;
		maxLife = 1;
		life = maxLife;
		entityType = monsterType;
		alive = true;
		dying = false;
		attack = 0;
		defense = 5;
		
		solidArea.x = 8;
		solidArea.y = 8;
		solidArea.width = 30;
		solidArea.height = 35;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		projectile = new PROJ_Explosion(gPanel);
		
		getImage();
	}
	
	public void getImage() {
		idle0 = setupImage("/monsters/barrel");
		idle1 = setupImage("/monsters/barrel");

	}

	public void draw(Graphics2D g2) {
		super.draw(g2);
		
	}
	
	@Override
	public void update() {
		super.update();
	} 
	
	public void checkDrop() {
		
		checkShotOrNot(5, 0);
		
		int i = new Random().nextInt(100) + 1;
		
		if (i <= 30) dropItem(new OBJ_Coins(gPanel));
	
	}
	
	public void checkShotOrNot(int rate, int shotInterval) {
		shotCounter++;
		int i = new Random().nextInt(rate) + 1;
		if (i < 3 && projectile.alive == false && shotCounter >= shotInterval) {
			projectile.set(worldX, worldY, direction ,true, this);
			gPanel.projectileList.add(projectile);
			shotCounter = 0;
		}
	}
	
	
	
}
