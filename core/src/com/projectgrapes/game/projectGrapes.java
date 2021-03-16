package com.projectgrapes.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.projectgrapes.game.Resources.Days;
import com.projectgrapes.game.Resources.Manager;
import com.projectgrapes.game.Resources.Resource;
import com.projectgrapes.game.Resources.miscellaneousStats;
import com.projectgrapes.game.Story.ActOne;

import static com.projectgrapes.game.MainMenu.prefs;
import static com.projectgrapes.game.StorySetup.state;
import static com.projectgrapes.game.StorySetup.transcript;
import static java.lang.StrictMath.round;

public class projectGrapes implements Screen {

	SpriteBatch batch;
	Texture img, notification;
	Stage stage1, stage2;
	public static setup game;
	public static boolean choiceAmountChange = false;
	setup direct = new setup();
	TextButton choice1, choice2, choice3, choice4, continueButton,
	mapActions, peopleActions, Settings, map, transcripts;
	BitmapFont font;
	TextureAtlas buttonAtlas;
	Skin skin;
	Table table1, table2;
	FreeTypeFontGenerator fontGenerator;
	FreeTypeFontGenerator.FreeTypeFontParameter fontParameter;
	Texture terminalBackground, icons;
	Resource resourceStats = new Resource();
	miscellaneousStats moreStats = new miscellaneousStats();
	Manager setup = new Manager();
	Days day = new Days();
	InputMultiplexer multipleStageTouch;



	public float TIMEPERCHAR = 0.055f; //play with this for dif speeds
	private float ctimeperchar = 0;
	private int numchars = 0;
	private boolean complete = false;
	public static boolean notif = false;

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


