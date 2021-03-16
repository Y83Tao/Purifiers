package com.projectgrapes.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.projectgrapes.game.utilities.Transitions;
import com.badlogic.gdx.graphics.Color;

import java.util.HashMap;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;

import static java.lang.StrictMath.round;

public class MainMenu implements Screen {


    Stage menu;
    Batch batch;
    TextButton play, about, reset;
    BitmapFont font;
    TextureAtlas buttonAtlas;
    public static setup game;

    Sprite titleHouse;
    Texture unamedHouse, terminalBackground;

    Skin skin;
    Table options;
    FreeTypeFontGenerator fontGenerator, fontGenerator2, fontGenerator3;
    FreeTypeFontGenerator.FreeTypeFontParameter fontParameter;
    public static HashMap<String, String> factions = new HashMap<>();
    public static HashMap<String, Integer> factionsNum = new HashMap<>();
    public static boolean aboutText;

    boolean ready = false, flash = false;

    TweenManager tweenManager;

    public static Preferences prefs = Gdx.app.getPreferences("save");

    Stage aboutSection;  Label label1;

    public MainMenu(final setup game) {
        this.game = game;
        batch = new SpriteBatch();
        menu = new Stage();
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();

        fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("DisposableDroidBB.ttf"));
        fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        fontParameter.size = (int)(17 * Gdx.graphics.getDensity());
        fontParameter.borderWidth = 0F;
        font = fontGenerator.generateFont(fontParameter);

        buttonAtlas = new TextureAtlas(Gdx.files.internal("Buttons/RetroButtons.pack"));
        skin = new Skin(buttonAtlas);
        Gdx.input.setInputProcessor(menu);

        options = new Table(skin);
        options.setBounds((float) Gdx.graphics.getWidth() / Gdx.graphics.getWidth(), (float) Gdx.graphics.getHeight() / Gdx.graphics.getHeight()  ,
                Gdx.graphics.getWidth(),(float) Gdx.graphics.getHeight() / 2);

        textButtonStyle.up = skin.getDrawable("ButtonNotPressed");
        textButtonStyle.down = skin.getDrawable("ButtonPressed");
        textButtonStyle.font = font;


