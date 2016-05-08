package SpaceImpact;

import java.awt.Rectangle;

import Object.GameObject;

public class Plasma extends GameObject {

	public Plasma(int x, int y) {
		this.x = x;
		this.y = y;
		rectangle = new Rectangle(x, y, 8, 3);
	}

}