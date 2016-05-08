package Gui;

import java.awt.Rectangle;

public class Background {
	Rectangle back1;
	Rectangle back2;
	
	Background(){
		back1=new Rectangle(0,0,484,312);
		back2=new Rectangle(484,0,484,312);
	}
	
	public void updatebg(){
		back1=new Rectangle((int)back1.getX()-4,0,484,312);
		back2=new Rectangle((int)back2.getX()-4,0,484,312);
		if(back1.getX()<=-484)
			back1=new Rectangle(484,0,484,312);
		if(back2.getX()<=-484)
			back2=new Rectangle(484,0,484,312);			
	}

	public Rectangle getBack1() {
		return back1;
	}
	
	public Rectangle getBack2() {
		return back2;
	}
}
