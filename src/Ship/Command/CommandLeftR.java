package Ship.Command;

import Ship.SpaceShip;

public class CommandLeftR extends Command{

	@Override
	public SpaceShip run(SpaceShip ship) {
		// TODO Auto-generated method stub
		ship.setMoveX(0);
		return ship;
	}

}
