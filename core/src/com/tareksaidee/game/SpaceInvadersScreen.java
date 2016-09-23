package com.tareksaidee.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import java.util.Random;

/**
 * Created by tarek on 9/19/2016.
 */
public class SpaceInvadersScreen implements Screen {

    ExtendViewport spaceInvadersViewport;
    ShapeRenderer renderer;
    Player player;
    Bullets playerBullets;
    Bullets enemyBullets;
    Enemies enemies;
    int randNum;

    @Override
    public void show() {
        spaceInvadersViewport = new ExtendViewport(Constants.WORLD_SIZE, Constants.WORLD_SIZE);
        renderer = new ShapeRenderer();
        renderer.setAutoShapeType(true);
        player = new Player(spaceInvadersViewport);
        playerBullets = new Bullets(spaceInvadersViewport,true);
        enemies = new Enemies(spaceInvadersViewport);
        enemyBullets = new Bullets(spaceInvadersViewport,false);
    }

    @Override
    public void render(float delta) {
        player.update(delta);
        playerBullets.update(delta, player.position);
        enemies.update(delta);
        randNum = new Random().nextInt(enemies.enemyList.size);
        enemyBullets.update(delta,enemies.enemyList.get(randNum).position);
        if(enemies.hitByBullet(playerBullets.getBulletPos())) {
            playerBullets.init();
        }
        spaceInvadersViewport.apply(true);
        Gdx.gl.glClearColor(Constants.BACKGROUND_COLOR.r, Constants.BACKGROUND_COLOR.g,
                Constants.BACKGROUND_COLOR.b, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        renderer.setProjectionMatrix(spaceInvadersViewport.getCamera().combined);
        renderer.begin();
        player.render(renderer);
        playerBullets.render(renderer);
        enemies.render(renderer);
        enemyBullets.render(renderer);
        renderer.end();
    }

    @Override
    public void resize(int width, int height) {
        spaceInvadersViewport.update(width, height, true);
        player.init();
        playerBullets.init();
        enemies.init();
        enemyBullets.init();
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
