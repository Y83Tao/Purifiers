package com.projectgrapes.game.Resources;

import static com.projectgrapes.game.MainMenu.prefs;
import static com.projectgrapes.game.Resources.Manager.Mails;
import static java.lang.StrictMath.abs;
import static java.lang.StrictMath.round;

import java.util.Random;

public class Days {
    public int Days;
    Resource resourcez = new Resource();
    Random rand = new Random();
    Manager setup = new Manager();
    CurrentStats statz = new CurrentStats();
    miscellaneousStats moreStats = new miscellaneousStats();
    public String report = "";
    public void Days(){
        Days = prefs.getInteger("Day");
    }
    public int getDays(){
        return (prefs.getInteger("Day"));
    }
    public void newDay(){
        Days += 1;
        prefs.putInteger("Day", (prefs.getInteger("Day") + 1));

        resourcez.changeGold(resourcez.goldRateIncrease);
        resourcez.changeGold(-resourcez.goldRateDecrease);

        resourcez.changeRespect(resourcez.getRespectIncrease());

        moreStats.addMercenariesAvailable(5);

        resourcez.changeResource(resourcez.resourceRateIncrease);
        resourcez.changeResource(-resourcez.resourceRateDecrease);

        int assassinationRoll = rand.nextInt(101);
        int armsRoll = rand.nextInt(101);
        int regionStatRoll = rand.nextInt(101);
        int factionStatRoll = rand.nextInt(101);
        int assassinChance = -1;
        System.out.println(armsRoll);
        System.out.println(statz.innovationChance);
        if (armsRoll <= statz.innovationChance){
            moreStats.addArmsUpgrade(1);
            prefs.putInteger("MailCount", (prefs.getInteger("MailCount") +1));
            prefs.flush();
            prefs.putString(prefs.getInteger("MailCount")+"", "Your scientist has just discovered \nnew technology for your weaponry!\n\nYou have gained one arms upgrade");
            prefs.putString(prefs.getInteger("MailCount")+""+prefs.getInteger("MailCount"), "Arms Upgrade Update [N]");
            prefs.flush();
            Mails.add(new Mail(prefs.getString(prefs.getInteger("MailCount")+""+prefs.getInteger("MailCount")), prefs.getString(prefs.getInteger("MailCount")+"")));
            Manager.mailKeys.put(prefs.getString(prefs.getInteger("MailCount")+""+prefs.getInteger("MailCount")), prefs.getInteger("MailCount"));
            System.out.println(Manager.mailKeys.keySet());
            prefs.flush();
        }
        if (regionStatRoll < CurrentStats.enemyPlan){
            int place = rand.nextInt(9);
            prefs.putInteger("MailCount", (prefs.getInteger("MailCount") +1));
            prefs.flush();
            prefs.putString(prefs.getInteger("MailCount")+"", "Our Watcher retrieved occupation data from the\n" + Manager.mapLocations.get(place).getRegionName() +
                    ".The information are as follows...\n\n" +
                    "Area 1 : " + Manager.mapLocations.get(place).getArea1() + "\nTroops: " + Manager.mapLocations.get(place).getArea1Amount()+"\n\n" +
                    "Area 2 : " + Manager.mapLocations.get(place).getArea2() + "\nTroops: " + Manager.mapLocations.get(place).getArea2Amount()+"\n\n" +
                    "Area 3 : " + Manager.mapLocations.get(place).getArea3() + "\nTroops: " + Manager.mapLocations.get(place).getArea3Amount()+"\n\n" +
                    "Area 4 : " + Manager.mapLocations.get(place).getArea4() + "\nTroops: " + Manager.mapLocations.get(place).getArea4Amount()+"\n\n" +
                    "Area 5 : " + Manager.mapLocations.get(place).getArea5() + "\nTroops: " + Manager.mapLocations.get(place).getArea5Amount()+"\n\n" );
            prefs.putString(prefs.getInteger("MailCount")+""+prefs.getInteger("MailCount"), Manager.mapLocations.get(place).getRegionName() + " data leak [N]");
            prefs.flush();
            Mails.add(new Mail(prefs.getString(prefs.getInteger("MailCount")+""+prefs.getInteger("MailCount")), prefs.getString(prefs.getInteger("MailCount")+"")));
            Manager.mailKeys.put(prefs.getString(prefs.getInteger("MailCount")+""+prefs.getInteger("MailCount")), prefs.getInteger("MailCount"));
            System.out.println(Manager.mailKeys.keySet());
            prefs.flush();
        }
        if (factionStatRoll < CurrentStats.enemyStat){
            int factionType = rand.nextInt(5);
            prefs.putInteger("MailCount", (prefs.getInteger("MailCount") +1));
            prefs.flush();
            prefs.putString(prefs.getInteger("MailCount")+"", "Our Watcher retrieved helpful data of the\n" + Manager.faction.get(factionType).getname() + ". The information are as follows..." +
                    "\n\nFirepower per unit: " + Manager.faction.get(factionType).getPerUnitFirePower());
            prefs.putString(prefs.getInteger("MailCount")+""+prefs.getInteger("MailCount"), Manager.faction.get(factionType).getname() + " stat leak [N]");
            prefs.flush();
            Mails.add(new Mail(prefs.getString(prefs.getInteger("MailCount")+""+prefs.getInteger("MailCount")), prefs.getString(prefs.getInteger("MailCount")+"")));
            Manager.mailKeys.put(prefs.getString(prefs.getInteger("MailCount")+""+prefs.getInteger("MailCount")), prefs.getInteger("MailCount"));
            System.out.println(Manager.mailKeys.keySet());
            prefs.flush();
        }







        if (true){
            for (int i = 0; i < Manager.mapLocations.size(); i++){
                int yourFP = round(Manager.mapLocations.get(i).getYourTroopCount() * (round(moreStats.getWeaponryPower() * 0.1)));
                int enemyFP = 0, purifiers = 0, ironLegion = 0, keepersOfEmber = 0, leatherJunction = 0, theArchitects = 0;
                for (int a = 0; a < Manager.faction.size(); a++){
                    if (Manager.faction.get(a).getKeyName().contains("The Purifiers")){
                        purifiers = a;
                    }
                    if (Manager.faction.get(a).getKeyName().contains("The Iron Legion")){
                        ironLegion = a;
                    }
                    if (Manager.faction.get(a).getKeyName().contains("The Keepers Of Ember")){
                        keepersOfEmber = a;
                    }
                    if (Manager.faction.get(a).getKeyName().contains("The Leather Junction")){
                        leatherJunction = a;
                    }
                    if (Manager.faction.get(a).getKeyName().contains("The Architects")){
                        theArchitects = a;
                    }
                }
                if ((Manager.mapLocations.get(i).getEngagement().contains("gather"))) {
                    if (Manager.mapLocations.get(i).getYourTroopCount() >0){
                        if(Manager.mapLocations.get(i).getResourceAmount() > 0){
                        Random rand = new Random();
                        int chanceGather = rand.nextInt(10);
                        if (chanceGather < 8){
                            resourcez.changeResource(Manager.mapLocations.get(i).getAmountPer());
                            Manager.mapLocations.get(i).setResourceAmount(-Manager.mapLocations.get(i).getAmountPer());
                            report += Manager.mapLocations.get(i).getRegionName() + " Report: \nOur troops gathered " + Manager.mapLocations.get(i).getAmountPer() + "r\n\n";
                        } else {
                            int loss = rand.nextInt(Manager.mapLocations.get(i).getYourTroopCount() / 10 + 1) ;
                            Manager.mapLocations.get(i).changeYourTroopCount(-loss);
                            report += Manager.mapLocations.get(i).getRegionName() + " Report: \nOur troops were ambushed while gathering resources."
                                    + "\nWe lost " + loss +" troops\n\n";
                        }}
                        else{
                            report += Manager.mapLocations.get(i).getRegionName() + " Report: \nOur troops have nothing left to gather.\n\n";
                        }
                    }
                }
                if (Manager.mapLocations.get(i).getEngagement().contains("offensive")){

                    String[] troopType = new String[5];
                    troopType[0] = Manager.mapLocations.get(i).getArea1();
                    troopType[1] = Manager.mapLocations.get(i).getArea2();
                    troopType[2] = Manager.mapLocations.get(i).getArea3();
                    troopType[3] = Manager.mapLocations.get(i).getArea4();
                    troopType[4] = Manager.mapLocations.get(i).getArea5();
                    int[] troopAmount = new int[5];
                    troopAmount[0] = Manager.mapLocations.get(i).getArea1Amount();
                    troopAmount[1] = Manager.mapLocations.get(i).getArea2Amount();
                    troopAmount[2] = Manager.mapLocations.get(i).getArea3Amount();
                    troopAmount[3] = Manager.mapLocations.get(i).getArea4Amount();
                    troopAmount[4] = Manager.mapLocations.get(i).getArea5Amount();

                    for (int b = 0; b < 5; b++){
                        boolean fight = false;
                        if (Manager.mapLocations.get(i).getYourTroopCount() >0){
                            if (troopAmount[b] > 0){
                                if (troopType[b].contains("Purifiers")){
                                    if (Manager.faction.get(purifiers).getPlayerStatus().contains("Enemy")){
                                        enemyFP = troopAmount[b] * Manager.faction.get(purifiers).getPerUnitFirePower();
                                        fight = true;
                                }}
                                else if (troopType[b].contains("Iron Legion")){
                                    if (Manager.faction.get(ironLegion).getPlayerStatus().contains("Enemy")){
                                        enemyFP = troopAmount[b] * Manager.faction.get(ironLegion).getPerUnitFirePower();
                                        fight = true;
                                }}
                                else if (troopType[b].contains("Keepers Of Ember")){
                                    if (Manager.faction.get(keepersOfEmber).getPlayerStatus().contains("Enemy")){
                                         enemyFP = troopAmount[b] * Manager.faction.get(keepersOfEmber).getPerUnitFirePower();
                                        fight = true;
                                }}
                                else if (troopType[b].contains("Leather Junction")){
                                    if (Manager.faction.get(leatherJunction).getPlayerStatus().contains("Enemy")){
                                        enemyFP = troopAmount[b]* Manager.faction.get(leatherJunction).getPerUnitFirePower();
                                        fight = true;
                                    } else if (Manager.faction.get(leatherJunction).getPlayerStatus().contains("Neutral")){
                                        report += "The leather junction will not allow\nyour troops to cross this area of \n" + Manager.mapLocations.get(i).getRegionName() + ".\n\n";
                                        break;
                                    }
                                }
                                else if (troopType[b].contains("Architects")){
                                    if (Manager.faction.get(theArchitects).getPlayerStatus().contains("Enemy")) {
                                        enemyFP = troopAmount[b] * Manager.faction.get(theArchitects).getPerUnitFirePower();
                                        fight = true;
                                    }
                                }

                            } }
                        if (fight){
                            int allyLoss = 0;
                            int enemyLoss = 0;
                            int recordedAllyLoss = 0, recordedAllyLoss1 = 0;
                            System.out.println("your FP = " + yourFP);
                            System.out.println("enemy FP = " + enemyFP);
                            if (yourFP > enemyFP){
                                Random rand = new Random();
                                if (yourFP - enemyFP <= 50){
                                    allyLoss = rand.nextInt(troopAmount[b] / 4 + 1);
                                    enemyLoss = rand.nextInt(abs(Manager.mapLocations.get(i).getYourTroopCount() / 3 )+ 1);
                                }
                                if (yourFP - enemyFP > 50 && Manager.mapLocations.get(i).getYourTroopCount() - enemyFP <= 100){
                                    allyLoss = rand.nextInt(troopAmount[b] / 5 + 1);
                                    enemyLoss = rand.nextInt(abs(Manager.mapLocations.get(i).getYourTroopCount() / 2) + 1);
                                }
                                if (yourFP - enemyFP > 100){
                                    allyLoss = rand.nextInt(troopAmount[b] / 6 + 1);
                                    enemyLoss = rand.nextInt(abs(Manager.mapLocations.get(i).getYourTroopCount()) + 1);

                                }
                            } else if (yourFP < enemyFP){
                                if (enemyFP - yourFP <= 50){
                                    allyLoss = rand.nextInt(troopAmount[b] / 2 + 1);
                                    enemyLoss = rand.nextInt(abs(Manager.mapLocations.get(i).getYourTroopCount()/ 4) + 1);
                                }
                                if (enemyFP - yourFP > 50 && yourFP - enemyFP <= 100){
                                    allyLoss = rand.nextInt(troopAmount[b] / 2 + 1);
                                    enemyLoss = rand.nextInt(abs(Manager.mapLocations.get(i).getYourTroopCount() / 5) + 1);
                                }
                                if (enemyFP - yourFP > 100){
                                    System.out.println(Manager.mapLocations.get(i).getYourTroopCount() / 6 + 1);
                                    allyLoss = rand.nextInt(troopAmount[b]);
                                    enemyLoss = rand.nextInt(abs(Manager.mapLocations.get(i).getYourTroopCount() / 6) + 1);
                                }
                            } else{
                                allyLoss = rand.nextInt(troopAmount[b] / 2 + 1);
                                enemyLoss = rand.nextInt(Manager.mapLocations.get(i).getYourTroopCount() / 2 + 1);
                            }
                            if (Manager.mapLocations.get(i).getYourTroopCount() - allyLoss < 0){
                                recordedAllyLoss1 = Manager.mapLocations.get(i).getYourTroopCount() - allyLoss;
                                System.out.println("redcord 1 = " + recordedAllyLoss1);
                                allyLoss = allyLoss + recordedAllyLoss1;
                                System.out.println("record 2 = " + allyLoss);
                            }
                            System.out.println("Ally loss = " + allyLoss);
                            System.out.println("Enemy loss = " + enemyLoss);
                            if (b == 0){
                                Manager.mapLocations.get(i).changeYourTroopCount(-allyLoss);
                                Manager.mapLocations.get(i).setArea1Amount(-enemyLoss);
                                report += Manager.mapLocations.get(i).getRegionName() + " Report: \nA seige against the " + Manager.mapLocations.get(i).getArea1() + " took place" +
                                        "\nAlly Casualties [" + yourFP + "fp] = " + ( allyLoss) + "\nEnemy Casualties [" + enemyFP +"fp] = " + enemyLoss + "\n\n";
                                if (Manager.mapLocations.get(i).getArea1Amount() < 1){
                                    report += "You have cleared the " + Manager.mapLocations.get(i).getArea1() + " from Area 1\n\n";
                                }
                            }
                            else if (b ==1){
                                Manager.mapLocations.get(i).changeYourTroopCount(-allyLoss);
                                Manager.mapLocations.get(i).setArea2Amount(-enemyLoss);
                                report += Manager.mapLocations.get(i).getRegionName() + " Report: \nA seige against the " + Manager.mapLocations.get(i).getArea2() + " took place" +
                                        "\nAlly Casualties [" + yourFP + "fp] = " + ( allyLoss) + "\nEnemy Casualties [" + enemyFP +"fp] = " + enemyLoss + "\n\n";
                                if (Manager.mapLocations.get(i).getArea2Amount() < 1){
                                    report += "You have cleared the " + Manager.mapLocations.get(i).getArea2() + " from Area 2\n\n";
                                }
                            }
                            else if (b ==2){
                                Manager.mapLocations.get(i).changeYourTroopCount(-allyLoss);
                                Manager.mapLocations.get(i).setArea3Amount(-enemyLoss);
                                report += Manager.mapLocations.get(i).getRegionName() + " Report: \nA seige against the " + Manager.mapLocations.get(i).getArea3() + " took place" +
                                        "\nAlly Casualties [" + yourFP + "fp] = " + ( allyLoss) + "\nEnemy Casualties [" + enemyFP +"fp] = " + enemyLoss + "\n\n";
                                if (Manager.mapLocations.get(i).getArea3Amount() < 1){
                                    report += "You have cleared the " + Manager.mapLocations.get(i).getArea3() + " from Area 3\n\n";
                                }
                            }
                            else if (b == 3){
                                Manager.mapLocations.get(i).changeYourTroopCount(-allyLoss);
                                Manager.mapLocations.get(i).setArea4Amount(-enemyLoss);
                                report += Manager.mapLocations.get(i).getRegionName() + " Report: \nA seige against the " + Manager.mapLocations.get(i).getArea4() + " took place" +
                                        "\nAlly Casualties [" + yourFP + "fp] = " + ( allyLoss) + "\nEnemy Casualties [" + enemyFP +"fp] = " + enemyLoss + "\n\n";
                                if (Manager.mapLocations.get(i).getArea4Amount() < 1){
                                    report += "You have cleared the " + Manager.mapLocations.get(i).getArea4() + " from Area 4\n\n";
                                }
                            }
                            else if (b == 4){
                                Manager.mapLocations.get(i).changeYourTroopCount(-allyLoss);
                                Manager.mapLocations.get(i).setArea5Amount(-enemyLoss);
                                report += Manager.mapLocations.get(i).getRegionName() + " Report: \nA seige against the " + Manager.mapLocations.get(i).getArea5() + " took place" +
                                        "\nAlly Casualties [" + yourFP + "fp] = " + ( allyLoss) + "\nEnemy Casualties [" + enemyFP +"fp] = " + enemyLoss + "\n\n";
                                if (Manager.mapLocations.get(i).getArea5Amount() < 1){
                                    report += "You have cleared the " + Manager.mapLocations.get(i).getArea5() + " from Area 5\n\n";
                                }
                            }

                            break;
                        }
                    }
                }
            }
            if (!report.equals("")) {
                prefs.putInteger("MailCount", (prefs.getInteger("MailCount") + 1));
                prefs.flush();
                prefs.putString(prefs.getInteger("MailCount") + "", report);
                prefs.putString(prefs.getInteger("MailCount") + "" + prefs.getInteger("MailCount"), "Daily Expedition Report [N]");
                prefs.flush();
                Mails.add(new Mail(prefs.getString(prefs.getInteger("MailCount") + "" + prefs.getInteger("MailCount")), prefs.getString(prefs.getInteger("MailCount") + "")));
                Manager.mailKeys.put(prefs.getString(prefs.getInteger("MailCount") + "" + prefs.getInteger("MailCount")), prefs.getInteger("MailCount"));
                System.out.println(Manager.mailKeys.keySet());
                prefs.flush();
            }
        }


        if (!(prefs.getString("events").charAt(0) == '9')){
            StringBuilder changed = new StringBuilder(prefs.getString("events"));
            int changedNum = Character.getNumericValue(prefs.getString("events").charAt(0)) - 1;

            changed.setCharAt(0, (char)(changedNum+'0'));
            prefs.putString("events", (changed.toString()));
        }
        if (!(prefs.getString("events").charAt(1) == '9')){
            StringBuilder changed = new StringBuilder(prefs.getString("events"));
            int changedNum = Character.getNumericValue(prefs.getString("events").charAt(1)) - 1;

            changed.setCharAt(1, (char)(changedNum+'0'));
            prefs.putString("events", (changed.toString()));
        }
        if (!(prefs.getString("events").charAt(2) == '9')){
            StringBuilder changed = new StringBuilder(prefs.getString("events"));
            int changedNum = Character.getNumericValue(prefs.getString("events").charAt(2)) - 1;

            changed.setCharAt(2, (char)(changedNum+'0'));
            prefs.putString("events", (changed.toString()));
        }
        System.out.println("events num check = " + prefs.getString("events"));



        //Attack roll based on RANDOM by small raid groups and attack by angry NEARBY factions (Raid risk status)
        //Bank Rob roll based on respect and nearby factions
        //Upgrade arms chance based on scientist, sent to email and updates arms tab

        //Events tab, max 4 per day


        //Apply cost reductions and bank security and respect bonus
        //Add firepower bonus
    }
}