        if (!prefs.contains("game")){
            play = new TextButton("      New Game     ", textButtonStyle);
            options.add(play).width(600f).height(150f);
            play.pad(15);
            play.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y){
                    prefs.putBoolean("game", true);
                    prefs.putInteger("act", 1);
                    prefs.putInteger("section", 1);
                    prefs.putString("relationship", "");
                    prefs.putBoolean("GeneratePeople", true);
                    prefs.putBoolean("SetupMails", true);
                    prefs.putInteger("MailCount", 0);
                    prefs.putInteger("Gold", 400);
                    prefs.putInteger("Respect", 55);
                    prefs.putInteger("Troops", 100);
                    prefs.putInteger("Resource", 250);
                    prefs.putInteger("GoldRateIncrease",  50 );
                    prefs.putInteger("GoldRateDecrease", 25);
                    prefs.putInteger("ResourceRateIncrease", 25);
                    prefs.putInteger("ResourceRateDecrease", 15);
                    prefs.putInteger("Day", 1);
                    prefs.putInteger("ChoiceAmount", 0);
                    prefs.putString("District", "000000000");
                    prefs.putInteger("landCapacity", 125);
                    prefs.putInteger("landCapacityPrice", 200);
                    prefs.putInteger("residentialSpace", 100);
                    prefs.putInteger("Industries", 5);
                    prefs.putInteger("recreational", 5);
                    prefs.putInteger("wallsHealth", 100);
                    prefs.putInteger("WeaponryPower", 25);
                    prefs.putInteger("MilitaryVehicles", 1);
                    prefs.putInteger("ArmsUpgrades", 5);
                    prefs.putInteger("MercenaryForHire", 10);
                    prefs.putString("Story", "0");
                    prefs.putString("events", "9999");
                    prefs.putBoolean("back", false);
                    prefs.putInteger("currentChoice", 0);
                    prefs.putBoolean("civilWarNoArms", false);
                    prefs.putBoolean("civilWarStatement", true);
                    prefs.putBoolean("civilWarArms", false);
                    prefs.putBoolean("PurifiersDefeated", false);

                    //add faction stuff here --------------------------------------------------------------------------
                    factionSetup("Purifiers", "Enemy", "Gold", "Resource", "false", 0, 0, 10, 5000, "Alive", "false", "The Purifiers");
                    factionSetup("Iron Legion", "Neutral", "Gold", "Resource","false", 50, 75, 5, 500, "Alive","false", "The Iron Legion");
                    factionSetup("Keepers Of Ember", "Enemy", "Resource", "Gold","false", 100, 75, 15, 250, "Alive","false", "The Keepers Of Ember" );
                    factionSetup("Leather Junction", "Neutral", "Gold", "Resource","false", 30, 60, 1, 1500, "Alive","false", "The Leather Junction");
                    factionSetup("The Architects", "Neutral", "Resource", "Gold", "false", 50, 90, 5, 500, "Alive", "false", "The Architects");


                    //add region stuff here ---------------------------------------------------------------------------
                    regionSetup("Dry Refinery", "Leather Junction", 50, "Iron Legion", 100, "Purifiers", 500, "Keepers Of Ember", 50, "Purifiers", 1000, 250, 5 , 0, "idle");
                    regionSetup("The Red Forest", "Purifiers", 50, "Iron Legion", 100, "Purifiers", 500, "Keepers Of Ember", 50, "Purifiers", 1000, 300, 15 , 0, "idle");
                    regionSetup("Ruins Of London", "Iron Legion", 75, "Architects", 250, "Keepers Of Ember", 400, "Purifiers", 200, "Purifiers", 500, 150, 2, 0, "idle");
                    regionSetup("Blackened BadLands", "Keepers Of Ember", 150, "Iron Legion", 200, "Keepers Of Ember", 250, "Iron Legion", 300, "Purifiers", 500, 100, 20, 0, "idle");
                    regionSetup("Alpha CP", "Leather Junction", 50, "Architects", 250, "Keepers Of Ember", 250, "Leather Junction", 500, "Purifiers", 100, 2000, 5, 0, "idle");
                    regionSetup("The Perished Garden", "Iron Legion", 20, "Architects", 250, "Keepers Of Ember", 250, "Purifiers", 500, "Iron Legion", 700, 500, 1, 0, "idle");
                    regionSetup("Omega CP", "Keepers Of Ember", 10, "Architects", 250, "Keepers Of Ember", 350, "Leather Junction", 500, "Purifiers", 100, 250, 10, 0, "idle");
                    regionSetup("The Blue Forest", "Iron Legion", 20, "Architects", 250, "Keepers Of Ember", 250, "Purifiers", 500, "Iron Legion", 700, 500, 5, 0, "idle");
                    regionSetup("Illuminated Shores", "Purifiers", 200, "Architects", 250, "Keepers Of Ember", 500, "Leather Junction", 500, "Purifiers", 1000, 250, 50, 0, "idle");
                    prefs.put(factions);
                    prefs.put(factionsNum);
                    //(String RegionName, String area1, int area1Amount, String area2, int area2Amount, String area3, int area3Amount, String area4, int area4Amount, String area5, int area5Amount, int ResourceAmount, int AmountPer)



                    prefs.flush();
                    System.out.println(prefs.get());
                    System.out.println(prefs.getInteger("act"));
                    StorySetup.state();

                }
            });

        } else if (prefs.contains("game")){
            play = new TextButton("      Continue     ", textButtonStyle);
            options.add(play).width(round((float) Gdx.graphics.getWidth() / 1.75)).height( round((float) Gdx.graphics.getHeight() / 12));
            play.pad(35);
            play.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y){
                    System.out.println(prefs.get());
                    StorySetup.state();
                }

            });

            reset = new TextButton("    Restart    ", textButtonStyle);
            options.row();
            options.add(reset).width(round((float) Gdx.graphics.getWidth() / 1.75)).height( round((float) Gdx.graphics.getHeight() / 12));
            reset.pad(35);
            reset.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y){
                    if (ready){
                        System.out.println(prefs.getBoolean("game"));
                        prefs.clear();
                        prefs.flush();
                        System.out.println(" " + prefs.contains("game"));
                        System.out.println("dafuq " + prefs.contains("act"));
                        System.out.println(prefs.get());
                        game.setScreen(new MainMenu(game));
                    } else {
                        ready = true;
                        reset.setText("Are you sure?");
                    }


                }
            });
        }

        about = new TextButton("     About     ", textButtonStyle);
        about.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y){
                if (!aboutText){
                aboutText = true;
                game.setScreen(new MainMenu(game));}
                else {
                    aboutText = false;
                }
            }
        });

        unamedHouse = new Texture("Background/deer.png");
        terminalBackground = new Texture("TerminalScreen.png");
        titleHouse = new Sprite(new Texture("purifiers symbol.png"));

        tweenManager = new TweenManager();
        Tween.registerAccessor(Sprite.class, new Transitions());
        Tween.set(titleHouse, Transitions.ALPHA).target(0).start(tweenManager);
        Tween.to(titleHouse, Transitions.ALPHA, 2f).target(1f).start(tweenManager);


        options.row();
        options.add(about).width(round((float) Gdx.graphics.getWidth() / 1.75)).height( round((float) Gdx.graphics.getHeight() / 12));

        menu.addActor(options);

        label1 = new Label("Developed By Yun Tao\n" +
                "Software: Android Studio (+Libgdx Extension)\n" +
                "Language: Java\n" +
                "\nComment:\n" +
                "\n\"This is the first mobile game I programmed \nand published online. It took around \n" +
                "three months (having school in the \nway and other programming projects) \n" +
                "which was a satisfying test of my \ncoding abilities. This was more or less a \nhobby." +
                " If there are any complications/errors \nregarding this application, please send a \nreview" +
                "with details on the problem and I will fix \nit as soon as possible!\"\n" +
                "\n" +
                "P.s, thanks for downloading! \n", new Label.LabelStyle(font, Color.WHITE ));
        label1.setPosition((float) Gdx.graphics.getWidth() / 10, (float) round(Gdx.graphics.getHeight() / 2.5));
        label1.setFontScale(0.8f);
        aboutSection = new Stage();
        aboutSection.addActor(label1);
        aboutSection.addAction(Actions.sequence(Actions.alpha(0f), Actions.alpha(1f, 2f)));
    }
    @Override
    public void show() {

    }

    public void factionSetup(String name, String playerStatus, String resourceRich, String resourceNeeds, String tradeStatus, int tradeInput, int tradeOutput, int perUnitFirePower, int troops, String status, String encountered, String keyName){
        factions.put(keyName + " name", name);
        factions.put(keyName + " playerStatus", playerStatus);
        factions.put(keyName + " tradeStatus", tradeStatus);
        factions.put(keyName +  " resourceRich", resourceRich);
        factions.put(keyName + " resourceNeeds", resourceNeeds);
        factions.put(keyName + " status", status);
        factions.put(keyName + " encountered", encountered);
        factionsNum.put(keyName + " troops", troops);
        factionsNum.put(keyName + " perUnitPower", perUnitFirePower);
        factionsNum.put(keyName +" tradeInput", tradeInput);
        factionsNum.put(keyName + " tradeOutput", tradeOutput);
    }
    public void regionSetup(String RegionName, String area1, int area1Amount, String area2, int area2Amount, String area3, int area3Amount, String area4, int area4Amount, String area5, int area5Amount, int ResourceAmount, int AmountPer, int yourTroopCount, String engagement){
        prefs.putString(RegionName + " regionName", RegionName);
        prefs.putString(RegionName + " area1", area1);
        prefs.putString(RegionName + " area2", area2);
        prefs.putString(RegionName + " area3", area3);
        prefs.putString(RegionName + " area4", area4);
        prefs.putString(RegionName + " area5", area5);
        prefs.putInteger(RegionName + " area1Amount", area1Amount);
        prefs.putInteger(RegionName + " area2Amount", area2Amount);
        prefs.putInteger(RegionName + " area3Amount", area3Amount);
        prefs.putInteger(RegionName + " area4Amount", area4Amount);
        prefs.putInteger(RegionName + " area5Amount", area5Amount);

        prefs.putInteger(RegionName + " ResourceAmount", ResourceAmount);
        prefs.putInteger(RegionName + " AmountPer", AmountPer);
        prefs.putInteger(RegionName + " yourTroopCount", yourTroopCount);
        prefs.putString(RegionName + " engagement", engagement);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(197, 176, 88, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        tweenManager.update(delta);
        batch.begin();
        batch.draw(terminalBackground, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();

        if (aboutText){
            aboutSection.act(delta);
            aboutSection.draw();
        }
        else {
            batch.begin();
            titleHouse.setBounds((float) - Gdx.graphics.getWidth() / 30, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            titleHouse.setScale(Gdx.graphics.getDensity() / 4);
            titleHouse.draw(batch);
            batch.end();
        }
        menu.act(delta);
        batch.begin();
        batch.end();
        menu.draw();



    }
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
