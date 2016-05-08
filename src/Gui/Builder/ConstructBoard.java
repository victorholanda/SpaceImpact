package Gui.Builder;

import Gui.Board;

public class ConstructBoard {
	static Builder builder;

	public static Board simpleBoarder() {
		builder = new BuilderSimpleBoarder();
		builder.createBorder();
		builder.buildCityLife();
		builder.buildMAXscore();
		builder.buildScore();
		builder.buildScrHeight();
		builder.buildScrWidth();
		builder.buildSpaceShipAnimation();
		builder.buildSpawnFrequency();
		builder.builReloadTime();
		return builder.get();

	}

}
