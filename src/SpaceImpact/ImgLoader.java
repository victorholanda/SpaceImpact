package SpaceImpact;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImgLoader {
	
	int width;
	int height;
	int rows;
	int cols;
	BufferedImage[][] enemy;
	BufferedImage[] heart;
	BufferedImage[] spaceShip;
	BufferedImage plasma;
	BufferedImage wave;
	BufferedImage background;
	BufferedImage bigImg;
	BufferedImage credits;
	BufferedImage hmlogo;
	BufferedImage hmlogoPressed;
	BufferedImage logoGame;
	BufferedImage start;
	BufferedImage startPressed;
	BufferedImage restart;
	BufferedImage restartPressed;
	
	public ImgLoader(){
	System.out.println(getClass());
	enemy = new BufferedImage[3][3];
	heart = new BufferedImage[3];
	spaceShip = new BufferedImage[3];
	load();
	}
	
	void load(){
		
			width = 30;
			height = 20;
			rows = 3;
			cols = 3;
			try {
			bigImg = ImageIO.read(getClass().getResource("/img/SpaceImpact_enemyspaceships.png"));
			} catch (IOException e) {
			e.printStackTrace();
			}
			for (int i = 0; i < rows; i++)
			{
			    for (int j = 0; j < cols; j++)
			    {
			        enemy[i][j] = bigImg.getSubimage(j * width, i * height, width, height);
			    }
			}
			
			width = 49;
			height = 15;
			rows = 1;
			cols = 3;
			try {
				bigImg = ImageIO.read(getClass().getResource("/img/SpaceImpact_health.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			for (int i = 0; i < rows; i++)
			{
			    for (int j = 0; j < cols; j++)
			    {
			        heart[(i * cols) + j] = bigImg.getSubimage(j * width, i * height, width, height);
			    }
			}		
			
			width = 30;
			height = 20;
			rows = 1;
			cols = 3;
			try {
				bigImg = ImageIO.read(getClass().getResource("/img/SpaceImpact_spaceship.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			for (int i = 0; i < rows; i++)
			{
			    for (int j = 0; j < cols; j++)
			    {
			        spaceShip[(i * cols) + j] = bigImg.getSubimage(j * width, i * height, width, height);
			    }
			}		
			
			try {	
			plasma = ImageIO.read(getClass().getResource("/img/SpaceImpact_ammo.png"));
			wave = ImageIO.read(getClass().getResource("/img/SpaceImpact_specialammo.png"));
			background = ImageIO.read(getClass().getResource("/img/SpaceImpact_ingame_bg.png"));
			credits = ImageIO.read(getClass().getResource("/img/credits.png"));
			hmlogo = ImageIO.read(getClass().getResource("/img/hmlogo.png"));
			hmlogoPressed = ImageIO.read(getClass().getResource("/img/hmlogoPressed.png"));
			logoGame = ImageIO.read(getClass().getResource("/img/logoGame.png"));
			start = ImageIO.read(getClass().getResource("/img/start.png"));
			startPressed = ImageIO.read(getClass().getResource("/img/startPressed.png"));
			restart = ImageIO.read(getClass().getResource("/img/restart.png"));
			restartPressed = ImageIO.read(getClass().getResource("/img/restartPressed.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
	
	}
	
	public BufferedImage getRestart() {
		return restart;
	}

	public BufferedImage getRestartPressed() {
		return restartPressed;
	}

	public BufferedImage getCredits() {
		return credits;
	}

	public BufferedImage getHmlogo() {
		return hmlogo;
	}

	public BufferedImage getHmlogoPressed() {
		return hmlogoPressed;
	}

	public BufferedImage getLogoGame() {
		return logoGame;
	}

	public BufferedImage getStart() {
		return start;
	}

	public BufferedImage getStartPressed() {
		return startPressed;
	}

	public BufferedImage[][] getEnemy() {
		return enemy;
	}
	
	public BufferedImage[] getHeart() {
		return heart;
	}
	
	public BufferedImage[] getSpaceShip() {
		return spaceShip;
	}
	
	public BufferedImage getPlasma() {
		return plasma;
	}
	
	public BufferedImage getWave() {
		return wave;
	}
	
	public BufferedImage getBackground() {
		return background;
	}
}
