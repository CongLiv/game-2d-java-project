package entity;



import java.awt.Rectangle;

import main.GamePanel;
import object.*;
import weapon.*;

public class NPC_Merchant extends Entity{
	
	public NPC_Merchant(GamePanel gPanel) {
		// TODO Auto-generated constructor stub
		
		super(gPanel);
		name = "Merchant";
		speed = 1;
		entityType = npcType;
		
		getImage(); 
		setDialogue();
		setItems();
	}
	
	public void getImage()
	{
		idle0 = setupImage("/npc/merchant_idle_f0");
		idle1 = setupImage("/npc/merchant_idle_f1");
		idle0L = setupImage("/npc/merchant_idle_f0_L");
		idle1L = setupImage("/npc/merchant_idle_f1_L");
		
	}
	
	public void setDialogue() {
		dialogues[0] = "Everything you need is here!";
	}
	
	
	public void setItems() {
		inventory.add(new OBJ_PotionRed(gPanel));
		inventory.add(new OBJ_PotionRed(gPanel));
		inventory.add(new OBJ_PotionRed(gPanel));
		inventory.add(new OBJ_PotionRed(gPanel));
		inventory.add(new OBJ_PotionYellow(gPanel));
		inventory.add(new OBJ_PotionYellow(gPanel));
		inventory.add(new OBJ_PotionRed(gPanel));
		inventory.add(new WEAP_Shield1(gPanel));
		inventory.add(new WEAP_Sword1(gPanel));
		inventory.add(new OBJ_PotionGreen(gPanel));
		inventory.add(new WEAP_Sword2(gPanel) );
		inventory.add(new OBJ_PotionGreen(gPanel));
		inventory.add(new OBJ_PotionGreen(gPanel));
		inventory.add(new OBJ_PotionGreen(gPanel));
		inventory.add(new OBJ_PotionYellow(gPanel));
	}
	
	@Override
	public void speak() {
		// TODO Auto-generated method stub
		gPanel.gameState = gPanel.tradeState;
		gPanel.ui.npc = this; 	
		super.speak();
		
		
	}
}
