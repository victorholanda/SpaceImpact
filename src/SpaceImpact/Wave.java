package SpaceImpact;

import java.awt.Rectangle;

public class Wave {
	
	int x;
	int y;
	Rectangle waveHB;
	
	
	public Wave(int x, int y){
		this.x = x;
		this.y = y;
		waveHB = new Rectangle(this.x,this.y,1,350);
	}


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


	public Rectangle getWaveHB() {
		return waveHB;
	}

}
