package Ship.Command;

import Ship.SpaceShip;

public class CommandRightR extends Command{

	@Override
	public SpaceShip run(SpaceShip ship) {
		// TODO Auto-generated method stub
		ship.setMoveX(0);
		return ship;
	}


}
