package com.tareksaidee.game;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by tarek on 9/20/2016.
 */

class Enemies {
    DelayedRemovalArray<Enemy> enemyList;
    private Viewport viewport;
    int score;

    Enemies(Viewport viewport) {
        this.viewport = viewport;
        init();
    }

    void init() {
        if (enemyList != null) {
            if (enemyList.size != 0)
                score = 0;
        }
        enemyList = new DelayedRemovalArray<Enemy>(false, (int) (Constants.ENEMY_NUMBER.x
                * Constants.ENEMY_NUMBER.y));
        for (int i = 0; i < Constants.ENEMY_NUMBER.x; i++) {
            for (int x = 0; x < Constants.ENEMY_NUMBER.y; x++) {
                enemyList.add(new Enemy(viewport, new Vector2(Constants.ENEMY_OFFSET.x * i,
                        Constants.ENEMY_OFFSET.y * x)));
            }
        }
    }

    boolean hitByBullet(Vector2 bulletPos) {
        enemyList.begin();
        boolean hit = false;
        for (int i = 0; i < enemyList.size; i++) {
            if (enemyList.get(i).position.x <= bulletPos.x &&
                    enemyList.get(i).position.x + Constants.ENEMY_DIMENSION.x >= bulletPos.x &&
                    ((enemyList.get(i).position.y <= bulletPos.y &&
                            enemyList.get(i).position.y + Constants.ENEMY_DIMENSION.y >= bulletPos.y)
                            || (enemyList.get(i).position.y <= bulletPos.y + 5 &&
                            enemyList.get(i).position.y + Constants.ENEMY_DIMENSION.y >= bulletPos.y + 5))) {
                enemyList.removeIndex(i);
                hit = true;
                score++;
                break;
            }
        }
        enemyList.end();
        if (enemyList.size == 0)
            init();
        return hit;
    }

    void update(float delta) {
        for (Enemy enemy : enemyList) {
            enemy.update(delta);
        }
    }

    void render(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(Constants.ENEMY_COLOR);
        for (Enemy enemy : enemyList) {
            enemy.render(shapeRenderer);
        }
    }
}
