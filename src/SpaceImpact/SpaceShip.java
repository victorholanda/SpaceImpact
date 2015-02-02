package SpaceImpact;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class SpaceShip {

    int x;
    int y;
    int life;
    int movementx = 0;
    int movementy = 0;
    boolean fire = false;
    boolean blockFire = true;
    boolean waveFire = false;
    boolean pause= false;
    Rectangle spaceShipHB;
    boolean death;
    
    public SpaceShip(int scrHeight, int scrWidth) {
        x = 10;
        y = (scrHeight-40)/2;
        life = 3;
    	spaceShipHB=new Rectangle(x,y,30,20);
    }

    public void move(){
    	x+=movementx;
    	y+=movementy;
    	spaceShipHB.x+=movementx;
    	spaceShipHB.y+=movementy;
    	
    	if (x < 5 ){
    		x = 5;
    		spaceShipHB.x = 5;
    	}
    	if (x > 370){
    		x = 370;
    		spaceShipHB.x = 370;
    	}
    	if (y < 25){
    		y = 25;
    		spaceShipHB.y = 25;
    	}
    	if (y > 298){
    		y = 298;
    		spaceShipHB.y = 298;
    	}
    		
    }
    
    

	public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();
        
        if (key == KeyEvent.VK_P) {
            if(pause)
            	pause=false;
            else
            	pause=true;
        }
            	
        if (key == KeyEvent.VK_A) {
            movementx=-2;
        }
        if (key == KeyEvent.VK_D) {
        	movementx=2;            
        }
        if (key == KeyEvent.VK_W) {
        	movementy=-2;
        }
        if (key == KeyEvent.VK_S) {
        	movementy=2;
        }
        if (key == KeyEvent.VK_K) {
        	death = true;
        }
        if (key == KeyEvent.VK_UP) {
        	if(blockFire == false){
        		blockFire = true;
        		fire = true;
        	}
        }
        
        if (key == KeyEvent.VK_DOWN) {
       		waveFire = true;
        }
	}
	
	public void keyReleased(KeyEvent e) {
            	
		int key = e.getKeyCode();
        if (key == KeyEvent.VK_A) {
        	movementx = 0; 
        }
        if (key == KeyEvent.VK_D) {
        	movementx = 0; 
        }
        if (key == KeyEvent.VK_W) {
        	movementy = 0; 
        }
        if (key == KeyEvent.VK_S) {
        	movementy = 0;
        }       
        if (key == KeyEvent.VK_UP) {
        	fire = false;
        	blockFire = false;
        }    
        if (key == KeyEvent.VK_DOWN) {
          	waveFire = false;
        }     
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public boolean getFire() {
		if (!(blockFire)){
		return true;
		}
		else{
			return false;
		}
	}
	public boolean isDeath(){
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
	
	public Rectangle getSpaceShipHB(){
		return spaceShipHB;
	}

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}
	
	
	
	
	
}