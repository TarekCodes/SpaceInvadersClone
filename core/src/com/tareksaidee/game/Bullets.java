package com.tareksaidee.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by tarek on 9/19/2016.
 */
public class Bullets {
    DelayedRemovalArray<Bullet> bulletList;
    Viewport viewport;
    boolean goUp;

    public Bullets(Viewport viewport, boolean state) {
        this.viewport = viewport;
        goUp = state;
        init();
    }

    public void init() {
        bulletList = new DelayedRemovalArray<Bullet>(false, 10);
    }

    public void update(float delta, Vector2 sourcePos) {
        if ((Gdx.input.isKeyPressed(Input.Keys.SPACE) && bulletList.size < Constants.ALLOWED_BULLETS && goUp)
                || (!goUp && bulletList.size < Constants.ALLOWED_BULLETS)) {
            Vector2 bulletPos = new Vector2(sourcePos.x, sourcePos.y);
            Bullet bullet = new Bullet(bulletPos, goUp);
            bulletList.add(bullet);
        }

        for (Bullet bullet : bulletList) {
            bullet.update(delta);
        }

        bulletList.begin();
        for (int i = 0; i < bulletList.size; i++) {
            if (bulletList.get(i).position.y > viewport.getWorldHeight() || bulletList.get(i).position.y < 0) {
                bulletList.removeIndex(i);
            }
        }
        bulletList.end();
    }

    public Vector2 getBulletPos() {
        if (bulletList.size != 0)
            return bulletList.get(0).position;
        else
            return new Vector2(0, 0);
    }

    public void render(ShapeRenderer renderer) {
        renderer.setColor(((goUp)?Constants.PLAYER_BULLET_COLOR:Constants.ENEMY_BULLET_COLOR));
        for (Bullet bullet : bulletList) {
            bullet.render(renderer);
        }
    }
}
