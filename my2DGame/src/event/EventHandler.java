package event;


import java.io.IOError;
import java.util.Random;

import entity.Entity;
import main.GamePanel;

public class EventHandler {
	
	GamePanel gPanel;
	EventRect eventRect[][][];
	
	public int lastEventX, lastEventY;
	boolean canTouch = false;
	
	public int tmpMap, tmpCol, tmpRow;
	
	public EventHandler(GamePanel gPanel) {
		// TODO Auto-generated constructor stub
		
		this.gPanel = gPanel;
		eventRect = new EventRect[gPanel.maxMap][gPanel.maxWorldCol][gPanel.maxWorldRow];
		for (int map = 0; map < gPanel.maxMap; map++)
		{
			for (int row = 0; row < gPanel.maxWorldCol; row++)
			{
				for (int col = 0; col < gPanel.maxWorldRow; col++)
				{
					eventRect[map][col][row] = new EventRect();
					eventRect[map][col][row].x = 23;
					eventRect[map][col][row].y = 23;
					eventRect[map][col][row].height = 2;
					eventRect[map][col][row].width = 2;
					eventRect[map][col][row].eventRectDefaultX = eventRect[map][col][row].x;
					eventRect[map][col][row].eventRectDefaultY = eventRect[map][col][row].y;
				}
			}
		}
		
		
	}
	
