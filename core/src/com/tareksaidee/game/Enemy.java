package com.tareksaidee.game;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by tarek on 9/20/2016.
 */

class Enemy {
    private Viewport viewport;
    Vector2 position;
    private float speed;
    private Vector2 offset;
    private int cycles;
    private int cyclesCount;
    private int level;

    Enemy(Viewport viewport, Vector2 offset, int level) {
        this.viewport = viewport;
        this.offset = offset;
        this.level = level;
        init();
    }

    private void init() {
        position = new Vector2(offset.x + 5, viewport.getWorldHeight() - 20 - offset.y);
        cycles = 0;
        speed = Constants.ENEMY_SPEED + (Constants.ENEMY_SPEED_LEVEL_FACTOR * level);
        cyclesCount = 0;
    }

    void update(float delta) {
        position.x += speed * delta;
        checkBounds();
        stepDown();
    }

    private boolean stepDown() {
        if (cycles == Constants.STEP_DOWN_AFTER && position.y > Constants.ENEMY_PLAYER_DISTANCE - offset.y) {
            cycles = 0;
            position.y -= Constants.ENEMY_OFFSET.y;
            cyclesCount++;
            return true;
        }
        return false;
    }

    private void checkBounds() {
        if (position.x - offset.x <= 1.5f || position.x + Constants.ENEMY_DIMENSION.x
                + ((Constants.ENEMY_NUMBER.x * 10) - 10 - offset.x) >
                viewport.getWorldWidth() - 1.5f) {
            speed = (-speed);
            cycles++;
        }
    }

    void render(ShapeRenderer shapeRenderer) {
        shapeRenderer.rect(position.x, position.y, Constants.ENEMY_DIMENSION.x,
                Constants.ENEMY_DIMENSION.y);
    }
}
