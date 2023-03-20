package main;

import entity.Entity;

public class CollisionChecker {

	GamePanel gPanel; 
	
	public CollisionChecker (GamePanel gPanel)
	{
		this.gPanel = gPanel;
	}
	
	public void checkTile(Entity entity)
	{
		int entityLeftWorldX = entity.worldX + entity.solidArea.x;
		int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
		int entityTopWorldY = entity.worldY + entity.solidArea.y;
		int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;
		
		int entityLeftCol = entityLeftWorldX / gPanel.tileSize;
		int entityRightCol = entityRightWorldX / gPanel.tileSize;
		int entityTopRow = entityTopWorldY / gPanel.tileSize;
		int entityBottomRow = entityBottomWorldY / gPanel.tileSize;
		
		int tileNum1, tileNum2;
		
		if (entity.direction == "up") {
			entityTopRow = (entityTopWorldY - entity.speed) / gPanel.tileSize;
			tileNum1 = gPanel.tileManager.mapTile[gPanel.currentMap][entityTopRow][entityLeftCol];
			tileNum2 = gPanel.tileManager.mapTile[gPanel.currentMap][entityTopRow][entityRightCol];
			if (gPanel.tileManager.tile[tileNum1].collision == true || gPanel.tileManager.tile[tileNum2].collision == true)
			{
				entity.collisionOn = true;
			}
		}
		if (entity.direction == "down") {
			entityBottomRow = (entityBottomWorldY + entity.speed) / gPanel.tileSize;
			tileNum1 = gPanel.tileManager.mapTile[gPanel.currentMap][entityBottomRow][entityLeftCol];
			tileNum2 = gPanel.tileManager.mapTile[gPanel.currentMap][entityBottomRow][entityRightCol];
			if (gPanel.tileManager.tile[tileNum1].collision == true || gPanel.tileManager.tile[tileNum2].collision == true)
			{
				entity.collisionOn = true;
			}
		}
		if (entity.direction == "left") {
			entityLeftCol = (entityLeftWorldX - entity.speed) / gPanel.tileSize;
			tileNum1 = gPanel.tileManager.mapTile[gPanel.currentMap][entityTopRow][entityLeftCol];
			tileNum2 = gPanel.tileManager.mapTile[gPanel.currentMap][entityBottomRow][entityLeftCol];
			if (gPanel.tileManager.tile[tileNum1].collision == true || gPanel.tileManager.tile[tileNum2].collision == true)
			{
				entity.collisionOn = true;
			}
		}
		if (entity.direction == "right") {
			entityRightCol = (entityRightWorldX + entity.speed) / gPanel.tileSize;
			tileNum1 = gPanel.tileManager.mapTile[gPanel.currentMap][entityTopRow][entityRightCol];
			tileNum2 = gPanel.tileManager.mapTile[gPanel.currentMap][entityBottomRow][entityRightCol];
			if (gPanel.tileManager.tile[tileNum1].collision == true || gPanel.tileManager.tile[tileNum2].collision == true)
			{
				entity.collisionOn = true;
			}
		}
		
		
	}
	
	public int checkObject(Entity entity, boolean isPlayer) {
		int index = 999;
		
		for (int i = 0; i < gPanel.objs[1].length; i++)
		{
			if (gPanel.objs[gPanel.currentMap][i] != null)
			{
				// Get entity's solid area position
				entity.solidArea.x = entity.worldX + entity.solidArea.x;
				entity.solidArea.y = entity.worldY + entity.solidArea.y;
				
				// Get the object's solid area position
				gPanel.objs[gPanel.currentMap][i].solidArea.x = gPanel.objs[gPanel.currentMap][i].worldX + gPanel.objs[gPanel.currentMap][i].solidArea.x;
				gPanel.objs[gPanel.currentMap][i].solidArea.y = gPanel.objs[gPanel.currentMap][i].worldY + gPanel.objs[gPanel.currentMap][i].solidArea.y;
				
				switch (entity.direction) {
					case "up":  
						entity.solidArea.y -= entity.speed;
						break;
					case "down":
						entity.solidArea.y += entity.speed;
						break;
					case "left":
						entity.solidArea.x -= entity.speed;
						break;
					case "right":
						entity.solidArea.x += entity.speed;
						break;
				}
				if (entity.solidArea.intersects(gPanel.objs[gPanel.currentMap][i].solidArea)) {
					if (gPanel.objs[gPanel.currentMap][i].collision == true)
					{
						entity.collisionOn = true;
					}
					if (isPlayer == true) index = i; 
				}
				entity.solidArea.x = entity.solidAreaDefaultX;
				entity.solidArea.y = entity.solidAreaDefaultY;
				gPanel.objs[gPanel.currentMap][i].solidArea.x = gPanel.objs[gPanel.currentMap][i].solidAreaDefaultX;
				gPanel.objs[gPanel.currentMap][i].solidArea.y = gPanel.objs[gPanel.currentMap][i].solidAreaDefaultY;
			}
		}
		
		return index;
		
	}
	
