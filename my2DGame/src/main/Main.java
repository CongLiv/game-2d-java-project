package main;

import javax.swing.JFrame;

public class Main {

	public static JFrame windowFrame;
	public static void main(String[] args) {
		
		windowFrame = new JFrame();
		windowFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		windowFrame.setResizable(false);
		windowFrame.setTitle("Maze Dungeon");
		//windowFrame.setUndecorated(true);
		
		GamePanel gPanel = new GamePanel();
		windowFrame.add(gPanel);
		
		if (gPanel.fullscreenOn = true) {
			gPanel.fullscreenOn = false;
		}
		gPanel.config.loadConfig();
		if (gPanel.fullscreenOn == true) {
			windowFrame.setUndecorated(true);
		}
		
		windowFrame.pack();
		
		windowFrame.setLocationRelativeTo(null);
		windowFrame.setVisible(true);
		
		gPanel.setupGame();
		gPanel.startGameThread();		// start loop
 
	}

}
