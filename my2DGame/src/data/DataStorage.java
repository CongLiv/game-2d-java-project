package data;

import java.io.Serializable;
import java.util.ArrayList;

public class DataStorage implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	int currentMap;
	int winBoss1;
	int winBoss2;
	
	int level;
	int strength;
	int dexterity;
	int exp;
	int nextLevelExp;
	int coin;
	
	// ITEMS
	ArrayList<String> itemsName = new ArrayList<>();
	ArrayList<Integer> itemsAmount = new ArrayList<>(); 
	ArrayList<Integer> itemsDecode = new ArrayList<>(); 
	
	int currentWeaponSlot;
	int currentShieldSlot;
	
	// OBJECTS ON MAP
	String mapObjectName[][];
	int mapObjectWorldX[][];
	int mapObjectWorldY[][];
	String mapObjectLootName[][];
	boolean mapObjectOpened[][];
	int mapObjectCode[][]; 
	int mapObjectDecode[][];
	
	
	
}
