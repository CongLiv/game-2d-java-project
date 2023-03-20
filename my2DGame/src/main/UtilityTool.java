package main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

public class UtilityTool {
	
	public BufferedImage scaleImage(BufferedImage originalImage, int width, int heigth)
	{
		BufferedImage scaledImage = new BufferedImage(width, heigth, originalImage.getType());
		Graphics2D g2 = scaledImage.createGraphics();
		g2.drawImage(originalImage, 0, 0, width, heigth, null);
		g2.dispose();
		
		return scaledImage;
	}
	
	public boolean checkPercentage(int percent) {
		
		if (percent >= 95) percent = 95;
		int ranNum = (int)Math.floor(Math.random() * (100 - 0 + 1) + 0); 
		if (ranNum <= percent) return true;
		return false; 
		
	}
}
