package main;

import java.util.Random;

import entity.Entity;
import entity.NPC_Demorden;
import entity.NPC_Merchant;
import monster.MON_Barrel;
import monster.MON_Bearzodia;
import monster.MON_Fly;
import monster.MON_Gladius;
import monster.MON_Goblin;
import monster.MON_Harpy;
import monster.MON_Slime;
import monster.MON_Spike;
import object.*;
import tile.TileManager;
import weapon.*;

public class AssetSetter {
	
	GamePanel gPanel;
	TileManager tileManager;
	
	public AssetSetter(GamePanel gPanel)
	{
		this.gPanel = gPanel;
		this.tileManager = gPanel.tileManager;
	}
	
	public void setObject()
	{
		
		// MAP 0
		int mapNum = 0;
		int index = 0;
		
		index = putObject(new OBJ_Prisoner(gPanel), index, mapNum, 26, 15);
		index = putObject(new OBJ_KeySilver(gPanel, 1000), index, mapNum, 24, 19);
		
		// MAP 1
		mapNum = 1;
		
		gPanel.objs[mapNum][0] = new OBJ_Chest(gPanel, 1000);		
		gPanel.objs[mapNum][0].setLoot(new WEAP_Stick(gPanel));
		gPanel.objs[mapNum][0].worldX = 22 * gPanel.tileSize;
		gPanel.objs[mapNum][0].worldY = 17 * gPanel.tileSize;
		index = 1;
		gPanel.tileManager.cantPut[mapNum][22][17] = true;
		index = putObject(new OBJ_Torch(gPanel), index, mapNum, 21, 23);
		index = putObject(new OBJ_Torch(gPanel), index, mapNum, 24, 23);
		index = putObject(new OBJ_FlagRed(gPanel), index, mapNum, 33, 14);
		index = putObject(new OBJ_Torch(gPanel), index, mapNum, 30, 27);
		index = putObject(new OBJ_Torch(gPanel), index, mapNum, 32, 31);
		
		
		
		// MAP 2
		mapNum = 2;
		index = 0;
		index = putObject(new OBJ_BookShelf(gPanel), index, mapNum, 21, 11);
		index = putObject(new OBJ_BookShelf(gPanel), index, mapNum, 22, 11);
		index = putObject(new OBJ_BookShelf(gPanel), index, mapNum, 26, 11);
		index = putObject(new OBJ_BookShelf(gPanel), index, mapNum, 27, 11);
		index = putObject(new OBJ_Torch(gPanel), index, mapNum, 23, 10);
		index = putObject(new OBJ_Torch(gPanel), index, mapNum, 25, 10);
		index = putObject(new OBJ_FlagGreen(gPanel), index, mapNum, 24, 9);
		
		
		// MAP 3
		mapNum = 3;
		gPanel.objs[mapNum][0] = new OBJ_Chest(gPanel, 3001);
		gPanel.objs[mapNum][0].setLoot(new WEAP_Shield1(gPanel));
		gPanel.objs[mapNum][0].worldX = 9 * gPanel.tileSize;
		gPanel.objs[mapNum][0].worldY = 41 * gPanel.tileSize;
		gPanel.tileManager.cantPut[mapNum][9][41] = true;
		
		index = 1;
		index = putObject(new OBJ_Door(gPanel, 3000), index, mapNum, 27, 25);
		index = putObject(new OBJ_Torch(gPanel), index, mapNum, 21, 18);
		index = putObject(new OBJ_Torch(gPanel), index, mapNum, 24, 25);
		index = putObject(new OBJ_Torch(gPanel), index, mapNum, 29, 30);
		index = putObject(new OBJ_Torch(gPanel), index, mapNum, 32, 39);
		index = putObject(new OBJ_Torch(gPanel), index, mapNum, 44, 42);
		index = putObject(new OBJ_Torch(gPanel), index, mapNum, 11, 43);
		index = putObject(new OBJ_Torch(gPanel), index, mapNum, 11, 39);
		index = putObject(new OBJ_Prisoner(gPanel), index, mapNum, 41, 29);
		index = putObject(new OBJ_PotionRed(gPanel), index, mapNum, 38, 37);
		
		

		
		
		// MAP 4
		mapNum = 4;
		gPanel.objs[mapNum][0] = new OBJ_Torch(gPanel);
		gPanel.objs[mapNum][0].worldX = 23 * gPanel.tileSize;
		gPanel.objs[mapNum][0].worldY = 24 * gPanel.tileSize;
		
		gPanel.objs[mapNum][1] = new OBJ_Torch(gPanel);
		gPanel.objs[mapNum][1].worldX = 28 * gPanel.tileSize;
		gPanel.objs[mapNum][1].worldY = 24 * gPanel.tileSize;
		
		gPanel.objs[mapNum][2] = new OBJ_Torch(gPanel);
		gPanel.objs[mapNum][2].worldX = 23 * gPanel.tileSize;
		gPanel.objs[mapNum][2].worldY = 17 * gPanel.tileSize;
		
		gPanel.objs[mapNum][3] = new OBJ_Torch(gPanel);
		gPanel.objs[mapNum][3].worldX = 28 * gPanel.tileSize;
		gPanel.objs[mapNum][3].worldY = 17 * gPanel.tileSize;
		
		gPanel.objs[mapNum][4] = new OBJ_Prisoner(gPanel);
		gPanel.objs[mapNum][4].worldX = 33 * gPanel.tileSize;
		gPanel.objs[mapNum][4].worldY = 20 * gPanel.tileSize;
		
		
		
		// MAP 5
		mapNum = 5;
		
		gPanel.objs[mapNum][0] = new OBJ_FlagRed(gPanel);
		gPanel.objs[mapNum][0].worldX = 25 * gPanel.tileSize;
		gPanel.objs[mapNum][0].worldY = 16 * gPanel.tileSize;
		
		gPanel.objs[mapNum][1] = new OBJ_FlagRed(gPanel);
		gPanel.objs[mapNum][1].worldX = 25 * gPanel.tileSize;
		gPanel.objs[mapNum][1].worldY = 24 * gPanel.tileSize;
		
		gPanel.objs[mapNum][2] = new OBJ_Torch(gPanel);
		gPanel.objs[mapNum][2].worldX = 21 * gPanel.tileSize;
		gPanel.objs[mapNum][2].worldY = 19 * gPanel.tileSize;
		
		gPanel.objs[mapNum][3] = new OBJ_Torch(gPanel);
		gPanel.objs[mapNum][3].worldX = 21 * gPanel.tileSize;
		gPanel.objs[mapNum][3].worldY = 21 * gPanel.tileSize;
		
		gPanel.objs[mapNum][4] = new OBJ_Prisoner(gPanel);
		gPanel.objs[mapNum][4].worldX = 18 * gPanel.tileSize;
		gPanel.objs[mapNum][4].worldY = 20 * gPanel.tileSize;
		
		gPanel.objs[mapNum][5] = new OBJ_Torch(gPanel);
		gPanel.objs[mapNum][5].worldX = 31 * gPanel.tileSize;
		gPanel.objs[mapNum][5].worldY = 19 * gPanel.tileSize;
		
		gPanel.objs[mapNum][6] = new OBJ_Torch(gPanel);
		gPanel.objs[mapNum][6].worldX = 31 * gPanel.tileSize;
		gPanel.objs[mapNum][6].worldY = 21 * gPanel.tileSize;
		
		
		// MAP 6
		mapNum = 6;
		
		gPanel.objs[mapNum][0] = new OBJ_Torch(gPanel);
		gPanel.objs[mapNum][0].worldX = 38 * gPanel.tileSize;
		gPanel.objs[mapNum][0].worldY = 73 * gPanel.tileSize;
		
		gPanel.objs[mapNum][1] = new OBJ_Torch(gPanel);
		gPanel.objs[mapNum][1].worldX = 50 * gPanel.tileSize;
		gPanel.objs[mapNum][1].worldY = 67 * gPanel.tileSize;
		
		gPanel.objs[mapNum][2] = new OBJ_Torch(gPanel);
		gPanel.objs[mapNum][2].worldX = 30 * gPanel.tileSize;
		gPanel.objs[mapNum][2].worldY = 67 * gPanel.tileSize;
		
		gPanel.objs[mapNum][3] = new OBJ_Torch(gPanel);
		gPanel.objs[mapNum][3].worldX = 37 * gPanel.tileSize;
		gPanel.objs[mapNum][3].worldY = 61 * gPanel.tileSize;
		
		gPanel.objs[mapNum][4] = new OBJ_Torch(gPanel);
		gPanel.objs[mapNum][4].worldX = 43 * gPanel.tileSize;
		gPanel.objs[mapNum][4].worldY = 61 * gPanel.tileSize;
		

		putObject(new OBJ_Door(gPanel, 6000), 5, mapNum, 40, 54);
		
		
		gPanel.objs[mapNum][6] = new OBJ_Torch(gPanel);
		gPanel.objs[mapNum][6].worldX = 37 * gPanel.tileSize;
		gPanel.objs[mapNum][6].worldY = 46 * gPanel.tileSize;
		
		gPanel.objs[mapNum][7] = new OBJ_Torch(gPanel);
		gPanel.objs[mapNum][7].worldX = 37 * gPanel.tileSize;
		gPanel.objs[mapNum][7].worldY = 42 * gPanel.tileSize;
		
		
		gPanel.objs[mapNum][8] = new OBJ_Torch(gPanel);
		gPanel.objs[mapNum][8].worldX = 35 * gPanel.tileSize;
		gPanel.objs[mapNum][8].worldY = 37 * gPanel.tileSize;
		
		gPanel.objs[mapNum][9] = new OBJ_Torch(gPanel);
		gPanel.objs[mapNum][9].worldX = 43 * gPanel.tileSize;
		gPanel.objs[mapNum][9].worldY = 37 * gPanel.tileSize;
		
		gPanel.objs[mapNum][10] = new OBJ_Torch(gPanel);
		gPanel.objs[mapNum][10].worldX = 52 * gPanel.tileSize;
		gPanel.objs[mapNum][10].worldY = 43 * gPanel.tileSize;
		
		putObject(new OBJ_Door(gPanel, 6001), 11, mapNum, 39, 29);
		
		gPanel.objs[mapNum][12] = new OBJ_Torch(gPanel);
		gPanel.objs[mapNum][12].worldX = 38 * gPanel.tileSize;
		gPanel.objs[mapNum][12].worldY = 30 * gPanel.tileSize;
		
		gPanel.objs[mapNum][13] = new OBJ_Torch(gPanel);
		gPanel.objs[mapNum][13].worldX = 41 * gPanel.tileSize;
		gPanel.objs[mapNum][13].worldY = 30 * gPanel.tileSize;
		
		gPanel.objs[mapNum][14] = new OBJ_Torch(gPanel);
		gPanel.objs[mapNum][14].worldX = 38 * gPanel.tileSize;
		gPanel.objs[mapNum][14].worldY = 24 * gPanel.tileSize;
		
		gPanel.objs[mapNum][15] = new OBJ_Torch(gPanel);
		gPanel.objs[mapNum][15].worldX = 41 * gPanel.tileSize;
		gPanel.objs[mapNum][15].worldY = 24 * gPanel.tileSize;
		
		gPanel.objs[mapNum][16] = new OBJ_Torch(gPanel);
		gPanel.objs[mapNum][16].worldX = 44 * gPanel.tileSize;
		gPanel.objs[mapNum][16].worldY = 29 * gPanel.tileSize;
		
		putObject(new OBJ_Torch(gPanel), 17, mapNum, 38, 20);
		putObject(new OBJ_Torch(gPanel), 18, mapNum, 41, 20);
		
		// MAP 7
		mapNum = 7;
		gPanel.objs[mapNum][0] = new OBJ_Chest(gPanel, 7001);
		gPanel.objs[mapNum][0].setLoot(new WEAP_Sword2(gPanel));
		gPanel.objs[mapNum][0].worldX = 33 * gPanel.tileSize;
		gPanel.objs[mapNum][0].worldY = 34 * gPanel.tileSize;
		gPanel.tileManager.cantPut[mapNum][33][34] = true;
		
		
		index = 1;
		index = putObject(new OBJ_Torch(gPanel), index, mapNum, 56, 51);
		index = putObject(new OBJ_Torch(gPanel), index, mapNum, 47, 55);
		index = putObject(new OBJ_Torch(gPanel), index, mapNum, 38, 55);
		index = putObject(new OBJ_Torch(gPanel), index, mapNum, 35, 50);
		index = putObject(new OBJ_Torch(gPanel), index, mapNum, 35, 48);
		index = putObject(new OBJ_Torch(gPanel), index, mapNum, 41, 43);
		index = putObject(new OBJ_Prisoner(gPanel), index, mapNum, 49, 42);
		index = putObject(new OBJ_FlagGreen(gPanel), index, mapNum, 37, 37);
		index = putObject(new OBJ_FlagGreen(gPanel), index, mapNum, 40, 37);
		index = putObject(new OBJ_BookShelf(gPanel), index, mapNum, 32, 28);
		index = putObject(new OBJ_BookShelf(gPanel), index, mapNum, 33, 28);
		index = putObject(new OBJ_BookShelf(gPanel), index, mapNum, 34, 28);
		index = putObject(new OBJ_BookShelf(gPanel), index, mapNum, 38, 28);
		index = putObject(new OBJ_BookShelf(gPanel), index, mapNum, 39, 28);
		index = putObject(new OBJ_BookShelf(gPanel), index, mapNum, 40, 28);
		index = putObject(new OBJ_Door(gPanel, 7000), index, mapNum, 38, 36);
		//index = putObject(new OBJ_KeyGold(gPanel, 7000), index, mapNum, 38, 40);
		
		// MAP 8
		mapNum = 8;
		
		gPanel.objs[mapNum][0] = new OBJ_Chest(gPanel, 8001);
		gPanel.objs[mapNum][0].setLoot(new WEAP_Sword2(gPanel));
		gPanel.objs[mapNum][0].worldX = 52 * gPanel.tileSize;
		gPanel.objs[mapNum][0].worldY = 59 * gPanel.tileSize;
		gPanel.tileManager.cantPut[mapNum][52][59] = true;
		
		index = 1;
		index = putObject(new OBJ_Door(gPanel, 8000), index, mapNum, 51, 55);
		index = putObject(new OBJ_Torch(gPanel), index, mapNum, 46, 55);
		index = putObject(new OBJ_Torch(gPanel), index, mapNum, 57, 55);
		index = putObject(new OBJ_Torch(gPanel), index, mapNum, 46, 36);
		index = putObject(new OBJ_Torch(gPanel), index, mapNum, 57, 36);
		index = putObject(new OBJ_Torch(gPanel), index, mapNum, 43, 46);
		index = putObject(new OBJ_Torch(gPanel), index, mapNum, 60, 46);
		
		
		
		// MAP 9
		mapNum = 9;
		index = 0;
		index = putObject(new OBJ_Torch(gPanel), index, mapNum, 36, 40);
		index = putObject(new OBJ_Torch(gPanel), index, mapNum, 43, 40);
		index = putObject(new OBJ_Torch(gPanel), index, mapNum, 43, 47);
		index = putObject(new OBJ_Torch(gPanel), index, mapNum, 36, 47);
		index = putObject(new OBJ_Prisoner(gPanel), index, mapNum, 39, 35);
		index = putObject(new OBJ_Torch(gPanel), index, mapNum, 25, 42);
		index = putObject(new OBJ_Torch(gPanel), index, mapNum, 25, 46);
		index = putObject(new OBJ_Torch(gPanel), index, mapNum, 15, 45);
		index = putObject(new OBJ_Torch(gPanel), index, mapNum, 10, 40);
		
		
		// MAP 10
		mapNum = 10;
		gPanel.objs[mapNum][0] = new OBJ_Chest(gPanel, 10000);
		gPanel.objs[mapNum][0].setLoot(new WEAP_Shield3(gPanel));
		gPanel.objs[mapNum][0].worldX = 32 * gPanel.tileSize;
		gPanel.objs[mapNum][0].worldY = 43 * gPanel.tileSize;
		gPanel.tileManager.cantPut[mapNum][32][43] = true;
		index = 1;
		index = putObject(new OBJ_Torch(gPanel), index, mapNum, 15, 41);
		index = putObject(new OBJ_Torch(gPanel), index, mapNum, 15, 45);
		index = putObject(new OBJ_Torch(gPanel), index, mapNum, 23, 48);
		index = putObject(new OBJ_Torch(gPanel), index, mapNum, 23, 37);
		index = putObject(new OBJ_Torch(gPanel), index, mapNum, 35, 43);
		index = putObject(new OBJ_Torch(gPanel), index, mapNum, 32, 41);
		index = putObject(new OBJ_Torch(gPanel), index, mapNum, 32, 45);
		
		
		
		// MAP 11
		mapNum = 11;
		index = 0;
		index = putObject(new OBJ_Torch(gPanel), index, mapNum, 45, 51);
		index = putObject(new OBJ_Torch(gPanel), index, mapNum, 51, 51);
		index = putObject(new OBJ_Torch(gPanel), index, mapNum, 39, 48);
		index = putObject(new OBJ_Torch(gPanel), index, mapNum, 57, 48);
		index = putObject(new OBJ_Torch(gPanel), index, mapNum, 35, 43);
		index = putObject(new OBJ_Torch(gPanel), index, mapNum, 61, 43);
		index = putObject(new OBJ_Torch(gPanel), index, mapNum, 35, 35);
		index = putObject(new OBJ_Torch(gPanel), index, mapNum, 61, 35);
		index = putObject(new OBJ_Torch(gPanel), index, mapNum, 57, 30);
		index = putObject(new OBJ_Torch(gPanel), index, mapNum, 39, 30);
		index = putObject(new OBJ_Torch(gPanel), index, mapNum, 61, 35);
		index = putObject(new OBJ_Torch(gPanel), index, mapNum, 43, 27);
		index = putObject(new OBJ_Torch(gPanel), index, mapNum, 53, 27);
		
		index = putObject(new OBJ_Torch(gPanel), index, mapNum, 57, 39);
		index = putObject(new OBJ_Torch(gPanel), index, mapNum, 39, 39);
		index = putObject(new OBJ_Torch(gPanel), index, mapNum, 48, 44);
		index = putObject(new OBJ_Torch(gPanel), index, mapNum, 48, 34);
		
		index = putObject(new OBJ_FlagGreen(gPanel), index, mapNum, 46, 27);
		index = putObject(new OBJ_FlagGreen(gPanel), index, mapNum, 50, 27);
		
		
	}

