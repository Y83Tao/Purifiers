package com.projectgrapes.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.projectgrapes.game.Resources.CurrentStats;
import com.projectgrapes.game.Resources.Factions;
import com.projectgrapes.game.Resources.Manager;
import com.projectgrapes.game.Resources.Resource;
import com.projectgrapes.game.Resources.miscellaneousStats;

import javax.xml.soap.Text;

import static com.projectgrapes.game.MainMenu.factionsNum;
import static com.projectgrapes.game.MainMenu.prefs;
import static java.lang.StrictMath.round;

public class InteractionSectors extends Manager implements Screen {

    setup game;
    Stage mapStage, returnButton, mapActionStage, peopleStage, peopleStageChoices, specialStage, switchStage, mailsStage, returnMailz, cityOptionsStage, armsOptionsStage, armsOptionsStage2, tradeFactions, tradeList, operationsStage1,
            factionSelect, factionOptions, troopAllocationStage, manageFactionStage, goBackStage, settingsStage;
    Batch batch;
    Texture terminalBackground, terminalMap;
    private TextButton exitButton, Seige, Enlist, Sectors, lynch, switchbutton,
            speak, operations,trade,arms,city,specialities, goBackButton;
    String type; TextureAtlas buttonAtlas, locationButtonAtlas; Skin skin, locationSkin;
    Table exitTable, mapActions, peopleactions, peopleActionChoices, specialTable, switchpeople, mails, mailReturn, cityOptionsTable, armsOptionsTable, armsOptionTable2, tradeFactionsTable, tradeListTable,
            operations1Table, factionSelectTable, factionOptionsTable, troopAllocationTable, manageFactionTable;
    BitmapFont font;
    FreeTypeFontGenerator fontGenerator;
    FreeTypeFontGenerator.FreeTypeFontParameter fontParameter;
    private final boolean[] interact = {false};
    String NamePicked, occupation, changeHead;
    int Strength, Strategy, Management, Science, Cooking, Corruption, troopIncreaseAmount = 1, troopIncreaseIntervals;
    boolean main = true, special = false, change = false, specialInteract = false, switchInitiate = false, peopleAct = true, changeOptions = false, trading = false, factionText = false, tradingTrue = false, tradingButtonTrue = false, mapInfo = false,
            operationsTab = false, regionSelect = false, regionSelectOptions = false, troopAllocation = false, manageTarget, backButton = false;
    private int defenseInt, offenseInt, watcherInt, VaultInt, scienceInt, citycareInt, guard1Int, guard2Int, chefInt, selectedIndex, factionIndex, inputAmount, outPutAmount, regionIndex;
    String occupationName, statsShown, mailMessageText, mailTitleText, factionName, factionStatus, tradeType, factionPlayerStatus, factionEngagementSelect1, factionEngagementSelect2, factionEngagementSelect3, landInfo, mapString;
    public static boolean newDay = true, mailSelect = true, mailMessage = false, checkingCity = false, armsTab = false, tradeTab;



    miscellaneousStats moreStats = new miscellaneousStats();
    Resource resources = new Resource();
    Manager setup = new Manager();
    CurrentStats calculatedStats = new CurrentStats();


    public InteractionSectors(final setup game, String type){
        this.game = game;
        this.type = type;
        returnButton = new Stage();
        mapActionStage = new Stage();
        peopleStage = new Stage();
        peopleStageChoices = new Stage();
        specialStage = new Stage();
        switchStage = new Stage();
        mapStage = new Stage();
        mailsStage = new Stage();
        returnMailz = new Stage();
        cityOptionsStage = new Stage();
        armsOptionsStage = new Stage();
        armsOptionsStage2 = new Stage();
        goBackStage = new Stage();
        settingsStage = new Stage();

        checkingCity = false;
        armsTab = false;
        tradeTab = false;

        terminalBackground = new Texture("TerminalScreen.png");
        terminalMap  = new Texture("Terminal Screen map.png");
        batch = new SpriteBatch();
        TextButton.TextButtonStyle exit = new TextButton.TextButtonStyle();
        final TextButton.TextButtonStyle buttons = new TextButton.TextButtonStyle();
        buttonAtlas = new TextureAtlas(Gdx.files.internal("Buttons/RetroButtons.pack"));
        skin = new Skin(buttonAtlas);

        if (true){
            fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("whitrabt.ttf"));
            fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
            fontParameter.size = (int)(13.4 * Gdx.graphics.getDensity());
            fontParameter.borderWidth = 0F;
            font = fontGenerator.generateFont(fontParameter);
            Color.RED.set(255,0,0, 0.65f);
            font.setColor(Color.RED);
            buttons.up = skin.getDrawable("ButtonNotPressed");
            buttons.down = skin.getDrawable("ButtonPressed");
            buttons.font = font;
            buttons.unpressedOffsetY = - 1;
            buttons.pressedOffsetY = + 1;
        } //button stuff

        InputMultiplexer multipleStageTouch = new InputMultiplexer();
        multipleStageTouch.addProcessor(returnButton);
        multipleStageTouch.addProcessor(switchStage);
        multipleStageTouch.addProcessor(peopleStage);

        exitTable = new Table(skin);
        exitTable.setBounds(0,0 ,Gdx.graphics.getWidth() ,(float) Gdx.graphics.getHeight() / 5);
        exit.up = skin.getDrawable("ButtonNotPressed");
        exit.down = skin.getDrawable("ButtonPressed");
        exit.font = font;
        exit.fontColor = Color.BLACK;
        exit.pressedOffsetY = -10;
        exit.unpressedOffsetY = -10;

