package Ship.Command;

import Ship.SpaceShip;

public class CommandPause extends Command {

	@Override
	public SpaceShip run(SpaceShip ship) {
		// TODO Auto-generated method stub
		boolean pause = ship.getPause();
		ship.setPause(!pause);
		return ship;
	}


}
