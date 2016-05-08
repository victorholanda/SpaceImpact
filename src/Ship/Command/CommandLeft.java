package Ship.Command;

import Ship.SpaceShip;

public class CommandLeft extends Command{

	@Override
	public SpaceShip run(SpaceShip ship) {
		// TODO Auto-generated method stub
		ship.moveX(-2);
		return ship;
	}

}
