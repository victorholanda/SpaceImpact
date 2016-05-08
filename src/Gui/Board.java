package Gui;

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
import java.util.HashMap;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

import Ship.Enemy;
import Ship.SpaceShip;
import Ship.Command.Command;
import Ship.Command.CommandBigShot;
import Ship.Command.CommandBigShotR;
import Ship.Command.CommandDown;
import Ship.Command.CommandDownR;
import Ship.Command.CommandKiller;
import Ship.Command.CommandLeft;
import Ship.Command.CommandLeftR;
import Ship.Command.CommandPause;
import Ship.Command.CommandRight;
import Ship.Command.CommandRightR;
import Ship.Command.CommandShot;
import Ship.Command.CommandShotR;
import Ship.Command.CommandUp;
import Ship.Command.CommandUpR;
import SpaceImpact.Death;
import SpaceImpact.Plasma;
import SpaceImpact.Wave;

@SuppressWarnings("serial")
public class Board extends JPanel implements ActionListener {
	private int score;
	private int spawnFrequency;
	private int scrWidth;
	private int scrHeight;
	private int cityLife;
	private int reloadTime;
	private int MAXscore;
	private int spaceShipAnimation;
	private boolean inMenu;
	private boolean reloading;
	private boolean linkClick;
	private int T = 10;
	private Timer timer;
	private Random rand;
	private static SpaceShip player;
	private ImgLoader loader;
	private Menu menu;
	private Death death;
	private Mouse mouse;
	private Background background;

	private ArrayList<Plasma> bullets;
	private ArrayList<Enemy> enemies;
	private ArrayList<Wave> waves;
	private HashMap<Integer, Command> pressed;
	private HashMap<Integer, Command> released;

