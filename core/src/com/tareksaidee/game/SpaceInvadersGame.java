package com.tareksaidee.game;

import com.badlogic.gdx.Game;

public class SpaceInvadersGame extends Game {
	@Override
	public void create() {
		setScreen(new SpaceInvadersScreen());
	}
}