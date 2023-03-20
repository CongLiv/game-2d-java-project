package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

	
	GamePanel gPanel;
	
	public boolean showDebugText = false;
	
	public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed, spacePressed;
	public boolean shotKeyPressed;			// TEST FIREBALL
	
	public KeyHandler(GamePanel gPanel) {
		// TODO Auto-generated constructor stub
		this.gPanel = gPanel;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	} 

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
		int code = e.getKeyCode();
		
		// TILE STATE
		if (gPanel.gameState == gPanel.titleState) titleState(code);

		// GAME PLAYING
		if (gPanel.gameState == gPanel.gamePlaying) playState(code);
		
		// GAME PAUSED
		else if (gPanel.gameState == gPanel.gamePause) pauseState(code);
		
		// DIALOGUE STATE
		else if (gPanel.gameState == gPanel.dialogueState) dialogueState(code);
		
		// CHARACTER STATE
		else if (gPanel.gameState == gPanel.characterState) characterState(code);
		
		// OPTION STATE
		else if (gPanel.gameState == gPanel.optionState) optionState(code);
		
		// GAME OVER STATE
		else if (gPanel.gameState == gPanel.gameOverState) gameOverState(code);
		
		// GAME TRADE STATE
		else if (gPanel.gameState == gPanel.tradeState) {tradeState(code);}
		

		
	}


	public void tradeState(int code) {
		
		
		if (code == KeyEvent.VK_ENTER) {
			enterPressed = true;
			gPanel.playSoundEffect(9);
		}
		
		if (gPanel.ui.subState == 0) {
			if (code == KeyEvent.VK_UP) {
				gPanel.ui.commandNum--;
				if (gPanel.ui.commandNum < 0) {
					gPanel.ui.commandNum = 2;
				}
				gPanel.playSoundEffect(8);
			}
			
			if (code == KeyEvent.VK_DOWN) {
				gPanel.ui.commandNum++;
				if (gPanel.ui.commandNum > 2) {
					gPanel.ui.commandNum = 0;
				}
				gPanel.playSoundEffect(8);
			}
		}
		
		if (gPanel.ui.subState == 1) {
			npcInvertory(code);
			if (code == KeyEvent.VK_ESCAPE)
			{
				gPanel.ui.subState = 0;
			}
		}
		
		if (gPanel.ui.subState == 2) {
			playerInvertory(code);
			if (code == KeyEvent.VK_ESCAPE)
			{
				gPanel.ui.subState = 0;
			}
		}
		
		
	}
	
	
	public void titleState(int code) {
		
		if (gPanel.ui.subState == 0) {
			if (code == KeyEvent.VK_UP) {
				
				gPanel.ui.commandNum--;
				if (gPanel.ui.commandNum < 0) gPanel.ui.commandNum = 3;
			}

			if (code == KeyEvent.VK_DOWN) {
				gPanel.ui.commandNum++;
				if (gPanel.ui.commandNum > 3) gPanel.ui.commandNum = 0;
			}
			
			if (code == KeyEvent.VK_ENTER) {
				if (gPanel.ui.commandNum == 0) 
				{
					gPanel.currentMap = 0;
					gPanel.player.setDefaultPositions();
				    gPanel.player.restoreStatus();
				    gPanel.aSetter.setNPC();
				    gPanel.winBoss1 = 0;
				    gPanel.winBoss2 = 0;
				    gPanel.aSetter.setMonster();
				    gPanel.player.setDefaultValues();
				    gPanel.aSetter.setObject();
					gPanel.gameState = gPanel.gamePlaying;
					gPanel.playMusic(0);
				}
				if (gPanel.ui.commandNum == 1) {
					gPanel.player.restoreStatus();
					gPanel.saveLoad.load();
					gPanel.gameState = gPanel.gamePlaying;
					gPanel.aSetter.setMonster();
					if ((gPanel.currentMap == 11 && gPanel.winBoss2 == 0)  || (gPanel.currentMap == 5 && gPanel.winBoss1 == 0)) gPanel.playMusic(15);
					else gPanel.playMusic(0);
					
				}
				
				if (gPanel.ui.commandNum == 2) {
					
					gPanel.ui.subState = 1;
				}
				if (gPanel.ui.commandNum == 3) {
					System.exit(0);
				}
			}
		}
		
		else if (gPanel.ui.subState == 1) {
			if (code == KeyEvent.VK_ENTER) {
				gPanel.ui.subState = 0;
			}
		}
		
	}
	public void playState(int code) {
		if (code == KeyEvent.VK_LEFT) {
			leftPressed = true;
		}
		if (code == KeyEvent.VK_RIGHT) {
			rightPressed = true;
		}
		if (code == KeyEvent.VK_UP) {
			
			upPressed = true;
		}

		if (code == KeyEvent.VK_DOWN) {
			downPressed = true;
		}
		if (code == KeyEvent.VK_ENTER) {
			enterPressed = true;
		}
		if (code == KeyEvent.VK_P)
		{	
			gPanel.gameState = gPanel.gamePause;
		}
		
		if (code == KeyEvent.VK_ESCAPE) {
			gPanel.gameState = gPanel.optionState;
		}
		
		if (code == KeyEvent.VK_SPACE && gPanel.player.counterAttack == 0 && gPanel.player.currentWeapon.name != "Unknown") {
			gPanel.playSoundEffect(4);
			spacePressed = true;
		}
		if (code == KeyEvent.VK_C)
		{
			gPanel.gameState = gPanel.characterState;
		}
		
//		if (code == KeyEvent.VK_T) {
//			if (showDebugText == true) showDebugText = false;
//			else showDebugText = true;
//		}
		
//		if (code == KeyEvent.VK_R) {
//			gPanel.tileManager.loadMap("/maps/map01.txt");
//		}
		
		if (code == KeyEvent.VK_R) {
			gPanel.player.usePotion("Red Potion");
		}
		if (code == KeyEvent.VK_G) {
			gPanel.player.usePotion("Green Potion");
		}
		if (code == KeyEvent.VK_Y) {
			gPanel.player.usePotion("Yellow Potion");
		}
		
		
		
//		if (code == KeyEvent.VK_F) {
//			shotKeyPressed = true;		// TEST FIREBALL
//		}
	}
	public void pauseState(int code) {
		if (code == KeyEvent.VK_P)
		{	
			gPanel.gameState = gPanel.gamePlaying;
		}
	}
	public void dialogueState(int code) {
		if (code == KeyEvent.VK_ENTER)
		{
			gPanel.gameState = gPanel.gamePlaying;
		}
	}
	public void characterState(int code) {
		if (code == KeyEvent.VK_C)
		{
			gPanel.gameState = gPanel.gamePlaying;
		}
		
		if (code == KeyEvent.VK_ESCAPE) {
			gPanel.gameState = gPanel.gamePlaying;
		}
		
		playerInvertory(code);
		
		if (code == KeyEvent.VK_ENTER) {
			gPanel.player.selectItem();
		}
	}
	
	public void playerInvertory(int code) {
		if (code == KeyEvent.VK_UP) {
			if (gPanel.ui.playerSlotRow != 0) {
				gPanel.ui.playerSlotRow--;
				gPanel.playSoundEffect(8);
			}
		}
		
		if (code == KeyEvent.VK_DOWN) {
			if (gPanel.ui.playerSlotRow != 4) {
				gPanel.ui.playerSlotRow++;
				gPanel.playSoundEffect(8);
			}
			
		}
		
		if (code == KeyEvent.VK_LEFT) {
			
			if (gPanel.ui.playerSlotCol != 0) {
				gPanel.ui.playerSlotCol--;
				gPanel.playSoundEffect(8);
			}
	
		}
		
		if (code == KeyEvent.VK_RIGHT) {
			if (gPanel.ui.playerSlotCol != 4) {
				gPanel.ui.playerSlotCol++;
				gPanel.playSoundEffect(8);
			}
		}
	}
	
	public void npcInvertory(int code) {
		if (code == KeyEvent.VK_UP) {
			if (gPanel.ui.npcSlotRow != 0) {
				gPanel.ui.npcSlotRow--;
				gPanel.playSoundEffect(8);
			}
		}
		
		if (code == KeyEvent.VK_DOWN) {
			if (gPanel.ui.npcSlotRow != 4) {
				gPanel.ui.npcSlotRow++;
				gPanel.playSoundEffect(8);
			}
			
		}
		
		if (code == KeyEvent.VK_LEFT) {
			
			if (gPanel.ui.npcSlotCol != 0) {
				gPanel.ui.npcSlotCol--;
				gPanel.playSoundEffect(8);
			}
	
		}
		
		if (code == KeyEvent.VK_RIGHT) {
			if (gPanel.ui.npcSlotCol != 4) {
				gPanel.ui.npcSlotCol++;
				gPanel.playSoundEffect(8);
			}
		}
	}
	
	public void optionState(int code) {
		if (code == KeyEvent.VK_ESCAPE) {
			gPanel.gameState = gPanel.gamePlaying;
		}
		
		if (code == KeyEvent.VK_ENTER) {
			enterPressed = true; 
		}
		int maxCommandNum = 0;
		switch (gPanel.ui.subState) {
		case 0: maxCommandNum = 5; break;
		case 3: maxCommandNum = 1; break;
		}
		
		if (code == KeyEvent.VK_DOWN) {
			gPanel.ui.commandNum++;
			gPanel.playSoundEffect(8);
			if (gPanel.ui.commandNum > maxCommandNum) gPanel.ui.commandNum = 0;
		}
		
		if (code == KeyEvent.VK_UP) {
			gPanel.ui.commandNum--;
			gPanel.playSoundEffect(8);
			if (gPanel.ui.commandNum <0) gPanel.ui.commandNum = maxCommandNum;
		}
		
		if (code == KeyEvent.VK_LEFT) {
			if (gPanel.ui.subState == 0) {
				if (gPanel.ui.commandNum == 1 && gPanel.music.volumeScale > 0) {
					gPanel.music.volumeScale--;
					gPanel.music.checkVolume();
					gPanel.playSoundEffect(8);
				}
				if (gPanel.ui.commandNum == 2 && gPanel.se.volumeScale > 0) {
					gPanel.se.volumeScale--;
					gPanel.playSoundEffect(8);
				}
				
			}
			
			
		}
		
		if (code == KeyEvent.VK_RIGHT) {
			if (gPanel.ui.subState == 0) {
				if (gPanel.ui.commandNum == 1 && gPanel.music.volumeScale < 5) {
					gPanel.music.volumeScale++;
					gPanel.music.checkVolume();
					gPanel.playSoundEffect(8);
				}
				
				if (gPanel.ui.commandNum == 2 && gPanel.se.volumeScale < 5) {
					gPanel.se.volumeScale++;
					gPanel.playSoundEffect(8);
				}
			}
		}
	}
	
	
	public void gameOverState(int code) {
		if (code == KeyEvent.VK_UP) {
            gPanel.ui.commandNum--;
            gPanel.playSoundEffect(8);
            if (gPanel.ui.commandNum < 0) {
            	gPanel.ui.commandNum = 1;
            }
        }

        if (code == KeyEvent.VK_DOWN) {
        	gPanel.ui.commandNum++;
            gPanel.playSoundEffect(8);
            if (gPanel.ui.commandNum> 1) {
            	gPanel.ui.commandNum = 0;
            	
            }
        }

        if (code == KeyEvent.VK_ENTER) {
            if (gPanel.ui.commandNum == 0) {
            	gPanel.gameState = gPanel.gamePlaying;
            	gPanel.resetGame(false);
            	
            	if ((gPanel.currentMap == 11 && gPanel.winBoss2 == 0)  || (gPanel.currentMap == 5 && gPanel.winBoss1 == 0)) gPanel.playMusic(15);
				else gPanel.playMusic(0);
            }
            
            else {
            	gPanel.gameState = gPanel.titleState;
            	gPanel.resetGame(true);
            }
        }
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
		int code = e.getKeyCode();

		if (code == KeyEvent.VK_LEFT) {
			leftPressed = false;
			
		}
		if (code == KeyEvent.VK_RIGHT) {
			rightPressed = false;
		}
		if (code == KeyEvent.VK_UP) {
			
			upPressed = false;
		}

		if (code == KeyEvent.VK_DOWN) {
			downPressed = false;
		}
		if (code == KeyEvent.VK_ENTER) {
			enterPressed = false;
		}
		
//		if (code == KeyEvent.VK_SPACE) {
//			spacePressed = false;
//		}
		
		
		if (code == KeyEvent.VK_F) {
			shotKeyPressed = false;		// TEST FIREBALL
		}
		

	}

}