	public void setNPC()
	{
		
		int mapNum = 0;
		gPanel.npc[mapNum][0] = new NPC_Demorden(gPanel);
		gPanel.npc[mapNum][0].worldX = 26 * gPanel.tileSize;
		gPanel.npc[mapNum][0].worldY = 19 * gPanel.tileSize;
		
		mapNum = 2;
		gPanel.npc[mapNum][0] = new NPC_Merchant(gPanel);
		gPanel.npc[mapNum][0].worldX = 24 * gPanel.tileSize;
		gPanel.npc[mapNum][0].worldY = 12 * gPanel.tileSize;

		
//		mapNum = 5;
//		gPanel.npc[mapNum][0] = new NPC_Demorden(gPanel);
//		gPanel.npc[mapNum][0].worldX = 23 * gPanel.tileSize;
//		gPanel.npc[mapNum][0].worldY = 20 * gPanel.tileSize;
//		
		mapNum = 7;
		gPanel.npc[mapNum][0] = new NPC_Merchant(gPanel);
		gPanel.npc[mapNum][0].worldX = 36 * gPanel.tileSize;
		gPanel.npc[mapNum][0].worldY = 29 * gPanel.tileSize;
		gPanel.npc[mapNum][0].inventory.add(new WEAP_Shield3(gPanel));
		Entity keyGold6001 = new OBJ_KeyGold(gPanel, 6001);
		keyGold6001.price = 250;
		gPanel.npc[mapNum][0].inventory.add(keyGold6001);
		
				
		
		
	}
	public void setMonster() {
		
		gPanel.tileManager.cantPut[gPanel.currentMap] = new boolean[150][150];
		// MAP 0
		int mapNum = 0;
		int index = 0;

		if (gPanel.currentMap == 0) {
			mapNum = 0;
			index = 0;
		}
		
		// MAP 1
		else if (gPanel.currentMap == 1) {
			mapNum = 1;
			gPanel.monster[mapNum][0] = new MON_Barrel(gPanel);
			gPanel.monster[mapNum][0].worldX = 39 * gPanel.tileSize;
			gPanel.monster[mapNum][0].worldY = 28 * gPanel.tileSize;
			index = 1;
			index = setUpMonster("Slime", index, 15, mapNum, 17, 38, 15, 32, 20);
			index = setUpMonster("Barrel", index, 10, mapNum, 17, 38, 15, 32, 20);
			
		}
		
		
		
		
		// MAP 3
		else if (gPanel.currentMap == 3) {
			mapNum = 3;
			
			gPanel.monster[mapNum][0] = new MON_Spike(gPanel);
			gPanel.monster[mapNum][0].worldX = 38 * gPanel.tileSize;
			gPanel.monster[mapNum][0].worldY = 35 * gPanel.tileSize;
			
			gPanel.monster[mapNum][1] = new MON_Spike(gPanel);
			gPanel.monster[mapNum][1].worldX = 39 * gPanel.tileSize;
			gPanel.monster[mapNum][1].worldY = 35 * gPanel.tileSize;
			
			gPanel.monster[mapNum][2] = new MON_Spike(gPanel);
			gPanel.monster[mapNum][2].worldX = 38 * gPanel.tileSize;
			gPanel.monster[mapNum][2].worldY = 38 * gPanel.tileSize;
			
			gPanel.monster[mapNum][3] = new MON_Spike(gPanel);
			gPanel.monster[mapNum][3].worldX = 39 * gPanel.tileSize;
			gPanel.monster[mapNum][3].worldY = 38 * gPanel.tileSize;
			
			gPanel.monster[mapNum][4] = new MON_Barrel(gPanel);
			gPanel.monster[mapNum][4].worldX = 11* gPanel.tileSize;
			gPanel.monster[mapNum][4].worldY = 40 * gPanel.tileSize;
			tileManager.cantPut[mapNum][11][40] = true;
			
			gPanel.monster[mapNum][5] = new MON_Barrel(gPanel);
			gPanel.monster[mapNum][5].worldX = 11* gPanel.tileSize;
			gPanel.monster[mapNum][5].worldY = 41 * gPanel.tileSize;
			tileManager.cantPut[mapNum][11][41] = true;

			gPanel.monster[mapNum][6] = new MON_Barrel(gPanel);
			gPanel.monster[mapNum][6].worldX = 11* gPanel.tileSize;
			gPanel.monster[mapNum][6].worldY = 42 * gPanel.tileSize;
			tileManager.cantPut[mapNum][11][42] = true;
			
			index = 7;
			
			index = setUpMonster("Slime", index, 5, mapNum, 16, 28, 14, 24, 15);
			index = setUpMonster("Slime", index, 10, mapNum, 0, 45, 30, 47, 20);
			index = setUpMonster("Harpy", index, 7, mapNum, 0, 45, 30, 47, 20);
			index = setUpMonster("Fly", index, 5, mapNum, 0, 45, 30, 47, 20);
		}
		
		
		
		
		// MAP 4
		else if (gPanel.currentMap == 4) {
			mapNum = 4;
			putMonster(new MON_Barrel(gPanel), 0, mapNum, 21, 20);
			putMonster(new MON_Barrel(gPanel), 1, mapNum, 21, 21);
			putMonster(new MON_Barrel(gPanel), 2, mapNum, 30, 20);
			putMonster(new MON_Barrel(gPanel), 3, mapNum, 30, 21);
		

			gPanel.monster[mapNum][4] = new MON_Spike(gPanel);
			gPanel.monster[mapNum][4].worldX = 24 * gPanel.tileSize;
			gPanel.monster[mapNum][4].worldY = 22 * gPanel.tileSize;
			
			gPanel.monster[mapNum][5] = new MON_Spike(gPanel);
			gPanel.monster[mapNum][5].worldX = 25 * gPanel.tileSize;
			gPanel.monster[mapNum][5].worldY = 22 * gPanel.tileSize;
			
			gPanel.monster[mapNum][6] = new MON_Spike(gPanel);
			gPanel.monster[mapNum][6].worldX = 26 * gPanel.tileSize;
			gPanel.monster[mapNum][6].worldY = 22 * gPanel.tileSize;
			
			gPanel.monster[mapNum][7] = new MON_Spike(gPanel);
			gPanel.monster[mapNum][7].worldX = 27 * gPanel.tileSize;
			gPanel.monster[mapNum][7].worldY = 22 * gPanel.tileSize;
			
			gPanel.monster[mapNum][8] = new MON_Spike(gPanel);
			gPanel.monster[mapNum][8].worldX = 24 * gPanel.tileSize;
			gPanel.monster[mapNum][8].worldY = 19 * gPanel.tileSize;
			
			gPanel.monster[mapNum][9] = new MON_Spike(gPanel);
			gPanel.monster[mapNum][9].worldX = 25 * gPanel.tileSize;
			gPanel.monster[mapNum][9].worldY = 19 * gPanel.tileSize;
			
			gPanel.monster[mapNum][10] = new MON_Spike(gPanel);
			gPanel.monster[mapNum][10].worldX = 26 * gPanel.tileSize;
			gPanel.monster[mapNum][10].worldY = 19 * gPanel.tileSize;
			
			gPanel.monster[mapNum][11] = new MON_Spike(gPanel);
			gPanel.monster[mapNum][11].worldX = 27 * gPanel.tileSize;
			gPanel.monster[mapNum][11].worldY = 19 * gPanel.tileSize;
			
			index = 12;
			setUpMonster("Slime", index, 10, mapNum, 19, 32, 15, 26, 18);
			setUpMonster("Fly", index, 5, mapNum, 19, 32, 15, 26, 15);
		}
		
		
		
		// MAP 5
		
		else if (gPanel.currentMap == 5)
		{
			mapNum = 5;
			
			
			
			gPanel.monster[mapNum][0] = new MON_Barrel(gPanel);
			gPanel.monster[mapNum][0].worldX = 30 * gPanel.tileSize;
			gPanel.monster[mapNum][0].worldY = 17 * gPanel.tileSize;
			
			gPanel.monster[mapNum][1] = new MON_Barrel(gPanel);
			gPanel.monster[mapNum][1].worldX = 30 * gPanel.tileSize;
			gPanel.monster[mapNum][1].worldY = 18 * gPanel.tileSize;
			
			gPanel.monster[mapNum][2] = new MON_Barrel(gPanel);
			gPanel.monster[mapNum][2].worldX = 30 * gPanel.tileSize;
			gPanel.monster[mapNum][2].worldY = 22 * gPanel.tileSize;
			
			gPanel.monster[mapNum][3] = new MON_Barrel(gPanel);
			gPanel.monster[mapNum][3].worldX = 30 * gPanel.tileSize;
			gPanel.monster[mapNum][3].worldY = 23 * gPanel.tileSize;
			
			if (gPanel.winBoss1 == 0) putMonster(new MON_Gladius(gPanel), 4, mapNum, 23, 20);
		}
		
		
		
		// MAP 6
		
		else if (gPanel.currentMap == 6)
		{
			mapNum = 6;
			index = 0;
			index = setUpMonster("Slime", index, 8, mapNum, 31, 49, 62, 71, 20);
			index = setUpMonster("Barrel", index, 8, mapNum, 31, 49, 62, 71, 15);
			index = setUpMonster("Harpy", index, 5, mapNum, 31, 49, 62, 71, 20);
			index = setUpMonster("Barrel", index, 30, mapNum, 29, 51, 31, 45, 30);
			index = setUpMonster("Slime", index, 10, mapNum, 29, 51, 31, 45, 20);
			index = setUpMonster("Harpy", index, 10, mapNum, 29, 51, 31, 45, 20);
			index = setUpMonster("Fly", index, 4, mapNum, 29, 51, 31, 45, 15);
			index = setUpMonster("Goblin", index, 3, mapNum, 29, 51, 31, 45, 20);
		}
		
		
		// MAP 7
		
		else if (gPanel.currentMap == 7) {
			mapNum = 7;
			index = 0;
			index = setUpMonster("Slime", index, 8, mapNum, 36, 55, 44, 54, 20);
			index = setUpMonster("Harpy", index, 8, mapNum, 36, 55, 44, 54, 30);
			index = setUpMonster("Fly", index, 5, mapNum, 36, 55, 44, 54, 30);
			index = setUpMonster("Barrel", index, 5, mapNum, 36, 55, 44, 54, 30);
		}
		
		
		// MAP 8
		
		else if (gPanel.currentMap == 8) {
			mapNum = 8;
			index = 0;
			index = putMonster(new MON_Spike(gPanel), index, mapNum, 47, 47);
			index = putMonster(new MON_Spike(gPanel), index, mapNum, 47, 48);
			index = putMonster(new MON_Spike(gPanel), index, mapNum, 47, 49);
			
			index = putMonster(new MON_Barrel(gPanel), index, mapNum, 52, 47);
			index = putMonster(new MON_Barrel(gPanel), index, mapNum, 52, 48);
			index = putMonster(new MON_Barrel(gPanel), index, mapNum, 52, 49);
			
			index = putMonster(new MON_Spike(gPanel), index, mapNum, 57, 47);
			index = putMonster(new MON_Spike(gPanel), index, mapNum, 57, 48);
			index = putMonster(new MON_Spike(gPanel), index, mapNum, 57, 49);
			
			index = putMonster(new MON_Spike(gPanel), index, mapNum, 47, 43);
			index = putMonster(new MON_Spike(gPanel), index, mapNum, 47, 44);
			index = putMonster(new MON_Spike(gPanel), index, mapNum, 47, 45);
			
			index = putMonster(new MON_Barrel(gPanel), index, mapNum, 52, 43);
			index = putMonster(new MON_Barrel(gPanel), index, mapNum, 52, 44);
			index = putMonster(new MON_Barrel(gPanel), index, mapNum, 52, 45);
			
			index = putMonster(new MON_Spike(gPanel), index, mapNum, 57, 43);
			index = putMonster(new MON_Spike(gPanel), index, mapNum, 57, 44);
			index = putMonster(new MON_Spike(gPanel), index, mapNum, 57, 45);
			
			index = setUpMonster("Slime", index, 5, mapNum, 44, 59, 37, 54, 30);
			index = setUpMonster("Harpy", index, 5, mapNum, 44, 59, 37, 54, 30);
			index = setUpMonster("Fly", index, 5, mapNum, 44, 59, 37, 54, 30);
			index = setUpMonster("Barrel", index, 8, mapNum, 44, 59, 37, 54, 30);

		}
				
		
		
		// MAP 9
		else if (gPanel.currentMap == 9) {
			mapNum = 9;
			index = 0;
			index = putMonster(new MON_Spike(gPanel), index, mapNum, 38, 42);
			index = putMonster(new MON_Spike(gPanel), index, mapNum, 38, 43);
			index = putMonster(new MON_Spike(gPanel), index, mapNum, 38, 44);
			index = putMonster(new MON_Spike(gPanel), index, mapNum, 38, 45);
			
			index = putMonster(new MON_Spike(gPanel), index, mapNum, 41, 42);
			index = putMonster(new MON_Spike(gPanel), index, mapNum, 41, 43);
			index = putMonster(new MON_Spike(gPanel), index, mapNum, 41, 44);
			index = putMonster(new MON_Spike(gPanel), index, mapNum, 41, 45);
			
			index = putMonster(new MON_Spike(gPanel), index, mapNum, 39, 42);
			index = putMonster(new MON_Spike(gPanel), index, mapNum, 40, 42);
			index = putMonster(new MON_Spike(gPanel), index, mapNum, 40, 45);
			index = putMonster(new MON_Spike(gPanel), index, mapNum, 39, 45);
			
			index = putMonster(new MON_Barrel(gPanel), index, mapNum, 43, 43);
			index = putMonster(new MON_Barrel(gPanel), index, mapNum, 43, 44);
			index = putMonster(new MON_Barrel(gPanel), index, mapNum, 36, 43);
			index = putMonster(new MON_Barrel(gPanel), index, mapNum, 36, 44);
			
			index = setUpMonster("Slime", index, 5, mapNum, 33, 46, 36, 50, 25);
			index = setUpMonster("Harpy", index, 5, mapNum, 33, 46, 36, 50, 25);
			index = setUpMonster("Fly", index, 3, mapNum, 33, 46, 36, 50, 30);
			index = setUpMonster("Goblin", index, 3, mapNum, 33, 46, 36, 50, 30);
			
			index = setUpMonster("Goblin", index, 5, mapNum, 8, 17, 37, 48, 20);
			index = setUpMonster("Fly", index, 8, mapNum, 8, 17, 37, 48, 20);
			index = setUpMonster("Slime", index, 5, mapNum, 8, 17, 37, 48, 20);
			index = setUpMonster("Barrel", index, 8, mapNum, 8, 17, 37, 48, 20);
		}
		
		// MAP 10
		else if (gPanel.currentMap == 10) {
			mapNum = 10;
			index = 0;
			index = setUpMonster("Goblin", index, 8, mapNum, 16, 34, 38, 47, 20);
			index = setUpMonster("Fly", index, 5, mapNum, 16, 34, 38, 47, 20);
			index = setUpMonster("Slime", index, 5, mapNum, 16, 34, 38, 47, 20);
			index = setUpMonster("Barrel", index, 8, mapNum, 16, 34, 38, 47, 20);
		}
		
		// MAP 10
			else if (gPanel.currentMap == 11) {
				mapNum = 11;
				index = 0;
				if (gPanel.winBoss2 == 0) putMonster(new MON_Bearzodia(gPanel) , index, mapNum, 48, 39);
			}
		
	}
	
	
	public int setUpMonster(String monsterName, int indexStart, int amount, int mapNum, int colStart, int colEnd, int rowStart, int rowEnd, int rate) {
		
		int randNum, index = indexStart;
		for (int col = colStart; col <= colEnd; col++)
		{
			for (int row = rowStart; row <= rowEnd; row++) {
				if (tileManager.tile[tileManager.mapTile[mapNum][row][col]].collision == false 
						&& tileManager.cantPut[mapNum][col][row] == false
						&& tileManager.mapTile[mapNum][row][col] != 20
						&& tileManager.mapTile[mapNum][row][col] != 42
						&& tileManager.mapTile[mapNum][row][col] != 43
						&& tileManager.mapTile[mapNum][row][col] != 44
						&& col != tileManager.getDefaultXInCurrentMap(mapNum) / gPanel.tileSize
						&& row != tileManager.getDefaultYInCurrentMap(mapNum) / gPanel.tileSize
					)
				{
					randNum = new Random().nextInt(rate);
					if (randNum < 1) {
						Entity entity;
						switch (monsterName) {
						case "Goblin": entity = new MON_Goblin(gPanel); break;
						case "Fly" : entity = new MON_Fly(gPanel); break;
						case "Barrel" : entity = new MON_Barrel(gPanel); break;
						case "Harpy" : entity = new MON_Harpy(gPanel); break;
						case "Slime" : entity = new MON_Slime(gPanel); break;
						default: entity = new MON_Barrel(gPanel); break;
						}
						gPanel.monster[mapNum][index] = entity;
						gPanel.monster[mapNum][index].worldX = col * gPanel.tileSize;
						gPanel.monster[mapNum][index].worldY = row * gPanel.tileSize;
						gPanel.tileManager.cantPut[mapNum][col][row] = true;
						index++;
					}
					
					if (index > indexStart + amount) break;
					
				}
			}
			if (index > indexStart + amount) break;
		}
		
		return index;
	}

