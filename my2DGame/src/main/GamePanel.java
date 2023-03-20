package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JPanel;

import ai.PathFinder;
import data.SaveLoad;
import entity.Entity;
import entity.Player;
import event.EventHandler;
import sound.Sound;
import tile.TileManager;
import ui.UI;

public class GamePanel extends JPanel implements Runnable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// SCREEN SETTING
	final int originalTileSize = 16;
	final int scale = 3;

	public final int tileSize = originalTileSize * scale;
	public final int maxScreenCol = 20;
	public final int maxScreenRow = 12;
	public final int screenWidth = tileSize * maxScreenCol;
	public final int screenHeight = tileSize * maxScreenRow;
	public boolean fullscreenOn;
	// FPS
	final int FPS = 60;

	// WORLD SETTING
	
	public final int maxWorldCol = 150;
	public final int maxWorldRow = 150;
	public final int worldWidth = maxScreenCol * tileSize;
	public final int worldHeight = maxScreenRow * tileSize;
	public final int maxMap = 15;
	public int currentMap = 0;
	
	// FOR FULL SCREEN
	int screenWidth2 = screenWidth;
	int screenHeight2 = screenHeight;
	BufferedImage tempScreen;
	Graphics2D g2;
	
	// SYSTEM
	public KeyHandler keyHandler = new KeyHandler(this);
	public EventHandler eHandler = new EventHandler(this);
	public TileManager tileManager = new TileManager(this);
	public Sound music = new Sound();
	public Sound se = new Sound();
	public UI ui = new UI(this);
	public CollisionChecker cChecker = new CollisionChecker(this);
	public AssetSetter aSetter = new AssetSetter(this);
	Thread gameThread;
	public Config config = new Config(this);
	public PathFinder pFinder = new PathFinder(this);
	public SaveLoad saveLoad = new SaveLoad(this);
	
	// PLAYER AND OBJECT AND NPC
	public Player player = new Player(this, keyHandler);
	public Entity objs[][] = new Entity[maxMap][40];

	public Entity npc[][] = new Entity[maxMap][20];
	public Entity monster[][] = new Entity[maxMap][80];
	ArrayList<Entity> entityList = new ArrayList<>();
	public ArrayList<Entity> projectileList = new ArrayList<>();
	
	public int winBoss1 = 0;
	public int winBoss2 = 0;
	
	// GAME STATEM
	public int gameState;
	public final int titleState = 0; 
	public final int gamePause = 1;
	public final int gamePlaying = 2;
	public final int dialogueState = 3;
	public final int characterState = 4;
	public final int optionState = 5;
	public final int gameOverState = 6;
	public final int transitionState = 7;
	public final int tradeState = 8;
	
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.white);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyHandler);
		this.setFocusable(true);
	}

	public void setupGame() {
		aSetter.setObject();
		aSetter.setNPC();
		aSetter.setMonster();
		//playMusic(0);
		gameState = titleState;
		
		tempScreen = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_ARGB);
		g2 = (Graphics2D)tempScreen.getGraphics();
		
		if (fullscreenOn == true) {
			setFullScreen();
		}
	
	}
	
	public void setFullScreen() {
		
		// GET YOUR SCREEN
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice gd = ge.getDefaultScreenDevice();
		gd.setFullScreenWindow(Main.windowFrame);
		
		// GET FULL SCREEN WIDTH AND HEIGHT
		screenHeight2 = Main.windowFrame.getHeight();
		screenWidth2 = Main.windowFrame.getWidth();
		
	}
	
	
	public void resetGame(boolean restart) {
		 player.restoreStatus();
		 player.setDefaultPositions();
		 aSetter.setNPC();
	     aSetter.setMonster();
	    
	     
	     
	     if (restart == true) {
	    	 player.setDefaultValues();
		     aSetter.setObject();
		     stopMusic();
	     }
	     
	}
	
	
	public void startGameThread() {
		gameThread = new Thread(this);

		gameThread.start();
		
		//gameState = gamePlaying;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		double drawInterval = 1000000000 / FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		
		while (gameThread != null) {
			currentTime = System.nanoTime();
			delta += (currentTime - lastTime) / drawInterval;
			lastTime = currentTime;
			
			if (delta > 1) {
				update();
//				repaint();
				drawToTempScreen();
				drawToScreen();
				delta--;
			}
			
		}

	}

	public void update() {
		
		if (gameState == gamePlaying) 
		{
			player.update();
			
			
			// ANIMATION OBJ
			for (int i = 0; i < objs[1].length; i++)
			{
				if (objs[currentMap][i] != null && objs[currentMap][i].haveAnimation == true)
				{
					objs[currentMap][i].update();
				}
			}
			
			// NPC
			for (int i = 0; i < npc[1].length; i++)
			{
				if (npc[currentMap][i] != null)
				{
					npc[currentMap][i].update();
				}
			}
			
			
			// MONSTER
			for (int i = 0; i < monster[1].length; i++)
			{
				if (monster[currentMap][i] != null)
				{
					if (monster[currentMap][i].alive == true && monster[currentMap][i].dying == false)  monster[currentMap][i].update();
					if (monster[currentMap][i].alive == false) {
						monster[currentMap][i].checkDrop();
						monster[currentMap][i] = null;
					}
				}
			}
			
			// PROJECTILE
			for (int i = 0; i < projectileList.size(); i++)
			{
				if (projectileList.get(i) != null)
				{
					if (projectileList.get(i).alive == true) projectileList.get(i).update();
					if (projectileList.get(i).alive == false) projectileList.remove(i);
				}
			}
			
			
		}
		if (gameState == gamePause) {
			
		}
	}

	public void drawToTempScreen() {
		long drawStart = System.nanoTime();
		
		if (gameState == titleState) {
			ui.draw(g2);
		}
		
		else {
			
			// DRAW TILE
			tileManager.draw(g2);
			
			// DRAW TRAP
			for (int i = 0; i < monster[1].length; i++) 
			{
				if (monster[currentMap][i] != null && monster[currentMap][i].name == "Spike") monster[currentMap][i].draw(g2);
			}
						
			// DRAW ALL ENTITY (OBJ + NPC + PLAYER + FIREBALL)
			
			entityList.add(player);
			for (int i = 0; i < npc[1].length; i++) 
			{
				if (npc[currentMap][i] != null) entityList.add(npc[currentMap][i]);
			}
			
			for (int i = 0; i < objs[1].length; i++) 
			{
				if (objs[currentMap][i] != null) entityList.add(objs[currentMap][i]);
			}
			
			
			for (int i = 0; i < monster[1].length; i++) 
			{
				if (monster[currentMap][i] != null && monster[currentMap][i].name != "Spike") entityList.add(monster[currentMap][i]);
			}
			
			
			
			for (int i = 0; i < projectileList.size(); i++) 
			{
				if (projectileList.get(i) != null) entityList.add(projectileList.get(i));
			}
				
			Collections.sort(entityList, new Comparator<Entity>() {

				@Override
				public int compare(Entity o1, Entity o2) {
					// TODO Auto-generated method stub
					int cmp = Integer.compare(o1.worldY, o2.worldY);
					return cmp;
				}
				
			}
			);
			
			for (Entity x : entityList)
			{
				x.draw(g2);
			}
			
			// REMOVE ALL ENTITY FROM LIST
			entityList.clear();
			
			// UI
			ui.draw(g2);
			
			
			// DEBUG
			if (keyHandler.showDebugText == true) { 
				long drawEnd = System.nanoTime();
				long passed = drawEnd - drawStart;
				
				g2.setFont(new Font("Arial", Font.PLAIN, 20));
				g2.setColor(Color.white);
				int x = 10, y = 400;
				int lineHeight = 20;
				g2.drawString("WorldX: " + player.worldX, x, y);
				y += lineHeight;
				g2.drawString("WorldY: " + player.worldY, x, y);
				y += lineHeight;
				g2.drawString("Col: " + (player.worldX + player.solidArea.x) / tileSize, x, y);
				y += lineHeight;
				g2.drawString("Row: " + (player.worldY + player.solidArea.y) / tileSize, x, y);
				y += lineHeight;
				g2.drawString("Draw time: " + passed, x, y);
				y += lineHeight;
			}
		}
			
	}
	
	public void drawToScreen() {
		Graphics g = getGraphics();
		g.drawImage(tempScreen, 0, 0, screenWidth2, screenHeight2, null);
		g.dispose();
	}
	
