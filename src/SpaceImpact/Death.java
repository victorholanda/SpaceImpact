package SpaceImpact;

import java.awt.Point;
import java.awt.Rectangle;

public class Death {
	Rectangle rectRestart;
	Point restart;
	Rectangle mouse;

	public Death() {
		restart = new Point(105, 214);
		rectRestart = new Rectangle(restart.x, restart.y, 280, 35);
		mouse = new Rectangle(0, 0, 3, 3);
	}

	public void updateMouse(int x, int y) {
		mouse.x = x;
		mouse.y = y;
	}

	public Rectangle getRectRestart() {
		return rectRestart;
	}

	public Rectangle getMouse() {
		return mouse;
	}
}
