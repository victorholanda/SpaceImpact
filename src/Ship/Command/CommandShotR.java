package Ship.Command;

import Ship.SpaceShip;

public class CommandShotR extends Command {

	@Override
	public SpaceShip run(SpaceShip ship) {
		// TODO Auto-generated method stub
		ship.setFire(false);
		ship.setBlockFire(false);
		return ship;
	}

}