//	public void paintComponent(Graphics g) {
//		super.paintComponent(g);
//
//		
//		Graphics2D g2 = (Graphics2D) g;
//		long drawStart = System.nanoTime();
//		
//		if (gameState == titleState) {
//			ui.draw(g2);
//		}
//		
//		else {
//			// DRAW TILE
//			tileManager.draw(g2);
//			
//			// DRAW ALL ENTITY (OBJ + NPC + PLAYER + FIREBALL)
//			entityList.add(player);
//			
//			for (int i = 0; i < npc.length; i++) 
//			{
//				if (npc[i] != null) entityList.add(npc[i]);
//			}
//			
//			for (int i = 0; i < objs.length; i++) 
//			{
//				if (objs[i] != null) entityList.add(objs[i]);
//			}
//			
//			for (int i = 0; i < monster.length; i++) 
//			{
//				if (monster[i] != null) entityList.add(monster[i]);
//			}
//			
//			for (int i = 0; i < projectileList.size(); i++) 
//			{
//				if (projectileList.get(i) != null) entityList.add(projectileList.get(i));
//			}
//				
//			Collections.sort(entityList, new Comparator<Entity>() {
//
//				@Override
//				public int compare(Entity o1, Entity o2) {
//					// TODO Auto-generated method stub
//					int cmp = Integer.compare(o1.worldY, o2.worldY);
//					return cmp;
//				}
//				
//			}
//			);
//			
//			for (Entity x : entityList)
//			{
//				x.draw(g2);
//			}
//			
//			// REMOVE ALL ENTITY FROM LIST
//			entityList.clear();
//			
//			// UI
//			ui.draw(g2);
//			
//			
//			// DEBUG
//			if (keyHandler.showDebugText == true) { 
//				long drawEnd = System.nanoTime();
//				long passed = drawEnd - drawStart;
//				
//				g2.setFont(new Font("Arial", Font.PLAIN, 20));
//				g2.setColor(Color.white);
//				int x = 10, y = 400;
//				int lineHeight = 20;
//				g2.drawString("WorldX: " + player.worldX, x, y);
//				y += lineHeight;
//				g2.drawString("WorldY: " + player.worldY, x, y);
//				y += lineHeight;
//				g2.drawString("Col: " + (player.worldX + player.solidArea.x) / tileSize, x, y);
//				y += lineHeight;
//				g2.drawString("Row: " + (player.worldY + player.solidArea.y) / tileSize, x, y);
//				y += lineHeight;
//				g2.drawString("Draw time: " + passed, x, y);
//				y += lineHeight;
//			}
//			
//			
//			g2.dispose();
//		
//		}
//	}
//	
	public void playMusic(int i) {
		music.setFile(i);
		music.play();
		music.loop();
	}
	
	public void stopMusic(){
		music.stop();
	}
	
	public void playSoundEffect(int i)
	{
		se.setFile(i);
		se.play();
	}
}
