package com.tareksaidee.game;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by tarek on 9/20/2016.
 */

public class Enemy {
    private Viewport viewport;
    public Vector2 position;
    private float speed;
    private Vector2 offset;
    public int cycles;

    public Enemy(Viewport viewport, Vector2 offset) {
        this.viewport = viewport;
        speed = Constants.ENEMY_SPEED;
        this.offset = offset;
        init();
    }

    public void init() {
        position = new Vector2(offset.x + 1, viewport.getWorldHeight() - 25 - offset.y);
        cycles = 0;
    }

    public void update(float delta) {
        position.x += speed * delta;
        checkBounds();
        stepDown();
    }

    private void stepDown() {
        if (cycles == 4 && position.y > Constants.ENEMY_PLAYER_DISTANCE - offset.y) {
            position.y -= Constants.ENEMY_OFFSET.y;
            cycles = 0;
        }
    }

    private void checkBounds() {
        if (position.x - offset.x <= 1 || position.x + 5 + (70 - offset.x) > viewport.getWorldWidth() - 1) {
            speed = (-speed);
            cycles++;
        }
    }

    public void render(ShapeRenderer shapeRenderer) {
        shapeRenderer.rect(position.x, position.y, Constants.ENEMY_DIMENSION.x,
                Constants.ENEMY_DIMENSION.y);
    }
}
