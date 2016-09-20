package com.tareksaidee.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
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

    public Bullets(Viewport viewport){
        this.viewport = viewport;
        init();
    }

    public void init() {
        bulletList = new DelayedRemovalArray<Bullet>(false, 100);
    }

    public void update(float delta, Vector2 playerPos) {
        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && bulletList.size<4){
            Vector2 bulletPos = new Vector2(playerPos.x,Constants.PLAYER_HEIGHT);
            Bullet bullet = new Bullet(bulletPos);
            bulletList.add(bullet);
        }

        for (Bullet bullet : bulletList) {
            bullet.update(delta);
        }

        bulletList.begin();
        for (int i = 0; i < bulletList.size; i++) {
            if (bulletList.get(i).position.y > viewport.getWorldHeight()) {
                bulletList.removeIndex(i);
            }
        }
        bulletList.end();
    }

    public void render(ShapeRenderer renderer) {
        renderer.setColor(Color.PURPLE);
        for (Bullet bullet : bulletList) {
            bullet.render(renderer);
        }
    }
}
