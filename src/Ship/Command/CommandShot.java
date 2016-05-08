package Ship.Command;

import Ship.SpaceShip;

public class CommandShot extends Command {

	@Override
	public SpaceShip run(SpaceShip ship) {
		// TODO Auto-generated method stub
		ship.setFire(true);
		ship.setBlockFire(true);
		return ship;
	}

}
