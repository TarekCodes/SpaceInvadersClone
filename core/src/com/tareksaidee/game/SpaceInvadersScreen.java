package com.tareksaidee.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

/**
 * Created by tarek on 9/19/2016.
 */
public class SpaceInvadersScreen implements Screen {

    public static final Color BACKGROUND_COLOR = Color.BLACK;
    ExtendViewport spaceInvadersViewport;
    ShapeRenderer renderer;
    Player player;
    Bullets bullets;


    @Override
    public void show() {
        spaceInvadersViewport = new ExtendViewport(Constants.WORLD_SIZE,Constants.WORLD_SIZE);
        renderer = new ShapeRenderer();
        renderer.setAutoShapeType(true);
        player = new Player(spaceInvadersViewport);
        bullets = new Bullets(spaceInvadersViewport);
    }

    @Override
    public void render(float delta) {
        player.update(delta);
        bullets.update(delta,player.position);
        spaceInvadersViewport.apply(true);
        Gdx.gl.glClearColor(BACKGROUND_COLOR.r, BACKGROUND_COLOR.g, BACKGROUND_COLOR.b, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        renderer.setProjectionMatrix(spaceInvadersViewport.getCamera().combined);
        renderer.begin();
        player.render(renderer);
        bullets.render(renderer);
        renderer.end();
    }

    @Override
    public void resize(int width, int height) {
        spaceInvadersViewport.update(width,height,true);
        player.init();
        bullets.init();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        renderer.dispose();
    }

    @Override
    public void dispose() {

    }
}
