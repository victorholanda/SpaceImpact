package Ship.Command;

import Ship.SpaceShip;

public class CommandBigShot extends Command{
	@Override
	public SpaceShip run(SpaceShip ship) {
		// TODO Auto-generated method stub
		ship.setWaveFire(true);
		return ship;
	}

}
