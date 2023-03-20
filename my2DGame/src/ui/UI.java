package ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import java.util.ArrayList;

import javax.imageio.ImageIO;

import entity.Entity;
import main.GamePanel;
import object.OBJ_Coins;
import object.OBJ_PotionGreen;
import object.OBJ_PotionYellow;




public class UI {
  
	GamePanel gPanel;
	Graphics2D g2;
	Font maruMonica;
	
	ArrayList<String> message = new ArrayList<>();
	ArrayList<Integer> messageCounter = new ArrayList<>();
	
	
	
	public boolean finishedGame = false;
	public String currentDialogue = "";
	public int commandNum = 0;
	
	public int playerSlotCol = 0;
	public int playerSlotRow = 0;
	
	public int npcSlotCol = 0;
	public int npcSlotRow = 0;
	
	public int subState = 0;
	
	public int counter = 0;
	
	
	public Entity npc;

	BufferedImage health_ui, coin;
	BufferedImage background, logo;
	BufferedImage greenPotion, yellowPotion;
	
	public UI(GamePanel gPanel) {
	
		this.gPanel = gPanel;
		
		try {
			InputStream iStream = getClass().getResourceAsStream("/font/x12y16pxMaruMonica.ttf");
			maruMonica = Font.createFont(Font.TRUETYPE_FONT, iStream);
			
			background = ImageIO.read(getClass().getResourceAsStream("/background/title_background.png"));
			logo = ImageIO.read(getClass().getResourceAsStream("/background/logo.png"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		
		// CREATE HUD OBJECT
		Entity health = new UI_Health(gPanel);
		health_ui = health.image;
		
		Entity Coin = new OBJ_Coins(gPanel);
		this.coin = Coin.idle0;
		
		Entity yPotion = new OBJ_PotionYellow(gPanel);
		Entity gPotion = new OBJ_PotionGreen(gPanel);
		greenPotion = gPotion.idle0;
		yellowPotion = yPotion.idle0;
		
	}
	
	public void addMessage(String text)
	{
		message.add(text);
		messageCounter.add(0);
	}
	
	public void draw(Graphics2D g2) {
		
		this.g2 = g2;
		g2.setFont(maruMonica);
		g2.setColor(Color.white); 
		
		
		// TITLE + CREDIT
		if (gPanel.gameState == gPanel.titleState)
		{
			drawTitleScreen();
		}
		
		// PLAYING STATE
		if (gPanel.gameState == gPanel.gamePlaying) {
			
			drawPlayerLife();
			drawAttackCooldown();
			drawMessage();
			if (gPanel.player.yellowPotionCounter > 0 || gPanel.player.greenPotionCounter > 0) {
				drawPotionBuff();
			}
			

		}
		
		// PAUSE STATE
		if (gPanel.gameState == gPanel.gamePause)
		{
			drawPauseScreen();
		}
		
		// DIALOGUE STATE
		if (gPanel.gameState == gPanel.dialogueState) {
			drawDialogueScreen();
		}
		
		// CHARACTER STATE
		if (gPanel.gameState == gPanel.characterState) {
			drawCharacterScreen();
			drawInventory(gPanel.player, true);
		}
		
		
		// OPTION STATE
		if (gPanel.gameState == gPanel.optionState) {
			drawOptionState();
		}
		
		// GAME OVER STATE
		if (gPanel.gameState == gPanel.gameOverState) {
			drawGameOverScreen();
		}
		
		// GAME TRANSITION STATE
		if (gPanel.gameState == gPanel.transitionState) {
			drawTransition();
		}
		
		// GAME TRADE STATE
		if (gPanel.gameState == gPanel.tradeState) {
			drawTradeScreen();
		}
	}
	
	public void drawBossLife(Entity boss) {
	
			double oneScale = (double) gPanel.tileSize * 8 / boss.maxLife;
			double hpBar = oneScale * boss.life;
			
			int screenX = gPanel.screenWidth / 2 - gPanel.tileSize * 4;
			int screenY = 48 * 10;
			
			g2.setColor(new Color(35, 35, 35));
			g2.fillRect(screenX - 1 , screenY - 1, gPanel.tileSize * 8 + 2, 22);
			
			g2.setColor(new Color(255, 0, 25));
			g2.fillRect(screenX, screenY, (int)hpBar, 20);
			
			g2.setFont(g2.getFont().deriveFont(Font.BOLD, 24f));
			g2.setColor(Color.white);
			g2.drawString(boss.name, screenX + 4, screenY - 10);
			
	}
	
	
	public void drawTradeScreen() {
	
		
		switch (subState) {
		case 0: trade_select(); break;
		case 1: trade_buy(); break;
		case 2: trade_sell(); break;
		}
		
		gPanel.keyHandler.enterPressed = false;
	}
	
	public void trade_select() {
		drawDialogueScreen();

        // DRAW WINDOW
        int x = gPanel.tileSize * 15;
        int y = gPanel.tileSize * 4;
        int width = gPanel.tileSize * 3;
        int height = (int) (gPanel.tileSize * 3.5);
        drawSubWindow(x, y, height, width);

        // DRAW TEXT
        x += gPanel.tileSize;
        y += gPanel.tileSize;
        g2.drawString("Buy", x, y);
        if (commandNum == 0) {
            g2.drawString(">", x - 24, y);
            if (gPanel.keyHandler.enterPressed) {
                subState = 1;
            }
        }
        y += gPanel.tileSize;
        g2.drawString("Sell", x, y);
        if (commandNum == 1) {
            g2.drawString(">", x - 24, y);
            if (gPanel.keyHandler.enterPressed) {
                subState = 2;
            }
        }
        y += gPanel.tileSize;
        g2.drawString("Leave", x, y);
        if (commandNum == 2) {
            g2.drawString(">", x - 24, y);
            if (gPanel.keyHandler.enterPressed) {
                commandNum = 0;
                subState = 0;
                gPanel.gameState = gPanel.dialogueState;
                currentDialogue = "Come again, hehe!";
            }
        }
	}
	
	public void trade_sell() {
		
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 18f));
		// DRAW PLAYER INVENTORY
        drawInventory(gPanel.player, true);
        
        // DRAW HINT WINDOW
        int x = gPanel.tileSize * 3;
        int y = gPanel.tileSize * 11;
        int width = gPanel.tileSize * 6;
        int height = gPanel.tileSize * 1;
        drawSubWindow(x, y, height, width);
        g2.drawString("[ESC] Back", x + 24, y + 30);

        // DRAW PLAYER COIN WINDOW
        x = gPanel.tileSize * 11;
        drawSubWindow(x, y, height, width);
        g2.drawString("Your Coin: " + gPanel.player.coin, x + 24, y + 30);

        // DRAW PRICE WINDOW
        int itemIndex = getItemIndexOnSlot(playerSlotCol, playerSlotRow);

        if (itemIndex < gPanel.player.inventory.size()) {
            x = (int) (gPanel.tileSize * 14.5);
            y = (int) (gPanel.tileSize * 6.5);
            width = (int) (gPanel.tileSize * 2.5);
            height = gPanel.tileSize;
            drawSubWindow(x, y, height, width);
            g2.drawImage(coin, x + 10, y + 8, 32, 32, null);

            int price = gPanel.player.inventory.get(itemIndex).price / 2;
            String text = String.valueOf(price);
            x = getXforAlignToRightText(text, gPanel.tileSize * 16);
            g2.drawString(text, x, y + 34);

            // SELL
            if (gPanel.keyHandler.enterPressed) {
                if (gPanel.player.inventory.get(itemIndex) == gPanel.player.currentWeapon
                        || gPanel.player.inventory.get(itemIndex) == gPanel.player.currentShield) {
                    commandNum = 0;
                    subState = 0;
                    gPanel.gameState = gPanel.dialogueState;
                    currentDialogue = "You cannot sell an equipped item!";
                    drawDialogueScreen();
                } else {
                    if (gPanel.player.inventory.get(itemIndex).amount > 1) {
                    	gPanel.player.inventory.get(itemIndex).amount--;
                    }
                    
                    else gPanel.player.inventory.remove(itemIndex);
                    
                    gPanel.player.coin += price;
                }
            }
        }
	}
	
	public void trade_buy() {
		
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 18f));
		// DRAW PLAYER INVENTORY
        drawInventory(gPanel.player, false);

