package SpaceImpact;

import java.awt.Rectangle;

public class Enemy {
	
	int x;
	int y;
	int image;
	int life;
	Rectangle enemyHB;
	
	
	
	public Enemy(int x, int y, int image){
		this.x = x;
		this.y = y;
		life = 3;
		this.image = image;
		enemyHB = new Rectangle(this.x,this.y,30,20);
		
		
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getImage() {
		return image;
	}

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}

	public Rectangle getEnemyHB(){
		return enemyHB;
	}
	
	
	
}