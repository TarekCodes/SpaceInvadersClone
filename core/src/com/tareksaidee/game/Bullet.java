package com.tareksaidee.game;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by tarek on 9/19/2016.
 */
public class Bullet {
    Vector2 position;

    public Bullet(Vector2 playerPos){
        position = playerPos;
    }

    public void update(float delta){
        position.y+=Constants.BULLET_SPEED*delta;
    }

    public void render(ShapeRenderer shapeRenderer){
        shapeRenderer.line(position.x,position.y,position.x,position.y+5);
    }
}
