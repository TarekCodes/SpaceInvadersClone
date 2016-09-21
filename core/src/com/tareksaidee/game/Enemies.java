package com.tareksaidee.game;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by tarek on 9/20/2016.
 */

public class Enemies {
    DelayedRemovalArray<Enemy> enemyList;
    Viewport viewport;

    public Enemies(Viewport viewport) {
        this.viewport = viewport;
        init();
    }

    public void init() {
        enemyList = new DelayedRemovalArray<Enemy>(false, (int) (Constants.ENEMY_NUMBER.x
                * Constants.ENEMY_NUMBER.y));
        for (int i = 0; i < Constants.ENEMY_NUMBER.x; i++) {
            for (int x = 0; x < Constants.ENEMY_NUMBER.y; x++) {
                enemyList.add(new Enemy(viewport, new Vector2(Constants.ENEMY_OFFSET.x * i,
                        Constants.ENEMY_OFFSET.y * x)));
            }
        }
    }

    public void update(float delta) {
        for (Enemy enemy : enemyList) {
            enemy.update(delta);
        }
    }

    public void render(ShapeRenderer shapeRenderer) {
        for (Enemy enemy : enemyList) {
            enemy.render(shapeRenderer);
        }
    }
}
