package com.projectgrapes.game;

import com.badlogic.gdx.Screen;

public class ActScene implements Screen {

    setup game; String choices;


    public ActScene(final setup game, String choice){
        choices = choice;
        StorySetup.actScene = false;
        StorySetup.state();
    }

    @Override
    public void show() {

    }
    @Override
    public void render(float delta) {}
    @Override
    public void resize(int width, int height) {

    }
    @Override
    public void pause() {

    }
    @Override
    public void resume() {

    }
    @Override
    public void hide() {

    }
    @Override
    public void dispose() {

    }
}