	public int putObject(Entity object, int index, int mapNum, int col, int row) {
		gPanel.objs[mapNum][index] = object;
		gPanel.objs[mapNum][index].worldX = col * gPanel.tileSize;
		gPanel.objs[mapNum][index].worldY = row * gPanel.tileSize;
		tileManager.cantPut[mapNum][col][row] = true;
		if (object.name == "Door") {
			tileManager.cantPut[mapNum][col + 1][row] = true;
			tileManager.cantPut[mapNum][col + 1][row + 1] = true;
			tileManager.cantPut[mapNum][col][row + 1] = true;
		}
		return index + 1;
	}
	
	public int putNPC(Entity npc, int index, int mapNum, int col, int row) {
		gPanel.npc[mapNum][index] = npc;
		gPanel.npc[mapNum][index].worldX = col * gPanel.tileSize;
		gPanel.npc[mapNum][index].worldY = row * gPanel.tileSize;
		tileManager.cantPut[mapNum][col][row] = true;
		return index + 1;
	}
	
	public int putMonster(Entity monster, int index, int mapNum, int col, int row) {
		gPanel.monster[mapNum][index] = monster;
		gPanel.monster[mapNum][index].worldX = col * gPanel.tileSize;
		gPanel.monster[mapNum][index].worldY = row * gPanel.tileSize;
		tileManager.cantPut[mapNum][col][row] = true;
		return index + 1;
	}
}
