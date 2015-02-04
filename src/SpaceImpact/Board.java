package SpaceImpact;


import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;


@SuppressWarnings("serial")
public class Board extends JPanel implements ActionListener
{
	int score = 0;
	int spawnFrequency=250;
	int scrWidth=500;
	int scrHeight=350;
	int waveMunition = 5;
	int cityLife = 100;
	int reloadTime = 0;
	int MAXscore = 25;
	int spaceShipAnimation = 0;
	boolean isDead = false;
	boolean isInMenu = true;
	boolean IsDeath=false;
	boolean reloading = false;
	boolean linkClick=true;
	int T=10;
	int move=0;
	private Timer timer;
	Random rand;
	SpaceShip spaceShip;
	ImgLoader loader;
	Menu menu;
	Death death;
	Mouse mouse;
	Enemy enemy;
	Plasma plasma;
	Background background;
	
	public ArrayList<Plasma> bullets = new ArrayList<Plasma>();
	public ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	public ArrayList<Wave> waves = new ArrayList<Wave>();
	

	public Board()
	{
		mouse=new Mouse();
		addMouseMotionListener(mouse);
		addMouseListener(mouse);
		addKeyListener(new TAdapter());
		setFocusable(true);
		setBackground(Color.LIGHT_GRAY);
		background=new Background();
		loader=new ImgLoader();
		death=new Death();
		menu=new Menu();
		spaceShip= new SpaceShip(scrHeight,scrWidth);
		timer=new Timer(T, this);
		timer.start();
		rand = new Random();
	}

	public void paint(Graphics g) 
	{
		super.paint(g);
		Graphics2D g2d = (Graphics2D)g;
		g2d.setColor(Color.BLACK);
		g2d.setFont(new Font("Purisa", Font.PLAIN, 13));
		g2d.setColor(Color.white);
		if(!isInMenu){
			if(!isDead){
				g2d.drawImage(loader.getBackground(),background.getBack1().x, 0, null);
				g2d.drawImage(loader.getBackground(),background.getBack2().x, 0, null);
				g2d.setColor(Color.BLACK);
				g2d.fill(new Rectangle(0, 0, 484, 19));
				g2d.setColor(Color.white);
				g2d.drawString("Score: "+score, 410, 15);
				if(!reloading){
					g2d.drawString("Wave Attack: Ready! ", 110, 15);
				}
				else{
					g2d.drawString("Wave Attack: Reloading "+ (30-(reloadTime/100)) + "s", 110, 15);
				}
				g2d.drawString("City: "+cityLife+"%", 310, 15);
				if(!spaceShip.getPause()){
					spaceShipAnimation++;
				}
				g2d.drawImage(loader.getSpaceShip()[(spaceShipAnimation/10)],spaceShip.getX(), spaceShip.getY(), null);
				if(spaceShipAnimation == 29){
					spaceShipAnimation = 0;
				}
				
				for(int i = 0; i < bullets.size(); i++){
					g2d.drawImage(loader.getPlasma(),bullets.get(i).x, bullets.get(i).y, null);
				}
				for(int i = 0; i < waves.size(); i++){
					g2d.drawImage(loader.getWave(),waves.get(i).x, waves.get(i).y, null);
				}
				for(int i = 0; i < enemies.size(); i++){
					g2d.drawImage(loader.getEnemy()[enemies.get(i).getImage()][spaceShipAnimation/10],enemies.get(i).getX(), enemies.get(i).getY(), null);
				}
				
				g2d.drawImage(loader.getHeart()[3 - spaceShip.getLife()],30,2, null);
				if(spaceShipAnimation == 29){
						spaceShipAnimation = 0;
				}
			}
			else{
				g2d.drawImage(loader.getBackground(),0, 0, null);
				g2d.setFont(new Font("Purisa", Font.PLAIN, 30));		
				g2d.drawString("Your score was: "+ score, 123, 130);
				if(!death.getMouse().intersects(death.getRectRestart()))	
					g2d.drawImage(loader.getRestart(),(int)death.getRectRestart().x,(int)death.getRectRestart().y, null);
				else
					g2d.drawImage(loader.getRestartPressed(),(int)death.getRectRestart().x,(int)death.getRectRestart().y, null);
			}
		}
		else{
			g2d.drawImage(loader.getBackground(),0, 0, null);
			if(!menu.getRectMouse().intersects(menu.getRectButton()))
				g2d.drawImage(loader.getStart(),(int)menu.getRectButton().getX(), (int)menu.getRectButton().getY(), null);
			else
				g2d.drawImage(loader.getStartPressed(),(int)menu.getRectButton().getX(), (int)menu.getRectButton().getY(), null);
			g2d.drawImage(loader.getCredits(),(int)menu.getRectCredits().getX(), (int)menu.getRectCredits().getY(), null);
			g2d.drawImage(loader.getLogoGame(),(int)menu.getRectGameLogo().getX(), (int)menu.getRectGameLogo().getY(), null);
			if(!menu.getRectMouse().intersects(menu.getRectLogo()))	
				g2d.drawImage(loader.getHmlogo(),(int)menu.getRectLogo().getX(), (int)menu.getRectLogo().getY(), null);
			else
				g2d.drawImage(loader.getHmlogoPressed(),(int)menu.getRectLogo().getX(), (int)menu.getRectLogo().getY(), null);
		}
		
		Toolkit.getDefaultToolkit().sync();
		g.dispose();
	}


