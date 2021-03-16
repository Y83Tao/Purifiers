package com.projectgrapes.game.Resources;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import static com.projectgrapes.game.MainMenu.factionsNum;
import static com.projectgrapes.game.MainMenu.prefs;
import static jdk.nashorn.internal.runtime.JSType.toInteger;

public class Manager {
    Random rand = new Random();
    int n = rand.nextInt(20);
    public static ArrayList<People> peoples = new ArrayList<People>();
    public static ArrayList<Mail> Mails = new ArrayList<Mail>();
    public static ArrayList<Factions> faction = new ArrayList<Factions>();
    public static ArrayList<MapLocations> mapLocations = new ArrayList<MapLocations>();
    static boolean PeopleDeployment = true, randomDeployment = true, mailDeployment = true, factionDeployment = true, regionDeployment = true;
    public static HashMap<String, Integer> mailKeys = new HashMap<>();
    public Manager(){

        /*for(int i = 0; i < People.names.length; i++){
            int num = rand.nextInt(People.names.length);

            peoples.add(new People(People.randomName(rand.nextInt(num))));
            People.names.

        }*/
        if(mailDeployment){
            if (prefs.getBoolean("SetupMails")) {
                prefs.putBoolean("SetupMails", false);
                Mail intro = new Mail("Introduction [N]", "MAINFRAME\n" +
                        "Welcome to the terminal link that operates the \n" +
                        "oddities. Your task is simple, keep your city\n" +
                        "intact and the citizens healthy. In order to do \n" +
                        "so, you will need to control any situation that \n" +
                        "comes your way. On the mainframe, you can \n" +
                        "supervise your gold, resources, troops, wall \n" +
                        "integrity, and respect. This is where you will \n" +
                        "be given situations of which to act upon. \n" +
                        "Most of your choices will have a direct impact \n" +
                        "on goods and status. If you have negative\n" +
                        "numbers on one of your goods, your terminal\n" +
                        "link will be toppled.\n" +
                        "\n" +
                        "REGIONS/FACTIONS\n" +
                        "Not only do you have to survive, but there are \n" +
                        "other factions that exist outside your city. You \n" +
                        "can either trade with them or oppose them. The \n" +
                        "Purifiers are the main target.Take them out. \n" +
                        "You can control your region activities in the \n" +
                        "operations tab. You can control over where you\n" +
                        "allocate troops. Be careful, if you have \n" +
                        "insufficient troops in your city, the enemies might\n" +
                        "take this opportunity to crush your reign.\n" +
                        "\n" +
                        "PEOPLE\n" +
                        "You have many personnel that operates certain \n" +
                        "aspects of your city. Their stats dictate their \n" +
                        "effectiveness as shown in the specialization tab.\n" +
                        "If you want to switch roles with for better stats, \n" +
                        "go for it. If you want to hire someone new in \n" +
                        "place of another, you will have to terminate the \n" +
                        "current person on that spot. We cannot have any\n" +
                        "liabilities.\n" +
                        "\n" +
                        "GOODLUCK\n" +
                        "Try not to get your terminal link cut. Also, have\n" +
                        "some fun while you are at it if possible.\n");
                prefs.putString(prefs.getInteger("MailCount")+"", intro.getMessage());
                prefs.putString(prefs.getInteger("MailCount")+""+prefs.getInteger("MailCount"), intro.getTitle());
                prefs.flush();

                /*
                prefs.putInteger("MailCount", (prefs.getInteger("MailCount") +1));
                prefs.flush();
                prefs.putString(prefs.getInteger("MailCount")+"", "Your scientist has just discovered \nnew technology for your weaponry!\n\nYou have gained one arms upgrade");
                prefs.putString(prefs.getInteger("MailCount")+""+prefs.getInteger("MailCount"), "Arms Ugrade Update [N]");
                prefs.flush();
                Mails.add(new Mail(prefs.getString(prefs.getInteger("MailCount")+""+prefs.getInteger("MailCount")), prefs.getString(prefs.getInteger("MailCount")+"")));
                Manager.mailKeys.put(prefs.getString(prefs.getInteger("MailCount")+""+prefs.getInteger("MailCount")), prefs.getInteger("MailCount"));
                System.out.println(Manager.mailKeys.keySet());
                prefs.flush();
                 */

            }
            for (int i = 0; i <= prefs.getInteger("MailCount"); i++){
                Mails.add(new Mail(prefs.getString(i+""+i), prefs.getString(i+"")));
                mailKeys.put(Mails.get(i).getTitle(), i);
            }
            mailDeployment = false;

        }
        if (prefs.getBoolean("GeneratePeople")){
            String usedNums = new String();
            int[] goodNums = new int[9];
            for (int i = 0; i < 9; i++){
                int num = rand.nextInt(19);
                if (!usedNums.contains(""+ num)){
                    goodNums[i] = num;
                    usedNums += ""+num;
                }else {
                    i -= 1;
                }
            }
            prefs.putString("Person1", ""+People.randomName(goodNums[0]));
                prefs.putString("Person1stats", rand.nextInt(4) + "5" +rand.nextInt(5)+""+
                                rand.nextInt(3)+""+rand.nextInt(2)+"01");
            prefs.putString("Person2", ""+People.randomName(goodNums[1]));
                prefs.putString("Person2stats", rand.nextInt(4) + "8" +rand.nextInt(5)+""+
                                 rand.nextInt(3)+""+rand.nextInt(2)+"02");
            prefs.putString("Person3", ""+People.randomName(goodNums[2]));
                prefs.putString("Person3stats", rand.nextInt(4) + "5" +rand.nextInt(5)+""+
                        rand.nextInt(3)+""+rand.nextInt(2)+"23");
            prefs.putString("Person4", ""+People.randomName(goodNums[3]));
                prefs.putString("Person4stats", rand.nextInt(4) +""+ rand.nextInt(5) +"8"+
                        rand.nextInt(3)+""+rand.nextInt(2)+""+rand.nextInt(3)+"4");
            prefs.putString("Person5", ""+People.randomName(goodNums[4]));
                prefs.putString("Person5stats", rand.nextInt(4) +""+ rand.nextInt(6) +""+rand.nextInt(5)+""+
                         "8"+rand.nextInt(2)+""+rand.nextInt(2)+"5");
            prefs.putString("Person6", ""+People.randomName(goodNums[5]));
                prefs.putString("Person6stats", rand.nextInt(4)+"" + rand.nextInt(5) +"4"+
                        rand.nextInt(3)+""+rand.nextInt(2)+""+rand.nextInt(2)+"6");
            prefs.putString("Person7", ""+People.randomName(goodNums[6]));
                prefs.putString("Person7stats", "5" + rand.nextInt(4) +""+rand.nextInt(4)+""+
                        rand.nextInt(3)+""+rand.nextInt(2)+""+rand.nextInt(5)+"7");
            prefs.putString("Person8", ""+People.randomName(goodNums[7]));
                prefs.putString("Person8stats", "6"+ rand.nextInt(4) +""+rand.nextInt(5)+""+
                        rand.nextInt(3)+""+rand.nextInt(4)+""+rand.nextInt(5)+"8");
            prefs.putString("Person9", ""+People.randomName(goodNums[8]));
            prefs.putString("Person9stats", rand.nextInt(4)+""+ rand.nextInt(4) +""+rand.nextInt(5)+""+
                    rand.nextInt(3)+"7"+rand.nextInt(5)+"9");


            prefs.putBoolean("GeneratePeople", false);
            prefs.flush();
        }

        if (factionDeployment){
            //add faction in -----------------------------------------------------------------------------------------
            String[] factions = new String[5]; factions[0] = "The Purifiers"; factions[1] = "The Iron Legion"; factions[2] = "The Keepers Of Ember";
            factions[3] = "The Leather Junction"; factions[4] = "The Architects";

            for (int i = 0; i < factions.length; i++) {
                String name = (prefs.get().get(factions[i] +" name").toString());
                String playerStatus = (prefs.get().get(factions[i] +" playerStatus").toString());
                String resourceRich = (prefs.get().get(factions[i] +" resourceRich").toString());
                String status = (prefs.get().get(factions[i] +" status").toString());
                String resourceNeeds = prefs.get().get(factions[i] + " resourceNeeds").toString();
                String encountered = prefs.get().get(factions[i] + " encountered").toString();
                String tradeStatus = prefs.get().get(factions[i] + " tradeStatus").toString();
                int troops = Integer.parseInt(prefs.get().get(factions[i] +" troops").toString());
                int perUnitPower = Integer.parseInt(prefs.get().get(factions[i] +" perUnitPower").toString());
                int tradeInput = Integer.parseInt(prefs.get().get(factions[i] + " tradeInput").toString());
                int tradeOutput = Integer.parseInt(prefs.get().get(factions[i] + " tradeOutput").toString());
                faction.add(new Factions(name, playerStatus, resourceRich, resourceNeeds,tradeStatus, tradeInput, tradeOutput, perUnitPower, troops, status, encountered, factions[i]));
            }

            //faction.add(purifiers);
            factionDeployment = false;
            //Tester ---------------------------------------------------------remove after]]]========------------------------------
            for (int i = 0; i< factions.length;i++) {
                System.out.println( faction.get(i).getname());
                System.out.println( faction.get(i).getStatus());
                System.out.println( faction.get(i).getEncountered());
                System.out.println( faction.get(i).getResourceRich());
                faction.get(i).addTroops(50);
                System.out.println(faction.get(i).getTroops());
            }
        }
        if (regionDeployment){
            String[] regionMapName = new String[9];
            regionMapName[0] = "Dry Refinery";
            regionMapName[1] = "The Red Forest";
            regionMapName[2] = "Ruins Of London";
            regionMapName[3] = "Blackened BadLands";
            regionMapName[4] = "Alpha CP";
            regionMapName[5] = "The Perished Garden";
            regionMapName[6] = "Omega CP";
            regionMapName[7] = "The Blue Forest";
            regionMapName[8] = "Illuminated Shores";

            for (int i = 0; i < regionMapName.length; i++){
                String RegionName = prefs.getString(regionMapName[i] + " regionName");
                String area1 = prefs.getString(regionMapName[i] + " area1");
                String area2 = prefs.getString(regionMapName[i] + " area2");
                String area3 = prefs.getString(regionMapName[i] + " area3");
                String area4 = prefs.getString(regionMapName[i] + " area4");
                String area5 = prefs.getString(regionMapName[i] + " area5");

                int area1Amount = prefs.getInteger(regionMapName[i] + " area1Amount");
                int area2Amount = prefs.getInteger(regionMapName[i] + " area2Amount");
                int area3Amount = prefs.getInteger(regionMapName[i] + " area3Amount");
                int area4Amount = prefs.getInteger(regionMapName[i] + " area4Amount");
                int area5Amount = prefs.getInteger(regionMapName[i] + " area5Amount");

                int ResourceAmount = prefs.getInteger(regionMapName[i] + " ResourceAmount");
                int AmountPer = prefs.getInteger(regionMapName[i] + " AmountPer");

                int yourTroops = prefs.getInteger(regionMapName[i] + " yourTroopCount");
                String engagement = prefs.getString(regionMapName[i] + " engagement");

                mapLocations.add(new MapLocations(RegionName, area1, area1Amount, area2, area2Amount, area3, area3Amount, area4, area4Amount, area5, area5Amount, ResourceAmount, AmountPer, yourTroops, engagement));
            }
            //System.out.println("getting stuff = " + mapLocations.get(0).getRegionName());

            regionDeployment = false;
        }




        if (PeopleDeployment){
            People p1 = new People(prefs.getString("Person1"),(prefs.getString("Person1stats")), "Person1","Person1stats");
            People p2 = new People(prefs.getString("Person2"),prefs.getString("Person2stats"),"Person2","Person2stats");
            People p3 = new People(prefs.getString("Person3"),prefs.getString("Person3stats"),"Person3","Person3stats");
            People p4 = new People(prefs.getString("Person4"),prefs.getString("Person4stats"), "Person4","Person4stats");
            People p5 = new People(prefs.getString("Person5"),prefs.getString("Person5stats"), "Person5","Person5stats");
            People p6 = new People(prefs.getString("Person6"),prefs.getString("Person6stats"), "Person6","Person6stats");
            People p7 = new People(prefs.getString("Person7"),prefs.getString("Person7stats"), "Person7","Person7stats");
            People p8 = new People(prefs.getString("Person8"),prefs.getString("Person8stats"), "Person8","Person8stats");
            People p9 = new People(prefs.getString("Person9"),prefs.getString("Person9stats"), "Person9", "Person9stats");
            peoples.add(p1);
            peoples.add(p2);
            peoples.add(p3);
            peoples.add(p4);
            peoples.add(p5);
            peoples.add(p6);
            peoples.add(p7);
            peoples.add(p8);
            peoples.add(p9);


            PeopleDeployment = false;
        }



    }
    public void fiveRandoms(){


        People random1 = new People("(S) "+People.randomName(rand.nextInt(9)), rand.nextInt(8)+""+ rand.nextInt(8) +""+rand.nextInt(7)+""+
                rand.nextInt(3)+""+rand.nextInt(5)+""+rand.nextInt(7)+"0", null, null);
        People random2 = new People("(S) "+People.randomName(rand.nextInt(9)), rand.nextInt(8)+""+ rand.nextInt(8) +""+rand.nextInt(7)+""+
                rand.nextInt(3)+""+rand.nextInt(5)+""+rand.nextInt(7)+"0", null, null);
        People random3 = new People("(S) "+People.randomName(rand.nextInt(9)), rand.nextInt(8)+""+ rand.nextInt(8) +""+rand.nextInt(7)+""+
                rand.nextInt(3)+""+rand.nextInt(5)+""+rand.nextInt(7)+"0", null, null);

        if (randomDeployment){
            peoples.add(random1);
            peoples.add(random2);
            peoples.add(random3);
        }

        if (randomDeployment = false) {
            peoples.set(10, random1);
            peoples.set(11, random2);
            peoples.set(12, random3);
            }
        }

    }





