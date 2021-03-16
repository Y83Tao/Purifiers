package com.projectgrapes.game.Story;

import com.projectgrapes.game.MainMenu;
import com.projectgrapes.game.Resources.CurrentStats;
import com.projectgrapes.game.Resources.Manager;
import com.projectgrapes.game.Resources.Resource;
import com.projectgrapes.game.projectGrapes;
import com.projectgrapes.game.setup;

import static com.projectgrapes.game.MainMenu.prefs;
import static com.projectgrapes.game.StorySetup.dialogue;
import static com.projectgrapes.game.projectGrapes.game;
import static java.lang.Math.round;

public class ActOne {

    public static String Choice1;
    public static String Choice2;
    public static String Choice3;
    public static String Choice4;
    boolean subChange1 = false, subChange2 = false,
            subChange01 = false, subChange02 = false;
    static int sectionInstance;
    public static int goldChange = 0, goldChange2 = 0, goldChange3 = 0, goldChange4 = 0;
    public static int troopChange = 0, troopChange2 = 0, troopChange3 = 0, troopChange4 = 0;
    public static int resourceChange = 0, resourceChange2 = 0, resourceChange3 = 0, resourceChange4 = 0;
    public static int respectChange = 0, respectChange2 = 0, respectChange3 = 0, respectChange4 = 0;
    Manager setup = new Manager();
    static Resource resourcezInActOne = new Resource();


