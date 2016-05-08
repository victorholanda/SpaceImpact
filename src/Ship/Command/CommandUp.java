package Ship.Command;

import Ship.SpaceShip;

public class CommandUp extends Command{

	@Override
	public SpaceShip run(SpaceShip ship) {
		// TODO Auto-generated method stub
		ship.moveY(-2);
		return ship;
	}


}
