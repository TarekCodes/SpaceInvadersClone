package com.tareksaidee.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by tarek on 9/19/2016.
 */
class Player {
    Vector2 position;
    private Viewport viewport;

    Player(Viewport viewport) {
        this.viewport = viewport;
        init();
    }

    void init() {
        position = new Vector2(viewport.getWorldWidth() / 2, Constants.PLAYER_HEIGHT);
    }

    void update(float delta) {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
            moveLeft(delta);
        else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            moveRight(delta);
        checkBounds();
    }

    private void checkBounds() {
        //".1" makes enemy bullet collision work properly when player touching left side of screen
        if (position.x - Constants.PLAYER_WIDTH / 2 < 0)
            position.x = (Constants.PLAYER_WIDTH / 2) + .1f;
        if (position.x + Constants.PLAYER_WIDTH / 2 > viewport.getWorldWidth())
            position.x = viewport.getWorldWidth() - Constants.PLAYER_WIDTH / 2;
    }

    boolean hitByBullet(Vector2 bulletPos) {
        boolean hit = false;
        if (position.x - Constants.PLAYER_WIDTH / 2 <= bulletPos.x
                && position.x + Constants.PLAYER_WIDTH / 2 >= bulletPos.x
                && ((1 <= bulletPos.y && Constants.PLAYER_HEIGHT >= bulletPos.y) || (1 <= bulletPos.y + 5
                && Constants.PLAYER_HEIGHT >= bulletPos.y + 5)))
            hit = true;
        return hit;
    }

    void render(ShapeRenderer renderer) {
        renderer.setColor(Constants.PLAYER_COLOR);
        renderer.set(ShapeRenderer.ShapeType.Filled);
        renderer.triangle(position.x - Constants.PLAYER_WIDTH / 2, 1, position.x + Constants.PLAYER_WIDTH / 2, 1,
                position.x, Constants.PLAYER_HEIGHT, Color.SKY, Color.RED, Color.GRAY);
    }

    void moveLeft(float delta){
        position.x -= (delta * Constants.PLAYER_MOVEMENT_SPEED);
    }

    void moveRight(float delta){
        position.x += (delta * Constants.PLAYER_MOVEMENT_SPEED);
    }
}
