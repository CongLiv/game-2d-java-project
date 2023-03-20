package projectile;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import entity.Entity;
import main.GamePanel;

public class Projectile extends Entity{

	Entity user;
	public Projectile(GamePanel gPanelanel) {
		super(gPanelanel);
	
	}
	
	public void set(int worldX, int worldY, String direction, boolean alive, Entity user) {
		this.worldX = worldX;
		this.worldY = worldY;
		this.direction = direction;
		this.alive = alive;
		this.user = user;
		this.life = this.maxLife;
		
	}
	@Override
	public void update() {
		
		if (user == gPanel.player) {
			int monsterIndex = gPanel.cChecker.checkEntity(this, gPanel.monster);
			if (monsterIndex != 999)
			{
				gPanel.player.damageMonster(monsterIndex);
				alive = false;
			}
		}
		
		else {
			boolean contactPlayer = gPanel.cChecker.checkPlayer(this);
			if (gPanel.player.invincible == false && contactPlayer == true) {
				damagePlayer(user.attack  + 1);
				if (user.name != "Barrel")
				alive = false;
			}
		}
		
		switch (direction) {
			case "up": worldY -= speed; break;
			case "down": worldY += speed; break;
			case "left": worldX -= speed; break;
			case "right": worldX += speed; break;
			default: break;
		}
		life--;
		
		if (life <= 0) {
			alive = false;
		}
		
		spriteCounter++;
		if (spriteCounter > 12) {
			if (spriteNum == 1) {
				spriteNum = 2;
			}
			else spriteNum = 1;
		}
			
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
			switch (direction) {
            case "up":
                if (spriteNum == 1) {
                    image = idle0;
                } else if (spriteNum == 2) {
                    image = idle1;
                }
                break;

            case "down":
                if (spriteNum == 1) {
                    image = run0;
                } else if (spriteNum == 2) {
                    image = run1;
                }
                break;

            case "left":
                if (spriteNum == 1) {
                    image = run2;
                } else if (spriteNum == 2) {
                    image = run3;
                }
                break;

            case "right":
                if (spriteNum == 1) {
                    image = run4;
                } else if (spriteNum == 2) {
                    image = run5;
                }
                break;
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
	}
	
		
	

}
