package SpaceImpact;

import java.awt.Point;
import java.awt.Rectangle;

public class Menu {
    Rectangle rectCredits;
	Point credits;
    Rectangle rectButton;
	Point button;
	Rectangle rectGameLogo;
	Point gameLogo;
	Rectangle rectLogo;
	Point logo;
	Rectangle mouse;
	
	Menu(){
		credits=new Point (267,122);
		rectCredits=new Rectangle(credits.x,credits.y,80,40);
		button=new Point (142,213);
		rectButton= new Rectangle(button.x,button.y,200,35);
		gameLogo=new Point (137,22);
		rectGameLogo= new Rectangle(gameLogo.x,gameLogo.y,210,100);
		logo=new Point (420,248);
		rectLogo= new Rectangle(logo.x,logo.y,64,64);
		mouse=new Rectangle(0,0,3,3);
	}
	
	public void updateMouse(int x,int y){
		mouse.x=x;
		mouse.y=y;
	}
	
	public Rectangle getRectMouse() {
		return mouse;
	}	
	
	public Rectangle getRectButton() {
		return rectButton;
	}

	public Rectangle getRectGameLogo() {
		return rectGameLogo;
	}
	
	public Rectangle getRectLogo() {
		return rectLogo;
	}
	
	public Rectangle getRectCredits() {
		return rectCredits;
	}

}