        // DRAW NPC INVENTORY
        drawInventory(npc, true);

        
        // DRAW HINT WINDOW
        int x = gPanel.tileSize * 3;
        int y = gPanel.tileSize * 11;
        int width = gPanel.tileSize * 6;
        int height = gPanel.tileSize * 1;
        drawSubWindow(x, y, height, width);
        g2.drawString("[ESC] Back", x + 24, y + 30);

        // DRAW PLAYER COIN WINDOW
        x = gPanel.tileSize * 11;
        drawSubWindow(x, y, height, width);
        g2.drawString("Your Coin: " + gPanel.player.coin, x + 24, y + 30);

        // DRAW PRICE WINDOW
        int itemIndex = getItemIndexOnSlot(npcSlotCol, npcSlotRow);

        if (itemIndex < npc.inventory.size()) {
            x = (int) (gPanel.tileSize * 6.5);
            y = (int) (gPanel.tileSize * 6.5);
            width = (int) (gPanel.tileSize * 2.5);
            height = gPanel.tileSize;
            drawSubWindow(x, y, height, width);
            g2.drawImage(coin, x + 10, y + 8, 32, 32, null);

            int price = npc.inventory.get(itemIndex).price;
            String text = String.valueOf(price);
            x = getXforAlignToRightText(text, gPanel.tileSize * 8);
            g2.drawString(text, x, y + 34);

            // BUY
            if (gPanel.keyHandler.enterPressed == true) {
                if (npc.inventory.get(itemIndex).price > gPanel.player.coin) {
                    subState = 0;
                    gPanel.gameState = gPanel.dialogueState;
                    currentDialogue = "You need more coin to buy that!";
                    drawDialogueScreen();
                }
                else {
                	if (gPanel.player.canObtainItem(npc.inventory.get(itemIndex)) == true) {
                		gPanel.player.coin -= npc.inventory.get(itemIndex).price;
                		npc.inventory.remove(itemIndex);
                		
                	}
                	else {
                		subState = 0;
                		gPanel.gameState = gPanel.dialogueState;
                		currentDialogue = "You cannot carry anymore!";
                		drawDialogueScreen();
                	}
                }
//                else if (gPanel.player.inventory.size() == gPanel.player.maxInventorySize) {
//                    subState = 0;
//                    gPanel.gameState = gPanel.dialogueState;
//                    currentDialogue = "You cannot carry anymore!";
//                    drawDialogueScreen();
//                } else {
//                    gPanel.player.coin -= npc.inventory.get(itemIndex).price;
//                    gPanel.player.inventory.add(npc.inventory.get(itemIndex));
//                    npc.inventory.remove(itemIndex);
//                }
            }
        }
	}
	public void drawTransition() {
		counter++;
		if (counter < 50) {
			g2.setColor(new Color(0, 0, 0, counter * 5));
	        g2.fillRect(0, 0, gPanel.screenWidth, gPanel.screenHeight);
		}
		else if (counter < 250) {
			g2.setColor(new Color(0, 0, 0));
	        g2.fillRect(0, 0, gPanel.screenWidth, gPanel.screenHeight);
	        String text = "LOADING...";
	        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 50f));
	        int x = getXCenteredText(text);
	        int y = gPanel.screenHeight / 2;
	        g2.setColor(Color.white);
	        g2.drawString(text, x, y);
	        
		}
        if (counter == 50) {
            gPanel.currentMap = gPanel.eHandler.tmpMap;
            gPanel.player.worldX = gPanel.tileSize * gPanel.eHandler.tmpCol;
            gPanel.player.worldY = gPanel.tileSize * gPanel.eHandler.tmpRow;
            gPanel.eHandler.lastEventX = gPanel.player.worldX;
            gPanel.eHandler.lastEventY = gPanel.player.worldY;
            gPanel.aSetter.setMonster();
            gPanel.saveLoad.save();
        }
        
        if (counter == 250) {
        	counter = 0;
            gPanel.gameState = gPanel.gamePlaying;
        }
        
	}
	


	public void drawPotionBuff() {
		
		int x = gPanel.screenWidth - gPanel.tileSize;
		int y = gPanel.tileSize / 3;
		;
		g2.setColor(Color.white);
		if (gPanel.player.yellowPotionCounter > 0) {
			g2.drawImage(yellowPotion, x, y, gPanel.tileSize, gPanel.tileSize, null);
			String yellowTimer =  Integer.toString(gPanel.player.yellowPotionCounter / 60);
			g2.drawString(yellowTimer, x - gPanel.tileSize / 2, y + gPanel.tileSize / 2 + 5);
			y += gPanel.tileSize;
		}
		
		if (gPanel.player.greenPotionCounter > 0) {
			g2.drawImage(greenPotion, x, y, gPanel.tileSize, gPanel.tileSize, null);
			String greenTimer =  Integer.toString(gPanel.player.greenPotionCounter / 60);
			g2.drawString(greenTimer, x - gPanel.tileSize / 2, y + gPanel.tileSize / 2 + 5);
		}
		
	}
	
	
	
	
	public void drawGameOverScreen() {
		g2.setColor(new Color(0, 0, 0, 150));
		g2.fillRect(0, 0, gPanel.screenWidth, gPanel.screenHeight);
		
		String text;
		int x, y;
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 110f));
		text = "GAME OVER";
		
		// SHADOW
		g2.setColor(Color.BLACK);
		x = getXCenteredText(text);
		y = gPanel.tileSize * 4;
		g2.drawString(text, x, y);
		
		// Main
		g2.setColor(Color.white);
		g2.drawString(text, x - 4, y - 4);
		
		
		 // RETRY
        g2.setFont(g2.getFont().deriveFont(50f));
        text = "Retry";
        x = getXCenteredText(text);
        y += gPanel.tileSize * 4;
        g2.drawString(text, x, y);
        if (commandNum == 0) {
            g2.drawString(">", x - 40, y);
            if (gPanel.keyHandler.enterPressed == true) {
                commandNum = 0;

            }
        }

        // BACK TO TITLE
        text = "Quit";
        x = getXCenteredText(text);
        y += 55;
        g2.drawString(text, x, y);
        if (commandNum == 1) {
            g2.drawString(">", x - 40, y);
            if (gPanel.keyHandler.enterPressed == true) {
                commandNum = 0;

            }
        }

        gPanel.keyHandler.enterPressed = false;
		
		
	}
	
	public void drawOptionState() {
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(32f));
		
		// SUB WINDOW
		
		int frameX = gPanel.tileSize * 6;
		int frameY = gPanel.tileSize;
		int frameWidth = gPanel.tileSize * 8;
		int frameLength = gPanel.tileSize * 10;
		drawSubWindow(frameX, frameY, frameLength, frameWidth);
		
		switch (subState) {
			
			case 0: options_top(frameX, frameY); break;
			case 1: options_fullScreenNotification(frameX, frameY); break;
			case 2: options_control(frameX, frameY); break;
			case 3: options_endGameConfirmation(frameX, frameY); break;
			
		
		}
		gPanel.keyHandler.enterPressed = false;
	}
	
	public void options_top(int frameX, int frameY) {
		
		int textX;
		int textY;
		
		// TITLE
		String text = "Options";
		textX = getXCenteredText(text);
		textY = frameY + gPanel.tileSize;
		
		g2.drawString(text, textX, textY);
		
		// FULL SCREEN ON/OFF
		
		textX = frameX + gPanel.tileSize;
		textY += gPanel.tileSize * 2;
		g2.drawString("Full screen" , textX, textY);
		if (commandNum == 0) {
			g2.drawString(">", textX - 25, textY);
			if (gPanel.keyHandler.enterPressed == true) {
				if (gPanel.fullscreenOn == false) {
					gPanel.fullscreenOn = true;
				}
				else if (gPanel.fullscreenOn == true) {
					gPanel.fullscreenOn = false;
				}
				subState = 1;
			
			}
		}
		
		// MUSIC
		textY += gPanel.tileSize;
		g2.drawString("Music", textX, textY);
		if (commandNum == 1) {
			g2.drawString(">", textX - 25, textY);
		}
		// SE
		textY += gPanel.tileSize;
		g2.drawString("Sound Effect", textX, textY);
		if (commandNum == 2) {
			g2.drawString(">", textX - 25, textY);
		}
		// CONTROL
		textY += gPanel.tileSize;
		g2.drawString("Control", textX, textY);
		if (commandNum == 3) {
			g2.drawString(">", textX - 25, textY);
			if (gPanel.keyHandler.enterPressed == true) {
				subState = 2;
				commandNum = 0;
			}
		}
		// END GAME
		textY += gPanel.tileSize;
		g2.drawString("End game", textX, textY);
		if (commandNum == 4) {
			g2.drawString(">", textX - 25, textY);
			
			if (gPanel.keyHandler.enterPressed == true) {
				subState = 3; 
				commandNum = 0;
				gPanel.stopMusic();
			}
		}
		// BACK
		textY += gPanel.tileSize * 2;
		g2.drawString("Back", textX, textY);
		if (commandNum == 5) {
			g2.drawString(">", textX - 25, textY);
			if (gPanel.keyHandler.enterPressed) {
				gPanel.gameState = gPanel.gamePlaying;
				commandNum = 0;
			}
		}
		
		
		// FULL SCREEN CHECK BOX
		textX = frameX + gPanel.tileSize * 5;
		textY = frameY + 120;
		g2.setStroke(new BasicStroke(3));
		g2.drawRect(textX, textY, 24, 24);
		if (gPanel.fullscreenOn == true) {
            g2.fillRect(textX, textY, 24, 24);
        }
		
		// MUSIC VOLUME
		textY += gPanel.tileSize;
        g2.drawRect(textX, textY, 120, 24); 
        int volumeWidth = 24 * gPanel.music.volumeScale;
        g2.fillRect(textX, textY, volumeWidth, 24);
        // SE VOLUME
     	textY += gPanel.tileSize;
        g2.drawRect(textX, textY, 120, 24); 
        volumeWidth = 24 * gPanel.se.volumeScale;
        g2.fillRect(textX, textY, volumeWidth, 24);
        
        
        gPanel.config.saveConfig();
        
	}
	
	public void options_fullScreenNotification(int frameX, int frameY) {
		int textX = frameX + gPanel.tileSize;
		int textY = frameY + gPanel.tileSize * 3;
		
		currentDialogue = "The change will take \neffect after restart game.";
		for (String line : currentDialogue.split("\n")) {
			g2.drawString(line, textX, textY);
			textY += 40;
		}
		
		// BACK
		textY = frameY + gPanel.tileSize * 9;
        g2.drawString("Back", textX, textY);
        g2.drawString(">", textX - 25, textY);
        if (gPanel.keyHandler.enterPressed == true) {
            subState = 0;
            commandNum = 0;
        }
        
        gPanel.config.saveConfig();
	}
	
	public void options_control(int frameX, int frameY) {
		int textX;
        int textY;

        // TITLE
        String text = "Controls";
        textX = getXCenteredText(text);
        textY = frameY + gPanel.tileSize;
        g2.drawString(text, textX, textY);
        
        g2.setFont(g2.getFont().deriveFont(28f));
        textX = frameX + gPanel.tileSize;
        textY += 36;
        g2.drawString("Move", textX, textY);
        g2.drawString("←↑→↓", textX + gPanel.tileSize * 5, textY);
        textY += 36;
        g2.drawString("Confirm/Interact/", textX, textY);
        g2.drawString("ENTER", textX + gPanel.tileSize * 5, textY);
        textY += 24;
        g2.drawString("Use/Equip/Unequip", textX, textY);
        textY += 36;
        g2.drawString("Attack", textX, textY);
        g2.drawString("SPACE", textX + gPanel.tileSize * 5, textY);
        textY += 36;
        g2.drawString("Use Red Potion", textX, textY);
        g2.drawString("R", textX + gPanel.tileSize * 5, textY);
        textY += 36;
        g2.drawString("Use Yellow Potion", textX, textY);
        g2.drawString("Y", textX + gPanel.tileSize * 5, textY);
        textY += 36;
        g2.drawString("Use Green Potion", textX, textY);
        g2.drawString("G", textX + gPanel.tileSize * 5, textY);
        textY += 36;
        g2.drawString("Character Screen", textX, textY);
        g2.drawString("C", textX + gPanel.tileSize * 5, textY);
        textY += 36;
        g2.drawString("Pause", textX, textY);
        g2.drawString("P", textX + gPanel.tileSize * 5, textY);

        // BACK
        textX = frameX + gPanel.tileSize;
        textY = frameY + gPanel.tileSize * 9;
        g2.drawString("Back", textX, textY);
        g2.drawString(">", textX - 25, textY);
        if (gPanel.keyHandler.enterPressed == true) {
            subState = 0;
            commandNum = 3;
        }
        
        gPanel.config.saveConfig();
	}
	
	public void drawMessage() {
		
		int messageX = gPanel.tileSize;
		int messageY = gPanel.tileSize * 4;
		
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 20f));
		
		for (int i = 0; i < message.size(); i++)
		{
			if (message.get(i) != null)
			{
				
				g2.setColor(Color.darkGray);
				g2.drawString(message.get(i),  messageX + 2, messageY);
				g2.setColor(Color.white);
				g2.drawString(message.get(i),  messageX, messageY);
				
				int counter = messageCounter.get(i) + 1;	// messageCounter++;
				messageCounter.set(i, counter);			//set the counter to the array
				messageY += 30;
				
				if (messageCounter.get(i) > 180) {
					message.remove(i);
					messageCounter.remove(i);
				}
			}
		}
	}
	
	public void options_endGameConfirmation(int frameX, int frameY) {
		int textX = frameX + gPanel.tileSize;
        int textY = frameY + gPanel.tileSize * 3;

        currentDialogue = "Quit the game and \nreturn to the title Screen?";

        for (String line : currentDialogue.split("\n")) {
            g2.drawString(line, textX, textY);
            textY += 40;
        }

        // YES
        String text = "Yes";
        textX = getXCenteredText(text);
        textY += gPanel.tileSize * 3;
        g2.drawString(text, textX, textY);
        if (commandNum == 0) {
            g2.drawString(">", textX - 25, textY);
            if (gPanel.keyHandler.enterPressed == true) {
                subState = 0;
                gPanel.gameState = gPanel.titleState;

            }
        }

        // NO
        text = "No";
        textX = getXCenteredText(text);
        textY += gPanel.tileSize;
        g2.drawString(text, textX, textY);
        if (commandNum == 1) {
            g2.drawString(">", textX - 25, textY);
            if (gPanel.keyHandler.enterPressed == true) {
                subState = 0;
                commandNum = 4;

            }
        }
        
        gPanel.config.saveConfig();
	}
	
	public void drawPlayerLife() {
		
		int x = gPanel.tileSize / 2;
		int y = gPanel.tileSize / 2;
		
		
		g2.setColor(Color.white);
		g2.fillRect(x + 20, y, 80 * 3, 14 * 3);
		g2.drawImage(health_ui, x, y, 90 * 3, 16 * 3, null);
		
		
		g2.setColor(new Color(161, 60, 60));
		g2.fillRect(x + 12, y, 50 * (gPanel.player.life + 1), 14 * 3);
		g2.drawImage(health_ui, x, y, 90 * 3, 16 * 3, null);
		
		
	}
	
	public void drawAttackCooldown() {
		
		int x = gPanel.tileSize / 2;
		int y = gPanel.tileSize * 2 - 20;
		
		double scaleOne = 245 / (double)gPanel.player.cooldownAttack;
		g2.setColor(Color.white);
		g2.fillRect(x + 10, y - 2, 245 + 4, 5 * 3 + 4);
		g2.setColor(new Color(132, 126, 135));
		
		if (gPanel.player.counterAttack == 0) {
			g2.fillRect(x + 12, y, 245, 5 * 3);
		}
		else g2.fillRect(x + 12, y, (int)(scaleOne * (gPanel.player.counterAttack)), 5 * 3);
	}
	
	
	public void drawTitleScreen() {
		
		// BACKGROUND COLOR

		g2.drawImage(background, 0, 0, gPanel.screenWidth, gPanel.screenHeight, null);
		
		if (subState == 0) {

			g2.setFont(g2.getFont().deriveFont(Font.BOLD, 80f));
			String title = "MAZE DUNGEON";
			int x = getXCenteredText(title);
			int y = gPanel.screenHeight - 9 * gPanel.tileSize;
			
			// DRAW SHADOW
			g2.setColor(Color.GRAY);
			g2.drawString(title, x + 4, y + 4);
			
			// TILE TEXT
			g2.setColor(Color.blue);
			g2.drawString(title, x, y);
			
			// BOY IN CENTER
			x = gPanel.screenWidth / 2 - (gPanel.tileSize * 2) / 2;
			y = gPanel.screenHeight / 2 - (gPanel.tileSize * 2) / 2;
			g2.drawImage(gPanel.player.run1, x, y, 72, 72, null);
			
			// MENU
			
			g2.setColor(Color.gray);
			String text = "NEW GAME";
			if (commandNum == 0) text = ">  " + text; 
			g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40f));
			x = getXCenteredText(text);
			y = gPanel.screenHeight - 4 * gPanel.tileSize;
			g2.drawString(text, x, y);
			
			text = "LOAD GAME";
			if (commandNum == 1) text = ">  " + text; 
			g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40f));
			x = getXCenteredText(text);
			y = gPanel.screenHeight - 3 * gPanel.tileSize;
			g2.drawString(text, x, y);
			
			text = "CREDIT";
			if (commandNum == 2) text = ">  " + text; 
			g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40f));
			x = getXCenteredText(text);
			y = gPanel.screenHeight - 2 * gPanel.tileSize;
			g2.drawString(text, x, y);
			
			text = "QUIT";
			if (commandNum == 3) text = ">  " + text; 
			g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40f));
			x = getXCenteredText(text);
			y = gPanel.screenHeight - 1 * gPanel.tileSize;
			g2.drawString(text, x, y);
			
			
			g2.setColor(Color.white);
			text = "NEW GAME";
			if (commandNum == 0) text = ">  " + text; 
			g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40f));
			x = getXCenteredText(text);
			y = gPanel.screenHeight - 4 * gPanel.tileSize;
			g2.drawString(text, x - 3, y - 3);
			
			
			text = "LOAD GAME";
			if (commandNum == 1) text = ">  " + text; 
			g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40f));
			x = getXCenteredText(text);
			y = gPanel.screenHeight - 3 * gPanel.tileSize;
			g2.drawString(text, x - 3, y - 3);
			
			text = "CREDIT";
			if (commandNum == 2) text = ">  " + text; 
			g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40f));
			x = getXCenteredText(text);
			y = gPanel.screenHeight - 2 * gPanel.tileSize;
			g2.drawString(text, x - 3, y - 3);
			
			text = "QUIT";
			if (commandNum == 3) text = ">  " + text; 
			g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40f));
			x = getXCenteredText(text);
			y = gPanel.screenHeight - 1 * gPanel.tileSize;
			g2.drawString(text, x - 3, y - 3);
			
		}
		
		else if (subState == 1) {
			g2.setColor(Color.white);
			g2.setFont(g2.getFont().deriveFont(32f));
			
			// SUB WINDOW
			
			int frameX = gPanel.tileSize * 6;
			int frameY = gPanel.tileSize;
			int frameWidth = gPanel.tileSize * 8;
			int frameLength = gPanel.tileSize * 10;
			drawSubWindow(frameX, frameY, frameLength, frameWidth);
			
			int x, y;
			// SHADOW
			g2.setColor(Color.gray);
			String text = "MAZE DUNGEON";
			g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40f));
			x = getXCenteredText(text);
			y = gPanel.tileSize * 3;
			g2.drawString(text, x, y);
			
			text = "-----------------";
			g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40f));
			x = getXCenteredText(text);
			y = 48 * 3 + 24;
			g2.drawString(text, x, y);
			
			g2.setColor(Color.WHITE);
			text = "MAZE DUNGEON";
			g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40f));
			x = getXCenteredText(text);
			y = gPanel.tileSize * 3;
			g2.drawString(text, x - 3, y - 3);
			
			text = "-----------------";
			g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40f));
			x = getXCenteredText(text);
			y = 48 * 3 + 24;
			g2.drawString(text, x - 3, y - 3);
			
			g2.setColor(Color.white);
			
			g2.setFont(g2.getFont().deriveFont(Font.BOLD, 20f));
			text = "Java Project made by Vu Thanh Cong";
			x = getXCenteredText(text);
			y += gPanel.tileSize * 2;
			g2.drawString(text, x, y);
			
			text = "Member of NEU Information Technology Club - NITC";
			x = getXCenteredText(text);
			y += 24;
			g2.drawString(text, x, y);
			
			g2.drawImage(logo, x + 35, y - 52, 57 * 5, 37 * 5, null);
			
			text = "Completed in 3/2023";
			x = getXCenteredText(text);
			y += 96;
			g2.drawString(text, x, y);
			
			text = "In-game graphics are provided";
			x = getXCenteredText(text);
			y += 48;
			g2.drawString(text, x, y);
			
			text = "from free game assets!";
			x = getXCenteredText(text);
			y += 24;
			g2.drawString(text, x, y);

			 
			
		}
		
		
		
	}
	
	
	public void drawPauseScreen() {
		String text = "PAUSED";
		int x = getXCenteredText(text);
		int y = gPanel.screenHeight / 2;
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 75f));
		g2.drawString(text, x - 2 * gPanel.tileSize, y);
		
	}
	
	public void drawDialogueScreen() {
		int x = gPanel.tileSize * 2;
		int y = gPanel.tileSize / 2;
		int height = gPanel.tileSize * 4;
		int width = gPanel.screenWidth - gPanel.tileSize * 4;
		drawSubWindow(x, y, height, width);
		
		x += gPanel.tileSize;
		y += gPanel.tileSize;
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 30f));
		//g2.setColor(Color.white);
		for (String line : currentDialogue.split("\n"))
		{
			g2.drawString(line, x, y);
			y += 40; 
		}
	}
	
	public void drawCharacterScreen() {
		// CREATE A FRAME
		final int frameX = gPanel.tileSize * 4;
		final int frameY = gPanel.tileSize;
		final int frameWidth = gPanel.tileSize * 5;
		final int frameHeight = gPanel.tileSize * 10;
		drawSubWindow(frameX, frameY, frameHeight, frameWidth);
		
		// TEXT
		g2.setColor(Color.white);
		g2.setFont(maruMonica);
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 20f));
		
		int textX = frameX + 20;
		int textY = frameY + gPanel.tileSize;
		final int lineHeight = 35;
		g2.drawString("STATS " , textX + gPanel.tileSize * 2 - 14, textY);
		textY += lineHeight;
		
		// NAMES
		g2.drawString("Level: " , textX, textY);
		textY += lineHeight;
		
		g2.drawString("Strength: ", textX, textY);
		textY += lineHeight;
		
		g2.drawString("Dexterity: ", textX, textY);
		textY += lineHeight;
		
		g2.drawString("Attack: ", textX, textY);
		textY += lineHeight;
		
		g2.drawString("Defense: ", textX, textY);
		textY += lineHeight;
		
		g2.drawString("EXP: ", textX, textY);
		textY += lineHeight;
		
		g2.drawString("Next level: ", textX, textY);
		textY += lineHeight;
		
		g2.drawString("Coin: ", textX, textY);
		textY += lineHeight;
				
		g2.drawString("Weapon: ", textX, textY);
		textY += lineHeight;
		
		g2.drawString("Shield: ", textX, textY);
		textY += lineHeight;
		
		
		// VALUES
		int tailX = (frameX + frameWidth) - 30;
		textY = frameY + gPanel.tileSize;
		String value;
		textY += lineHeight;
		
		value = String.valueOf(gPanel.player.level);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gPanel.player.strength);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gPanel.player.dexterity);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gPanel.player.attack);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gPanel.player.defense);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gPanel.player.exp);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gPanel.player.nextLevelExp);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gPanel.player.coin);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gPanel.player.currentWeapon.name);
		textX = getXforAlignToRightText(value, tailX - 30);
		g2.drawString(value, textX, textY);
		g2.drawImage(gPanel.player.currentWeapon.idle0, tailX - 24, textY - 20, 30, 30,  null);
		textY += lineHeight;
		
		value = String.valueOf(gPanel.player.currentShield.name);
		textX = getXforAlignToRightText(value, tailX - 30);
		g2.drawString(value, textX, textY);
		g2.drawImage(gPanel.player.currentShield.idle0, tailX - 24, textY - 20, 30, 30,  null);
		textY += lineHeight;
		
		
		// DRAW PLAYER MODEL
		g2.drawImage(gPanel.player.currentShield.idle0, tailX - 96, textY + 15, 45, 45,  null);
		g2.drawImage(gPanel.player.idle0, tailX - 128, textY - 10, 72, 72, null);
		g2.drawImage(gPanel.player.currentWeapon.idle0, tailX - 100, textY + 8, 45, 45,  null);
		
	}	
	
	public void drawInventory(Entity entity, boolean curson) {
		
		int frameX = 0;
		int frameY = 0;
		int frameWidth = 0;
		int frameHeight = 0;
		int slotCol = 0;
		int slotRow = 0;
		
		if (entity == gPanel.player) {
			if (gPanel.gameState == gPanel.characterState) frameX = gPanel.tileSize * 10;
			else frameX = gPanel.tileSize * 11;
			frameY = gPanel.tileSize;
			frameWidth = gPanel.tileSize * 6;
			frameHeight = gPanel.tileSize * 6;
			slotCol = playerSlotCol;
			slotRow = playerSlotRow;
		}
		
		else {
			frameX = gPanel.tileSize * 3;
			frameY = gPanel.tileSize;
			frameWidth = gPanel.tileSize * 6;
			frameHeight = gPanel.tileSize * 6;
			slotCol = npcSlotCol;
			slotRow = npcSlotRow;
		}
		
		
		// FRAMES
		
		
		drawSubWindow(frameX, frameY, frameHeight, frameWidth);
		
		// SLOT
		final int slotXStart = frameX + 25;
		final int slotYStart = frameY + 25;
		int slotX = slotXStart;
		int slotY = slotYStart;
		int slotSize = gPanel.tileSize;
		
		// DRAW ITEM
		for (int i = 0; i < entity.inventory.size(); i++)
		{
			// EQUIP CURSON
			if (entity.inventory.get(i) == entity.currentWeapon ||
					entity.inventory.get(i) == entity.currentShield)
			{
				g2.setColor(new Color(240, 190, 90));
				g2.fillRoundRect(slotX, slotY, gPanel.tileSize, gPanel.tileSize, 10, 10);
			}
			g2.drawImage(entity.inventory.get(i).idle0, slotX, slotY, null);
			
			// DISPLAY AMOUNT
			if (entity == gPanel.player &&  entity.inventory.get(i).amount > 1) {
				g2.setFont(g2.getFont().deriveFont(20f));
				int amountX, amountY;
				
				String string = "" + entity.inventory.get(i).amount;
				amountX = getXforAlignToRightText(string, slotX + 44);
				amountY = slotY + gPanel.tileSize - 5;
				
				// SHADOW
				g2.setColor(new Color(60, 60, 60));
				g2.drawString(string, amountX, amountY);
				// NUMBER
				g2.setColor(Color.white);
				g2.drawString(string, amountX + 2, amountY + 2);
				
			}
			slotX += gPanel.tileSize;
			if (i == 4 || i == 9 || i == 14 || i == 19)
			{
				slotX = slotXStart;
				slotY += slotSize;
			}
		}
		
		// CURSON
		
		if (curson == true) {
			int cursonX = slotXStart + (slotSize * slotCol);
			int cursonY = slotYStart + (slotSize * slotRow);
			int cursonWidth = gPanel.tileSize;
			int cursonHeight = gPanel.tileSize;
			
			// DRAW CURSON
			g2.setColor(Color.white);
			g2.setStroke(new BasicStroke(3));
			g2.drawRoundRect(cursonX, cursonY, cursonWidth, cursonHeight, 10, 10);
			
			// DESCRIPTION FRAME
			int dFrameX = frameX;
			int dFrameY = frameY + frameHeight;
			int dFrameWidth = frameWidth;
			int dFrameHeight = gPanel.tileSize * 4;
			
			
			// DRAW TEXT DESCRIPTION
			int textX = dFrameX + 20;
			int textY = dFrameY + gPanel.tileSize;
			g2.setFont(g2.getFont().deriveFont(20f));
			
			int itemIndex = getItemIndexOnSlot(slotCol, slotRow);
			if (itemIndex < entity.inventory.size()) {
				drawSubWindow(dFrameX, dFrameY, dFrameHeight, dFrameWidth);
				for (String line : entity.inventory.get(itemIndex).description.split("\n"))
				{
						g2.drawString(line, textX, textY);
						textY += 32;
				}
				
			}	
		}
		
	}
	
	
	
	
	public int getItemIndexOnSlot(int slotCol, int slotRow) {
		
		int itemIndex = slotCol + (slotRow * 5);
		return itemIndex;
	}
	public void drawSubWindow(int x, int y, int heigth, int width) {
		Color color = new Color(0, 0, 0, 230);
		g2.setColor(color);
		g2.fillRoundRect(x, y, width, heigth, 35, 35);
		
		color = new Color(255, 255, 255);
		g2.setColor(color);
		g2.setStroke(new BasicStroke(5));
		g2.drawRoundRect(x + 5, y + 5, width - 10, heigth - 10, 25, 25);
	}
	
	
	public int getXforAlignToRightText(String text, int tailX) {
		int textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = tailX - textLength;
		return x;
	}
	

	public int getXCenteredText(String text)
	{
		int textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = gPanel.screenWidth / 2 - textLength / 2;
		return x;
	}
}
