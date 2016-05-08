package Ship.Command;

import Ship.SpaceShip;

public class CommandRight extends Command{

	@Override
	public SpaceShip run(SpaceShip ship) {
		// TODO Auto-generated method stub
		ship.moveX(2);
		return ship;
	}


}