    public ActOne(int sections){
        goldChange = 0; troopChange = 0; resourceChange = 0; respectChange = 0;
        goldChange2 = 0; troopChange2 = 0; resourceChange2 = 0; respectChange2 = 0;
        goldChange3 = 0; troopChange3 = 0; resourceChange3 = 0; respectChange3 = 0;
        goldChange4 = 0; troopChange4 = 0; resourceChange4 = 0; respectChange4 = 0;

        if (sectionInstance == 1001){
            prefs.clear();
            prefs.flush();
            MainMenu.game.setScreen(new MainMenu(game));
        }
        if (sectionInstance == 1002){
            prefs.clear();
            prefs.flush();
            MainMenu.game.setScreen(new MainMenu(game));
        }
        if (sectionInstance == 1003){
            prefs.clear();
            prefs.flush();
            MainMenu.game.setScreen(new MainMenu(game));
        }
        if (sectionInstance == 1005){
            prefs.clear();
            prefs.flush();
            MainMenu.game.setScreen(new MainMenu(game));
        }
        if (sectionInstance == 51){
            prefs.clear();
            prefs.flush();
            MainMenu.game.setScreen(new MainMenu(game));
        }



        sectionInstance = sections;
        if (sections == 0) {startMsg();}
        if (sections == 1){one();}
        else if (sections == 2){two();}
        else if (sections == 3){three();}
        else if (sections == 4){four();}
        else if (sections == 5){five();}
        else if (sections == 6){six();}
        else if (sections == 7){seven();}
        else if (sections == 8){eight();}
        else if (sections == 9){nine();}
        else if (sections == 10){ten();}
        else if (sections == 11){eleven();}
        else if (sections == 12){twelve();}
        else if (sections == 13){thirdteen();}
        else if (sections == 14){fourteen();}
        else if (sections == 15){fifthteen();}
        else if (sections == 16){sixteen();}
        else if (sections == 17){seventeen();}
        else if (sections == 18){eighteen();}
        else if (sections == 19){nineteen();}


        else if (sections == 20){eventOne();}
        else if (sections == 21){eventThree();}
        else if (sections == 22){eventSix();}

        else if (sections == 23){attacked();}

        else if (sections == 50){attackLostWallIntact();}
        else if (sections == 51){attackLostWallDestroyed();}
        else if (sections == 52){attackRepelled();}


        else if (sections == 501){civilWarStatement();}
        else if (sections == 502){civilWarOne();}
        else if (sections == 503){civilWarTwo();}
        else if (sections == 504){civilWarThree();}
        else if (sections == 505){civilWarFive();}
        else if (sections == 506){civilWarSix();}
        else if (sections == 507){civilWarSeven();}
        else if (sections == 508){civilWarArmedOne();}
        else if (sections == 509){civilWarArmedTwo();}
        else if (sections == 510){civilWarArmedThree();}

        else if (sections == 801){six();}
        else if (sections == 901){eventTwo();}
        else if (sections == 902){eventFour();}
        else if (sections == 903){eventFive();}
        else if (sections == 904) {eventSeven();}
        else if (sections == 1001){loseAtNoGold();}
        else if (sections == 1002){loseAtNoResource();}
        else if (sections == 1003){noTroops();}
        else if (sections == 1006){blocked();}
        else if (sections == 1005){assassinated();}
        projectGrapes.stateChange(Choice1, Choice2, Choice3, Choice4);
    }
    public static void changeChoice1(){
        resourcezInActOne.changeGold(goldChange);
        resourcezInActOne.changeResource( resourceChange);
        resourcezInActOne.changeRespect(respectChange);
        resourcezInActOne.changeTroops(troopChange);
        if (sectionInstance == 801 || sectionInstance == 6 ){
            resourcezInActOne.changeGoldRateIncrease(10);
        }
        if (sectionInstance == 21){
            StringBuilder changed = new StringBuilder(prefs.getString("events"));
            changed.setCharAt(1, '5');
            prefs.putString("events", changed.toString());
        }
        if (sectionInstance == 22){
            StringBuilder changed = new StringBuilder(prefs.getString("events"));
            changed.setCharAt(2, '3');
            prefs.putString("events", changed.toString());
        }
        if (sectionInstance == 23){
            StringBuilder changed = new StringBuilder(prefs.getString("events"));
            changed.setCharAt(3, '0');
            prefs.putString("events", changed.toString());
        }

    }
    public static void changeChoice2(){
        resourcezInActOne.changeGold(goldChange2);
        resourcezInActOne.changeResource( resourceChange2);
        resourcezInActOne.changeRespect(respectChange2);
        resourcezInActOne.changeTroops(troopChange2);
        if (sectionInstance == 20){
            StringBuilder changed = new StringBuilder(prefs.getString("events"));
            changed.setCharAt(0, '4');
            prefs.putString("events", changed.toString());
        }
        if (sectionInstance == 801  || sectionInstance == 6 ){
            resourcezInActOne.changeGoldRateIncrease(20);
        }
        if (sectionInstance == 502){
            prefs.putBoolean("civilWarArms", true);
        }
        if (sectionInstance == 503 ){
            resourcezInActOne.changeGoldRateIncrease(-10);
        }
    }
    public static void changeChoice3(){
        resourcezInActOne.changeGold(goldChange3);
        resourcezInActOne.changeResource( resourceChange3);
        resourcezInActOne.changeRespect(respectChange3);
        resourcezInActOne.changeTroops(troopChange3);
        if (sectionInstance == 801 || sectionInstance == 6 ){
            resourcezInActOne.changeGoldRateIncrease(35);
        }
    }

    public static void changeChoice4(){
        resourcezInActOne.changeGold(goldChange4);
        resourcezInActOne.changeResource( resourceChange4);
        resourcezInActOne.changeRespect(respectChange4);
        resourcezInActOne.changeTroops(troopChange4);
    }

    void startMsg(){
        dialogue("         - New Message : Android-89 -\n" +
                "\nPlaying...\n\n" +
                "\"Rule my kingdom, maintain our legacy, for you\n" +
                "are the new ruler. I have built and protected\n" +
                "our oddity reign. Do not fail me.\"\n" +
                "\nThe Purifiers have posed a threat\n" +
                "to the oddities. They must perish.\n\n" +
                ""+
                "Ending..\n");
        Choice1 = "Sweet";
        Choice2 = null;
        Choice3 = null;
        Choice4 = null;
    }


