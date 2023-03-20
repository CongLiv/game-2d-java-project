package ui;

import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Entity;
import main.GamePanel;

public class UI_Health extends Entity{
	
	GamePanel gPanel;
	
	public UI_Health(GamePanel gPanel) {
		// TODO Auto-generated constructor stub
		super(gPanel);
		name = "Health";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/health_ui.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
}