	public void checkEvent() {
		
		
		// Check if player more far than 1 tile size from last event
		int distanceX = Math.abs(gPanel.player.worldX - lastEventX);
		int distanceY = Math.abs(gPanel.player.worldY - lastEventY);
		int distance = Math.max(distanceX, distanceY);
		
		if (distance > gPanel.tileSize) {canTouch = true;}
		
		
		if (canTouch == true)
		{
//			if (hit(0, 6, 6, "up") == true) {damagePit(gPanel.dialogueState);}
//			if (hit(0, 6, 7, "any") == true) {damagePit(gPanel.dialogueState);}
//			if (hit(0, 14, 9, "down") == true) {healingPool(gPanel.dialogueState);}
			if (hit(0, 26, 18, "any") == true) {teleport(1, 18, 31);}
			if (hit(1, 18, 31, "any") == true) {teleport(0, 26, 18);}		// 0 -> 1
			
			if (hit(1, 37, 17, "any") == true) {teleport(2, 24, 19);}
			if (hit(2, 24, 19, "any") == true) {teleport(1, 37, 17);}		// 1 -> 2
			
			if (hit(1, 36, 31, "any") == true) {teleport(3, 17, 16);}
			if (hit(3, 17, 16, "any") == true) {teleport(1, 36, 31);}		// 1 -> 3
			
			if (hit(3, 41, 42, "any") == true) {teleport(4, 20, 25);}
			if (hit(4, 20, 25, "any") == true) {teleport(3, 41, 42);}		// 3 -> 4
			
			if (hit(4, 31, 16, "any") == true) {teleport(8, 49, 60);}
			if (hit(8, 49, 60, "any") == true) {teleport(4, 31, 16);}		// 4 -> 8
			
			if (hit(3, 4, 45, "any") == true) {gPanel.stopMusic(); teleport(5, 31, 20); gPanel.playMusic(15);}
			if (hit(5, 31, 20, "any") == true) {gPanel.stopMusic(); teleport(3, 4, 45); gPanel.playMusic(0);}		// 3 -> 5
			
			if (hit(1, 45, 28, "any") == true) {teleport(6, 41, 73);}
			if (hit(6, 41, 73, "any") == true) {teleport(1, 45, 28);}		// 1 -> 6
			
			if (hit(6, 32, 63, "any") == true) {teleport(7, 54, 53);}
			if (hit(7, 54, 53, "any") == true) {teleport(6, 32, 63);}		// 6 -> 7
			
			if (hit(6, 48, 63, "any") == true) {teleport(8, 45, 38);}
			if (hit(8, 45, 38, "any") == true) {teleport(6, 48, 63);}		// 6 -> 8
			
			if (hit(6, 30, 42, "any") == true) {teleport(9, 47, 43);}
			if (hit(9, 47, 43, "any") == true) {teleport(6, 30, 42);}		// 6 -> 9
			
			if (hit(6, 51, 45, "any") == true) {teleport(10, 15, 43);}
			if (hit(10, 15, 43, "any") == true) {teleport(6, 51, 45);}		// 6 -> 10
			
			if (hit(6, 39, 20, "any") == true) { gPanel.stopMusic(); teleport(11, 48, 52); gPanel.playMusic(15);}
			if (hit(11, 48, 52, "any") == true) { gPanel.stopMusic();teleport(6, 39, 20); gPanel.playMusic(0);}		// 6 -> 11
			
			if (hit(6, 40, 20, "any") == true) {gPanel.stopMusic();teleport(11, 48, 52); gPanel.playMusic(15);}
			if (hit(11, 48, 52, "any") == true) {gPanel.stopMusic();teleport(6, 40, 20);gPanel.playMusic(0);}		// 6 -> 11
			
			Random random = new Random(); 
			int i = 0;
			if (hit(7, 29, 49, "any") == true) {
				
				do {
					i = random.nextInt(8);
				} while (i == 5);
				teleport(i, gPanel.tileManager.getDefaultXInCurrentMap(i) / gPanel.tileSize, gPanel.tileManager.getDefaultYInCurrentMap(i) / gPanel.tileSize);
			}
			
			
			if (hit(2, 24, 14, "up") == true) {speak(gPanel.npc[2][0]); gPanel.keyHandler.enterPressed = false;}		// map 2
			if (hit(7, 36, 31, "up") == true) {speak(gPanel.npc[7][0]); gPanel.keyHandler.enterPressed = false;}		// map 7
			
		}	
		
		
		
	}

	
	public boolean hit(int map, int col, int row, String reqDirection)
	{
		boolean hit = false;
		
		if (map == gPanel.currentMap)
		{
			gPanel.player.solidArea.x = gPanel.player.worldX + gPanel.player.solidArea.x;
			gPanel.player.solidArea.y = gPanel.player.worldY + gPanel.player.solidArea.y;
			
			eventRect[map][col][row].x = col * gPanel.tileSize + eventRect[map][col][row].x;
			eventRect[map][col][row].y = row * gPanel.tileSize + eventRect[map][col][row].y;
			
			if (gPanel.player.solidArea.intersects(eventRect[map][col][row]) && eventRect[map][col][row].eventDone == false)
			{
				if (gPanel.player.lastDirection2.contentEquals(reqDirection) || reqDirection.contentEquals("any"))
				{
					hit = true;
					lastEventX = gPanel.player.worldX;
					lastEventY = gPanel.player.worldY;
				}
			}
			
			gPanel.player.solidArea.x = gPanel.player.solidAreaDefaultX;
			gPanel.player.solidArea.y = gPanel.player.solidAreaDefaultY;
			
			eventRect[map][col][row].x = eventRect[map][col][row].eventRectDefaultX;
			eventRect[map][col][row].y = eventRect[map][col][row]. eventRectDefaultY;
			
			
		}
		return hit;
		
	}
	
	public void damagePit(int gameState)
	{
		gPanel.gameState = gameState;
		gPanel.ui.currentDialogue = "You fall into a pit";
		gPanel.player.life--;
//		eventRect[map][col][row].eventDone = true;
		canTouch = false;
	}
	
	public void healingPool(int gameState)
	{
		if (gPanel.keyHandler.enterPressed == true)
		{
			gPanel.gameState = gameState;
			gPanel.ui.currentDialogue = "You have been recovered!";
			gPanel.player.life = gPanel.player.maxLife;
			
		}
		
	}
	
	public void teleport(int map, int col, int row) {
		gPanel.gameState = gPanel.transitionState;
		tmpMap = map;
		tmpCol = col;
		tmpRow = row;
		//gPanel.saveLoad.save();
		gPanel.ui.addMessage("The progress have been saved!");
		canTouch = false;
	}
	
	
	public void speak(Entity entity) {
		
		if (gPanel.keyHandler.enterPressed == true) {
			gPanel.gameState = gPanel.dialogueState;
			//gPanel.player.attackCanceled = true;
			entity.speak();
		}
	}
}
