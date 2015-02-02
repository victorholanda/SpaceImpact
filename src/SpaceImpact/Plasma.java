package SpaceImpact;

import java.awt.Rectangle;

public class Plasma {
	
	int x = 0;
	int y = 0;
	int i = 0;
	Rectangle plasmaHB;
	
	
    public Plasma(int x, int y) {
    	this.x = x;
    	this.y = y;
    	plasmaHB = new Rectangle(this.x,this.y,8,3);
    }
    
    public Rectangle getPlasmaHB(){
    	return plasmaHB;
    }
}