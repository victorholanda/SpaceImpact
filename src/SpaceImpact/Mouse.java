package SpaceImpact;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputListener;


class Mouse implements MouseInputListener {

	Rectangle mouse;
	private boolean click;
	
	Mouse(){
		mouse = new Rectangle(0,0,1,1);
	}
	
	public void mouseMoved(MouseEvent e) {
		mouse = new Rectangle(e.getX(),e.getY(),10,10);
    }

    public Rectangle getMousePos() {
        return mouse;
    }
    
    public boolean Click(){
    	return click;
    }
    
    public void ClickSet(boolean set){
    	this.click=set;
    }
    
	public void mousePressed(MouseEvent e) {
		click=true;
	}
	
	public void mouseReleased(MouseEvent e) {
		click=false;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}
	
    @Override	//azioni non implementate
	public void mouseDragged(MouseEvent arg0) {
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		
	}
}

