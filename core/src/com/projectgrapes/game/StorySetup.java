package com.projectgrapes.game;

import com.badlogic.gdx.math.Interpolation;
import com.projectgrapes.game.Resources.CurrentStats;
import com.projectgrapes.game.Resources.Days;
import com.projectgrapes.game.Resources.Manager;
import com.projectgrapes.game.Resources.Resource;
import com.projectgrapes.game.Story.ActOne;

import java.util.Random;

import static com.projectgrapes.game.InteractionSectors.newDay;
import static com.projectgrapes.game.MainMenu.game;
import static com.projectgrapes.game.MainMenu.prefs;
import static com.projectgrapes.game.projectGrapes.choiceAmountChange;

public class StorySetup {

    public static int Act = prefs.getInteger("act");
    public static int section = prefs.getInteger("section");
    public static Resource resourceStats = new Resource();
    public static int change = 0;
    public static String transcript;
    static boolean actScene = false; //fix later
    public static int changesMade = 0;


    public static void dialogue(String words){
        transcript = words;
    }

    public static void state(){

        System.out.println(prefs.getBoolean("civilWarNoArms"));
        System.out.println(prefs.getBoolean("civilWarArms"));
        System.out.println(prefs.getBoolean("civilWarStatement"));


        if (resourceStats.getRespect() < 51){
            prefs.putBoolean("civilWarNoArms", true);
            prefs.flush();
        }
        System.out.println(Manager.mapLocations.get(8).getRegionName());
        if (Manager.mapLocations.get(8).getArea5Amount() <= 0){
            prefs.putBoolean("PurifiersDefeated", true);
            prefs.flush();
        }

        if (resourceStats.getGold() < 0){
            change = 1001;
            new ActOne(change);
        } else if (resourceStats.getResource() < 0){
            change = 1002;
            new ActOne(change);
        }  else if (resourceStats.getTroops() <= 0){
            change = 1003;
            new ActOne(change);
        }

        else if (!prefs.getBoolean("back")){

            Random rand = new Random();
            if (choiceAmountChange){
                choiceAmountChange = false;
                prefs.putInteger("ChoiceAmount", (prefs.getInteger("ChoiceAmount") + 1));
                prefs.flush();
                changesMade = prefs.getInteger("ChoiceAmount");
                System.out.println(changesMade % 4 == 0);
                if ((changesMade % 4) == 0){
                    Days day = new Days();
                    day.newDay();
                }
            }
            int assassinChance = 0;
            int assassinationRoll = rand.nextInt(101);
            if (Resource.respect < 75 && Resource.respect > 50){assassinChance = 1;}
            else if (Resource.respect < 50 && Resource.respect > 25){assassinChance = 10;}
            else if (Resource.respect < 25){assassinChance = 20;}

            if (prefs.getString("Story").charAt(0) == '0'){
                change = 0;
                prefs.putString("Story", "1");
            } else if (assassinationRoll < assassinChance){
                int assassinationBlock = rand.nextInt(100);
                if (assassinationBlock < CurrentStats.overallAssassinationBlock){
                    change = 1006;
                }//block
                else{
                   change = 1005;
                }
            }

            else if (prefs.getString("events").charAt(3) == '0') {
                StringBuilder changed = new StringBuilder(prefs.getString("events"));
                changed.setCharAt(3, '9');
                prefs.putString("events", changed.toString());
                System.out.println("run");
                if (rand.nextInt(101) > CurrentStats.chanceToRepelAttack){
                    Resource.wallHpChange(-25);
                    if (Resource.wallHp > 0){
                        change = 50; }
                    else {
                        change = 51; }
                } else {
                    change = 52;
                }
            }

            else if (prefs.getString("events").charAt(0) == '0'){
                change = 901;
                StringBuilder changed = new StringBuilder(prefs.getString("events"));
                changed.setCharAt(0, '9');
                prefs.putString("events", changed.toString());

            }else if (prefs.getString("events").charAt(1) == '0'){
                StringBuilder changed = new StringBuilder(prefs.getString("events"));
                changed.setCharAt(1, '9');
                prefs.putString("events", changed.toString());
                if (rand.nextInt(2) == 1){
                    change = 902;
                }
                else {
                    change = 903;
                }

            }else if (prefs.getString("events").charAt(2) == '0'){
                StringBuilder changed = new StringBuilder(prefs.getString("events"));
                changed.setCharAt(2, '9');
                prefs.putString("events", changed.toString());
                if (rand.nextInt(2) == 1){
                    change = 904;
                }
                else {
                    change = 17;
                }
            }


            else if (prefs.getBoolean("civilWarNoArms")){
                int chanceApples = rand.nextInt(2);
                System.out.println("rannn");
                if (resourceStats.getRespect() > 74){
                    prefs.putBoolean("civilWarNoArms", false);
                    prefs.putBoolean("civilWarArms", false);
                    prefs.putBoolean("civilWarStatement", true);
                    prefs.flush();
                    change = 500;
                    new ActOne(change);
                }
                if (prefs.getBoolean("civilWarStatement")) {
                    System.out.println("rann");
                    prefs.putBoolean("civilWarStatement", false);
                    prefs.flush();
                    change = 501;
                    new ActOne(change);
                }
                else if ((chanceApples == 0)){
                    if (prefs.getBoolean("civilWarNoArms") || prefs.getBoolean("civilWarArms")){
                        if (prefs.getBoolean("civilWarArms")){change = rand.nextInt(9) + 502;}
                        else if (prefs.getBoolean("civilWarNoArms")) {change = rand.nextInt(6) + 502;}
                        new ActOne(change);
                    }
                }  else{
                    change = rand.nextInt(20) + 1;
                    if (!(prefs.getString("events").charAt(0) == '9' && change == 20)){
                        change = rand.nextInt(5) + 1;
                    }
                }
            }
            else if (Resource.troops < 50){
                int changez = rand.nextInt(10);
                if (changez == 9){
                    change = 801;
                }
                else{
                    change = rand.nextInt(23) + 1;

                        if (!(prefs.getString("events").charAt(0) == '9' && change == 20)){
                            change = rand.nextInt(19) + 1;
                        }
                        if (!(prefs.getString("events").charAt(1) == '9' && change == 21)){
                            change = rand.nextInt(19) + 1;
                        }
                        if (!(prefs.getString("events").charAt(2) == '9' && change == 22)){
                            change = rand.nextInt(19) + 1;
                        }
                        if (!(prefs.getString("events").charAt(3) == '9') && change == 23){
                            change = rand.nextInt(19) + 1;
                        }

                }

            } else{
                change = rand.nextInt(23) + 1;

                    if (!(prefs.getString("events").charAt(0) == '9') && change == 20){
                        change = rand.nextInt(19) + 1;
                    }
                    if (!(prefs.getString("events").charAt(1) == '9') && change == 21){
                        change = rand.nextInt(19) + 1;
                    }
                    if (!(prefs.getString("events").charAt(2) == '9') && change == 22){
                        change = rand.nextInt(19) + 1;
                    }
                    if (!(prefs.getString("events").charAt(3) == '9') && change == 23){
                        change = rand.nextInt(19) + 1;
                    }
                }
        //conditions
            System.out.println("Changes made = " + changesMade);
            prefs.putInteger("currentChoice", change);
            prefs.putInteger("section", change);
            prefs.flush();
            projectGrapes.notif = false;
            new ActOne(change);

        }
        else if (prefs.getBoolean("back")){
            new ActOne(prefs.getInteger("currentChoice"));
            prefs.putBoolean("back", false);
        }

    }


    }





