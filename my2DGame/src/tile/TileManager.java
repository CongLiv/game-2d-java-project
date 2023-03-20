package tile;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class TileManager {

	GamePanel gPanel;
	public Tile[] tile;

	public int mapTile[][][];
	public boolean cantPut[][][];
	ArrayList<String> fileNames = new ArrayList<>();
	ArrayList<String> collisionStatus = new ArrayList<>();  
	
	boolean drawPath = false;
	public TileManager(GamePanel gPanel) {
		// READ DATA TILE 
		InputStream is = getClass().getResourceAsStream("/maps/tiledata.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		
		String line;
		try {
			while ((line = br.readLine()) != null)
			{
				fileNames.add(line);
				collisionStatus.add(br.readLine());
			}
			
			br.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		this.gPanel = gPanel;
		tile = new Tile[fileNames.size()];
		getTileImage();
		mapTile = new int[gPanel.maxMap][gPanel.maxWorldCol][gPanel.maxWorldRow];
		cantPut = new boolean[gPanel.maxMap][gPanel.maxWorldCol][gPanel.maxWorldRow];
		loadMap("/maps/map00.txt", 0);
		loadMap("/maps/map01.txt", 1);
		loadMap("/maps/map02.txt", 2);
		loadMap("/maps/map03.txt", 3);
		loadMap("/maps/map04.txt", 4);
		loadMap("/maps/map05.txt", 5);
		loadMap("/maps/map06.txt", 6);
		loadMap("/maps/map07.txt", 7);
		loadMap("/maps/map08.txt", 8);
		loadMap("/maps/map09.txt", 9);
		loadMap("/maps/map10.txt", 10);
		loadMap("/maps/map11.txt", 11);
	}

	public int getDefaultXInCurrentMap(int map) {
		
		int worldX = 0;
		switch (map) {
		case 0: worldX = gPanel.tileSize * 21; break;
		case 1: worldX = gPanel.tileSize * 19; break;
		case 2: worldX = gPanel.tileSize * 24; break;
		case 3: worldX = gPanel.tileSize * 18; break;
		case 4: worldX = gPanel.tileSize * 21; break;
		case 5: worldX = gPanel.tileSize * 30; break;
		case 6: worldX = gPanel.tileSize * 41; break;
		case 7: worldX = gPanel.tileSize * 52; break;
		case 8: worldX = gPanel.tileSize * 47; break;
		case 9: worldX = gPanel.tileSize * 46; break;
		case 10: worldX = gPanel.tileSize * 16; break;
		case 11: worldX = gPanel.tileSize * 48; break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + map);
		}
		
		return worldX;
	}
	
	public int getDefaultYInCurrentMap(int map) {
		
		int worldY = 0;
		switch (map) {
		case 0: worldY = gPanel.tileSize * 21; break;
		case 1: worldY = gPanel.tileSize * 31; break;
		case 2: worldY = gPanel.tileSize * 17; break;
		case 3: worldY = gPanel.tileSize * 16; break;
		case 4: worldY = gPanel.tileSize * 25; break;
		case 5: worldY = gPanel.tileSize * 20; break;
		case 6: worldY = gPanel.tileSize * 72; break;
		case 7: worldY = gPanel.tileSize * 53; break;
		case 8: worldY = gPanel.tileSize * 38; break;
		case 9: worldY = gPanel.tileSize * 43; break;
		case 10: worldY = gPanel.tileSize * 43; break;
		case 11: worldY = gPanel.tileSize * 51; break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + map);
		}
		
		return worldY;
	}
	
	public void getTileImage() {

			for (int i = 0; i  < fileNames.size(); i++)
			{
				String fileName;
				boolean collision;
				
				fileName = fileNames.get(i);
				if (collisionStatus.get(i).equals("true")) {
					collision = true;
				}
				else collision = false;
				setup(i, fileName, collision);
			}
			
			

	}
	
	
	public void setup(int index, String tileName, boolean collision) {
		
		UtilityTool uTool = new UtilityTool();
		
		try {
			tile[index] = new Tile();
			tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/" + tileName));
			tile[index].image = uTool.scaleImage(tile[index].image, gPanel.tileSize, gPanel.tileSize);
			tile[index].collision = collision;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}

	public void loadMap(String fileMapPath, int map) {
		 
        try {
            InputStream inputStream = getClass().getResourceAsStream(fileMapPath);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            
            for (int row = 0; row < gPanel.maxWorldRow; row++)
            {
            	String line = bufferedReader.readLine();
            	String[] numbers = line.split(" ");
            	for (int i = 0; i < numbers.length; i++)
            	{
            		int num = Integer.parseInt(numbers[i]);
            		mapTile[map][row][i] = num;
            	}
            }

            bufferedReader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
		  
		
	}

	public void draw(Graphics2D g2) {

		int worldX = 0;
		int worldY = 0;
		
		for (int worldCol = 0; worldCol < gPanel.maxWorldCol; worldCol++)
		{	
			for (int worldRow = 0; worldRow < gPanel.maxWorldRow; worldRow++)
			{
				int tileNum = mapTile[gPanel.currentMap][worldCol][worldRow];
				if (worldRow == 0) worldX = 0;
				int screenX = worldX - gPanel.player.worldX + gPanel.player.screenX;
				int screenY = worldY - gPanel.player.worldY + gPanel.player.screenY;
				
				if (worldX + gPanel.tileSize > gPanel.player.worldX - gPanel.player.screenX &&
						worldX - gPanel.tileSize < gPanel.player.worldX + gPanel.player.screenX &&
						worldY + gPanel.tileSize > gPanel.player.worldY - gPanel.player.screenY &&
						worldY - gPanel.tileSize < gPanel.player.worldY + gPanel.player.screenY
						) 
				g2.drawImage(tile[tileNum].image, screenX, screenY, null);
				
				worldX = (worldRow + 1) * gPanel.tileSize;
			}
			
			worldY = (worldCol + 1) * gPanel.tileSize;
			
		}
		
		if (drawPath == true) {
			drawPathLine(g2);
		}

	}
	
	public void drawPathLine(Graphics2D g2) {
		g2.setColor(new Color(255, 0, 0, 100));
		for (int i = 0; i < gPanel.pFinder.pathList.size(); i++) {
			int pWorldX = gPanel.pFinder.pathList.get(i).col * gPanel.tileSize;
			int pWorldY = gPanel.pFinder.pathList.get(i).row * gPanel.tileSize;
			int pScreenX = pWorldX - gPanel.player.worldX + gPanel.player.screenX;
			int pScreenY = pWorldY - gPanel.player.worldY + gPanel.player.screenY;
			g2.fillRect(pScreenX, pScreenY, gPanel.tileSize, gPanel.tileSize);
		}
		
	}

}
