package data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import entity.Entity;
import main.GamePanel;
import object.*;
import weapon.*;

public class SaveLoad {

	public GamePanel gPanel;
	
	public SaveLoad(GamePanel gPanel) {
		this.gPanel = gPanel;
	}
	
	
	public Entity getObjects(String itemName, int code, int decode) {
		
		Entity obj = null;
		
		switch (itemName) {
			case "Book Shelf" : obj = new OBJ_BookShelf(gPanel); break;
			case "Prisoner Skeleton": obj = new OBJ_Prisoner(gPanel); break;
			case "Green Flag": obj = new OBJ_FlagGreen(gPanel); break;
			case "Red Flag": obj = new OBJ_FlagRed(gPanel); break;
			case "Torch" : obj = new OBJ_Torch(gPanel); break;
			case "Coins": obj = new OBJ_Coins(gPanel); break; 
			case "Chest" : obj = new OBJ_Chest(gPanel, code); break;
			case "Door" : obj = new OBJ_Door(gPanel, code); break;
			case "Key Gold" : obj = new OBJ_KeyGold(gPanel, decode); break;
			case "Key Silver" : obj = new OBJ_KeySilver(gPanel, decode); break;
			case "Red Potion" : obj = new OBJ_PotionRed(gPanel); break;
			case "Green Potion" : obj = new OBJ_PotionGreen(gPanel); break;
			case "Yellow Potion" : obj = new OBJ_PotionYellow(gPanel); break;
			case "Shield 1" : obj = new WEAP_Shield1(gPanel); break;
			case "Shield 2" : obj = new WEAP_Shield2(gPanel); break;
			case "Shield 3" : obj = new WEAP_Shield3(gPanel); break;
			case "Sword 1" : obj = new WEAP_Sword1(gPanel); break;
			case "Sword 2" : obj = new WEAP_Sword2(gPanel); break;
			case "Sword 3" : obj = new WEAP_Sword3(gPanel); break;
			case "Stick" : obj = new WEAP_Stick(gPanel); break;
			default: case "Unknown" : obj = new WEAP_NoSword(gPanel); break;
		}
		
		
		return obj;
	}
	
	
	