	public Board() {
		mouse = new Mouse();
		addMouseMotionListener(mouse);
		addMouseListener(mouse);
		addKeyListener(new TAdapter());
		setFocusable(true);
		setBackground(Color.LIGHT_GRAY);
		bullets = new ArrayList<Plasma>();
		enemies = new ArrayList<Enemy>();
		waves = new ArrayList<Wave>();
		background = new Background();
		loader = new ImgLoader();
		death = new Death();
		menu = new Menu();
		player = new SpaceShip(scrHeight, scrWidth);
		timer = new Timer(T, this);
		timer.start();
		rand = new Random();
		initCommands();
	}

	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.BLACK);
		g2d.setFont(new Font("Purisa", Font.PLAIN, 13));
		g2d.setColor(Color.white);
		if (!inMenu) {
			if (!player.isDeath()) {
				g2d.drawImage(loader.getBackground(), background.getBack1().x, 0, null);
				g2d.drawImage(loader.getBackground(), background.getBack2().x, 0, null);
				g2d.setColor(Color.BLACK);
				g2d.fill(new Rectangle(0, 0, 484, 19));
				g2d.setColor(Color.white);
				g2d.drawString("Score: " + score, 410, 15);
				if (!reloading) {
					g2d.drawString("Wave Attack: Ready! ", 110, 15);
				} else {
					g2d.drawString("Wave Attack: Reloading " + (30 - (reloadTime / 100)) + "s", 110, 15);
				}
				g2d.drawString("City: " + cityLife + "%", 310, 15);
				if (!player.getPause()) {
					spaceShipAnimation++;
				}
				g2d.drawImage(loader.getSpaceShip()[(spaceShipAnimation / 10)], player.getX(), player.getY(), null);
				if (spaceShipAnimation == 29) {
					spaceShipAnimation = 0;
				}

				for (int i = 0; i < bullets.size(); i++) {
					g2d.drawImage(loader.getPlasma(), bullets.get(i).getX(), bullets.get(i).getY(), null);
				}
				for (int i = 0; i < waves.size(); i++) {
					g2d.drawImage(loader.getWave(), waves.get(i).getX(), waves.get(i).getY(), null);
				}
				for (int i = 0; i < enemies.size(); i++) {
					g2d.drawImage(loader.getEnemy()[enemies.get(i).getImage()][spaceShipAnimation / 10],
							enemies.get(i).getX(), enemies.get(i).getY(), null);
				}

				g2d.drawImage(loader.getHeart()[3 - player.getLife()], 30, 2, null);
				if (spaceShipAnimation == 29) {
					spaceShipAnimation = 0;
				}
			} else {
				g2d.drawImage(loader.getBackground(), 0, 0, null);
				g2d.setFont(new Font("Purisa", Font.PLAIN, 30));
				g2d.drawString("Your score was: " + score, 123, 130);
				if (!death.getMouse().intersects(death.getRectRestart()))
					g2d.drawImage(loader.getRestart(), (int) death.getRectRestart().x, (int) death.getRectRestart().y,
							null);
				else
					g2d.drawImage(loader.getRestartPressed(), (int) death.getRectRestart().x,
							(int) death.getRectRestart().y, null);
			}
		} else {
			g2d.drawImage(loader.getBackground(), 0, 0, null);
			if (!menu.getRectMouse().intersects(menu.getRectButton()))
				g2d.drawImage(loader.getStart(), (int) menu.getRectButton().getX(), (int) menu.getRectButton().getY(),
						null);
			else
				g2d.drawImage(loader.getStartPressed(), (int) menu.getRectButton().getX(),
						(int) menu.getRectButton().getY(), null);
			g2d.drawImage(loader.getCredits(), (int) menu.getRectCredits().getX(), (int) menu.getRectCredits().getY(),
					null);
			g2d.drawImage(loader.getLogoGame(), (int) menu.getRectGameLogo().getX(),
					(int) menu.getRectGameLogo().getY(), null);
			if (!menu.getRectMouse().intersects(menu.getRectLogo()))
				g2d.drawImage(loader.getHmlogo(), (int) menu.getRectLogo().getX(), (int) menu.getRectLogo().getY(),
						null);
			else
				g2d.drawImage(loader.getHmlogoPressed(), (int) menu.getRectLogo().getX(),
						(int) menu.getRectLogo().getY(), null);
		}

		Toolkit.getDefaultToolkit().sync();
		g.dispose();
	}

	public void actionPerformed(ActionEvent e) {
		if (!inMenu) {
			if (!player.isDeath()) {
				if (!player.getPause()) {
					background.updatebg();
					if (player.isDeath())
						player.setDeath(true);
					player.move();
					if (player.getFire()) {
						bullets.add(new Plasma(player.getX() + 30, player.getY() + 8));
						player.setBlockFire(true);
					}

					if (player.getWaveFire() && !reloading) {
						waves.add(new Wave(player.getX() + 30, 20));
						reloading = true;
						reloadTime = 0;
					}

					if (reloading && reloadTime < 3000) {
						reloadTime++;
						if (reloadTime == 3000) {
							reloading = false;
						}
					}

					for (int i = 0; i < bullets.size(); i++) {
						if (!(bullets.get(i).getX() > 500)) {
							bullets.get(i).increaseX(3);
						} else {
							bullets.remove(i);
						}
					}

					for (int i = 0; i < waves.size(); i++) {
						if (!(waves.get(i).getX() > 500)) {
							waves.get(i).increaseX(3);
						} else {
							waves.remove(i);
						}
					}

					if (rand.nextInt(spawnFrequency) == 0) {
						enemies.add(new Enemy(500, rand.nextInt(273) + 25, rand.nextInt(3)));
					}

					for (int i = 0; i < enemies.size(); i++) {

						if (enemies.get(i).getX() > -31) {
							enemies.get(i).increaseX(-1);
						} else {
							enemies.remove(i);
							cityLife -= 5;
						}
					}

					for (int i = 0; i < enemies.size(); i++) {
						for (int b = 0; b < bullets.size(); b++) {
							if (enemies.get(i).getRectangle().intersects(bullets.get(b).getRectangle())) {
								enemies.get(i).setLife(enemies.get(i).getLife() - 1);
								bullets.remove(b);
							}
						}
						for (int w = 0; w < waves.size(); w++) {
							if (enemies.get(i).getRectangle().intersects(waves.get(w).getRectangle())) {
								enemies.get(i).setLife(0);
							}
						}
						if (enemies.get(i).getRectangle().intersects(player.getRectangle())) {
							player.setLife(player.getLife() - 1);
							enemies.remove(i);
						}
					}

					for (int c = 0; c < enemies.size(); c++) {
						if (enemies.get(c).getLife() <= 0) {
							enemies.remove(c);
							score++;
							if (spawnFrequency > 54) {
								spawnFrequency -= 1;

							}
							if (score == MAXscore && T > 5) {
								T--;
								MAXscore += 25;
								timer.stop();
								timer = new Timer(T, this);
								timer.start();
							}
						}
					}
					if (player.getLife() <= 0 || cityLife <= 0) {
						player.setDeath(true);
					}

				}
			} else {
				death.updateMouse(mouse.getMousePos().x, mouse.getMousePos().y);
				if (death.getMouse().intersects(death.getRectRestart()) && mouse.Click())
					reset();
			}
		} else {
			if (!menu.getRectMouse().intersects(menu.getRectLogo()) && mouse.Click())
				inMenu = false;
			if (menu.getRectMouse().intersects(menu.getRectLogo()) && mouse.Click() && linkClick) {
				openWebpage("http://homemadestudios.altervista.org/");
				linkClick = false;
			}
			menu.updateMouse(mouse.getMousePos().x, mouse.getMousePos().y);
		}
		repaint();
	}

	void reset() {
		timer.restart();
		death = new Death();
		menu = new Menu();
		player = new SpaceShip(scrHeight, scrWidth);
		inMenu = true;
		reloading = false;
		linkClick = true;
		mouse.ClickSet(false);
		bullets.clear();
		enemies.clear();
		waves.clear();
		setCityLife(100);
	}

	private class TAdapter extends KeyAdapter {

		public void keyPressed(KeyEvent e) {
			try {
				player = pressed.get(e.getKeyCode()).run(player);
			} catch (Exception ex) {
				// TODO: handle exception
				System.out.println(ex.getMessage());
			}

		}

		public void keyReleased(KeyEvent e) {
			try {
				player = released.get(e.getKeyCode()).run(player);
			} catch (Exception ex) {
				// TODO: handle exception
				System.out.println(ex.getMessage());
			}
		}
	}

	public static void openWebpage(String urlString) {
		try {
			Desktop.getDesktop().browse(new URL(urlString).toURI());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setScore(int score) {
		this.score = score;
	}

	public void setSpawnFrequency(int spawnFrequency) {
		this.spawnFrequency = spawnFrequency;
	}

	public void setScrWidth(int scrWidth) {
		this.scrWidth = scrWidth;
	}

	public void setScrHeight(int scrHeight) {
		this.scrHeight = scrHeight;
	}

	public void setCityLife(int cityLife) {
		this.cityLife = cityLife;
	}

	public void setReloadTime(int reloadTime) {
		this.reloadTime = reloadTime;
	}

	public void setMAXscore(int mAXscore) {
		MAXscore = mAXscore;
	}

	public void setSpaceShipAnimation(int spaceShipAnimation) {
		this.spaceShipAnimation = spaceShipAnimation;
	}

	public void setInMenu(boolean isInMenu) {
		this.inMenu = isInMenu;
	}

	public void setReloading(boolean reloading) {
		this.reloading = reloading;
	}

	public void setLinkClick(boolean linkClick) {
		this.linkClick = linkClick;
	}

	public void setT(int t) {
		T = t;
	}

	public void setTimer(Timer timer) {
		this.timer = timer;
	}

	public void setRand(Random rand) {
		this.rand = rand;
	}

	public static void setPlayer(SpaceShip player) {
		Board.player = player;
	}

	public void setLoader(ImgLoader loader) {
		this.loader = loader;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public void setDeath(Death death) {
		this.death = death;
	}

	public void setMouse(Mouse mouse) {
		this.mouse = mouse;
	}

	public void setBackground(Background background) {
		this.background = background;
	}

	public void setBullets(ArrayList<Plasma> bullets) {
		this.bullets = bullets;
	}

	public void setEnemies(ArrayList<Enemy> enemies) {
		this.enemies = enemies;
	}

	public void setWaves(ArrayList<Wave> waves) {
		this.waves = waves;
	}

	private void initCommands() {
		pressed = new HashMap<>();
		released = new HashMap<>();
		pressed.put(KeyEvent.VK_A, new CommandLeft());
		pressed.put(KeyEvent.VK_S, new CommandDown());
		pressed.put(KeyEvent.VK_W, new CommandUp());
		pressed.put(KeyEvent.VK_D, new CommandRight());
		pressed.put(KeyEvent.VK_K, new CommandKiller());
		pressed.put(KeyEvent.VK_UP, new CommandShot());
		pressed.put(KeyEvent.VK_DOWN, new CommandBigShot());
		pressed.put(KeyEvent.VK_P, new CommandPause());
		released.put(KeyEvent.VK_UP, new CommandShotR());
		released.put(KeyEvent.VK_DOWN, new CommandBigShotR());
		released.put(KeyEvent.VK_A, new CommandLeftR());
		released.put(KeyEvent.VK_S, new CommandDownR());
		released.put(KeyEvent.VK_W, new CommandUpR());
		released.put(KeyEvent.VK_D, new CommandRightR());
	}

}