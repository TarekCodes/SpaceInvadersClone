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
    int cycles;

    Enemy(Viewport viewport, Vector2 offset) {
        this.viewport = viewport;
        speed = Constants.ENEMY_SPEED;
        this.offset = offset;
        init();
    }

    private void init() {
        position = new Vector2(offset.x + 1, viewport.getWorldHeight() - 25 - offset.y);
        cycles = 0;
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
            return true;
        }
        return false;
    }

    private void checkBounds() {
        if (position.x - offset.x <= 1 || position.x + 5 + (70 - offset.x) > viewport.getWorldWidth() - 1) {
            speed = (-speed);
            cycles++;
        }
    }

    void render(ShapeRenderer shapeRenderer) {
        shapeRenderer.rect(position.x, position.y, Constants.ENEMY_DIMENSION.x,
                Constants.ENEMY_DIMENSION.y);
    }
}
