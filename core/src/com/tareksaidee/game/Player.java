package com.tareksaidee.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by tarek on 9/19/2016.
 */
public class Player {
    Vector2 position;
    Viewport viewport;

    public Player(Viewport viewport){
        this.viewport = viewport;
        init();
    }

    public void init(){
        position = new Vector2(viewport.getWorldWidth()/2,Constants.PLAYER_HEIGHT);
        System.out.println(position.x);
    }

    public void update(float delta){
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT))
            position.x-=(delta* Constants.PLAYER_MOVEMENT_SPEED);
        else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT))
                position.x+=(delta*Constants.PLAYER_MOVEMENT_SPEED);
        checkBounds();
    }

    private void checkBounds(){
        if(position.x-Constants.PLAYER_WIDTH/2<0)
            position.x=Constants.PLAYER_WIDTH/2;
        if(position.x+Constants.PLAYER_WIDTH/2>viewport.getWorldWidth())
            position.x=viewport.getWorldWidth()-Constants.PLAYER_WIDTH/2;
    }

    public void render(ShapeRenderer renderer) {
        renderer.setColor(Constants.PLAYER_COLOR);
        renderer.set(ShapeRenderer.ShapeType.Filled);
        renderer.triangle(position.x-Constants.PLAYER_WIDTH/2,1,position.x+Constants.PLAYER_WIDTH/2,1,
                position.x,Constants.PLAYER_HEIGHT, Color.WHITE,Color.BLUE,Color.PINK);
    }
}
