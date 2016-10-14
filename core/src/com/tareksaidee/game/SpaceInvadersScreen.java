package com.tareksaidee.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.util.Random;

/**
 * Created by tarek on 9/19/2016.
 */
class SpaceInvadersScreen extends InputAdapter implements Screen {

    ExtendViewport spaceInvadersViewport;
    ShapeRenderer renderer;
    ScreenViewport textViewport;
    Batch batch;
    BitmapFont font;
    Player player;
    Bullets playerBullets;
    Bullets enemyBullets;
    Enemies enemies;
    int randNum;
    int temp;
    float delt;

    @Override
    public void show() {
        spaceInvadersViewport = new ExtendViewport(Constants.WORLD_SIZE, Constants.WORLD_SIZE);
        renderer = new ShapeRenderer();
        renderer.setAutoShapeType(true);
        textViewport = new ScreenViewport();
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        player = new Player(spaceInvadersViewport);
        playerBullets = new Bullets(spaceInvadersViewport, true);
        enemies = new Enemies(spaceInvadersViewport);
        enemyBullets = new Bullets(spaceInvadersViewport, false);
        Gdx.input.setInputProcessor(this);
        temp = (spaceInvadersViewport.getScreenWidth() / 3) * 2;
    }

    @Override
    public void render(float delta) {
        delt = delta;
        player.update(delta);
        playerBullets.update(delta, player.position, enemies.currentLevel);
        enemies.update(delta);
        randNum = new Random().nextInt(enemies.enemyList.size);
        enemyBullets.update(delta, enemies.enemyList.get(randNum).position, enemies.currentLevel);
        if (enemies.hitByBullet(playerBullets.getBulletPos())) {
            playerBullets.init();
        }
        if (player.hitByBullet(enemyBullets.getBulletPos())) {
            resetGame();
        }
        touchControl(delta);
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
        writeStats();
    }

    @Override
    public void resize(int width, int height) {
        spaceInvadersViewport.update(width, height, true);
        textViewport.update(width, height, true);
        font.getData().setScale(Math.min(width, height) / Constants.FONT_SCALE);
        resetGame();
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
        batch.dispose();
    }

    @Override
    public void dispose() {

    }

    private void resetGame() {
        player.init();
        playerBullets.init();
        enemies.init();
        enemyBullets.init();
    }


    private void writeStats() {
        textViewport.apply();
        batch.setProjectionMatrix(textViewport.getCamera().combined);
        batch.begin();
        font.draw(batch, "Level: " + (enemies.currentLevel + 1), 20, textViewport.getWorldHeight() - 30);
        font.draw(batch, "Score: " + enemies.score, 20, textViewport.getWorldHeight() - 10);
        batch.end();
    }

    private void touchControl(float delta) {
        for (int i = 0; i < 20; i++) {
            if (Gdx.input.isTouched(i)) {
                if (Gdx.input.getX(i) > spaceInvadersViewport.getScreenWidth() / 2) {
                    if (Gdx.input.getY(i) < (spaceInvadersViewport.getScreenHeight() / 4)*3)
                        player.moveLeft(delta);
                    else
                        player.moveRight(delta);
                }
                else{
                    playerBullets.fireBullet(player.position, enemies.currentLevel);
                }
            }
        }
    }
}