    void one(){
        dialogue("A group of citizens wants to advocate on an eco-\nfriendly environment" +
                " by planting thousands of \ntrees all across the city." +
                "\nIt will be great for the environment!\n" +
                "\t“\"We will be able to mimic the nature of \nour ancestors!\"”\n");
        Choice1 = "Allow (-150g)";
        Choice2 = "Deny";
        Choice3 = null;
        Choice4 = null;

        goldChange = -150;
        respectChange = +3;
        respectChange2 = -2;
    }
    void two(){
        dialogue("\"We should overhaul our roads with more finer " +
                        "\nconcrete!\"" + "\nIt would feel nicer to drive on roads.");
        Choice1 = "Sure (-80g)(-80r)";
        Choice2 = "Unecesary";
        Choice3 = null;
        Choice4 = null;

        goldChange = -80;
        respectChange = 5;
        resourceChange = -80;
        respectChange2 = -2;
    }
    void three(){
        dialogue("A local business wants to sell furniture to \nwilling " +
                "citizens. They lack certain materials to \nmake them." +
                "“I believe this trade would be \nbeneficial to both\n" +
                "parties”\t");
        Choice1 = "Alright (-100r)(+100g)";
        Choice2 = "Sorry";
        Choice3 = null;
        Choice4 = null;
        goldChange = 100;
        resourceChange = -100;
    }

