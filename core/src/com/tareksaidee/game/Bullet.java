package com.tareksaidee.game;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by tarek on 9/19/2016.
 */
class Bullet {
    Vector2 position;
    boolean goUp;
    float speed;

    Bullet(Vector2 playerPos, boolean goUp, int level) {
        position = playerPos;
        this.goUp = goUp;
        speed = Constants.BULLET_SPEED + (level * Constants.ENEMY_BULLET_LEVEL_FACTOR *((goUp)?0:1));
    }

    void update(float delta) {
        position.y += speed * delta * ((goUp) ? 1 : -1);
    }

    void render(ShapeRenderer shapeRenderer) {
        shapeRenderer.line(position.x, position.y, position.x, position.y + 5);
    }
}
