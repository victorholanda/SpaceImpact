package SpaceImpact;

import java.awt.Rectangle;

import Object.GameObject;

public class Wave extends GameObject {

	public Wave(int x, int y) {
		this.x = x;
		this.y = y;
		rectangle = new Rectangle(x, y, 1, 350);
	}

}