    void four(){
        dialogue("A party is spreading negative propaganda \n" +
                "around the city. They are affecting \n" +
                "the citizen’s respect for you.\n");
        Choice1 = "Take Lethal Action (-10t)";
        Choice2 = "Spread Positivity (-100g)";
        Choice3 = "Do Nothing";
        Choice4 = null;
        troopChange = -10;
        respectChange = -3;
        goldChange2 = -100;
        respectChange2 = -2;
        respectChange3 = -5;
    }
    void five(){
        dialogue("A group of citizens want to improve\n" +
                "their recreational program and require \n" +
                "some funding.\n" +
                "\"The children will love the additions we\n" +
                "bring\"\n");
        Choice1 = "Allow (-100g)";
        Choice2 = "Deny";
        Choice3 = null;
        Choice4 = null;
        goldChange = -100;
        respectChange = 3;
        respectChange2 = -2;
    }
    void six(){
        dialogue("A representative of the department of defense\n" +
                "recommends a tax increase to fund more troops\n" +
                "to guard the city and engage in region based \n" +
                "engagement.\n");
        Choice1 = "1% (+10g/d)";
        Choice2 = "2% (+20g/d";
        Choice3 = "3.5% (+35g/d)";
        Choice4 = "Deny";
        respectChange = -2;
        respectChange2 = -5;
        respectChange3 = -10;
    }
    //new =======================================================================
    void seven(){
        dialogue("Rats are spreading an illness around the city.\n" +
                "We must use chemical weapons specialized\n" +
                "in eliminating these rats or else they will kill\n" +
                "many of our people.\n");
        Choice1 = "Use (-100r)";
        Choice2 = "Should be fine";
        Choice3 = null;
        Choice4 = null;

        resourceChange = -100;
        troopChange2 = -15;
        respectChange2 = -10;
    }
    void eight(){
        dialogue("A gang of oddity defectors has taken over a bank\n" +
                "and are holding many people hostage. The defectors\n" +
                "or robbers are requesting safe passage out of the\n" +
                "city with 250g from the bank. Else they will start\n" +
                "a firefight and there may be civilian casualties.\n");
        Choice1 = "Oppose (-20t)";
        Choice2 = "Allow (-250g)";
        Choice3 = null;
        Choice4 = null;

        troopChange = -20;
        respectChange = 2;
        goldChange2 = -250;
        respectChange2 = -5;
    }
    void nine(){
        dialogue("There are many reports of pickpocketing and \n" +
                "thievery. The oddities requests to have more\n" +
                "patrols and security guards to scare away \nthe thieves." +
                "They also requests harsher consequences \nto discourage" +
                "thievery.\n");
        Choice1 = "Yes (-25t)(-150g)";
        Choice2 = "Can't";
        Choice3 = null;
        Choice4 = null;

        troopChange = -25;
        goldChange = -150;
        respectChange2 = -5;
        respectChange = 5;
    }
    void ten(){
        dialogue("There is an ongoing gang fight in the \nstreets. People " +
                "appear to be cheering as several \nmen are fighting" +
                " until blood spills. \nShall we intervene?\n");
        Choice1 = "Yes (-5t)";
        Choice2 = "No";
        Choice3 = null;
        Choice4 = null;

        troopChange = -5;
        respectChange = -5;
        respectChange2 = 2;
    }
    void eleven(){
        dialogue("A heavy storm is about to reach the city. \nThis is a " +
                "uncommon event so the oddities are \nnot prepared " +
                "to face such weather. Shall \nwe provide some umbrellas," +
                "raincoats and \nother necessities?\n");
        Choice1 = "Yes (-50g)(-50r)(-10t)";
        Choice2 = "Can't";
        Choice3 = null;
        Choice4 = null;

        goldChange = -50;
        resourceChange = -50;
        troopChange = -10;
        resourceChange = 7;
        respectChange2 = -5;
    }
    void twelve(){
        dialogue("The mining department has just discovered an\n" +
                "abundance of rich minerals! They are a bit greedy\n" +
                "though. They can liquidate the minerals but you will\n" +
                "have to give them some materials or they won't do it.\n");
        Choice1 = "Sure (+300g)(-100r)";
        Choice2 = "Force (-10t)(+300g)";
        Choice3 = "Leave it";
        Choice4 = null;

        goldChange = 300;
        resourceChange = -100;
        troopChange2 = -10;
        goldChange2 = 300;
        respectChange2 = -5;
    }
    void thirdteen(){
        dialogue("The city carer wants to host large party \nto improve" +
                "the happiness of the oddities. \nIt is quite costly" +
                "as it will have top tier \nfood, entertainment, etc!");
        Choice1 = "Sure (-100g)";
        Choice2 = "Can't";
        Choice3 = null;
        Choice4 = null;

        goldChange = -100;
        respectChange = 5;
        respectChange2 = -3;
    }
    void fourteen(){
        dialogue("A group of supposedly pro-oddities are standing \noutside" +
                " the walls. They want to fight for \nyou as they have been" +
                " discriminated and persecuted \nwhen living outside the city." +
                "They are tough and well \narmed, maybe they will be of " +
                "some use.");
        Choice1 = "Allow Entry";
        Choice2 = "Deny";
        Choice3 = null;
        Choice4 = null;

        troopChange = 25;
        respectChange = -2;
        respectChange2 = 2;
    }
    void fifthteen(){
        dialogue("A bear is seen wandering the streets. It has been \n" +
                "eating market food and is scaring many of the\n" +
                "passerbys. We should send some troops to \n" +
                "exterminate it.\n");
        Choice1 = "Exterminate (-10t)";
        Choice2 = "Lead out city (-5t)";
        Choice3 = "Leave it";
        Choice4 = null;

        troopChange = -10;
        troopChange2 = -5;
        respectChange = -4;
        respectChange2 = 3;
        respectChange3 = -3;
    }
    void sixteen(){
        dialogue("There are peculiar events taking place\n" +
                "inside alleyways. We should send some\n" +
                "troops to investigate. It may ease the\n" +
                "minds of the oddities.\n");
        Choice1 = "Investigate (-5t)";
        Choice2 = "Leave it";
        Choice3 = null;
        Choice4 = null;

        troopChange = -5;
        respectChange = 2;
        respectChange2 = -3;
    }
    void seventeen(){
        dialogue("A traveling merchant has arrived at your\n" +
                "door steps. He wants to offer you 150 resources\n" +
                "for 100 gold pieces. \n" +
                "“A fine price for a fine trade”\n");
        Choice1 = "Accept (-100g)(+150r)";
        Choice2 = "Deny";
        Choice3 = null;
        Choice4 = null;

        goldChange = -100;
        resourceChange = 150;
    }
    void eighteen(){
        dialogue("There was a chemical disaster at a factory nearby.\n" +
                "Without proper resources to deal with the \n" +
                "disaster, many people will die.\n");
        Choice1 = "Fix (-100r)";
        Choice2 = "Do nothing";
        Choice3 = null;
        Choice4 = null;

        resourceChange = -100;
        troopChange2 = -20;
    }
    void nineteen(){
        dialogue("There was an earthquake that caused a lot of\n" +
                "property damage to residents. We should \n" +
                "fix up these properties by offering resources\n" +
                "to those who lost their residents.\n");
        Choice1 = "Sure (-100r)";
        Choice2 = "No";
        Choice3 = null;
        Choice4 = null;

        resourceChange = -100;
        respectChange = 3;
        respectChange2 = -5;
    }
    //end==========================================================

