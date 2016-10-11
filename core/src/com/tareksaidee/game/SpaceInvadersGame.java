package com.tareksaidee.game;

import com.badlogic.gdx.Game;

public class SpaceInvadersGame extends Game {
	@Override
	public void create() {
		setScreen(new IntroScreen(this));
	}

	public void showIntroScreen(){
		setScreen(new SpaceInvadersScreen());
	}
}