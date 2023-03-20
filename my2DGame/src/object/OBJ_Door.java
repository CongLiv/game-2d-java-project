package object;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Entity;
import main.GamePanel;

public class OBJ_Door extends Entity{


	public OBJ_Door(GamePanel gPanel, int code) {
		super(gPanel);
		this.code = code;
		name = "Door";
		collision = true;
		direction = "down";
		entityType = obstacleType;
		haveAnimation = true;
		solidArea = new Rectangle(0, 32, 96, 64);
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		spriteNum = 1;
		
		description = "[" + name + "]" + "\nKnock knock";
		getImage();
	}
	
	public void getImage() {
		
		try {
		
			run0 = ImageIO.read(getClass().getResourceAsStream("/objects/door_anim_opening_f0.png"));
			run1 = ImageIO.read(getClass().getResourceAsStream("/objects/door_anim_opening_f1.png"));
			run2 = ImageIO.read(getClass().getResourceAsStream("/objects/door_anim_opening_f2.png"));
			run3 = ImageIO.read(getClass().getResourceAsStream("/objects/door_anim_opening_f3.png"));
			run4 = ImageIO.read(getClass().getResourceAsStream("/objects/door_anim_opening_f4.png"));
			run5 = ImageIO.read(getClass().getResourceAsStream("/objects/door_anim_opening_f5.png"));
			
			run0L = ImageIO.read(getClass().getResourceAsStream("/objects/door_anim_opening_f6.png"));
			run1L = ImageIO.read(getClass().getResourceAsStream("/objects/door_anim_opening_f7.png"));
			run2L = ImageIO.read(getClass().getResourceAsStream("/objects/door_anim_opening_f8.png"));
			run3L = ImageIO.read(getClass().getResourceAsStream("/objects/door_anim_opening_f9.png"));
			run4L = ImageIO.read(getClass().getResourceAsStream("/objects/door_anim_opening_f10.png"));
			run5L = ImageIO.read(getClass().getResourceAsStream("/objects/door_anim_opening_f11.png"));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public boolean interact() {
		
		if (open == false) {
			for (int i = 0; i < gPanel.player.inventory.size(); i++)
			{
				if (gPanel.player.inventory.get(i).name == "Key Gold" && gPanel.player.inventory.get(i).decode == code) {
					
					open = true;
					gPanel.ui.addMessage("You opened the Door!"); 
					gPanel.player.inventory.remove(i);
		
					return true;
				}
			}
			
			gPanel.gameState = gPanel.dialogueState;
			gPanel.ui.currentDialogue = "You need a key to open this door!";
			return false;
		}
		
		return true;
		
	}
	
	@Override
	public void draw(Graphics2D g2) {
		// TODO Auto-generated method stub
		int screenX = worldX - gPanel.player.worldX + gPanel.player.screenX;
		int screenY = worldY - gPanel.player.worldY + gPanel.player.screenY;
		
		if (worldX + gPanel.tileSize * 2 > gPanel.player.worldX - gPanel.player.screenX &&
				worldX - gPanel.tileSize * 2 < gPanel.player.worldX + gPanel.player.screenX &&
				worldY + gPanel.tileSize * 2 > gPanel.player.worldY - gPanel.player.screenY &&
				worldY - gPanel.tileSize * 2 < gPanel.player.worldY + gPanel.player.screenY
				) 
		if (open == false) {
			g2.drawImage(run0, screenX, screenY, gPanel.tileSize * 2, gPanel.tileSize * 2, null);
		}
		
		else {
			BufferedImage image = null;
			if (spriteNum < 10) image = run0;
			else if (spriteNum < 15) image = run1;
			else if (spriteNum < 20) image = run2;
			else if (spriteNum < 25) image = run3;
			else if (spriteNum < 30) image = run4;
			else if (spriteNum < 35) image = run5;
			else if (spriteNum < 40) image = run0L;
			else if (spriteNum < 45) image = run1L;
			else if (spriteNum < 50) image = run2L;
			else if (spriteNum < 55) image = run3L;
			else if (spriteNum < 60) image = run4L;
			else if (spriteNum < 66) image = run5L;
			
			g2.drawImage(image, screenX, screenY, gPanel.tileSize * 2, gPanel.tileSize * 2, null);
			
		}
		
	}
	
	public void update() {
		checkCollision();
		if (collisionOn == false)
		{
						
			if(direction == "up") worldY -= speed; 
			if(direction == "down") worldY += speed; 
			if(direction == "left") {worldX -= speed; lastDirection = "left";}
			if(direction == "right") {worldX += speed; lastDirection = "right";}
						
		}
		
		if (open == true) {
			if (spriteNum < 65) spriteNum++;
			else solidArea = new Rectangle(0, 0, 0, 0);
		}
		
	}
	
}