		batch.begin();
		batch.draw(terminalBackground, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		font.draw(batch,"Gold : " + resourceStats.getGold() + " (" +(resourceStats.getGoldRateIncrease() - resourceStats.getGoldRateDecrease())+")\n" +
				"Respect : " + resourceStats.getRespect() + "% (" + resourceStats.getRespectIncrease() +")\n" +
				"Troops : " + resourceStats.getTroops() +"\n"+
				"Resources : " + resourceStats.getResource() + " (" + (resourceStats.getResourceRateIncrease() - resourceStats.getResourceRateDecrease())+")" ,
				(float) Gdx.graphics.getWidth() / 10,round(Gdx.graphics.getHeight() / 1.165));

		font.draw(batch, "Day " + prefs.getInteger("Day"), (float) round(Gdx.graphics.getWidth() / 2.1), round(Gdx.graphics.getHeight() / 1.188));
		font.draw(batch, "Wall Integrity  \n   " + prefs.getInteger("wallsHealth") + "|100", (float)round( Gdx.graphics.getWidth() / 1.5 ), round(Gdx.graphics.getHeight() / 1.17));
		batch.end();

        for (String key : Manager.mailKeys.keySet()) {
            if (key.contains("[N]")) {
                batch.begin();
                batch.draw(notification,(float) Gdx.graphics.getWidth() / 11 , (float) (Gdx.graphics.getHeight() / 8 ), (int)(35 * Gdx.graphics.getDensity()) , (int)(35 * Gdx.graphics.getDensity()));
                batch.end();
            }
        }

		stage2.act(delta);
		stage2.draw();
		batch.begin();
		batch.draw(icons, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		batch.end();

		batch.begin();
		String dialogue = transcript;
		if(numchars<dialogue.length()){
			ctimeperchar+=delta;
			if(ctimeperchar>=TIMEPERCHAR){
				ctimeperchar=0;
				numchars++;
			}
		} else {
			complete = true;
		}
		dialogue = dialogue.substring(0, numchars);
		font.draw(batch, dialogue, round(Gdx.graphics.getWidth() / 6.5),round(Gdx.graphics.getHeight() / 1.7));
		batch.end();
		if (complete){

			multipleStageTouch.addProcessor(stage1);
			Gdx.input.setInputProcessor(multipleStageTouch);
			stage1.act(delta);
			stage1.draw();
		}
		if (Gdx.input.isTouched()){
			TIMEPERCHAR = -10f;
		}


	}

	public projectGrapes(final setup game, final String choice1Text, final String choice2Text, final String choice3Text, final String choice4Text){
		this.game = game;
		batch = new SpriteBatch();
		stage1 = new Stage();
		stage2 = new Stage();

		TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();

		fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("whitrabt.ttf"));
		fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		fontParameter.size = (int)(13.4 * Gdx.graphics.getDensity());
		fontParameter.borderWidth = 0F;
		font = fontGenerator.generateFont(fontParameter);
		Color.RED.set(255,0,0, 0.65f);
		font.setColor(Color.RED);

		buttonAtlas = new TextureAtlas(Gdx.files.internal("Buttons/RetroButtons.pack"));
		skin = new Skin(buttonAtlas);

		multipleStageTouch = new InputMultiplexer();
		multipleStageTouch.addProcessor(stage2);
		Gdx.input.setInputProcessor(multipleStageTouch);

		table1 = new Table(skin);
		table2 = new Table(skin);
		table1.setBounds(0,0,Gdx.graphics.getWidth(),(float) round(Gdx.graphics.getHeight() / 1.64));
		table2.setBounds(0,0,Gdx.graphics.getWidth(),(float) round(Gdx.graphics.getHeight() / 5.3));
		terminalBackground = new Texture("TerminalScreen.png");
		icons = new Texture("Terminal Screen Icons.png");
		notification = new Texture("Buttons/LocationNotPressed.png");

		textButtonStyle.up = skin.getDrawable("ButtonNotPressed");
		textButtonStyle.down = skin.getDrawable("ButtonPressed");
		textButtonStyle.font = font;
		textButtonStyle.pressedOffsetY = -10;
		textButtonStyle.unpressedOffsetY = -10;
		if (choice1Text != null) {
			choice1 = new TextButton(choice1Text, textButtonStyle);
			choice1.addListener(new ClickListener() {
				@Override
				public void clicked(InputEvent event, float x, float y) {
                    ActOne.changeChoice1();
                    choiceAmountChange = true;
					choiceDirection("c1");
				}
			});
			table1.add(choice1).width(round((float) Gdx.graphics.getWidth() / 2)).height( round((float) Gdx.graphics.getHeight() / 14));
			table1.row();
		}
		if (choice2Text != null) {
			choice2 = new TextButton(choice2Text, textButtonStyle);
			choice2.addListener(new ClickListener() {
				@Override
				public void clicked(InputEvent event, float x, float y) {
                    ActOne.changeChoice2();
					choiceAmountChange = true;
					choiceDirection("c2");
				}
			});
			table1.add(choice2).width(round((float) Gdx.graphics.getWidth() /  2)).height( round((float) Gdx.graphics.getHeight() / 14));
			table1.row();
		}
		if (choice3Text != null) {
			choice3 = new TextButton(choice3Text, textButtonStyle);
			choice3.addListener(new ClickListener() {
				@Override
				public void clicked(InputEvent event, float x, float y) {
                    ActOne.changeChoice3();
					choiceAmountChange = true;
                    choiceDirection("c3");
				}
			});
			table1.add(choice3).width(round((float) Gdx.graphics.getWidth() /  2)).height( round((float) Gdx.graphics.getHeight() / 14));
			table1.row();
		}
		if (choice4Text != null) {

			choice4 = new TextButton(choice4Text, textButtonStyle);

			choice4.addListener(new ClickListener() {
				@Override
				public void clicked(InputEvent event, float x, float y) {
                    ActOne.changeChoice4();
					choiceAmountChange = true;
                    choiceDirection("c4");
				}
			});
			table1.add(choice4).width(round((float) Gdx.graphics.getWidth() /  2)).height( round((float) Gdx.graphics.getHeight() / 14));

		}
		if (choice1Text == null && choice2Text == null && choice3Text == null && choice4Text == null){
			continueButton = new TextButton("Next", textButtonStyle);
			continueButton.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				choiceDirection("c1");
			}
		});
			table1.add(continueButton).width(round((float) Gdx.graphics.getWidth() /  2)).height( round((float) Gdx.graphics.getHeight() / 14));
		}

		//interaction button layout
		if (true){ //Main Button Layout

		map = new TextButton("", textButtonStyle);
		map.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				MainMenu.game.setScreen(new InteractionSectors(game, "map"));
			}
		});
		mapActions = new TextButton("", textButtonStyle);
		mapActions.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				MainMenu.game.setScreen(new InteractionSectors(game, "MapActions"));
			}
		});
		Settings = new TextButton("", textButtonStyle);
		Settings.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				MainMenu.game.setScreen(new InteractionSectors(game, "Settings"));
			}
		});
		peopleActions = new TextButton("", textButtonStyle);
		peopleActions.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				MainMenu.game.setScreen(new InteractionSectors(game, "PeopleActions"));
			}
		});
		transcripts = new TextButton("", textButtonStyle);
		transcripts.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				MainMenu.game.setScreen(new InteractionSectors(game, "Mail"));
			}
		});
		table2.add(transcripts).width(round((float) Gdx.graphics.getWidth() /  7)).height( round((float) Gdx.graphics.getHeight() / 12.5)).pad(10);
		table2.add(mapActions).width(round((float) Gdx.graphics.getWidth() /  5.5)).height( round((float) Gdx.graphics.getHeight() / 12.5)).pad(10);
		table2.add(map).width(round((float) Gdx.graphics.getWidth() /  7)).height( round((float) Gdx.graphics.getHeight() / 12.5)).pad(10);
		table2.add(peopleActions).width(round((float) Gdx.graphics.getWidth() /  5.5)).height( round((float) Gdx.graphics.getHeight() / 12.5)).pad(10);
		table2.add(Settings).width(round((float) Gdx.graphics.getWidth() /  7)).height( round((float) Gdx.graphics.getHeight() / 12.5)).pad(10);}

		MoveToAction action = new MoveToAction();
		action.setPosition(0f, -30f);
		action.setDuration(0.2f);
		stage1.addActor(table1);
		stage2.addActor(table2);
		stage1.addAction(action);
		stage1.addAction(Actions.sequence(Actions.alpha(0f), Actions.alpha(1f, 0.2f)));


	}


	public void choiceDirection(String Choice){

		if (Choice == "c1"){
			state();
		} else if (Choice == "c2"){
			state();
		} else if (Choice == "c3") {
			state();
		} else if (Choice == "c4"){
			state();
		}


	}

	public static void stateChange(String choice1, String choice2, String choice3, String choice4){
		MainMenu.game.setScreen(new projectGrapes(game, choice1, choice2,
				choice3, choice4));
	}

	@Override
	public void show() {
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
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