    void civilWarStatement(){
        dialogue("The oddities have become unfavourable \n" +
                "in your rule. They have formed a group called\n" +
                "the resistance and are protesting on the \nstreets " +
                "in defiance to you reign.\n");
        Choice1 = "Damn";
        Choice2 = null;
        Choice3 = null;
        Choice4 = null;
    }
    void civilWarEnd(){
        dialogue("The resistance ended their protest\n" +
                "as they feel their desires have been\n" +
                "fulfilled. They have dissolved for now…\n");
        Choice1 = "Sweet";
        Choice2 = null;
        Choice3 = null;
        Choice4 = null;
    }

    void civilWarOne(){
        dialogue("The resistance are sieging the armory\n" +
                "with makeshift weaponry. If they \n" +
                "succeed they will possess enough\n" +
                "weaponry to start deadly civil war.\n");
        Choice1 = "Resist (-25t)";
        Choice2 = "Give in";
        Choice3 = null;
        Choice4 = null;

        troopChange = -25;
        respectChange = -2;
    }
    void civilWarTwo(){
        dialogue("The streets are filled with thousands of protesters\n" +
                "covering the streets and disrupting workflow.\n" +
                "Without action many people will fall down \n" +
                "on the tax bracket.\n");
        Choice1 = "Take Action (-10t)";
        Choice2 = "Do Nothing (-10g/d)";
        troopChange = -10;
    }
    //=============================================NEW CIVIL WAR

    void civilWarThree(){
        dialogue("Oddities are raiding the stores and market circles.\n" +
                "They are stealing a lot of gold and resources. \n" +
                "Without an intervention, they run away with \n" +
                "these stolen goods.\n");
        Choice1 = "Intervene (-20t)";
        Choice2 = "Do nothing (-100g)(-100r)";
        Choice3 = null;
        Choice4 = null;

        troopChange = -20;
        respectChange = -2;
        goldChange2 = -100;
        resourceChange2 = -100;
    }
    void civilWarFive(){
        dialogue("Homicide and ransacking is ongoing on the\n" +
                "streets. It is chaos down there, without \n" +
                "military control the city will fall.\n");
        Choice1 = "Send Troops (-20t)";
        Choice2 = "Do nothing";
        Choice3 = null;
        Choice4 = null;

        troopChange = -20;
        respectChange = -4;
        resourceChange2 = -50;
    }
    void civilWarSix(){
        dialogue("Our servers and data are being hacked into.\n" +
                "They will disclose our secrets without proper\n" +
                "resources and dispatchment of troops.\n" +
                "Without an intervention, they will know\n" +
                "everything !\n");
        Choice1 = "Intervene (-50r)(-5t)";
        Choice2 = "Do nothing";
        Choice3 = null;
        Choice4 = null;

        resourceChange = -50;
        troopChange = -5;
        respectChange2 = -10;
    }
    void civilWarSeven(){
        dialogue("There is a large group of oddities outside the\n" +
                "building. We must disperse them or they will\n" +
                "attack and they will take everything!\n");
        Choice1 =  "Disperse (-10t)";
        Choice2 = "Do nothing ";
        Choice3 = null;
        Choice4 = null;

        troopChange = -10;
        goldChange2 = -1000;
        respectChange2 = -1000;

    }

