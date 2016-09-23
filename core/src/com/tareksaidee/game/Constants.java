package com.tareksaidee.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by tarek on 9/19/2016.
 */
class Constants {
    static final float WORLD_SIZE = 150.0f;
    static final Color BACKGROUND_COLOR = Color.BLACK;


    static final float PLAYER_WIDTH = 12.0f;
    static final int PLAYER_HEIGHT = 14;
    static final Color PLAYER_COLOR = Color.RED;
    static final float PLAYER_MOVEMENT_SPEED = 50.0f;

    static final float BULLET_SPEED = 50.0f;
    static final float ALLOWED_BULLETS = 1;
    static final Color ENEMY_BULLET_COLOR = Color.GREEN;
    static final Color PLAYER_BULLET_COLOR = Color.WHITE;

    static final float ENEMY_SPEED = 100f;
    static final Vector2 ENEMY_DIMENSION = new Vector2(5, 5);
    static final Vector2 ENEMY_OFFSET = new Vector2(10, 10);
    static final Vector2 ENEMY_NUMBER = new Vector2(8, 3);
    static final float ENEMY_PLAYER_DISTANCE = 50;
    static final Color ENEMY_COLOR = Color.GOLDENROD;
    static final int STEP_DOWN_AFTER = 6;

    static final int FONT_SCALE = 450;
}
