package entity;

import java.awt.Rectangle;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import main.GamePanel;

public class NPC_Demorden extends Entity{
	
	public NPC_Demorden(GamePanel gPanel) {
		// TODO Auto-generated constructor stub
		
		super(gPanel);
		name = "Demorden";
		direction = "idle";
		speed = 1;
		
		entityType = npcType;
		solidArea = new Rectangle(8, 16, 32, 32);
		solidAreaDefaultX = 8;
		solidAreaDefaultY = 16;
		
		getImage(); 
		setDialogue();
	}
	
	public void getImage()
	{
		idle0 = setupImage("/npc/demorden_idle_f0");
		idle1 = setupImage("/npc/demorden_idle_f2");
		run0 = setupImage("/npc/demorden_run_f0");
		run1 = setupImage("/npc/demorden_run_f1");
		run2 = setupImage("/npc/demorden_run_f2");
		run3 = setupImage("/npc/demorden_run_f3");
		run4 = setupImage("/npc/demorden_run_f0");
		run5 = setupImage("/npc/demorden_run_f1");
		
		idle0L = setupImage("/npc/demorden_idle_f0_L");
		idle1L = setupImage("/npc/demorden_idle_f2_L");
		run0L = setupImage("/npc/demorden_run_f0_L");
		run1L = setupImage("/npc/demorden_run_f1_L");
		run2L = setupImage("/npc/demorden_run_f2_L");
		run3L = setupImage("/npc/demorden_run_f3_L");
		run4L = setupImage("/npc/demorden_run_f0_L");
		run5L = setupImage("/npc/demorden_run_f1_L");
		
		
	}
	
	public void setDialogue() {
		dialogues[0] = "Hello lad!";
		dialogues[1] = "You are a brave knight!";
		dialogues[2] = "Hope you will have an amazing advanture in this \nmaze and kill the boss here!";
	
	}
	
	@Override
	public void setAction() {
		
			direction = "idle";
		
		
	}
	
	@Override
	public void speak() {
		// TODO Auto-generated method stub
		super.speak();
		onPath = true;
	}
}
