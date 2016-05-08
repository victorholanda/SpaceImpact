package Ship;

import java.awt.Rectangle;

import Object.Ship;

public class SpaceShip extends Ship {

	private int moveX = 0;
	private int moveY = 0;
	private boolean fire = false;
	private boolean blockFire = true;
	private boolean waveFire = false;
	private boolean pause = false;
	private boolean death;

	public SpaceShip(int scrHeight, int scrWidth) {
		x = 10;
		y = (scrHeight - 40) / 2;
		life = 3;
		rectangle = new Rectangle(x, y, 30, 20);
	}

	public void move() {
		increaseX(moveX);
		increaseY(moveY);
		if (x < 5) {
			x = 5;
			rectangle.x = 5;
		}
		if (x > 370) {
			x = 370;
			rectangle.x = 370;
		}
		if (y < 25) {
			y = 25;
			rectangle.y = 25;
		}
		if (y > 298) {
			y = 298;
			rectangle.y = 298;
		}

	}

	public boolean getFire() {
		return !blockFire;
	}

	public boolean isDeath() {
		return death;
	}

	public void setBlockFire(boolean blockFire) {
		this.blockFire = blockFire;
	}

	public boolean getWaveFire() {
		return waveFire;
	}

	public boolean getPause() {
		return pause;
	}

	public boolean isFire() {
		return fire;
	}

	public void setFire(boolean fire) {
		this.fire = fire;
	}

	public void setDeath(boolean death) {
		this.death = death;
	}

	public int getMoveX() {
		return moveX;
	}

	public void setMoveX(int moveX) {
		this.moveX = moveX;
	}

	public int getMoveY() {
		return moveY;
	}

	public void setMoveY(int moveY) {
		this.moveY = moveY;
	}

	public boolean isBlockFire() {
		return blockFire;
	}

	public void setWaveFire(boolean waveFire) {
		this.waveFire = waveFire;
	}

	public void setPause(boolean pause) {
		this.pause = pause;
	}

	public void moveX(int length) {
		this.moveX += length;
	}

	public void moveY(int length) {
		this.moveY += length;
	}

}