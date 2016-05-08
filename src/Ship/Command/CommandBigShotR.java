package Ship.Command;

import Ship.SpaceShip;

public class CommandBigShotR extends Command{
	@Override
	public SpaceShip run(SpaceShip ship) {
		// TODO Auto-generated method stub
		ship.setWaveFire(false);
		return ship;
	}

}
