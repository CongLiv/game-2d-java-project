package projectile;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import main.GamePanel;

public class PROJ_Explosion extends Projectile{
	public PROJ_Explosion(GamePanel gPanel) {
		super(gPanel);
		name = "Explosion";
		speed = 0;
		maxLife = 48;			// time it fly
		life = maxLife;
		attack = 1;
		alive = false;
		getImage();
	}
	
	public void getImage() {
		
		run0 = setupImage("/projectile/explosion_anim_f1");
		run1 = setupImage("/projectile/explosion_anim_f2");
		run2 = setupImage("/projectile/explosion_anim_f3");
		run3 = setupImage("/projectile/explosion_anim_f4");
		run4 = setupImage("/projectile/explosion_anim_f5");
		run5 = setupImage("/projectile/explosion_anim_f6");
		
	}
	
	@Override
	public void draw(Graphics2D g2) {
		
		BufferedImage image = null;
		int screenX = worldX - gPanel.player.worldX + gPanel.player.screenX;
		int screenY = worldY - gPanel.player.worldY + gPanel.player.screenY;
		
		if (worldX + gPanel.tileSize > gPanel.player.worldX - gPanel.player.screenX &&
				worldX - gPanel.tileSize < gPanel.player.worldX + gPanel.player.screenX &&
				worldY + gPanel.tileSize > gPanel.player.worldY - gPanel.player.screenY &&
				worldY - gPanel.tileSize < gPanel.player.worldY + gPanel.player.screenY) 
		
		{
			if (life <= 48 && life > 40) image = run0;
			else if (life <= 40 && life > 32) image = run1;
			else if (life <= 32 && life > 24) image = run2;
			else if (life <= 24 && life > 16) image = run3;
			else if (life <= 16 && life > 8) image = run4;
			else if (life <= 8 && life > 0) image = run5;
			
			g2.drawImage(image, screenX, screenY, gPanel.tileSize, gPanel.tileSize, null);
			
		}
		
	}
	@Override
	public void update() {
		// TODO Auto-generated method stub
		super.update();
		if (life == 47) gPanel.playSoundEffect(14);
	}
	
	
}
