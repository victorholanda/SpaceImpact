package Object;

import java.awt.Rectangle;

public class GameObject {

	protected int x;
	protected int y;
	protected Rectangle rectangle;

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Rectangle getRectangle() {
		return rectangle;
	}

	public void setRectangle(Rectangle rectangle) {
		this.rectangle = rectangle;
	}

	public void increaseX(int n) {
		this.x += n;
		rectangle.x += n;
	}
	public void increaseY(int n) {
		this.y += n;
		rectangle.y += n;
	}

}
