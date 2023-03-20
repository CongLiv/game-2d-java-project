package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Config {
	public final GamePanel gamePanel;

    public Config(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void saveConfig() {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("config.txt"));

            // FULLSCREEN
            if (gamePanel.fullscreenOn == true) {
                bufferedWriter.write("On");
            } else {
                bufferedWriter.write("Off");
            }
            bufferedWriter.newLine();

            // MUSIC VOLUME
            bufferedWriter.write(String.valueOf(gamePanel.music.volumeScale));
            bufferedWriter.newLine();

            // SOUND EFFECT VOLUME
            bufferedWriter.write(String.valueOf(gamePanel.se.volumeScale));
            bufferedWriter.newLine();

            bufferedWriter.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadConfig() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("config.txt"));

            String s = bufferedReader.readLine();

            // FULLSCREEN
            if (s.equals("On")) {
                gamePanel.fullscreenOn = true;
            } else if (s.equals("Off")){
                gamePanel.fullscreenOn = false;
            }

            // MUSIC VOLUME
            s = bufferedReader.readLine();
            gamePanel.music.volumeScale = Integer.parseInt(s);

            // MUSIC VOLUME
            s = bufferedReader.readLine();
            gamePanel.se.volumeScale = Integer.parseInt(s);
            
            bufferedReader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