    void civilWarArmedOne(){
        dialogue("There is a shootout in the city between oddities\n" +
                "and security. Many of our men are wounded.\n" +
                "We need immediate backup or we will have major\n" +
                "troop casualties and lose moral.\n");
        Choice1 = "Backup (-10t)";
        Choice2 = "Leave it";
        Choice3 = null;
        Choice4 = null;

        troopChange = -10;
        troopChange2 = -25;
        resourceChange2 = -5;
    }
    void civilWarArmedTwo(){
        dialogue("Many people are trying to leave the city. \nThey are well " +
                "armed and will not hesitate to \nstart ashootout.If we let them " +
                "go, our moral will \ndecrease tremendously and they will get " +
                "out \nwith a lot of our resources.\n");
        Choice1 = "Deny (-25t)";
        Choice2 = "Allow (-150r)";
        Choice3 = null;
        Choice4 = null;

        troopChange = -25;
        resourceChange2 = -150;
        respectChange2 = -5;
    }
    void civilWarArmedThree(){
        dialogue("Emergency services, hospitals and educational \ninstitutions " +
                "have been ransacked and taken as supply \nstations. Without" +
                "military force, these \ninstitutions will continue to be controlled" +
                "\nby protestors and lose moral. \n");
        Choice1 = "Engage (-25t)";
        Choice2 = "Do nothing ";
        Choice3 = null;
        Choice4 = null;

        troopChange = -25;
        respectChange2 = -5;
    }

    //==============================================================================
    void loseAtNoGold(){
        dialogue("The economy has crashed. You are unable to\n" +
                "fund your personnel or maintain the city's \n" +
                "integrity. Your terminal link has been cut.\n");
        Choice1 = "Damn";
        Choice2 = null;
        Choice3 = null;
        Choice4 = null;
    }
    void loseAtNoResource(){
        dialogue("You do not have enough resources to feed your \n" +
                "people. The people grow hungry which leads to \nyour loss " +
                "of power. Your terminal link has been cut.\n");
        Choice1 = "Damn";
        Choice2 = null;
        Choice3 = null;
        Choice4 = null;
    }


    void eventOne(){
        dialogue("There are stragglers outside the walls \n" +
                "seeking refugee. They are unarmed.\n" +
                "However the majority of the \n" +
                "oddities feel uneasy.\n");
        Choice1 = "Allow";
        Choice2 = "Deny";
        Choice3 = "Eliminate";
        Choice4 = null;

        respectChange = -3;
        respectChange3 = -2;
    } void eventTwo(){
        dialogue("The stragglers that you denied refugee\n" +
                "have returned and are well armed with \n" +
                "explosives. They demand resources or\n" +
                "they will take lethal action.\n");
        Choice1 = "Eliminate (-5t)";
        Choice2 = "Give Resources (-50r)";
        Choice3 = null;
        Choice4 = null;

        troopChange = -5;
        respectChange = -1;
        resourceChange2 = -50;
        respectChange2 = -5;
    }

    //new=-=================================================================
    void eventThree(){
        dialogue("A white cloak guild member is looking to \n" +
                "redeem his name. He knows the location of\n" +
                "a small establishment of oddity defectors. He can\n" +
                "eliminate them and return the goods at a\n" +
                "price.\n");
        Choice1 = "Sure (-150g)";
        Choice2 = "Not needed";
        Choice3 = null;
        Choice4 = null;

        goldChange = -150;
    }
    void eventFour(){
        dialogue("The white cloak member has returned.\n" +
                "He bring you the head of the oddity defectors and\n" +
                "200 r. He is glad to do business.\n");
        Choice1 = "Sweet (+200)";
        Choice2 = null;
        Choice3 = null;
        Choice4 = null;
        goldChange = 200;
    }
    void eventFive(){
        dialogue("The white cloak member has not returned.\n" +
                "It appears that his journey did not go as\n" +
                "planned. His status is now M.I.A.\n" +
                "Your deposit will not be compensated.\n");
        Choice1 = "Damn";
        Choice2 = null;
        Choice3 = null;
        Choice4 = null;
    }