        exitButton = new TextButton("Return To Main", exit);
        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                prefs.putBoolean("back", true);
                StorySetup.state();
            }
        });
        exitTable.add(exitButton).width(round((float) Gdx.graphics.getWidth() /  3.6)).height(round((float) Gdx.graphics.getHeight() / 10));
        returnButton.addActor(exitTable);



        if (type == "MapActions") {
            tradeFactions = new Stage();
            tradeList = new Stage();
            operationsStage1 = new Stage();
            operations1Table = new Table(skin);

            tradeFactionsTable = new Table(skin);
            tradeListTable = new Table(skin);
            mapActions = new Table(skin);
            specialTable = new Table(skin);
            cityOptionsTable = new Table(skin);
            specialTable.setBounds(0,0,(float) Gdx.graphics.getWidth() / 2,Gdx.graphics.getHeight());
            mapActions.setBounds(0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

            multipleStageTouch.addProcessor(mapActionStage);
            multipleStageTouch.addProcessor(specialStage);
            multipleStageTouch.addProcessor(cityOptionsStage);
            multipleStageTouch.addProcessor(armsOptionsStage);
            multipleStageTouch.addProcessor(tradeFactions);
            multipleStageTouch.addProcessor(tradeList);
            multipleStageTouch.addProcessor(operationsStage1);
            multipleStageTouch.addProcessor(goBackStage);
            goBackButton = new TextButton("Back", exit);
            goBackButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    MainMenu.game.setScreen(new InteractionSectors(game, "MapActions"));
                }
            });
            goBackButton.setPosition((float) round(Gdx.graphics.getWidth() / 1.5),(float) Gdx.graphics.getHeight() / 16 );
            goBackButton.setWidth(round((float) Gdx.graphics.getWidth() /  7));
            goBackButton.setHeight(round((float) Gdx.graphics.getHeight() / 12.5));
            goBackStage.addActor(goBackButton);

            operations= new TextButton("Operations", buttons);
            operations.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    main = false;
                    operationsTab = true;
                    backButton = true;
                }
            });
            trade = new TextButton("Trade", buttons);
            trade.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    main = false;
                    tradeTab = true;
                    backButton = true;
                }
            });
            arms = new TextButton("Arms", buttons);
            arms.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    main = false;
                    armsTab = true;
                    backButton = true;
                }
            });
            city = new TextButton("City", buttons);
            city.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    main = false;
                    checkingCity = true;
                    backButton = true;
                }
            });
            specialities = new TextButton("Specialities", buttons);
            specialities.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    main = false;
                    special = true;
                    backButton = true;
                }
            });
            MoveToAction action = new MoveToAction();
            action.setPosition(0f, -30f);
            action.setDuration(0.5f);

            if (true){

                final TextButton cityChoice1 = new TextButton("Expand Land [" + moreStats.getLandCost() + "g]", buttons);
                cityChoice1.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {

                        if (resources.getGold() < moreStats.landCost) {cityChoice1.setText("Insufficent Gold");}

                        else if (resources.getGold() >= moreStats.landCost){
                            resources.changeGold(-moreStats.landCost);
                            moreStats.addLandCapacity(50);
                            moreStats.addLandCost(50);
                            cityChoice1.setText("Expand Land [" + moreStats.getLandCost() + "g]");
                        }
                    }
                });
                final TextButton cityChoice2 = new TextButton("+5 Housing [50g]", buttons);
                cityChoice2.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        if ((moreStats.getResidentialSpace() + 5) > moreStats.getLandCapacity()){cityChoice2.setText("Not enough space");}
                        else if (resources.getGold() < 50){cityChoice2.setText("Insufficent Gold");}
                        else if (resources.getGold() >= 50){
                                resources.changeGold(-50);
                                moreStats.addResidentialSpace(5);
                            }

                    }
                });
                final TextButton cityChoice3 = new TextButton("+1 Recreation [75g]", buttons);
                cityChoice3.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        if (resources.getGold() < 75){cityChoice3.setText("Insufficent Gold");}
                        else if (resources.getGold() >= 75) {
                            resources.changeGold(-75);
                            moreStats.addRecreational(1);
                        }
                    }
                });


                cityOptionsTable.add(cityChoice1).width(round((float) Gdx.graphics.getWidth() /  2.3)).height(round((float) Gdx.graphics.getHeight() / 13));
                cityOptionsTable.row();
                cityOptionsTable.add(cityChoice2).width(round((float) Gdx.graphics.getWidth() /  2.3)).height(round((float) Gdx.graphics.getHeight() / 13));
                cityOptionsTable.row();
                cityOptionsTable.add(cityChoice3).width(round((float) Gdx.graphics.getWidth() /  2.3)).height(round((float) Gdx.graphics.getHeight() / 13));
                cityOptionsTable.row();

                ScrollPane cityOptionsScroll = new ScrollPane(cityOptionsTable);
                cityOptionsScroll.setBounds(290, 350, 500, 400);
                cityOptionsScroll.setPosition(290, 350);
                cityOptionsScroll.setTransform(true);

                cityOptionsStage.addActor(cityOptionsScroll);
                cityOptionsStage.addAction(action);
                cityOptionsStage.addAction(Actions.sequence(Actions.alpha(0f), Actions.alpha(1f, 0.1f)));


            } //City Stuff
            if (true){
                armsOptionsTable = new Table(skin);
                armsOptionTable2 = new Table(skin);

                final TextButton upgradeArms = new TextButton("Upgrade Weaponry [100r & 100g](" + moreStats.getArmsUpgrade()+")", buttons);
                upgradeArms.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        if (moreStats.getArmsUpgrade() < 1){ upgradeArms.setText("No Upgrade At Hand");}
                        else if (resources.getResource() < 100 || resources.getGold() < 100){ upgradeArms.setText("Insufficent Gold/Resources");}
                        else{
                            resources.changeGold(-100);
                            resources.changeResource(-100);
                            moreStats.addWeaponryPower((round(moreStats.getWeaponryPower() * ((float) calculatedStats.upgradeScale / 100))));
                            moreStats.addArmsUpgrade(-1);
                            moreStats.firePowerUpdate();
                            upgradeArms.setText("Upgrade Weaponry [100r & 100g](" + moreStats.getArmsUpgrade()+")");
                        }
                    }
                });
                final TextButton increaseVehicles = new TextButton("Build Vehicles [50g & 50r]", buttons);
                increaseVehicles.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        if (resources.getGold() < 50 || resources.getResource() < 50 ){ increaseVehicles.setText("Insufficent Gold/Resources");}
                        else{
                            resources.changeResource(-50);
                            resources.changeGold(-50);
                            moreStats.addVehicles(1);
                            moreStats.firePowerUpdate();
                        }
                    }
                });
                final TextButton HireMercenary = new TextButton("Hire Mercenary [10g] (" + moreStats.getMercenariesAvailable() + ")", buttons);
                HireMercenary.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        if (moreStats.getMercenariesAvailable() < 1){HireMercenary.setText("No Mercenaries Avaiable");}
                        else if ((resources.getTroops() + 1) > moreStats.getResidentialSpace()) {HireMercenary.setText("Housing At Max");}
                        else if (resources.getGold() < 10){HireMercenary.setText("Insufficent Gold");}
                        else {
                            resources.changeGold(-10);
                            resources.changeTroops(1);
                            moreStats.addMercenariesAvailable(-1);
                            moreStats.firePowerUpdate();
                            HireMercenary.setText("Hire Mercenary [10g] (" + moreStats.getMercenariesAvailable() + ")");
                        }
                    }
                });
                armsOptionsTable.add(upgradeArms).width(round((float) Gdx.graphics.getWidth() /  2)).height(round((float) Gdx.graphics.getHeight() / 13));
                armsOptionsTable.row();
                armsOptionsTable.add(increaseVehicles).width(round((float) Gdx.graphics.getWidth() /  2)).height(round((float) Gdx.graphics.getHeight() / 13));
                armsOptionsTable.row();
                armsOptionsTable.add(HireMercenary).width(round((float) Gdx.graphics.getWidth() /  2)).height(round((float) Gdx.graphics.getHeight() / 13));

                ScrollPane scrollArms  = new ScrollPane(armsOptionsTable);
                scrollArms.setBounds((round((float) Gdx.graphics.getWidth() / 4)), (round((float) Gdx.graphics.getHeight() / 5.5)), (round((float) Gdx.graphics.getWidth() /  2)),(round((float) Gdx.graphics.getHeight() / 5)));
                scrollArms.setTransform(true);

                armsOptionsStage.addActor(scrollArms);
                armsOptionsStage.addAction(action);
                armsOptionsStage.addAction(Actions.sequence(Actions.alpha(0f), Actions.alpha(1f, 0.1f)));

            } //Arms stuff
            if (true){

                final TextButton initiateTrade = new TextButton(tradeType, buttons);
                initiateTrade.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {

                        if (faction.get(factionIndex).getTradeStatus().equals("false")) {
                            faction.get(factionIndex).setTradeStatus("true");
                            if (faction.get(factionIndex).getResourceNeeds().equals("Gold")){
                                resources.changeGoldRateDecrease(inputAmount);
                                System.out.println("ran");
                            }
                            else if (faction.get(factionIndex).getResourceNeeds().equals("Resource")){
                                resources.changeResourceRateDecrease(inputAmount);
                                System.out.println("ran2");
                            }

                            if (faction.get(factionIndex).getResourceRich().equals("Gold")){
                                resources.changeGoldRateIncrease(outPutAmount);
                                System.out.println("ran3");
                            }
                            else if (faction.get(factionIndex).getResourceRich().equals("Resource")){
                                resources.changeResourceRateIncrease(outPutAmount);
                                System.out.println("ran4");
                            }
                            initiateTrade.setText("End Trade");


                        }
                        else if (faction.get(factionIndex).getTradeStatus().equals("true")){
                            faction.get(factionIndex).setTradeStatus("false");
                            if (faction.get(factionIndex).getResourceNeeds().equals("Gold")){
                                resources.changeGoldRateDecrease(-inputAmount);
                            }
                            else if (faction.get(factionIndex).getResourceNeeds().equals("Resource")){
                                resources.changeResourceRateDecrease(-inputAmount);
                            }
                            if (faction.get(factionIndex).getResourceRich().equals("Gold")){
                                resources.changeGoldRateIncrease(-outPutAmount);
                            }
                            else if (faction.get(factionIndex).getResourceRich().equals("Resource")){
                                resources.changeResourceRateIncrease(-outPutAmount);
                            }
                            initiateTrade.setText("Initiate Trade");
                        }
                    }
                });
                for (final Factions i : Manager.faction){
                    if (i.getPlayerStatus().equals("Enemy")){
                        factionPlayerStatus = "[E]";
                    }
                    else if (i.getPlayerStatus().equals("Neutral")){
                        factionPlayerStatus = "[N]";
                    }
                    else if (i.getPlayerStatus().equals("Friendly")){
                        factionPlayerStatus = "[F]";
                    }
                    if (i.getStatus() != "Dead" /*&& i.getEncountered() != "false"*/) {
                        final TextButton buttonForFactionList = new TextButton(i.getname() + " " + factionPlayerStatus, buttons);
                        buttonForFactionList.addListener(new ClickListener() {
                            @Override
                            public void clicked(InputEvent event, float x, float y) {
                                factionIndex = Manager.faction.indexOf(i);
                                factionName = i.getname();
                                factionStatus = i.getStatus();
                                inputAmount = i.getTradeInput();
                                outPutAmount = i.getTradeOutput();
                                if (i.getTradeStatus().equals("false")){initiateTrade.setText("Initiate Trade");}
                                else if (i.getTradeStatus().equals("true")){initiateTrade.setText("End Trade");}
                                trading = true;
                                factionText = true;
                                if (!i.getPlayerStatus().equals("Enemy")){
                                    tradingButtonTrue = true;
                                    tradingTrue = true;
                                } else {
                                    tradingButtonTrue = false;
                                    tradingTrue = false;
                                }

                            }
                        });
                        tradeFactionsTable.add(buttonForFactionList).width(round((float) Gdx.graphics.getWidth() /  3)).height(round((float) Gdx.graphics.getHeight() / 13));
                        tradeFactionsTable.row();
                    }
                }


                tradeListTable.add(initiateTrade).width(round((float) Gdx.graphics.getWidth() /  3)).height(round((float) Gdx.graphics.getHeight() / 13));

                ScrollPane tradeFactionScroll = new ScrollPane(tradeFactionsTable);
                tradeFactionScroll.setBounds(0, 0, (float) round(Gdx.graphics.getWidth() / 1.85) , Gdx.graphics.getHeight() );
                //tradeFactionScroll.setPosition(-250,350);
                tradeFactionScroll.setTransform(true);
                tradeFactions.addActor(tradeFactionScroll);
                tradeFactions.addAction(action);
                tradeFactions.addAction(Actions.sequence(Actions.alpha(0f), Actions.alpha(1f, 0.1f)));
                tradeListTable.setPosition( (float) round(Gdx.graphics.getWidth() / 1.5) , (float) round(Gdx.graphics.getHeight() / 2.7) );
                tradeList.addActor(tradeListTable);


            } //Trade With Faction Stuff\\
            if (true){
                factionSelect = new Stage();
                factionOptions = new Stage();
                manageFactionStage = new Stage();
                troopAllocationStage = new Stage();
                troopAllocationTable = new Table(skin);
                manageFactionTable = new Table(skin);
                multipleStageTouch.addProcessor(factionSelect);
                multipleStageTouch.addProcessor(factionOptions);
                multipleStageTouch.addProcessor(troopAllocationStage);
                multipleStageTouch.addProcessor(manageFactionStage);
                factionSelectTable = new Table(skin);
                factionOptionsTable = new Table(skin);
                operations1Table.setBounds(0, 0,Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
                TextButton region = new TextButton("Select Region", buttons);
                region.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        regionSelect = true;
                        operationsTab = false;
                    }
                });
                TextButton troops = new TextButton("Troop Allocation", buttons);
                troops.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        troopAllocation = true;
                        operationsTab = false;
                    }
                });
                TextButton targets = new TextButton("Manage Targets", buttons);
                targets.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        manageTarget = true;
                        operationsTab = false;
                    }
                });
                final TextButton offensive = new TextButton(factionEngagementSelect1 , buttons);
                final TextButton gathering = new TextButton(factionEngagementSelect2, buttons);
                final TextButton idle = new TextButton(factionEngagementSelect3, buttons);
                offensive.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        Manager.mapLocations.get(factionIndex).setEngagement("offensive");
                        offensive.setText("Offensive [S]");
                        gathering.setText("Gather [\\]");
                        idle.setText("Standby [\\]");
                    }
                });
                gathering.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        Manager.mapLocations.get(factionIndex).setEngagement("gather");
                        offensive.setText("Offensive [\\]");
                        gathering.setText("Gather [S]");
                        idle.setText("Standby [\\]");
                    }
                });
                idle.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        Manager.mapLocations.get(factionIndex).setEngagement("idle");
                        offensive.setText("Offensive [\\]");
                        gathering.setText("Gather [\\]");
                        idle.setText("Standby [S]");
                    }
                });

                for (int i = 0; i < Manager.mapLocations.size(); i++){
                    final int finalI = i;
                    TextButton regionInstance = new TextButton(Manager.mapLocations.get(i).getRegionName(), buttons);

                    regionInstance.addListener(new ClickListener() {
                        @Override
                        public void clicked(InputEvent event, float x, float y) {
                            regionIndex = finalI;
                            if (Manager.mapLocations.get(finalI).getEngagement().contains("offensive")){
                                offensive.setText("Offensive [S]");
                                gathering.setText("Gather [\\]");
                                idle.setText("Standby [\\]");
                                factionIndex = finalI;
                                regionSelectOptions = true;
                            }
                            else if (Manager.mapLocations.get(finalI).getEngagement().contains("gather")){
                                offensive.setText("Offensive [\\]");
                                gathering.setText("Gather [S]");
                                idle.setText("Standby [\\]");
                                factionIndex = finalI;
                                regionSelectOptions = true;
                            }
                            else if (Manager.mapLocations.get(finalI).getEngagement().contains("idle")){
                                offensive.setText("Offensive [\\]");
                                gathering.setText("Gather [\\]");
                                idle.setText("Standby [S]");
                                factionIndex = finalI;
                                regionSelectOptions = true;
                            }
                            landInfo = Manager.mapLocations.get(finalI).getRegionName() + " \n\nOccupying Troops = " + Manager.mapLocations.get(finalI).getYourTroopCount() +
                                    "\n\nResources = " + Manager.mapLocations.get(finalI).getResourceAmount() + "r\nGathering Amount = " + Manager.mapLocations.get(finalI).getAmountPer() + "r/d";
                        }
                    });
                    factionSelectTable.add(regionInstance).width(round((float) Gdx.graphics.getWidth() /  3.5)).height(round((float) Gdx.graphics.getHeight() / 15)); factionSelectTable.row();
                }

                for (int i = 0; i < Manager.mapLocations.size(); i++){
                    TextButton downPress1 = new TextButton("-", buttons);
                    final int finalI = i;
                    downPress1.addListener(new ClickListener(){
                        @Override
                        public void clicked(InputEvent event, float x, float y) {
                            if ((Manager.mapLocations.get(finalI).getYourTroopCount() - troopIncreaseAmount) >-1) {
                                resources.changeTroops(troopIncreaseAmount);
                                Manager.mapLocations.get(finalI).changeYourTroopCount(-troopIncreaseAmount);
                            }
                        }
                    });
                    TextButton upPress1 = new TextButton("+", buttons);
                    upPress1.addListener(new ClickListener(){
                        @Override
                        public void clicked(InputEvent event, float x, float y) {
                            System.out.println(resources.getTroops() + "   " + troopIncreaseAmount);
                            if ((resources.getTroops() - troopIncreaseAmount) > -1) {
                                resources.changeTroops(-troopIncreaseAmount);
                                Manager.mapLocations.get(finalI).changeYourTroopCount(troopIncreaseAmount);
                            }
                        }
                    });
                    troopAllocationTable.add(downPress1).width(round((float) Gdx.graphics.getWidth() /  13)).height(round((float) Gdx.graphics.getHeight() / 23)).padRight(10);
                    troopAllocationTable.add(upPress1).width(round((float) Gdx.graphics.getWidth() /  13)).height(round((float) Gdx.graphics.getHeight() / 23)).padRight(10);
                    troopAllocationTable.row().padBottom(10f).padTop(10f);
                }

                TextButton changeTroopIncrease = new TextButton("", buttons);
                changeTroopIncrease.addListener(new ClickListener(){
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                            troopIncreaseIntervals += 1;
                            if (troopIncreaseIntervals == 1){
                                troopIncreaseAmount = 10;
                            }
                            else if (troopIncreaseIntervals == 2){
                                troopIncreaseAmount = 50;
                            }
                            else if (troopIncreaseIntervals == 3){
                                troopIncreaseAmount = 100;
                            }
                            else if (troopIncreaseIntervals == 4){
                                troopIncreaseAmount = 1;
                                troopIncreaseIntervals = 0;
                            }

                    }
                });
                changeTroopIncrease.setPosition((round((float) Gdx.graphics.getWidth() /  1.7)) , (round((float) Gdx.graphics.getHeight() / 1.35)));
                changeTroopIncrease.setWidth(round((float) Gdx.graphics.getHeight() / 23));
                changeTroopIncrease.setHeight(round((float) Gdx.graphics.getHeight() / 23));
                troopAllocationTable.setBounds(0,0, (float) round(Gdx.graphics.getWidth() / 0.65), (float) round(Gdx.graphics.getHeight() / 1.065));
                troopAllocationStage.addActor(troopAllocationTable);
                troopAllocationStage.addActor(changeTroopIncrease);

                factionOptionsTable.add(offensive).width(round((float) Gdx.graphics.getWidth() /  3.65)).height(round((float) Gdx.graphics.getHeight() / 15)); factionOptionsTable.row();
                factionOptionsTable.add(gathering).width(round((float) Gdx.graphics.getWidth() /  3.65)).height(round((float) Gdx.graphics.getHeight() / 15)); factionOptionsTable.row();
                factionOptionsTable.add(idle).width(round((float) Gdx.graphics.getWidth() /  3.65)).height(round((float) Gdx.graphics.getHeight() / 15)); factionOptionsTable.row();
                factionOptionsTable.setBounds(0, 0, (float) round(Gdx.graphics.getWidth() / 0.73), (float) round(Gdx.graphics.getHeight() / 1.5));
                factionOptions.addActor(factionOptionsTable);

                factionSelectTable.setBounds(0, 0, (float) Gdx.graphics.getWidth() / 2 ,(float) Gdx.graphics.getHeight() );
                factionSelect.addActor(factionSelectTable);
                operations1Table.add(region).width(round((float) Gdx.graphics.getWidth() /  2.6)).height(round((float) Gdx.graphics.getHeight() / 13)); operations1Table.row();
                operations1Table.add(troops).width(round((float) Gdx.graphics.getWidth() /  2.6)).height(round((float) Gdx.graphics.getHeight() / 13));operations1Table.row();
                operations1Table.add(targets).width(round((float) Gdx.graphics.getWidth() /  2.6)).height(round((float) Gdx.graphics.getHeight() / 13));operations1Table.row();

                operationsStage1.addActor(operations1Table);
                operationsStage1.addAction(action);
                operationsStage1.addAction(Actions.sequence(Actions.alpha(0f), Actions.alpha(1f, 0.1f)));

                for (int i = 0; i < Manager.faction.size(); i++){
                    if (!Manager.faction.get(i).getPlayerStatus().equals("Enemy")){
                    final TextButton war = new TextButton("Declare Enemy", buttons);
                    final int finalI = i;
                    war.addListener(new ClickListener(){
                        @Override
                        public void clicked(InputEvent event, float x, float y) {
                            if (war.getText().toString().contains("Are You Sure?")){
                                war.remove();
                                Manager.faction.get(finalI).setPlayerStatus("Enemy");
                            }
                            war.setText("Are You Sure?");

                        }
                    });
                    war.setPosition((float) round(Gdx.graphics.getWidth() /  1.5 ), round((float) Gdx.graphics.getHeight() / 1.6) - (i * round((float) Gdx.graphics.getHeight() / 14.6)));
                    war.setHeight(round((float) Gdx.graphics.getHeight() / 16));
                    war.setWidth(round((float) Gdx.graphics.getWidth() /  4));
                    manageFactionStage.addActor(war);}
                }




            }//Operations stuff
            if (true){
                for (int i = 0; i < Manager.peoples.size(); i++) {
                    String set = Manager.peoples.get(i).getOccupation();
                    if (set == "Department of Defense"){defenseInt = i;}if (set == "General of Offense"){offenseInt = i;}
                    if (set == "The Watcher"){watcherInt = i;}if (set == "Vault Manager"){VaultInt = i;}
                    if (set == "Scientist"){scienceInt = i;}if (set == "City Carer"){citycareInt = i;}
                    if (set == "Personal Guard"){guard1Int = i;}if (set == "Secondary Guard"){guard2Int = i;}if (set == "Chef"){chefInt = i;}
                }

                TextButton defense = new TextButton("Department of Defense", buttons);
                defense.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        specialInteract  = true;
                        occupationName = Manager.peoples.get(defenseInt).getName();
                        statsShown = "City Security = %" +  calculatedStats.citySecurity +
                                     "\n\nChance to repel\nattacks = %" + calculatedStats.chanceToRepelAttack +
                                    "\n\nPersonel Security = %" + calculatedStats.personelSecurity;
                    }
                });
                TextButton offense = new TextButton("General of Offense", buttons);
                offense.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        specialInteract  = true;
                        occupationName = Manager.peoples.get(offenseInt).getName();
                        CurrentStats calculatedStats = new CurrentStats();
                        statsShown = "Attack Efficiency = %" +  calculatedStats.attackEfficency +
                                "\n\nFirepower Bonus = +" + calculatedStats.firepowerBonus;
                    }
                });
                TextButton watcher = new TextButton("The Watcher", buttons);
                watcher.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        specialInteract  = true;
                        occupationName = Manager.peoples.get(watcherInt).getName();
                        CurrentStats calculatedStats = new CurrentStats();
                        statsShown = "Enemies In Region Leakage\nchance = %" + calculatedStats.enemyPlan +
                                "\n\nFaction Stat Leakage\nchance = %" + calculatedStats.enemyStat;
                    }
                });
                TextButton vault = new TextButton("Vault Manager", buttons);
                vault.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        specialInteract  = true;
                        occupationName = Manager.peoples.get(VaultInt).getName();
                        CurrentStats calculatedStats = new CurrentStats();
                        statsShown = "Daily Cost Reduction = %" + calculatedStats.dailyReduction +
                                "\n\nBank Security \nEfficiency = %" + calculatedStats.bankSecurity;
                    }
                });
                TextButton science = new TextButton("Scientist", buttons);
                science.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        specialInteract  = true;
                        occupationName = Manager.peoples.get(scienceInt).getName();
                        CurrentStats calculatedStats = new CurrentStats();
                        statsShown = "Arms Upgrade Chance = %" + calculatedStats.innovationChance +
                                "\n\nUpgrade Amount range = %" + calculatedStats.upgradeScale;
                    }
                });
                TextButton citycare = new TextButton("City Carer", buttons);
                citycare.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        specialInteract  = true;
                        occupationName = Manager.peoples.get(citycareInt).getName();
                        CurrentStats calculatedStats = new CurrentStats();
                        statsShown = "Respect Bonus = +%" + calculatedStats.cityHappinessBonus +
                                    "\n\nRespect Percent\nBonus = %" + calculatedStats.cityHappinessPercentBonus;

                    }
                });
                TextButton guard1 = new TextButton("Personal Guard", buttons);
                guard1.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        specialInteract  = true;
                        occupationName = Manager.peoples.get(guard1Int).getName();
                        CurrentStats calculatedStats = new CurrentStats();
                        statsShown = "Assassination block\nchance = %" + calculatedStats.assassinationBlock +
                                "\n*(Secondary Guard Bonus) +%" + calculatedStats.assassinationBlockBonus+
                                "\n\nPersonnel Bonus = +%" + calculatedStats.personnelBonus +
                                "\n\n[Overall Assassination\nBlock Chance= %" + calculatedStats.overallAssassinationBlock + "]";
                    }
                });
                TextButton guard2 = new TextButton("Secondary Guard", buttons);
                guard2.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        specialInteract  = true;
                        occupationName = Manager.peoples.get(guard2Int).getName();
                        CurrentStats calculatedStats = new CurrentStats();
                        statsShown = "Assassination Block\nBonus = %" + calculatedStats.assassinationBlockBonus+
                        "\n\nPersonnel Bonus = +%" + calculatedStats.personnelBonus2;
                    }
                });
                TextButton chef = new TextButton("Chef", buttons);
                chef.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        specialInteract  = true;
                        occupationName = Manager.peoples.get(chefInt).getName();
                        CurrentStats calculatedStats = new CurrentStats();
                        statsShown = "Family Satisfaction = %" + calculatedStats.familySatisfaction;
                    }
                });
                specialTable.add(defense).width(round((float) Gdx.graphics.getWidth() /  2.6)).height(round((float) Gdx.graphics.getHeight() / 13)); specialTable.row();
                specialTable.add(offense).width(round((float) Gdx.graphics.getWidth() /  2.6)).height(round((float) Gdx.graphics.getHeight() / 13)); specialTable.row();
                specialTable.add(watcher).width(round((float) Gdx.graphics.getWidth() /  2.6)).height(round((float) Gdx.graphics.getHeight() / 13)); specialTable.row();
                specialTable.add(vault).width(round((float) Gdx.graphics.getWidth() /  2.6)).height(round((float) Gdx.graphics.getHeight() / 13)); specialTable.row();
                specialTable.add(science).width(round((float) Gdx.graphics.getWidth() /  2.6)).height(round((float) Gdx.graphics.getHeight() / 13)); specialTable.row();
                specialTable.add(citycare).width(round((float) Gdx.graphics.getWidth() /  2.6)).height(round((float) Gdx.graphics.getHeight() / 13)); specialTable.row();
                specialTable.add(guard1).width(round((float) Gdx.graphics.getWidth() /  2.6)).height(round((float) Gdx.graphics.getHeight() / 13)); specialTable.row();
                specialTable.add(guard2).width(round((float) Gdx.graphics.getWidth() /  2.6)).height(round((float) Gdx.graphics.getHeight() / 13)); specialTable.row();
                specialTable.add(chef).width(round((float) Gdx.graphics.getWidth() /  2.6)).height(round((float) Gdx.graphics.getHeight() / 13)); specialTable.row();
                specialStage.addActor(specialTable); } //minimizable specialties utilities

            if (true){} //minimizable Arms utilities


            ScrollPane scrollspecial = new ScrollPane(specialTable);
            scrollspecial.setBounds(500, 1000, Gdx.graphics.getWidth() , round(Gdx.graphics.getHeight() / 1.5));
            scrollspecial.setPosition(250,350);
            scrollspecial.setTransform(true);

            specialStage.addActor(scrollspecial);
            specialStage.addAction(action);
            specialStage.addAction(Actions.sequence(Actions.alpha(0f), Actions.alpha(1f, 0.1f)));


            mapActions.add(operations).width(round((float) Gdx.graphics.getWidth() /  2.6)).height(round((float) Gdx.graphics.getHeight() / 13));
            mapActions.row();
            mapActions.add(trade).width(round((float) Gdx.graphics.getWidth() /  2.6)).height(round((float) Gdx.graphics.getHeight() / 13));
            mapActions.row();
            mapActions.add(arms).width(round((float) Gdx.graphics.getWidth() /  2.6)).height(round((float) Gdx.graphics.getHeight() / 13));
            mapActions.row();
            mapActions.add(city).width(round((float) Gdx.graphics.getWidth() /  2.6)).height(round((float) Gdx.graphics.getHeight() / 13));
            mapActions.row();
            mapActions.add(specialities).width(round((float) Gdx.graphics.getWidth() /  2.6)).height(round((float) Gdx.graphics.getHeight() / 13));
            mapActionStage.addActor(mapActions);

            //specialities info


            mapActionStage.addAction(action);
            mapActionStage.addAction(Actions.sequence(Actions.alpha(0f), Actions.alpha(1f, 0.5f)));
        }


        if (type == "PeopleActions"){

            if (newDay){fiveRandoms(); newDay = false;}
            peopleactions = new Table(skin);
            peopleactions.setBounds(0,0, Gdx.graphics.getWidth(), (float) Gdx.graphics.getHeight() / 2);
            peopleStage.addActor(peopleactions);
            TextButton apple = new TextButton("apple", buttons);
            TextButton oranges = new TextButton("oranges", buttons);
            for (int i = 0; i < Manager.peoples.size(); i++){
                final int app = i;
                TextButton peopleInstance = new TextButton(""+Manager.peoples.get(i).getName(), buttons);
                peopleactions.add(peopleInstance).width(round((float) Gdx.graphics.getWidth() /  2.6)).height(round((float) Gdx.graphics.getHeight() / 13));
                peopleactions.row();
                peopleInstance.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        interact[0] = true;
                        NamePicked = Manager.peoples.get(app).getName();
                        Strength = Manager.peoples.get(app).getStrength();
                        Strategy = Manager.peoples.get(app).getStrategy();
                        Management = Manager.peoples.get(app).getManagement();
                        Science = Manager.peoples.get(app).getScience();
                        Cooking = Manager.peoples.get(app).getCooking();
                        Corruption = Manager.peoples.get(app).getCorruption();
                        occupation = Manager.peoples.get(app).getOccupation();
                        selectedIndex = app;
                        if  (!Manager.peoples.get(app).getName().contains("(S)") && !Manager.peoples.get(app).getName().contains("[Deceased]")){changeOptions = true;}
                        else {changeOptions = false;}
                        System.out.println("Index:  " + selectedIndex);
                    }
                });
            }
            MoveToAction action = new MoveToAction();
            action.setPosition(0f, -30f);
            action.setDuration(0.5f);
            peopleStage.addAction(action);
            peopleStage.addAction(Actions.sequence(Actions.alpha(0f), Actions.alpha(1f, 0.5f)));
            ScrollPane scroll = new ScrollPane(peopleactions);
            scroll.setBounds(0,  round((float) Gdx.graphics.getHeight() / 5), (float) round(Gdx.graphics.getWidth() / 1.8), (float) round(Gdx.graphics.getHeight() / 1.5));
            scroll.setTransform(true);

            peopleStage.addActor(scroll);


            multipleStageTouch.addProcessor(peopleStageChoices);
            peopleActionChoices = new Table(skin);
            peopleActionChoices.setPosition(round((float)Gdx.graphics.getWidth() / 1.2), round((float)Gdx.graphics.getHeight() / 2.35));
            peopleStage.addActor(peopleActionChoices);
            lynch = new TextButton("", buttons);
            lynch.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                switchPanel(selectedIndex, buttons, "Execute");
                changeHead = "Replace With...";
                switchInitiate = true;
                peopleAct = false;
            }
        });

            speak= new TextButton("",buttons);
            switchbutton = new TextButton("", buttons);
            switchbutton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    switchPanel(selectedIndex, buttons, "Switch");
                    switchInitiate = true;
                    peopleAct = false;
                    changeHead = "Exchange With...";
                }
            });
            peopleActionChoices.add(switchbutton).width(round((float) Gdx.graphics.getWidth() /  10)).height(round((float) Gdx.graphics.getHeight() / 20)); peopleActionChoices.row();
            peopleActionChoices.add(lynch).width(round((float) Gdx.graphics.getWidth() /  10)).height(round((float) Gdx.graphics.getHeight() / 20)); peopleActionChoices.row();

            peopleStageChoices.addActor(peopleActionChoices);

            peopleStageChoices.addAction(action);
            peopleStageChoices.addAction(Actions.sequence(Actions.alpha(0f), Actions.alpha(1f, 0.2f)));



            //switching

        }

        if (type == "Mail"){
            mails = new Table(skin);
            mailReturn = new Table(skin);
            mailReturn.setPosition((float) round(Gdx.graphics.getWidth() / 1.4),(float) Gdx.graphics.getHeight() / 10);
            mailSelect = true;
            mailMessage = false;
            new Manager();
            System.out.println(Manager.Mails.size());
            for (int i = Manager.Mails.size() - 1; i >= 0; i--) {
                final TextButton mailInstance = new TextButton(Manager.Mails.get(i).getTitle(), buttons);
                final int finalI = i;
                mailInstance.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        mailSelect = false;
                        mailMessage = true;
                        mailMessageText = Manager.Mails.get(finalI).getMessage();
                        mailTitleText = Manager.Mails.get(finalI).getTitle();
                        if (mailTitleText.contains("[N]")){
                            Manager.mailKeys.remove(mailTitleText);
                            Manager.Mails.get(finalI).changeTitle(Manager.Mails.get(finalI).getTitle().replace("[N]", ""));
                            prefs.putString(finalI+""+""+finalI, Manager.Mails.get(finalI).getTitle() );
                            prefs.flush();
                            mailInstance.setText(Manager.Mails.get(finalI).getTitle());}
                    }
                });
                mails.add(mailInstance).width(round((float) Gdx.graphics.getWidth() /  1.7)).height(round((float) Gdx.graphics.getHeight() / 13.5));
                mails.row();
            }
            mailsStage.addActor(mails);
            TextButton returnMail = new TextButton("", buttons);
            returnMail.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    mailSelect = true;
                    mailMessage = false;

                }
            });
            mailReturn.add(returnMail).width(round((float) Gdx.graphics.getWidth() /  9)).height(round((float) Gdx.graphics.getHeight() / 12.5));


            ScrollPane scroll = new ScrollPane(mails);
            scroll.setBounds(round(Gdx.graphics.getWidth() / 5.5 ),round((float) Gdx.graphics.getHeight() / 3.5), round((float) Gdx.graphics.getWidth() /  1.5) , (round((float) Gdx.graphics.getHeight() / 2)));
            scroll.setTransform(true);
            MoveToAction action = new MoveToAction();
            action.setPosition(0f, -30f);
            action.setDuration(0.5f);

            mailsStage.addActor(scroll);
            returnMailz.addActor(mailReturn);
            multipleStageTouch.addProcessor(returnMailz);
            mailsStage.addAction(action);
            mailsStage.addAction(Actions.sequence(Actions.alpha(0f), Actions.alpha(1f, 0.1f)));
            multipleStageTouch.addProcessor(mailsStage);




        }
        if (type == "map"){
            TextButton.TextButtonStyle locationButtons = new TextButton.TextButtonStyle();
            locationButtonAtlas = new TextureAtlas(Gdx.files.internal("Buttons/LocationButton.pack"));
            locationSkin = new Skin(locationButtonAtlas);
            locationButtons.up = locationSkin.getDrawable("LocationNotPressed");
            locationButtons.down = locationSkin.getDrawable("LocationPressed");
            locationButtons.font = font;
            multipleStageTouch.addProcessor(mapStage);

            if (true) {
                final TextButton locationOne = new TextButton("", locationButtons);
                final TextButton locationTwo = new TextButton("", locationButtons);
                final TextButton locationThree = new TextButton("", locationButtons);
                final TextButton locationFour = new TextButton("", locationButtons);
                final TextButton locationFive = new TextButton("", locationButtons);
                final TextButton locationSix = new TextButton("", locationButtons);
                final TextButton locationSeven = new TextButton("", locationButtons);
                final TextButton locationEight = new TextButton("", locationButtons);
                final TextButton locationNine = new TextButton("", locationButtons);
                locationOne.getStyle().checked = locationOne.getStyle().down;
                locationTwo.getStyle().checked = locationOne.getStyle().down;
                locationThree.getStyle().checked = locationOne.getStyle().down;
                locationFour.getStyle().checked = locationOne.getStyle().down;
                locationFive.getStyle().checked = locationOne.getStyle().down;
                locationSix.getStyle().checked = locationOne.getStyle().down;
                locationSeven.getStyle().checked = locationOne.getStyle().down;
                locationEight.getStyle().checked = locationOne.getStyle().down;
                locationNine.getStyle().checked = locationOne.getStyle().down;
                locationOne.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        System.out.println("Clicked1");
                        mapString = Manager.mapLocations.get(4).getRegionName() + "\nTroops Present: " + Manager.mapLocations.get(4).getYourTroopCount();
                        mapInfo = true;
                    }
                });
                locationTwo.getStyle().checked = locationTwo.getStyle().down;
                locationTwo.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        System.out.println("Clicked2");
                        mapString = Manager.mapLocations.get(1).getRegionName() + "\nTroops Present: " + Manager.mapLocations.get(1).getYourTroopCount();
                        mapInfo = true;
                    }
                });
                locationThree.getStyle().checked = locationTwo.getStyle().down;
                locationThree.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        System.out.println("Clicked3");
                        mapString = Manager.mapLocations.get(2).getRegionName() + "\nTroops Present: " + Manager.mapLocations.get(2).getYourTroopCount();
                        mapInfo = true;
                    }
                });
                locationFour.getStyle().checked = locationTwo.getStyle().down;
                locationFour.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        System.out.println("Clicked4");
                        mapString = Manager.mapLocations.get(3).getRegionName() + "\nTroops Present: " + Manager.mapLocations.get(3).getYourTroopCount();
                        mapInfo = true;
                    }
                });
                locationFive.getStyle().checked = locationTwo.getStyle().down;
                locationFive.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        System.out.println("Clicked5");
                        mapString = Manager.mapLocations.get(7).getRegionName() + "\nTroops Present: " + Manager.mapLocations.get(7).getYourTroopCount();
                        mapInfo = true;
                    }
                });
                locationSix.getStyle().checked = locationTwo.getStyle().down;
                locationSix.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        System.out.println("Clicked6");
                        mapString = Manager.mapLocations.get(6).getRegionName() + "\nTroops Present: " + Manager.mapLocations.get(6).getYourTroopCount();
                        mapInfo = true;
                    }
                });
                locationSeven.getStyle().checked = locationTwo.getStyle().down;
                locationSeven.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        System.out.println("Clicked7");
                        mapString = Manager.mapLocations.get(5).getRegionName() + "\nTroops Present: " + Manager.mapLocations.get(5).getYourTroopCount();
                        mapInfo = true;
                    }
                });
                locationEight.getStyle().checked = locationTwo.getStyle().down;
                locationEight.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        System.out.println("Clicked8");
                        mapString = Manager.mapLocations.get(8).getRegionName() + "\nTroops Present: " + Manager.mapLocations.get(8).getYourTroopCount();
                        mapInfo = true;
                    }
                });
                locationNine.getStyle().checked = locationTwo.getStyle().down;
                locationNine.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        System.out.println("Clicked9");
                        mapString = Manager.mapLocations.get(0).getRegionName() + "\nTroops Present: " + Manager.mapLocations.get(0).getYourTroopCount();
                        mapInfo = true;
                    }
                });
                locationOne.setPosition(round((float) Gdx.graphics.getWidth() / 1.65) ,round((float) Gdx.graphics.getHeight() / 1.8));
                locationOne.setTransform(true);
                locationTwo.setPosition(round((float) Gdx.graphics.getWidth() / 1.35) ,round((float) Gdx.graphics.getHeight() / 1.95));
                locationThree.setPosition(round((float) Gdx.graphics.getWidth() / 2) ,round((float) Gdx.graphics.getHeight() / 2));
                locationFour.setPosition(round((float) Gdx.graphics.getWidth() / 1.6) ,round((float) Gdx.graphics.getHeight() / 2.25));
                locationFive.setPosition(round((float) Gdx.graphics.getWidth() / 2.2) ,round((float) Gdx.graphics.getHeight() / 2.3));
                locationSix.setPosition(round((float) Gdx.graphics.getWidth() / 3.5) ,round((float) Gdx.graphics.getHeight() / 1.89));
                locationSeven.setPosition(round((float) Gdx.graphics.getWidth() / 2.35) ,round((float) Gdx.graphics.getHeight() / 1.7));
                locationEight.setPosition( round((float) Gdx.graphics.getWidth() / 10) ,round((float) Gdx.graphics.getHeight() / 1.87));
                locationNine.setPosition(round((float) Gdx.graphics.getWidth() / 2.35) ,round((float) Gdx.graphics.getHeight() / 1.7));
                locationTwo.setTransform(true);locationThree.setTransform(true);locationFour.setTransform(true);
                locationFive.setTransform(true);locationSix.setTransform(true);locationSeven.setTransform(true);
                locationEight.setTransform(true);locationNine.setTransform(true);

                locationTwo.setScale((float)(0.25 * Gdx.graphics.getDensity()));
                locationTwo.pad(5f);
                locationOne.setScale((float)(0.25 * Gdx.graphics.getDensity()));
                locationThree.setScale((float)(0.25 * Gdx.graphics.getDensity()));
                locationFour.setScale((float)(0.25 * Gdx.graphics.getDensity()));
                locationFive.setScale((float)(0.25 * Gdx.graphics.getDensity()));
                locationSix.setScale((float)(0.25 * Gdx.graphics.getDensity()));
                locationSeven.setScale((float)(0.25 * Gdx.graphics.getDensity()));
                locationEight.setScale((float)(0.25 * Gdx.graphics.getDensity()));
                locationNine.setScale((float)(0.25 * Gdx.graphics.getDensity()));

                ButtonGroup buttonGroup = new ButtonGroup(locationOne, locationTwo, locationThree, locationFour, locationFive, locationSix, locationSeven, locationEight, locationNine);
                buttonGroup.setMaxCheckCount(1);
                buttonGroup.setMinCheckCount(2);
                buttonGroup.setUncheckLast(true);
                mapStage.addActor(locationOne);
                mapStage.addActor(locationTwo);
                mapStage.addActor(locationThree);
                mapStage.addActor(locationFour);
                mapStage.addActor(locationFive);
                mapStage.addActor(locationSix);
                mapStage.addActor(locationSeven);
                mapStage.addActor(locationEight);
                mapStage.addActor(locationNine);
            }
        }
        if (type == "Settings"){
            final TextButton cheats = new TextButton("", buttons);
            cheats.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                   if (prefs.getBoolean("PurifiersDefeated")){
                       resources.changeGold(50000);
                       resources.changeResource(50000);
                       resources.changeTroops(50000);
                       cheats.setText("Done");
                   }
                   else {
                       cheats.setText("Denied");
                   }

                }
            });
            cheats.setHeight(150f);
            cheats.setWidth(150f);
            cheats.setPosition((float) Gdx.graphics.getWidth() / 2 - 70, (float) Gdx.graphics.getHeight() / 2);
            settingsStage.addActor(cheats);
            multipleStageTouch.addProcessor(settingsStage);
        }
        Gdx.input.setInputProcessor(multipleStageTouch);

    }
    @Override
    public void show() {
    }


    public void switchPanel(final int select, TextButton.TextButtonStyle but, final String type){
        switchpeople = new Table();
        switchpeople.setBounds(0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        for (int i = 0; i < Manager.peoples.size(); i++){
            if (i != select){
                final String[] name = new String[1];
                if ((type == "Switch" && !Manager.peoples.get(i).getName().contains("(S)")) && !Manager.peoples.get(i).getName().contains("[Deceased]") || (type == "Execute" && Manager.peoples.get(i).getName().contains("(S)"))){
                final int changeIndex = i;
                final StringBuilder currentString = new StringBuilder(Manager.peoples.get(select).getStatKey());
                currentString.setCharAt(6, (Manager.peoples.get(changeIndex).getStatKey()).charAt(6));
                final StringBuilder finalString = new StringBuilder(Manager.peoples.get(changeIndex).getStatKey());
                finalString.setCharAt(6, (Manager.peoples.get(select).getStatKey()).charAt(6));
                TextButton peopleInstanceSwitch = new TextButton(""+Manager.peoples.get(i).getName(), but);
                peopleInstanceSwitch.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                            if (type == "Switch"){prefs.putString(Manager.peoples.get(select).getStatKeyName(), currentString.toString() );
                                System.out.println((Manager.peoples.get(select).getName() +" changed to = "+ currentString.toString()));
                                Manager.peoples.get(select).setOccupationName(currentString.toString());
                                prefs.putString(Manager.peoples.get(changeIndex).getStatKeyName(), finalString.toString() );
                                System.out.println((Manager.peoples.get(changeIndex).getName() +" changed to = "+ finalString.toString()));
                                Manager.peoples.get(changeIndex).setOccupationName(finalString.toString());
                                prefs.flush();
                                MainMenu.game.setScreen(new projectGrapes(game, null, null, null, null));}

                            if (type == "Execute") {
                                name[0] = Manager.peoples.get(select).getName();
                                prefs.putString(Manager.peoples.get(select).getKey(), Manager.peoples.get(changeIndex).getName().replace("(S)", ""));
                                prefs.putString(Manager.peoples.get(select).getStatKeyName(), finalString.toString());
                                Manager.peoples.get(select).changeSkills(finalString.toString());
                                Manager.peoples.get(select).setName(Manager.peoples.get(changeIndex).getName().replace("(S)", ""));
                                prefs.putString(Manager.peoples.get(changeIndex).getKey(), "[Deceased] " + Manager.peoples.get(select).getName());
                                prefs.putString(Manager.peoples.get(changeIndex).getStatKeyName(), "000000000");
                                Manager.peoples.get(changeIndex).changeSkills("000000000");
                                Manager.peoples.get(changeIndex).setName("[Deceased] " +name[0]);
                                prefs.flush();
                                MainMenu.game.setScreen(new projectGrapes(game, null, null, null, null));}




                    }});
                switchpeople.add(peopleInstanceSwitch).width(round((float) Gdx.graphics.getWidth() /  2.6)).height(round((float) Gdx.graphics.getHeight() / 13));
                switchpeople.row();}}
            switchStage.addActor(switchpeople);
    }}

    @Override
    public void render(float delta) {

        if (type == "map") {
            batch.begin();
            batch.draw(terminalMap, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            batch.end();
            mapStage.act(delta);
            mapStage.draw();
            returnButton.act(delta);
            returnButton.draw();
            if (mapInfo){
                batch.begin();
                font.draw(batch, mapString, ((float) Gdx.graphics.getWidth() / 4) ,round((float) Gdx.graphics.getHeight() / 4));
                batch.end();
            }
        } else if (type == "Settings"){
            batch.begin();
            batch.draw(terminalBackground, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            font.draw(batch, "        Enable Cheats\n(Must have defeated Purifiers \n    in Illuminated Shores)", round((float) Gdx.graphics.getWidth() / 3.5) ,round((float) Gdx.graphics.getHeight() / 1.5));
            batch.end();
            settingsStage.act(delta);
            settingsStage.draw();
            returnButton.act(delta);
            returnButton.draw();

        }
        else if (type == "MapActions"){
            batch.begin();
            batch.draw(terminalBackground, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            batch.end();
            if (main){
                mapActionStage.act(delta);
                mapActionStage.draw();
            }
            else if (special){
                specialStage.act(delta);
                specialStage.draw();
                mapActionStage.clear();
                operationsStage1.clear();
                if(specialInteract){
                    batch.begin();
                    font.draw(batch, occupationName, round((float) Gdx.graphics.getWidth() / 7.5) ,round((float) Gdx.graphics.getHeight() / 1.4));
                    font.draw(batch, statsShown, round((float) Gdx.graphics.getWidth() / 12) ,round((float) Gdx.graphics.getHeight() / 1.5) );
                    batch.end();
                }}
            else if (checkingCity){
                cityOptionsStage.act(delta);
                cityOptionsStage.draw();
                specialStage.clear();
                mapActionStage.clear();
                operationsStage1.clear();
                batch.begin();
                font.draw(batch, "Gold : " + resources.getGold(), round((float) Gdx.graphics.getWidth() / 2.34) ,round((float) Gdx.graphics.getHeight() / 2.5));
                font.draw(batch, "-------------------[City Terminal]-----------------", round((float) Gdx.graphics.getWidth() / 8) ,round((float) Gdx.graphics.getHeight() / 1.2 ));
                font.draw(batch, "-City Structures-\n\nResidential Spaces [" + moreStats.getResidentialSpace() + "|" + moreStats.getLandCapacity() + "]" +
                        "\nIndustries [" + moreStats.getIndustries() +"]" +
                        "\nRecreational [" + moreStats.getRecreational() + "] ~ (Daily Respect Increase : " + resources.getRespectIncrease() +")", round((float) Gdx.graphics.getWidth() / 8) ,round((float) Gdx.graphics.getHeight() / 1.3 ));
                font.draw(batch, "-City District Conditions-\n\nDistrict 1: [" + moreStats.Districts[0] + "]" +
                        "\nDistrict 2: [" + moreStats.Districts[1] + "]" +
                        "\nDistrict 3: [" + moreStats.Districts[2] + "]" +
                        "\nDistrict 4: [" + moreStats.Districts[3] + "]" +
                        "\nDistrict 5: [" + moreStats.Districts[4] + "]" +
                        "\nDistrict 6: [" + moreStats.Districts[5] + "]" +
                        "\nDistrict 7: [" + moreStats.Districts[6] + "]" +
                        "\nDistrict 8: [" + moreStats.Districts[7] + "]" +
                        "\nDistrict 9: [" + moreStats.Districts[8] + "]", round((float) Gdx.graphics.getWidth() / 8) ,round((float) Gdx.graphics.getHeight() / 1.55 ));
                batch.end();
            }
            else if (armsTab){

                batch.begin();
                font.draw(batch, "-------------------[Arms Terminal]-----------------", round((float) Gdx.graphics.getWidth() / 7.6) ,round((float) Gdx.graphics.getHeight() / 1.2 ));
                font.draw(batch, "- City Power -                   - External Unit Power -\n\nWeaponry Power : " + moreStats.getWeaponryPower() + "fp" +
                        "\n\nMilitary Vehicles [" + moreStats.getVehicles() + "] ~ (" + (moreStats.getVehicles() * 5) + "fp)" +
                        "\n\nTroops [" + resources.getTroops() + "|" + moreStats.getResidentialSpace() +"] ~ (" + resources.getTroops() + "fp)", round((float) Gdx.graphics.getWidth() / 8) ,round((float) Gdx.graphics.getHeight() / 1.3 ));
                font.draw(batch, "PerUnitPower = " + (round(moreStats.getWeaponryPower() * 0.1)), round((float) Gdx.graphics.getWidth() / 1.5) ,round((float) Gdx.graphics.getHeight() / 1.36 ));
                font.draw(batch, "-Total City FirePower-", round((float) Gdx.graphics.getWidth() / 2.8) ,round((float) Gdx.graphics.getHeight() / 1.78));
                font.draw(batch, "Weapon Upgrades", round((float) Gdx.graphics.getWidth() / 2.5) ,round((float) Gdx.graphics.getHeight() / 2));
                font.draw(batch, "( %" + ((float) calculatedStats.upgradeScale/100) + " x " + moreStats.getWeaponryPower()+ " = +" + (round(moreStats.getWeaponryPower() * ((float) calculatedStats.upgradeScale / 100))) +"fp )",
                        round((float) Gdx.graphics.getWidth() / 2.8) ,round((float) Gdx.graphics.getHeight() / 2.1));
                font.draw(batch, "Gold : " + resources.getGold() + "   Resources :" +resources.getResource(), round((float) Gdx.graphics.getWidth() / 3.2) ,round((float) Gdx.graphics.getHeight() / 2.5));
                font.getData().setScale((float)((0.5 * Gdx.graphics.getDensity())+0.1));
                font.draw(batch, ""+moreStats.getFirePower(), round((float) Gdx.graphics.getWidth() / 2.1) ,round((float) Gdx.graphics.getHeight() / 1.87));
                font.getData().setScale((float)((0.285 * Gdx.graphics.getDensity()) + 0.1));
                batch.end();
                armsOptionsStage.act(delta);
                armsOptionsStage.draw();
                cityOptionsStage.clear();
                specialStage.clear();
                mapActionStage.clear();
                operationsStage1.clear();
                tradeFactions.clear();
            }
            else if (tradeTab){
                cityOptionsStage.clear();
                specialStage.clear();
                mapActionStage.clear();
                armsOptionsStage.clear();
                operationsStage1.clear();
                tradeFactions.act(delta);
                tradeFactions.draw();


                if (trading){
                    batch.begin();
                    if (factionText) {font.draw(batch, "Faction Name: " +faction.get(factionIndex).getname() + "\nTrade Status: " + faction.get(factionIndex).getTradeStatus() +
                            "\nPlayer Relations: " + faction.get(factionIndex).getPlayerStatus(), round((float) Gdx.graphics.getWidth() / 2.1) ,round((float) Gdx.graphics.getHeight() / 1.5));}
                    if (tradingTrue) {font.draw(batch, "\n\nCurrent Daily Income: \n" + (resources.getGoldRateIncrease() - resources.getGoldRateDecrease()) +"g" +
                            "\n" + (resources.getResourceRateIncrease() - resources.getResourceRateDecrease()) + "r" +
                            "\n\n\nDaily Trading Conditions:\nGain - " + outPutAmount + "" + Character.toLowerCase( faction.get(factionIndex).getResourceRich().charAt(0)) +
                            "\nGive - " + inputAmount + "" + Character.toLowerCase( faction.get(factionIndex).getResourceNeeds().charAt(0)),  round((float) Gdx.graphics.getWidth() / 2.1) ,round((float) Gdx.graphics.getHeight() / 1.6));}
                    batch.end();
                    if (tradingButtonTrue){
                    tradeList.act(delta);
                    tradeList.draw();}
                }

            } else if (operationsTab){
                batch.begin();
                font.draw(batch, "-----------------[Operations Terminal]----------------", round((float) Gdx.graphics.getWidth() / 9) ,round((float) Gdx.graphics.getHeight() / 1.25 ));
                batch.end();
                operationsStage1.act(delta);
                operationsStage1.draw();
                mapActionStage.clear();
                cityOptionsStage.clear();
                specialStage.clear();
                mapActionStage.clear();
                armsOptionsStage.clear();
                tradeFactions.clear();
                tradeList.clear();
            }
            else if (regionSelect){
                operationsStage1.clear();
                factionSelect.act(delta);
                factionSelect.draw();
                if  (regionSelectOptions){
                    factionOptions.act(delta);
                    factionOptions.draw();
                    batch.begin();
                    font.draw(batch, landInfo, round((float) Gdx.graphics.getWidth() / 2.1) ,round((float) Gdx.graphics.getHeight() / 1.5));
                    batch.end();
                }
            }
            else if (troopAllocation){
                operationsStage1.clear();
                factionSelect.clear();
                factionOptions.clear();
                troopAllocationStage.act(delta);
                troopAllocationStage.draw();
                batch.begin();
                font.draw(batch, "Change By " +troopIncreaseAmount+"", round((float) Gdx.graphics.getWidth() / 1.4) ,round((float) Gdx.graphics.getHeight() / 1.3));
                font.draw(batch, "Troop In City = " + resources.getTroops()+"", round((float) Gdx.graphics.getWidth() / 10) ,round((float) Gdx.graphics.getHeight() / 1.35));
                font.draw(batch, Manager.mapLocations.get(0).getRegionName() + " | Troop Count: " +Manager.mapLocations.get(0).getYourTroopCount(), round((float) Gdx.graphics.getWidth() / 10) ,round((float) Gdx.graphics.getHeight() / 1.46));
                font.draw(batch, Manager.mapLocations.get(1).getRegionName() + " | Troop Count: " +Manager.mapLocations.get(1).getYourTroopCount(), round((float) Gdx.graphics.getWidth() / 10) ,round((float) Gdx.graphics.getHeight() / 1.56));
                font.draw(batch, Manager.mapLocations.get(2).getRegionName() + " | Troop Count: " +Manager.mapLocations.get(2).getYourTroopCount(), round((float) Gdx.graphics.getWidth() / 10) ,round((float) Gdx.graphics.getHeight() / 1.70));
                font.draw(batch, Manager.mapLocations.get(3).getRegionName() + " | Troop Count: " +Manager.mapLocations.get(3).getYourTroopCount(), round((float) Gdx.graphics.getWidth() / 10) ,round((float) Gdx.graphics.getHeight() / 1.87));
                font.draw(batch, Manager.mapLocations.get(4).getRegionName() + " | Troop Count: " +Manager.mapLocations.get(4).getYourTroopCount(), round((float) Gdx.graphics.getWidth() / 10) ,round((float) Gdx.graphics.getHeight() / 2.08));
                font.draw(batch, Manager.mapLocations.get(5).getRegionName() + " | Troop Count: " +Manager.mapLocations.get(5).getYourTroopCount(), round((float) Gdx.graphics.getWidth() / 10) ,round((float) Gdx.graphics.getHeight() / 2.35));
                font.draw(batch, Manager.mapLocations.get(6).getRegionName() + " | Troop Count: " +Manager.mapLocations.get(6).getYourTroopCount(), round((float) Gdx.graphics.getWidth() / 10) ,round((float) Gdx.graphics.getHeight() / 2.70));
                font.draw(batch, Manager.mapLocations.get(7).getRegionName() + " | Troop Count: " +Manager.mapLocations.get(7).getYourTroopCount(), round((float) Gdx.graphics.getWidth() / 10) ,round((float) Gdx.graphics.getHeight() / 3.12));
                font.draw(batch, Manager.mapLocations.get(8).getRegionName() + " | Troop Count: " +Manager.mapLocations.get(8).getYourTroopCount(), round((float) Gdx.graphics.getWidth() / 10) ,round((float) Gdx.graphics.getHeight() / 3.75));

                batch.end();
            }
            else if (manageTarget){
                batch.begin();
                font.draw(batch, "------------- Faction Status ---------------", round((float) Gdx.graphics.getWidth() / 5) ,round((float) Gdx.graphics.getHeight() / 1.25 ));
                font.draw(batch, Manager.faction.get(0).getname() + " | Status = " + Manager.faction.get(0).getPlayerStatus() + " |", round((float) Gdx.graphics.getWidth() / 10) ,round((float) Gdx.graphics.getHeight() / 1.5));
                font.draw(batch, Manager.faction.get(1).getname() + " | Status = " + Manager.faction.get(1).getPlayerStatus() + " |", round((float) Gdx.graphics.getWidth() / 10) ,round((float) Gdx.graphics.getHeight() / 1.68));
                font.draw(batch, Manager.faction.get(2).getname() + " | Status = " + Manager.faction.get(2).getPlayerStatus() + " |", round((float) Gdx.graphics.getWidth() / 10) ,round((float) Gdx.graphics.getHeight() / 1.9));
                font.draw(batch, Manager.faction.get(3).getname() + " | Status = " + Manager.faction.get(3).getPlayerStatus() + " |", round((float) Gdx.graphics.getWidth() / 10) ,round((float) Gdx.graphics.getHeight() / 2.2));
                font.draw(batch, Manager.faction.get(4).getname() + " | Status = " + Manager.faction.get(4).getPlayerStatus() + " |", round((float) Gdx.graphics.getWidth() / 10) ,round((float) Gdx.graphics.getHeight() / 2.6));
                batch.end();
                operationsStage1.clear();
                factionSelect.clear();
                factionOptions.clear();
                troopAllocationStage.clear();
                manageFactionStage.act(delta);
                manageFactionStage.draw();
            }

            if (backButton){
                goBackStage.act(delta);
                goBackStage.draw();
            }

            returnButton.act(delta);
            returnButton.draw();
        }
        else if (type == "Mail"){
            batch.begin();
            batch.draw(terminalBackground, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            batch.end();
            if (mailSelect){
                mailsStage.act(delta);
                mailsStage.draw();
            }
            else if (mailMessage){
                mailsStage.unfocusAll();
                batch.begin();
                font.draw(batch, "Title: " + mailTitleText + "\n\n--------------------------------------------------------", round((float) Gdx.graphics.getWidth() / 12) ,round((float) Gdx.graphics.getHeight() / 1.2 ));
                font.draw(batch, mailMessageText, round((float) Gdx.graphics.getWidth() / 8) ,round((float) Gdx.graphics.getHeight() / 1.3 ));
                batch.end();
                returnMailz.act(delta);
                returnMailz.draw();
            }
            returnButton.act(delta);
            returnButton.draw();
        }
        else if (type == "PeopleActions"){
            if (peopleAct){
                batch.begin();
                batch.draw(terminalBackground, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
                batch.end();

                peopleStage.act(delta);
                peopleStage.draw();
                returnButton.act(delta);
                returnButton.draw();
                if (interact[0]){
                    batch.begin();
                    font.draw(batch, "Name: "+ NamePicked + "\n\n" + "Strength (" +Strength+")"+ "\n\n" +
                            "Strategy (" +Strategy+")"+ "\n\n" + "Management (" +Management+")"+ "\n\n" +
                            "Science (" +Science+")"+ "\n\n" +
                   "Cooking (" +Cooking+")"+ "\n\n" + "{"+occupation+"}", round((float) Gdx.graphics.getWidth() / 1.8) ,round((float) Gdx.graphics.getHeight() / 1.35 ));
                    batch.end();
                    if (changeOptions){
                        batch.begin();
                        font.draw(batch, "\n[Switch Role]\n\n\n[Terminate]\n\n\n", 600, 900);
                        batch.end();
                        peopleStageChoices.act(delta);
                        peopleStageChoices.draw();}
                }}
            if (switchInitiate){
                batch.begin();
                batch.draw(terminalBackground, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
                font.draw(batch, changeHead, round((float) Gdx.graphics.getWidth() / 2.5) ,round((float) Gdx.graphics.getHeight() / 1.2 ));
                batch.end();
                switchStage.act(delta);
                switchStage.draw();
                returnButton.act(delta);
                returnButton.draw();
            }
        }



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