	public void actionPerformed(ActionEvent e)
	{
		if(!isInMenu){
			if(!isDead){
				if(!spaceShip.getPause()){
					background.updatebg();
					if(spaceShip.isDeath())
						isDead=true;
					spaceShip.move();
					
					if (spaceShip.getFire()){
						bullets.add(new Plasma(spaceShip.getX() + 30, spaceShip.getY() + 8));
						spaceShip.setBlockFire(true);
					}
					
					if (spaceShip.getWaveFire() && !reloading){
						waves.add(new Wave(spaceShip.getX() + 30, 20));
						reloading = true;
						reloadTime = 0;
					}
					
					if(reloading && reloadTime < 3000){
						reloadTime++;
						if (reloadTime == 3000){
							reloading = false;
						}
					}
					
				    for (int i = 0; i < bullets.size(); i++){
						if (!(bullets.get(i).x > 500)){
							bullets.get(i).x += 3;
							bullets.get(i).plasmaHB.x += 3;
						}
						else{
							bullets.remove(i);
						}
			    	}
				    
				    for (int i = 0; i < waves.size(); i++){
						if (!(waves.get(i).x > 500)){
							waves.get(i).x += 3;
							waves.get(i).waveHB.x += 3;
						}
						else{
							waves.remove(i);
						}
			    	}
				    
				    if (rand.nextInt(spawnFrequency) == 0){
				    	enemies.add(new Enemy(500, rand.nextInt(273) + 25, rand.nextInt(3)));
				    }
					
				    for (int i = 0; i < enemies.size(); i++){
				    
						if (enemies.get(i).x > -31){
							enemies.get(i).x -= 1;
							enemies.get(i).enemyHB.x -= 1;
						}
						else{
							enemies.remove(i);
							cityLife -= 5;
						}
			    	}
				    
				    for (int i = 0; i < enemies.size(); i++){
				    	for (int b = 0; b < bullets.size(); b++){
				    		if(enemies.get(i).getEnemyHB().intersects(bullets.get(b).getPlasmaHB())){
				    			enemies.get(i).setLife(enemies.get(i).getLife() - 1);
				    			bullets.remove(b);
				    		}
				    	}
				    	for (int w = 0; w < waves.size(); w++){
				    		if(enemies.get(i).getEnemyHB().intersects(waves.get(w).getWaveHB())){
				    			enemies.get(i).setLife(0);
				    		}
				    	}
				    	System.out.println(spaceShip.getLife());
				    	if (enemies.get(i).getEnemyHB().intersects(spaceShip.getSpaceShipHB())){
				    		spaceShip.life -= 1;
				    		enemies.remove(i);
				    	}
				    }
				    
				    for (int c = 0; c < enemies.size(); c++){
				    	if(enemies.get(c).getLife() <= 0){
				    		enemies.remove(c);
				    		score ++;
				    		if(spawnFrequency > 54){
				    			spawnFrequency -= 1;
				    		
				    		}
				    		if(score == MAXscore && T>5){
				    			T--;
				    			MAXscore+=25;
				    			timer.stop();
				    			timer=new Timer(T, this);
				    			timer.start();
				    		}
				    	}
				    }
				    if(spaceShip.getLife() <= 0 || cityLife <= 0){
				    	isDead = true;
				    }
				    	    
				}
			}
			else{
				death.updateMouse(mouse.getMousePos().x, mouse.getMousePos().y);
				if(death.getMouse().intersects(death.getRectRestart())&&mouse.Click())
					reset();
			}
		}
		else{
		if(!menu.getRectMouse().intersects(menu.getRectLogo())&&mouse.Click())	
			isInMenu=false;
		if(menu.getRectMouse().intersects(menu.getRectLogo())&&mouse.Click() && linkClick){	
			openWebpage("http://homemadestudios.altervista.org/");
			linkClick=false;
		}
		menu.updateMouse(mouse.getMousePos().x,mouse.getMousePos().y);
		}
		repaint();
	}
	
	void reset()
	{
		timer.stop();
		death=new Death();
		menu=new Menu();
		spaceShip= new SpaceShip(scrHeight,scrWidth);
		isDead = false;
		isInMenu = true;
		IsDeath=false;
		reloading = false;
		linkClick=true;
		mouse.ClickSet(false);
		bullets.clear();
		enemies.clear();
		waves.clear();
		timer=new Timer(T, this);
		timer.start();
	}
	
	private class TAdapter extends KeyAdapter {

		public void keyPressed(KeyEvent e) {
			spaceShip.keyPressed(e);
		}
		public void keyReleased(KeyEvent e){
			spaceShip.keyReleased(e);
		}
	}
	
	public static void openWebpage(String urlString) {
	    try {
	        Desktop.getDesktop().browse(new URL(urlString).toURI());
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
}