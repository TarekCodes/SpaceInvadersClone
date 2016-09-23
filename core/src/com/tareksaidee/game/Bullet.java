package com.tareksaidee.game;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by tarek on 9/19/2016.
 */
class Bullet {
    Vector2 position;
    boolean goUp;

    Bullet(Vector2 playerPos, boolean goUp) {
        position = playerPos;
        this.goUp = goUp;
    }

    void update(float delta) {
        position.y += Constants.BULLET_SPEED * delta * ((goUp) ? 1 : -1);
    }

    void render(ShapeRenderer shapeRenderer) {
        shapeRenderer.line(position.x, position.y, position.x, position.y + 5);
    }
}