	public void save() {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("save.dat")));
			
			DataStorage ds = new DataStorage();
			
			ds.currentMap = gPanel.currentMap;
			ds.winBoss1 = gPanel.winBoss1;
			ds.winBoss2 = gPanel.winBoss2;
			
			ds.level = gPanel.player.level;
			ds.strength = gPanel.player.strength;
			ds.dexterity = gPanel.player.dexterity;
			ds.exp = gPanel.player.exp;
			ds.nextLevelExp = gPanel.player.nextLevelExp;
			ds.coin = gPanel.player.coin;
			
			// INVENTORY
			
			for (int i = 0; i < gPanel.player.inventory.size(); i++)
			{
				ds.itemsName.add(gPanel.player.inventory.get(i).name);
				ds.itemsAmount.add(gPanel.player.inventory.get(i).amount);			
				ds.itemsDecode.add(gPanel.player.inventory.get(i).decode);
			}
			
			
			ds.currentWeaponSlot = gPanel.player.getCurrentWeaponSlot();
			ds.currentShieldSlot = gPanel.player.getCurrentShieldSlot();
			
			
			
			// OBJECTS ON MAP
			ds.mapObjectName = new String[gPanel.maxMap][gPanel.objs[1].length];
			ds.mapObjectWorldX = new int[gPanel.maxMap][gPanel.objs[1].length];
			ds.mapObjectWorldY = new int[gPanel.maxMap][gPanel.objs[1].length];
			ds.mapObjectLootName = new String[gPanel.maxMap][gPanel.objs[1].length];
			ds.mapObjectOpened = new boolean[gPanel.maxMap][gPanel.objs[1].length];
			ds.mapObjectCode = new int[gPanel.maxMap][gPanel.objs[1].length];
			ds.mapObjectDecode = new int[gPanel.maxMap][gPanel.objs[1].length];
			for (int mapNum = 0; mapNum < gPanel.maxMap; mapNum++)
			{
				for (int i = 0; i < gPanel.objs[1].length; i++)
				{
					if (gPanel.objs[mapNum][i] == null) {
						ds.mapObjectName[mapNum][i] = "NA"; 
					}
					else {
						ds.mapObjectName[mapNum][i] = gPanel.objs[mapNum][i].name;
						ds.mapObjectWorldX[mapNum][i] = gPanel.objs[mapNum][i].worldX;
						ds.mapObjectWorldY[mapNum][i] = gPanel.objs[mapNum][i].worldY;
						if (gPanel.objs[mapNum][i].loot != null) {
							ds.mapObjectLootName[mapNum][i] = gPanel.objs[mapNum][i].loot.name;
						}
						ds.mapObjectOpened[mapNum][i] = gPanel.objs[mapNum][i].open;
						ds.mapObjectCode[mapNum][i] = gPanel.objs[mapNum][i].code;
						ds.mapObjectDecode[mapNum][i] = gPanel.objs[mapNum][i].decode;
					}
				}
			}
			
			oos.writeObject(ds);
 		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public void load() {
		try {
			
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("save.dat")));
			
			DataStorage ds = (DataStorage) ois.readObject();
			
			gPanel.currentMap = ds.currentMap;
			gPanel.winBoss1 = ds.winBoss1;
			gPanel.winBoss2 = ds.winBoss2;
			
			gPanel.player.worldX = gPanel.tileManager.getDefaultXInCurrentMap(ds.currentMap);
			gPanel.player.worldY = gPanel.tileManager.getDefaultYInCurrentMap(ds.currentMap);
			gPanel.player.level = ds.level;
			gPanel.player.strength = ds.strength;
			gPanel.player.dexterity = ds.dexterity;
			gPanel.player.exp = ds.exp;
			gPanel.player.nextLevelExp = ds.nextLevelExp;
			gPanel.player.coin = ds.coin;
			
			
			// LOAD INVENTORY
			gPanel.player.inventory.clear();
			
			for (int i = 0; i < ds.itemsName.size(); i++) {
				gPanel.player.inventory.add(getObjects(ds.itemsName.get(i), 0, ds.itemsDecode.get(i)));
				gPanel.player.inventory.get(i).amount = ds.itemsAmount.get(i);
			}
			
			// PLAYER EQUIPPED
			if (ds.currentWeaponSlot != -1)  gPanel.player.currentWeapon = gPanel.player.inventory.get(ds.currentWeaponSlot);
			if (ds.currentShieldSlot != -1) gPanel.player.currentShield = gPanel.player.inventory.get(ds.currentShieldSlot);
			
			// OBJECT ON MAP
			
			for (int mapNum = 0; mapNum < gPanel.maxMap; mapNum++)
			{
				for (int i = 0; i < gPanel.objs[1].length; i++)
				{
					if (ds.mapObjectName[mapNum][i].equals("NA")) {
						gPanel.objs[mapNum][i] = null;
					}
					else {
						gPanel.objs[mapNum][i] = getObjects(ds.mapObjectName[mapNum][i], ds.mapObjectCode[mapNum][i], ds.mapObjectDecode[mapNum][i]);
						gPanel.objs[mapNum][i].worldX = ds.mapObjectWorldX[mapNum][i];
						gPanel.objs[mapNum][i].open = ds.mapObjectOpened[mapNum][i];
						if (ds.mapObjectLootName[mapNum][i] != null) {
							gPanel.objs[mapNum][i].loot = getObjects(ds.mapObjectLootName[mapNum][i], 0, 0);
						}
						gPanel.objs[mapNum][i].worldY = ds.mapObjectWorldY[mapNum][i];
						
					}
				}
				
			}
			gPanel.player.getAttack();
			gPanel.player.getDefense();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