    void eventSix(){
        dialogue("A rich merchant has been robbed. He asks you\n" +
                "to aid him in searching for the robbers. He\n" +
                "will compensate this service with raw materials.\n" +
                "However he will need some of your men.\n");
        Choice1 = "Here (-25t)(+100r)";
        Choice2 = "Sorry";
        Choice3 = null;
        Choice4 = null;
        troopChange = -25;
        resourceChange = 100;
        respectChange2 = -5;
    }
    void eventSeven(){
        dialogue("The criminals that robbed the merchant was \n" +
                "sought out and arrested. They plead that they do\n" +
                "not want to go to prison or be lynched. They offer\n" +
                "you a bribe of 100g if you let them go. \n");
        Choice1 = "Lynch";
        Choice2 = "Not guilty (+100g)";
        Choice3 = null;
        Choice4 = null;
        respectChange = 3;
        respectChange2 = -5;
        goldChange2 = 100;
    }





    void attacked(){
        dialogue("An invasion by the Purifiers is raging\n" +
                "outside the walls. A message is received,\n" +
                "saying that they are willing to call off the attack\n" +
                "if you offer them 50% of your gold and resources.\n" +
                "Else, they will engage and topple your reign.\n");
        Choice1 = "Fight me";
        Choice2 = "Accept Trade";
        Choice3 = null;
        Choice4 = null;
        goldChange2 = - round((int)(resourcezInActOne.getGold() / 2));
        resourceChange2 = - round((int) (resourcezInActOne.getResource() / 2));
    }
    void attackLostWallIntact(){
        dialogue("The Purifiers have defeated your line of defenses\n" +
                "and damaged your wall. However, they did not \n" +
                "possess the resources to topple the wall. \nThey have " +
                "retreated.\n");
        Choice1 = "Sweet";
        Choice2 = null;
        Choice3 = null;
        Choice4 = null;
    }
    void attackLostWallDestroyed(){
        dialogue("The Purifiers have defeated your line of defenses,\n" +
                "toppled your wall and massacred your city.\n" +
                "The Purifiers have taken down the oddities\n" +
                "and cut your terminal link. The oddities\n" +
                "are no more.\n");
        Choice1 = "Damn";
        Choice2 = null;
        Choice3 = null;
        Choice4 = null;
    }
    void attackRepelled(){
        dialogue("\n" +
                "Your line of defenses defeated the Purifiers \ninvasion " +
                "They are in full retreat and will not \nbe returning " +
                "for a bit. Lets be prepared \nand insure our " +
                "Defensive strategy \nis on point.\n");
        Choice1 = "Lets go!";
        Choice2 = null;
        Choice3 = null;
        Choice4 = null;
    }

    void noTroops(){
        dialogue("You lack any troops inside your city.\n" +
                "A nearby faction took note of this absence\n" +
                "and broke through the wall. Your city is\n"+
                "taken and your terminal link is cut.");
        Choice1 = "Damn";
        Choice2 = null;
        Choice3 = null;
        Choice4 = null;
    }
    void blocked(){
        dialogue("Someone attempted to assassinate\n"+
                "you. However, your guards were able to \n" +
                "disarm and restrain the culprit. \nYou are " +
                "safe.");
        Choice1 = "Sweet";
        Choice2 = null;
        Choice3 = null;
        Choice4 = null;
    }
    void assassinated(){
        dialogue("Someone enters your vicinity armed.\n" +
                "Your guards were unable to disarm the\n" +
                "culprit. He/She forcefully cuts your\n" +
                "terminal link. Your reign ends here.");
        Choice1 = "Damn";
        Choice2 = null;
        Choice3 = null;
        Choice4 = null;
    }

}
