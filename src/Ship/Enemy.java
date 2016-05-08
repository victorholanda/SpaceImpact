package Ship;

import java.awt.Rectangle;

import Object.Ship;

public class Enemy extends Ship {
	
	private int image;

	public Enemy(int x, int y, int image) {
		this.x = x;
		this.y = y;
		life = 3;
		this.image = image;
		rectangle = new Rectangle(this.x, this.y, 30, 20);

	}

	public int getImage() {
		return image;
	}
}