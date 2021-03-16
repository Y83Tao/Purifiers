package com.projectgrapes.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

public class setup extends Game {


    @Override
    public void create() {
        this.setScreen(new MainMenu(this));
        //StorySetup.dialogue("pples are big...\nand this big penis can suck meoff\nlmaolololol");
        //this.setScreen(new projectGrapes(this, "lowddadafwaefwadawl", "test", "testing", "lmao"));

    }
    @Override
    public void render () {
        super.render();
    }
}
