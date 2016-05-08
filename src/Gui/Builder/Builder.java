package Gui.Builder;

import Gui.Board;

public abstract class Builder {
	Board board;

	public void createBorder() {
		board = new Board();
		board.setInMenu(true);
		board.setReloading(false);
		board.setLinkClick(true);
	}
	
	public Board get() {
		return board;
	}

	public abstract void buildScore();

	public abstract void buildSpawnFrequency();

	public abstract void buildScrWidth();

	public abstract void buildScrHeight();

	public abstract void buildCityLife();

	public abstract void builReloadTime();

	public abstract void buildMAXscore();

	public abstract void buildSpaceShipAnimation();
}
