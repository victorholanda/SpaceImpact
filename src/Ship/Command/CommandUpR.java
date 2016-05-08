package Ship.Command;

import Ship.SpaceShip;

public class CommandUpR extends Command{

	@Override
	public SpaceShip run(SpaceShip ship) {
		// TODO Auto-generated method stub
		ship.setMoveY(0);
		return ship;
	}


}