	// NPC OR MONSTER
	public int checkEntity(Entity entity, Entity target[][]) {
		int index = 999;
			
		for (int i = 0; i < target[1].length; i++)
		{
			if (target[gPanel.currentMap][i] != null)
			{
				// Get entity's solid area position
				entity.solidArea.x = entity.worldX + entity.solidArea.x;
				entity.solidArea.y = entity.worldY + entity.solidArea.y;
					
				// Get the object's solid area position
				target[gPanel.currentMap][i].solidArea.x = target[gPanel.currentMap][i].worldX + target[gPanel.currentMap][i].solidArea.x;
				target[gPanel.currentMap][i].solidArea.y = target[gPanel.currentMap][i].worldY + target[gPanel.currentMap][i].solidArea.y;
					
				switch (entity.direction) {
					case "up":  
						entity.solidArea.y -= entity.speed;
						break;
					case "down":
						entity.solidArea.y += entity.speed;
						break;
					case "left":
						entity.solidArea.x -= entity.speed;
						break;
					case "right":
						entity.solidArea.x += entity.speed;
						break;
				}
				if (entity.solidArea.intersects(target[gPanel.currentMap][i].solidArea)) {
					
					if (target[gPanel.currentMap][i] != entity)
						{
							if (target[gPanel.currentMap][i].name != "Spike") entity.collisionOn = true;
							index = i; 
						} 

				}
				entity.solidArea.x = entity.solidAreaDefaultX;
				entity.solidArea.y = entity.solidAreaDefaultY;
				target[gPanel.currentMap][i].solidArea.x = target[gPanel.currentMap][i].solidAreaDefaultX;
				target[gPanel.currentMap][i].solidArea.y = target[gPanel.currentMap][i].solidAreaDefaultY;
			}
			
		}
		
		return index;
	}
	
	public boolean checkPlayer(Entity entity)
	{
			boolean contactPlayer = false;
	
			// Get entity's solid area position
			entity.solidArea.x = entity.worldX + entity.solidArea.x;
			entity.solidArea.y = entity.worldY + entity.solidArea.y;
				
			// Get the object's solid area position
			gPanel.player.solidArea.x = gPanel.player.worldX + gPanel.player.solidArea.x;
			gPanel.player.solidArea.y = gPanel.player.worldY + gPanel.player.solidArea.y;
				
			switch (entity.direction) {
				case "up":  
					entity.solidArea.y -= entity.speed;
					break;
				case "down":
					entity.solidArea.y += entity.speed;
					break;
				case "left":
					entity.solidArea.x -= entity.speed;
					break;
				case "right":
					entity.solidArea.x += entity.speed;
					break;
			}
			if (entity.solidArea.intersects(gPanel.player.solidArea)) {
				
					entity.collisionOn = true;
					contactPlayer = true;

			}
			entity.solidArea.x = entity.solidAreaDefaultX;
			entity.solidArea.y = entity.solidAreaDefaultY;
			gPanel.player.solidArea.x = gPanel.player.solidAreaDefaultX;
			gPanel.player.solidArea.y = gPanel.player.solidAreaDefaultY;
			
			return contactPlayer;

	}
}
