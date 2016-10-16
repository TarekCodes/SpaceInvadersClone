package com.tareksaidee.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FillViewport;

/**
 * Created by tarek on 10/15/2016.
 */

public class GameOverScreen extends InputAdapter implements Screen {

    SpaceInvadersGame game;
    BitmapFont font;
    SpriteBatch batch;
    FillViewport viewport;

    public GameOverScreen(SpaceInvadersGame game) {
        this.game = game;
    }

    @Override
    public void show() {
        viewport = new FillViewport(Constants.WORLD_SIZE,Constants.WORLD_SIZE);
        batch = new SpriteBatch();
        Gdx.input.setInputProcessor(this);
        font = new BitmapFont();
        font.getData().setScale(.5f);
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
    }

    @Override
    public void render(float delta) {
        viewport.apply();
        Gdx.gl.glClearColor(Constants.BACKGROUND_COLOR.r, Constants.BACKGROUND_COLOR.g, Constants.BACKGROUND_COLOR.b, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();
        font.setColor(Color.RED);
        font.draw(batch, Constants.GAME_OVER, Constants.WORLD_SIZE / 2,
                Constants.WORLD_SIZE / 2, 0, Align.center, false);
        font.setColor(Color.WHITE);
        font.draw(batch, Constants.PLAY_AGAIN, Constants.WORLD_SIZE / 2,
                Constants.WORLD_SIZE / 2, 0, Align.center, false);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width,height,true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        game.showGameScreen();
        return true;
    }
}
