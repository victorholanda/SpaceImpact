package Ship.Command;

import Ship.SpaceShip;

public class CommandKiller extends Command{

	@Override
	public SpaceShip run(SpaceShip ship) {
		// TODO Auto-generated method stub
		ship.setDeath(true);
		return ship;
	}

}
